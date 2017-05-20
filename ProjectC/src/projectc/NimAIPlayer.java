package projectc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class NimAIPlayer extends NimPlayer implements Testable
{

    public NimAIPlayer() 
    {

    }
    
    @Override
    public int removeStone(int upperBoundLimit, int numberOfStones, Scanner input) 
    {
        List listRands = new ArrayList();
        int removeStones = 1;
        Random r = new Random();
        int M = 0;
        
        if(numberOfStones - upperBoundLimit >= 0)
        {
            removeStones = r.nextInt(upperBoundLimit);
            M = upperBoundLimit + 1;
        }
        else
        {
            removeStones = r.nextInt(numberOfStones);
            M = numberOfStones + 1;
        }
        
        boolean decider = true;
        int i=upperBoundLimit;
        while(i>=1)
        {
            int tempNumberOfStones = numberOfStones - i;
            int k = 0;
            while(decider && ((k * (M) + 1) <= tempNumberOfStones))
            {
                if(tempNumberOfStones ==  (k * (M) + 1))
                {
                    decider = false;
                    removeStones = i;
                }
                k++;
            }
            i--;
        }
        return removeStones;
    }
    
    public String advancedMove(boolean[] available, String lastMove) 
    {
        String move = "";
        return move;
    }
    
}
