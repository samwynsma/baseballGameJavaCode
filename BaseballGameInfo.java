public class BaseballGameInfo {
	
	public BallTeam team1;
	public BallTeam team2;
	public GameData gameData;
	public PlayMaker playMaker;
	public boolean isTopInning;
	public boolean[] bases;
	public int inning;
	
	public BaseballGameInfo(String team1Name, String team2Name) {
		this.team1 = new BallTeam(team1Name);
		this.team2 = new BallTeam(team2Name);
		this.gameData = new GameData(team1, team2);
		this.playMaker = new PlayMaker(team1, team2, gameData);
		this.isTopInning = false;
		this.bases = new boolean[] {false, false, false};
		this.inning = 1;
	}
}