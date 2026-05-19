public class BaseRunning {

    private GameData gameData;

    public BaseRunning(GameData gameData) {
        this.gameData = gameData;
    }

    public void Pickoff(int base) {
        if(this.gameData.bases[base])
        {
            System.out.print("The pitcher picked off the runner at ");
            switch(base)
            {
                case 0:
                    System.out.print("first ");
                    break;
                case 1:
                    System.out.print("second ");
                    break;
                case 2:
                    System.out.print("third ");
                    break;
            }
            System.out.println("base.");
            this.gameData.bases[base] = false;
            this.gameData.outs++;
        }
        else
        {
            System.out.println("Invalid play: can't pickoff a base when there's nobody on that base.");
        }
    }

    public void Steal(boolean successful, int startBase) {
        if(!this.gameData.bases[startBase])
        {
            System.out.println("Invalid play: cannot steal or get caught stealing if there's no runner on the starting base.");
        }
        else if(startBase < 2 && this.gameData.bases[startBase+1])
        {
            System.out.println("Invalid play: you can't steal or get caught stealing a base if the next base is already occupied.");
        }
        else if(successful)
        {
            switch(startBase)
            {
                case 0:
                    System.out.println("The runner on first successfully steals second base.");
                    this.gameData.bases[0] = false;
                    this.gameData.bases[1] = true;
                    break;
                case 1:
                    System.out.println("The runner on second successfully steals third base.");
                    this.gameData.bases[1] = false;
                    this.gameData.bases[2] = true;
                    break;
                case 2:
                    System.out.println("The runner on third steals home! A dramatic and rare development, folks!");
                    this.gameData.bases[2] = false;
                    this.gameData.AddPoints(1);
                    break;
            }
        }
        else
        {
            switch(startBase)
            {
                case 0:
                    System.out.println("The runner on first was caught stealing second base.");
                    this.gameData.bases[0] = false;
                    break;
                case 1:
                    System.out.println("The runner on second was caught stealing third base.");
                    this.gameData.bases[1] = false;
                    break;
                case 2:
                    System.out.println("The runner on third was caught stealing home. It's quite rare to steal home successfully. What was he thinking?");
                    this.gameData.bases[2] = false;
                    break;
            }
            this.gameData.outs++;
        }
    }

}
