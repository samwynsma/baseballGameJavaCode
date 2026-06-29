import java.awt.*;
import java.io.*;
import javax.swing.*;

public class PlayBallGUI extends JFrame {

    private BaseballGameInfo newGame;
    private JTextArea outputArea;
    private JTextField commandField;
    private JButton submitButton;
    private JButton statusButton;
    private JLabel promptLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayBallGUI());
    }

    public PlayBallGUI() {
        super("PlayBall GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(6, 6));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game Output"));
        add(scrollPane, BorderLayout.CENTER);

        promptLabel = new JLabel("Enter a play command and press Submit.");
        commandField = new JTextField();
        submitButton = new JButton("Submit");
        statusButton = new JButton("Status");

        JPanel controlPanel = new JPanel(new BorderLayout(6, 6));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        buttonPanel.add(statusButton);
        buttonPanel.add(submitButton);
        controlPanel.add(promptLabel, BorderLayout.NORTH);
        controlPanel.add(commandField, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.SOUTH);

        installOutputRedirect();

        setSize(720, 500);
        setLocationRelativeTo(null);

        String team1 = getTeamName("away");
        String team2 = getTeamName("home");

        if (team1 == null || team2 == null) {
            appendOutput("Game cancelled.\n");
            disableControls();
        } else {
            newGame = new BaseballGameInfo(team1, team2);
            appendOutput("Welcome to PlayBall GUI.\n");
            startTurn();
        }

        submitButton.addActionListener(e -> handleCommand());
        commandField.addActionListener(e -> handleCommand());
        statusButton.addActionListener(e -> displayStatus());

        setVisible(true);
    }

    private void installOutputRedirect() {
        PrintStream guiOut = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendOutput(Character.toString((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                appendOutput(new String(b, off, len));
            }
        }, true);
        System.setOut(guiOut);
        System.setErr(guiOut);
    }

    private String getTeamName(String role) {
        String name = null;
        while (name == null || name.trim().isEmpty()) {
            name = JOptionPane.showInputDialog(this, "What is the " + role + " team's name?", "Team Name", JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                return null;
            }
            name = name.trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a team name.", "Input Required", JOptionPane.WARNING_MESSAGE);
            }
        }
        return name;
    }

    private void startTurn() {
        if (newGame == null) {
            return;
        }
        if (newGame.gameData.isGameOver) {
            displayGameOver();
            return;
        }

        BallTeam team = newGame.gameData.isTopInning ? newGame.gameData.team1 : newGame.gameData.team2;
        int playerNumber = team.currentPlayer;
        String playerName = team.teamPlayers[playerNumber].playerName;
        promptLabel.setText("At bat: " + playerName + " — enter a play or click Status.");
        appendOutput("Welcome to the plate, " + playerName + ", let's see what happens. (Type \"Status\" to get the current game state, type \"Quit\" to quit)\n");
        commandField.setText("");
        commandField.requestFocusInWindow();
    }

    private void handleCommand() {
        if (newGame == null || newGame.gameData.isGameOver) {
            return;
        }
        String event = commandField.getText().trim().toLowerCase();
        if (event.isEmpty()) {
            return;
        }
        appendOutput("> " + event + "\n");
        if (event.equals("status")) {
            displayStatus();
        } else if (event.equals("quit")) {
            appendOutput("Game has been aborted.\n");
            disableControls();
        } else {
            newGame.playMaker.MakePlay(event);
            if (newGame.gameData.isGameOver) {
                displayGameOver();
            } else {
                startTurn();
            }
        }
    }

    private void displayStatus() {
        if (newGame != null) {
            newGame.gameData.DisplayStatus();
        }
    }

    private void displayGameOver() {
        appendOutput("\nGame Over: ");
        if (newGame.gameData.team1.score > newGame.gameData.team2.score) {
            appendOutput(newGame.gameData.team1.name + " wins. This is the result: \n");
        } else if (newGame.gameData.team1.score == newGame.gameData.team2.score) {
            appendOutput("This is the result: \n");
        } else {
            appendOutput(newGame.gameData.team2.name + " wins. This is the result: \n");
        }
        newGame.gameData.DisplayStatus();
        disableControls();
    }

    private void disableControls() {
        submitButton.setEnabled(false);
        commandField.setEnabled(false);
        statusButton.setEnabled(false);
        promptLabel.setText("Game finished.");
    }

    private void appendOutput(String text) {
        SwingUtilities.invokeLater(() -> {
            outputArea.append(text);
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });
    }
}
