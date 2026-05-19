public class PlayMaker {
	
	private BallTeam team1;
	private BallTeam team2;
	private GameData gameData;
    private Plays plays;
    private Pitches pitches;

	public PlayMaker(BallTeam team1, BallTeam team2, GameData gameData) {
		this.team1 = team1;
		this.team2 = team2;
		this.gameData = gameData;
		this.pitches = new Pitches(this.gameData);
	}

	public void MakePlay(String event) {
		switch(event)
		{
		case "strike":
			this.pitches.Strike();
			break;
		case "ball":
			this.pitches.Ball();
			break;
		case "foul":
			Foul();
			break;
		case "foul bunt":
			this.pitches.Strike();
			break;
		case "intentional walk":
			this.pitches.Walk();
			break;
		case "single":
			Single();
			break;
		case "double":
			Double();
			break;
		case "triple":
			Triple();
			break;
		case "home run":
			HomeRun();
			break;
		case "out":
			Out();
			break;
		case "double play":
			DoublePlay();
			break;
		case "triple play":
			TriplePlay();
			break;
		default:
			System.out.println("Invalid play");
			break;
		}
		
		if(this.gameData.outs == 3)
		{
			this.gameData.InningChange();
		}
	}

	private void TriplePlay() {
		if(this.gameData.bases[0] == true && this.gameData.bases[1] == true && this.gameData.outs == 0)
		{
			this.gameData.outs = 3;
			this.gameData.ResetCount();
		}
		else
		{
			System.out.println("Not enough runners in the right places or too many outs for a triple play.");
		}
		
	}

	private void DoublePlay() {
		if(this.gameData.bases[0] == true && this.gameData.outs < 2)
		{
			this.gameData.outs += 2;
			this.gameData.ResetCount();
			this.gameData.EmptyBase(0);
		}
		else if(this.gameData.bases[0] == false)
		{
			System.out.println("Nobody is on first base");
		}
        else
        {
            System.out.println("Too many outs for a double play.");
        }
	}

	private void HomeRun() {
		this.gameData.AdvanceBases(4);
		this.gameData.ResetCount();
	}

	private void Triple() {
		this.gameData.AdvanceBases(3);
		this.gameData.ResetCount();
	}

	private void Double() {
		this.gameData.AdvanceBases(2);
		this.gameData.ResetCount();
	}

	private void Foul() {
		if(this.gameData.strikes < 2)
			this.gameData.strikes++;
	}

	private void Out() {
		this.gameData.outs++;
		this.gameData.ResetCount();
	}

	private void Single() {
		this.gameData.AdvanceBases(1);
		this.gameData.ResetCount();
	}

}
