public class PlayMaker {
	
	private BallTeam team1;
	private BallTeam team2;
	private GameData gameData;
    private Plays plays;
    private Pitches pitches;
	private BaseRunning running;

	public PlayMaker(BallTeam team1, BallTeam team2, GameData gameData) {
		this.team1 = team1;
		this.team2 = team2;
		this.gameData = gameData;
		this.pitches = new Pitches(this.gameData);
		this.plays = new Plays(this.gameData);
		this.running = new BaseRunning(this.gameData);
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
			this.pitches.Foul();
			break;
		case "foul bunt":
			this.pitches.Strike();
			break;
		case "intentional walk":
			this.pitches.Walk();
			break;
		case "passed ball":
			this.pitches.PassedBall();
			break;
		case "single":
			this.plays.Single();
			break;
		case "double":
			this.plays.Double();
			break;
		case "triple":
			this.plays.Triple();
			break;
		case "home run":
			this.plays.HomeRun();
			break;
		case "out":
			this.pitches.Out();
			break;
		case "double play":
			this.plays.DoublePlay();
			break;
		case "triple play":
			this.plays.TriplePlay();
			break;
		case "pickoff first":
			this.running.Pickoff(0);
			break;
		case "pickoff second":
			this.running.Pickoff(1);
			break;
		case "pickoff third":
			this.running.Pickoff(2);
			break;
		case "caught stealing second":
			this.running.Steal(false, 0);
			break;
		case "caught stealing third":
			this.running.Steal(false, 1);
			break;
		case "caught stealing home":
			this.running.Steal(false, 2);
			break;
		case "steal second":
			this.running.Steal(true, 0);
			break;
		case "steal third":
			this.running.Steal(true, 1);
			break;
		case "steal home":
			this.running.Steal(true, 2);
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

}
