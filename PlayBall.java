import java.util.Scanner;

public class PlayBall {
    public static void main(String[] Args)
	{
		String team1 = "";
		String team2 = "";
		System.out.println("What is the away team's name?");
		Scanner teamText = new Scanner(System.in);
		team1 = teamText.nextLine();
		System.out.println("What is the home team's name?");
		team2 = teamText.nextLine();
		BaseballGameInfo newGame = new BaseballGameInfo(team1, team2);
		while(!newGame.gameData.isGameOver)
		{
			if(newGame.gameData.isTopInning)
			{
				int playerNumber = newGame.gameData.team1.currentPlayer;
				System.out.println("Welcome to the plate, " + newGame.gameData.team1.teamPlayers[playerNumber].playerName + ", let's see what happens. (Type \"Status\" to get the current game state, type \"Quit\" to quit)");
			}
			else
			{
				int playerNumber = newGame.gameData.team2.currentPlayer;
				System.out.println("Welcome to the plate, " + newGame.gameData.team2.teamPlayers[playerNumber].playerName + ", let's see what happens. (Type \"Status\" to get the current game state, type \"Quit\" to quit)");
			}
			String event = teamText.nextLine().toLowerCase();
			if(event.equals("status"))
			{
				newGame.gameData.DisplayStatus();
			}
			else if(event.equals("quit"))
			{
				System.out.println("Game has been aborted.");
				break;
			}
			else
			{
				newGame.playMaker.MakePlay(event);
			}
		}
		if(newGame.gameData.team1.score > newGame.gameData.team2.score)
		{
			System.out.println("Game Over: " + newGame.gameData.team1.name + " wins. This is the result: ");
		}
		else if(newGame.gameData.team1.score == newGame.gameData.team2.score)
		{
			System.out.println("Game Over: This is the result: ");
		}
		else
		{
			System.out.println("Game Over: " + newGame.gameData.team2.name + " wins. This is the result: ");
		}
		newGame.gameData.DisplayStatus();
		teamText.close();
	}
}
