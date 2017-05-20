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
        try
        {
            int removeStones = Integer.parseInt(input.nextLine());
            if(!((removeStones <= upperBoundLimit) && (numberOfStones - removeStones) >= 0))
                throw new Exception();
            return removeStones;
        }
        catch(Exception ex)
        {
            int upperLimit = 0;
            if(numberOfStones < upperBoundLimit)
                upperLimit = numberOfStones;
            else
                upperLimit = upperBoundLimit;
            System.out.println("Invalid Move. You must remove between 1 and " + upperLimit + "stones.");
        }
        return 0;
    }
}
