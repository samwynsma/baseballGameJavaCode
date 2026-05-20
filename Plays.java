public class Plays {
    // This class will handle any type of hit, except for a single out.

    private GameData gameData;

    public Plays(GameData gameData)
    {
        this.gameData = gameData;
    }

    public void Single() {
        System.out.println("That's a nice single.");
        this.gameData.AdvanceBases(1);
		this.gameData.ResetCount();
    }

    public void Double() {
        System.out.println("Excellent double!");
        this.gameData.AdvanceBases(2);
		this.gameData.ResetCount();
    }

    public void Triple() {
        System.out.println("A rare triple!");
        this.gameData.AdvanceBases(3);
		this.gameData.ResetCount();
    }

    public void HomeRun() {
        System.out.println("Going, going, gone!");
        if(this.gameData.bases[0] && this.gameData.bases[1] && this.gameData.bases[2])
        {
            System.out.println("Get out the rye bread and the mustard, grandma! It's grand salami time!");
        }
        else
        {
            System.out.println("That's a home run!");
        }
        this.gameData.AdvanceBases(4);
		this.gameData.ResetCount();
    }

    public void DoublePlay() {
        if(this.gameData.bases[0] == true && this.gameData.outs < 2)
		{
            System.out.println("The defense turned that grounder into a double play.");
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

    public void TriplePlay() {
        if(this.gameData.bases[0] == true && this.gameData.bases[1] == true && this.gameData.outs == 0)
		{
            System.out.println("Poor baserunning from the offense. We've got ourselves a triple play on our hands.");
			this.gameData.outs = 3;
			this.gameData.ResetCount();
		}
        else if(this.gameData.outs > 0)
        {
            System.out.println("Too many outs for a triple play.");
        }
		else
		{
			System.out.println("Need more people on base for a triple play.");
		}
    }

    public void MinorError() {
        System.out.println("Whoops, team threw an error.");
        this.gameData.AdvanceBases(0);
    }
}
