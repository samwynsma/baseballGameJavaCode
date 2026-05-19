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
}
