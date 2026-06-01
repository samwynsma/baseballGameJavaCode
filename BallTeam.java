public class BallTeam {
	
	public String name;
	public int score;
	public int hits;
	public int errors;
	public int currentPlayer;
	public BallPlayer[] teamPlayers;
	
	
	public BallTeam(String teamName) {
		this.name = teamName;
		this.score = 0;
		this.hits = 0;
		this.errors = 0;
		this.currentPlayer = 0;
		teamPlayers = new BallPlayer[9];
		for(int i = 0; i < 9; i++)
		{
			teamPlayers[i] = new BallPlayer(teamName + "#" + i);
		}
	}
	
	public void AddError() {
		errors++;
	}
	
	public void AddHit() {
		hits++;
	}
	
	public void AddRuns(int val) {
		score += val;
	}

}
