package sample;

public class gameStats {
    private static gameStats single_instance = null;
    private int reds;
    private int whites;
    private int turn;

    private gameStats()
    {
        this.reds = 12;
        this.whites = 12;
        this.turn = 1;
    }

    public static gameStats getInstance()
    {
        if (single_instance == null)
            single_instance = new gameStats();
        return single_instance;
    }

    public void nextTurn()
    {
        turn *= -1;
    }
    public void eatRed()
    {
        reds--;
    }
    public void eatWhite()
    {
        whites--;
    }
    public int getReds()
    {
        return reds;
    }

    public int getTurn()
    {
        return turn;
    }
}
