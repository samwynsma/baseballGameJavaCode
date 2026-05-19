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
        
    }

}
