package projectc;

import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer
{
    public NimHumanPlayer()
    {
    
    }
    
    @Override
    public int removeStone(int upperBoundLimit, int numberOfStones, Scanner input) 
    {
        int removeStones = 0;
        removeStones = Integer.parseInt(input.nextLine());
        if((removeStones <= upperBoundLimit) && (numberOfStones - removeStones) >= 0)
            return removeStones;
        else
            return 0;
    }
}
