public class Team {
	
	public String name;
	public int score;
	public int hits;
	public int errors;
	
	
	public Team(String teamName) {
		this.name = teamName;
		this.score = 0;
		this.hits = 0;
		this.errors = 0;
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
