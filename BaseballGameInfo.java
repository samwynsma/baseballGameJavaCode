public class BaseballGameInfo {
	
	public Team team1;
	public Team team2;
	public GameData gameData;
	public PlayMaker playMaker;
	public boolean isTopInning;
	public boolean[] bases;
	public int inning;
	
	public BaseballGameInfo(String team1Name, String team2Name) {
		this.team1 = new Team(team1Name);
		this.team2 = new Team(team2Name);
		this.gameData = new GameData(team1, team2);
		this.playMaker = new PlayMaker(team1, team2, gameData);
		this.isTopInning = false;
		this.bases = new boolean[] {false, false, false};
		this.inning = 1;
	}
}