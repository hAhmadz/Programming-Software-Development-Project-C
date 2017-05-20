package projectc;
import java.util.Scanner;
public class NimGame 
{
    private int stoneCount;
    private int upperBound;
    private NimPlayer player1;
    private NimPlayer player2;
    
    
    NimGame()
    {
        this.stoneCount = 0;
        this.upperBound = 0;
        this.player1 = null;
        this.player2 = null;
    }
    
    NimGame(NimPlayer player1, NimPlayer player2 , int stoneCount , int upperBound)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.stoneCount = stoneCount;
        this.upperBound = upperBound;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public NimPlayer getPlayer1() {
        return player1;
    }
    
    public NimPlayer getPlayer2() {
        return player2;
    }

    public int getUpperBound() {
        return upperBound;
    }
    
    private void setPlayer1(NimPlayer player1) {
        this.player1 = player1;
    }

    private void setPlayer2(NimPlayer player2) {
        this.player2 = player2;
    }

    private void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    private void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
    
    //prints the number of stones that is passed
    private void printStones(int numberOfStones) 
    {
        System.out.print(numberOfStones + " stones left:");
        for(int i=0;i<numberOfStones;i++)       
            System.out.print(" *");
        
        System.out.print("\n");
    }
    
     //updates both the player stats when game is finished
    private void UpdatePlayersStats()
    {
        int gamesPlayed = player1.getGamesPlayed();
        gamesPlayed++;
        player1.setGamesPlayed(gamesPlayed);
        
        gamesPlayed = player2.getGamesPlayed();
        gamesPlayed++;
        player2.setGamesPlayed(gamesPlayed);
    
    }
    
    //winner player stats + message printing
    private void declareWinner(NimPlayer player)
    {
        int gamesWon = player.getGamesWon();
        gamesWon++;
        player.setGamesWon(gamesWon);
        
        String winnerPlayer = player.getGivenName() + " " + player.getFamilyName();
        
        System.out.print("\n");
        System.out.println("Game Over");
        System.out.println(winnerPlayer + " wins!"); //player wins
        
    }
    
    //just to check the valid moves.
    private boolean validMoveCheck(int stoneCount)
    {
        if(stoneCount - this.getStoneCount() == 0)
            return false;   
        
        return true;
    }
    
    //player turn called for each player / removal of stones.
    private int getPlayerTurn(NimPlayer player,int upperBoundLimit,int numberOfStones,Scanner inputScan) 
    {
        System.out.print("\n");
        printStones(this.getStoneCount());
        System.out.println(player.getGivenName() + "'s turn - remove how many?");
        int removeStones = player.removeStone(upperBoundLimit, numberOfStones, inputScan);
        
        
        // Conflict code due to new try catch handling. will handle it further down the layer
        if (removeStones == 0)
            return -1;
        //
        numberOfStones = numberOfStones - removeStones;
        return numberOfStones;
    }
    
    //main game loop that handles the upperbound limitations as well and returns true for each successive move.
    private Boolean gameLoop(NimPlayer player,Scanner inputScan)
    {
        if(this.getStoneCount() < 1)
        {
            declareWinner(player);
            return false;
        }
        else
        {
            int stonesToRemove = getPlayerTurn(player,this.getUpperBound(),this.getStoneCount(),inputScan);
            
            //Repetition of this code in further functions. /Preferrably to be used in lower functions
            if(stonesToRemove == -1)
            {
                System.out.print("\n");
                int limit = 0;
                if(this.getStoneCount() < this.getUpperBound())
                    limit = this.getStoneCount();
                else
                    limit = this.getUpperBound();
                
                System.out.println("Invalid move. You must remove between 1 and " + limit + " stones.");
                return true;
            }
            
            this.setStoneCount(stonesToRemove);
            return true;
        }
    }
       
    public void gameStart(Scanner inputScan) 
    {
        System.out.print("\n");
        System.out.println("Initial stone count: " + this.getStoneCount());
        System.out.println("Maximum stone removal: " + this.getUpperBound());
        System.out.println("Player 1: " + this.player1.getGivenName() + " " + this.player1.getFamilyName());
        System.out.println("Player 2: " + this.player2.getGivenName() + " " + this.player2.getFamilyName());
        
        NimPlayer playerTurn;
        int tempStoneCount;
        Boolean gameFlowFlag = true;
        Boolean validMoveA = true;
        Boolean validMoveB = true;
        
        while(gameFlowFlag)
        {
            if(validMoveB)
            {
                tempStoneCount = this.getStoneCount();
                playerTurn = this.player1;
                gameFlowFlag = gameLoop(playerTurn,inputScan);
                validMoveA = validMoveCheck(tempStoneCount);
            }
            if(gameFlowFlag && validMoveA)
            {
                tempStoneCount = this.getStoneCount();
                playerTurn = this.player2;
                gameFlowFlag = gameLoop(playerTurn,inputScan);
                validMoveB = validMoveCheck(tempStoneCount);
            }
        }
        UpdatePlayersStats();
    }
}
