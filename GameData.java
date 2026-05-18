public class GameData {

	public boolean isGameOver;
	public Team team1;
	public Team team2;
	public boolean[] bases;
	public int inning;
	public boolean isTopInning;
	public int strikes;
	public int balls;
	public int outs;

	public GameData(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.inning = 1;
		this.bases = new boolean[] {false, false, false};
		this.strikes = 0;
		this.balls = 0;
		this.outs = 0;
		this.isTopInning = true;
	}

	public void DisplayStatus() {
		System.out.println("Here is the status of the game:");
		System.out.println(team1.name + ": " + Integer.toString(team1.score) + " " + Integer.toString(team1.hits) + " " + Integer.toString(team1.errors));
		System.out.println(team2.name + ": " + Integer.toString(team2.score) + " " + Integer.toString(team2.hits) + " " + Integer.toString(team2.errors));
		System.out.print(Integer.toString(this.balls) + "-" + Integer.toString(this.strikes) + " " + Integer.toString(this.outs) + " out ");
		if(this.isTopInning)
		{
			System.out.print("^");
		}
		else
		{
			System.out.print("v");
		}
		
		System.out.print(Integer.toString(this.inning));
		if(this.inning == 1 || (this.inning > 20 && this.inning % 10 == 1))
		{
			System.out.print("st");
		}
		else if(this.inning == 2 || (this.inning > 20 && this.inning % 10 == 2))
		{
			System.out.print("nd");
		}
		else
		{
			System.out.print("th");
		}
		System.out.println(" inning");
		
		for(int i = 0; i < 3; i++)
		{
			if(this.bases[i])
			{
				System.out.print("1");
			}
			else
			{
				System.out.print("0");
			}
		}
		System.out.print("\n");
	}

	public void AddPoints(int i) {
		if(this.isTopInning)
		{
			team1.score += i;
		}
		else
		{
			team2.score += i;
			if(this.inning >= 9 && team1.score < team2.score)
			{
				this.isGameOver = true;
			}
		}
	}

	public void InningChange() {
		if(this.isTopInning)
		{
			this.isTopInning = false;
		}
		else if(this.inning >= 9 && team1.score > team2.score)
		{
			this.isGameOver = true;
		}
		else
		{
			this.inning++;
			this.isTopInning = true;
		}
		EmptyBases();
		ResetCount();
		this.outs = 0;
	}

	public void EmptyBases() {
		this.bases = new boolean[] {false, false, false};
	}
	
	public void EmptyBase(int num)
	{
		this.bases[num] = false;
	}

	public void ResetCount() {
		this.strikes = 0;
		this.balls = 0;
	}
	
	public void AdvanceBases(int baseCount) {
		int score = 0;
		if(isTopInning && baseCount >= 1)
		{
			this.team1.hits++;
		}
		else if(isTopInning && baseCount < 1)
		{
			this.team2.errors++;
		}
		else if(!isTopInning && baseCount >= 1)
		{
			this.team2.hits++;
		}
		else
		{
			this.team1.errors++;
		}
		switch(baseCount)
		{
		case 1:
			if(bases[2])
				score++;
			EmptyBase(2);
			if(bases[1])
				bases[2] = true;
			EmptyBase(1);
			if(bases[0])
				bases[1] = true;
			bases[0] = true;
			break;
		case 2:
			if(bases[2])
				score++;
			EmptyBase(2);
			if(bases[1])
				score++;
			if(bases[0])
				bases[2] = true;
			EmptyBase(0);
			bases[1] = true;
			break;
		case 3:
			if(bases[2])
				score++;
			if(bases[1])
				score++;
			if(bases[0])
				score++;
			EmptyBase(0);
			EmptyBase(1);
			bases[2] = true;
			break;
		case 4:
			score++;
			if(bases[2])
				score++;
			if(bases[1])
				score++;
			if(bases[0])
				score++;
			EmptyBases();
			break;
		default:
			if(bases[2])
				score++;
			EmptyBase(2);
			if(bases[1])
				bases[2] = true;
			EmptyBase(1);
			if(bases[0])
				bases[1] = true;
			EmptyBase(0);
			break;
		}
		this.AddPoints(score);
	}

}
