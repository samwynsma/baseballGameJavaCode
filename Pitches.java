public class Pitches {
    // This class will handle fouls, balls, strikes, and outs.

    private GameData gameData;

    public Pitches(GameData gameData)
    {
        this.gameData = gameData;
    }

    public void Strike() {
        this.gameData.strikes++;
        System.out.println("Strike " + this.gameData.strikes);
		if(gameData.strikes == 3)
		{
            System.out.println("Strikeout!");
			Out();
		}
    }

    public void Ball() {
        this.gameData.balls++;
        System.out.println("Ball " + this.gameData.balls);
		if(gameData.balls == 4)
		{
			Walk();
		}
    }

    public void Out() {
        this.gameData.outs++;
        if(this.gameData.outs == 1)
        {
            System.out.println("That's 1 out!");
        }
        else 
        {
            System.out.println("That's " + this.gameData.outs + " outs!");
        }
		this.gameData.ResetCount();
    }

    public void Walk() {
        System.out.println("That's a walk.");
        if(!this.gameData.bases[0])
		{
			this.gameData.bases[0] = true;
		}
		else if(!this.gameData.bases[1])
		{
			this.gameData.bases[1] = true;
		}
		else if(!this.gameData.bases[2])
		{
			this.gameData.bases[2] = true;
		}
		else
		{
			this.gameData.AddPoints(1);
		}
		this.gameData.ResetCount();
    }

    
}
