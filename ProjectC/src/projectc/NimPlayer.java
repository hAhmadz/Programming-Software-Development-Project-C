package projectc;
import java.util.Scanner;
public abstract class NimPlayer 
{
    private String userName;
    private String givenName;
    private String familyName;
    private int gamesPlayed;
    private int gamesWon;
    private String playerType;
    
    // *** constructors ***
    NimPlayer()
    {
        userName = "";
        givenName = "";
        familyName = "";
        gamesPlayed = 0;
        gamesWon = 0;
        playerType = "";
    }
    
    NimPlayer(String username, String givenname, String familyname, int gamesplayed, int gameswon, String playertype)
    {
        this.userName = username;
        this.givenName = givenname;
        this.familyName = familyname;
        this.gamesPlayed = gamesplayed;
        this.gamesWon = gameswon;
        this.playerType = playertype;
    }

    public String getUserName() {
        return userName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }
    
    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
    
    public abstract int removeStone(int upperBoundLimit, int numberOfStones, Scanner input);
//    {
//        int removeStones = 0;
//        removeStones = Integer.parseInt(input.nextLine());
//        if((removeStones <= upperBoundLimit) && (numberOfStones - removeStones) >= 0)
//            return removeStones;
//        else
//            return 0;
//    }
}
