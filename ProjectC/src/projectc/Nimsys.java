package projectc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Nimsys 
{
    private final int ARRAY_SIZE = 100;  
    private NimPlayer[] playerArr; 
    private int arrayLengthwithValues;
    private final String REMOVEPLAYER = "removeplayer";
    private final String RANKINGS = "rankings";
    private final String DESC = "desc";
    private final String ASC = "asc";
    private final String EDITPLAYER = "editplayer";
    private final String ADDAIPLAYER = "addaiplayer";
    private final String STARTGAME = "startgame";
    private final String ADDPLAYER = "addplayer";
    private final String EXIT = "exit";
    private final String DISPLAYPLAYER = "displayplayer";
    private final String RESETSTATS = "resetstats";
    private final String YES = "y";
    private final String NO = "n";
    
    public Nimsys()
    {
        playerArr = new NimPlayer[ARRAY_SIZE];
        arrayLengthwithValues = 0;
    }

    public void setPlayerArr(NimPlayer[] playerArr) 
    {
        this.playerArr = playerArr;
    }

    public void setArrayLengthwithValues(int arrayLengthwithValues) 
    {
        this.arrayLengthwithValues = arrayLengthwithValues;
    }
    
    
    
    
    public Nimsys(NimPlayer[] playersArrayData , int arrayLengthData)
    {
        this.playerArr = playersArrayData;
        this.arrayLengthwithValues = arrayLengthData;
    }
    
    private void AlphabeticalSorter(NimPlayer[] playerArr)
    {
        //selection sort
        for(int i=0;i<arrayLengthwithValues;i++)
        {
            int min = i;
            for(int j=i+1; j<arrayLengthwithValues;j++)
                if(playerArr[j].getUserName().compareToIgnoreCase(playerArr[min].getUserName()) <= 0)
                    min = j;
            if(min != i)
            {
                NimPlayer tempElement = playerArr[i];
                playerArr[i] = playerArr[min];
                playerArr[min] = tempElement;
            }
        }
    }
    
    private String[] rankSorter(NimPlayer[] playerArr, String order)
    {
        double valueMin = 0;
        double valueIterated = 0;
        double gamesWon;
        double GamesPlayed;
        
        if(order.equals(ASC)) // asc
        {
            //selection sort
            for(int i=0;i<arrayLengthwithValues;i++)
            {
                int min = i;
                for(int j=i+1; j<arrayLengthwithValues;j++)
                {
                    gamesWon = playerArr[j].getGamesWon();
                    GamesPlayed = playerArr[j].getGamesPlayed();
                    if(GamesPlayed == 0.0)
                        valueIterated = 0.0;
                    else
                        valueIterated = (100*(gamesWon / GamesPlayed));
                    
                    gamesWon = playerArr[min].getGamesWon();
                    GamesPlayed = playerArr[min].getGamesPlayed();
                    
                    if(GamesPlayed == 0.0)
                        valueMin = 0.0;
                    else
                        valueMin = (100*(gamesWon/ GamesPlayed));
                    
                    if(valueIterated < valueMin )
                        min = j;
                    else if(valueIterated == valueMin)
                        if(playerArr[j].getUserName().compareToIgnoreCase(playerArr[min].getUserName()) <= 0)
                            min = j;
                }
                    
                if(min != i)
                {
                    NimPlayer tempElement = playerArr[i];
                    playerArr[i] = playerArr[min];
                    playerArr[min] = tempElement;
                }
            }
        }
        else if (order.equals(DESC)) // desc
        {
            for(int i=0;i<arrayLengthwithValues;i++)
            {
                int max = i;
                for(int j=i+1; j<arrayLengthwithValues;j++)
                {
                    gamesWon = playerArr[j].getGamesWon();
                    GamesPlayed = playerArr[j].getGamesPlayed();
                    
                    if(GamesPlayed == 0.0)
                        valueIterated = 0.0;
                    else
                        valueIterated = (100*(gamesWon/ GamesPlayed));
                    
                    gamesWon = playerArr[max].getGamesWon();
                    GamesPlayed = playerArr[max].getGamesPlayed();
                    
                    if(GamesPlayed == 0.0)
                        valueMin = 0.0;
                    else
                        valueMin = (100*(gamesWon / GamesPlayed));
                    
                    if(valueIterated > valueMin)
                        max = j;
                    else if(valueIterated == valueMin)
                        if(playerArr[j].getUserName().compareToIgnoreCase(playerArr[max].getUserName()) <= 0)
                            max = j;
                }
                    
                if(max != i)
                {
                    NimPlayer tempElement = playerArr[i];
                    playerArr[i] = playerArr[max];
                    playerArr[max] = tempElement;
                }
            }
        }
        
        String[] playerStatsArr = new String[arrayLengthwithValues];
        for(int i = 0; i<arrayLengthwithValues ; i++)
        {
            gamesWon = playerArr[i].getGamesWon();
            GamesPlayed = playerArr[i].getGamesPlayed();
            String percentage = Double.toString(Math.round(100*(gamesWon / GamesPlayed))).replace(".0", "%");
            percentage = String.format("%-5s" , percentage);
            String gamesPlayedFormat = String.format("%-9s", String.format("%02d", playerArr[i].getGamesPlayed()) + " games");
            playerStatsArr[i] = percentage + "| "+ gamesPlayedFormat + "| " + playerArr[i].getGivenName() + " " + playerArr[i].getFamilyName();
        }
        return playerStatsArr;
    }
    
    private int ArraySearch(String values)
    {
        for(int i = 0 ; i < arrayLengthwithValues; i++)
            if(values.equals(playerArr[i].getUserName()))
                return i; 
        return -1;
    }
    
    private void addPlayer(String command, String[] playerDetails,int process,int iterator)
    {   
        if(process == 0)
        {
            int searchResults = ArraySearch(playerDetails[0]);
            if(arrayLengthwithValues == ARRAY_SIZE)
                System.out.println("No more Players can be added");
            else if(searchResults == -1)  
            {
                int index = arrayLengthwithValues++;
                if(command.equals(ADDPLAYER))
                {
                    playerArr[index] = new NimHumanPlayer();
                    playerArr[index].setPlayerType("HUMAN");
                }
                else
                {
                    playerArr[index] = new NimAIPlayer();    
                    playerArr[index].setPlayerType("AI");
                }

                playerArr[index].setUserName(playerDetails[0]);
                playerArr[index].setFamilyName(playerDetails[1]);
                playerArr[index].setGivenName(playerDetails[2]);
                playerArr[index].setGamesPlayed(0);
                playerArr[index].setGamesWon(0);
            }
            else
                System.out.println("The player already exists.");
        }
        else
        {
            // for player additions from file
            if(command.equals("HUMAN"))
                playerArr[iterator] = new NimHumanPlayer();
            else
                playerArr[iterator] = new NimAIPlayer();
            
            playerArr[iterator].setUserName(playerDetails[0]);
            playerArr[iterator].setFamilyName(playerDetails[1]);
            playerArr[iterator].setGivenName(playerDetails[2]);
            playerArr[iterator].setGamesPlayed(Integer.parseInt(playerDetails[3]));
            playerArr[iterator].setGamesWon(Integer.parseInt(playerDetails[4]));
            playerArr[iterator].setPlayerType(playerDetails[5]);
        }
    }
    
    private void removeAllPlayers()
    {
        for(int i=0;i<arrayLengthwithValues;i++)
            playerArr[i] = null;
        
        arrayLengthwithValues = 0;
    }
    
    private void removePlayer(String values)
    {
        int searchResult = ArraySearch(values);
        if(searchResult == -1)  
            System.out.println("The player does not exist.");
        else
        {
            for(int i = searchResult ; i < arrayLengthwithValues-1; i++)
                playerArr[i] = playerArr[i+1]; 

            arrayLengthwithValues--;
            playerArr[arrayLengthwithValues] = null;
        }
    }
    
    private void editPlayer(String[] playerDetails)
    {
        int searchResult = ArraySearch(playerDetails[0]);
        if(searchResult == -1)  
            System.out.println("The player does not exist.");
        else
        {
            playerArr[searchResult].setFamilyName(playerDetails[1]);
            playerArr[searchResult].setGivenName(playerDetails[2]);
        }
    }
    
    private void resetStats(String playerDetails)
    {
        int searchResult = ArraySearch(playerDetails);
        if(searchResult == -1)  
            System.out.println("The player does not exist.");
        else
        {
            playerArr[searchResult].setGamesPlayed(0);
            playerArr[searchResult].setGamesWon(0);
        }
    }
    
    private void resetAllStats()
    {
        for(int i=0;i<arrayLengthwithValues;i++)
        {
            playerArr[i].setGamesPlayed(0);
            playerArr[i].setGamesWon(0);;
        }
    }
    
    private void displayAllPlayers()
    {
        NimPlayer[] tempArray = playerArr;
        AlphabeticalSorter(tempArray);

        for(int i=0;i<arrayLengthwithValues;i++)
        {
            String userName = tempArray[i].getUserName();
            String givenName = tempArray[i].getGivenName();
            String familyName = tempArray[i].getFamilyName();
            int gamesPlayed = tempArray[i].getGamesPlayed();
            int gamesWon = tempArray[i].getGamesWon();
            System.out.println(userName + ',' + givenName + ',' + familyName + ',' + gamesPlayed + " games" + ',' + gamesWon + " wins");
        }
    }
    
    private void displayPlayer(String playerDetails)
    {
        int searchResult = ArraySearch(playerDetails);
        if(searchResult == -1)  
            System.out.println("The player does not exist.");
        else
        {
            String userName = playerArr[searchResult].getUserName();
            String givenName = playerArr[searchResult].getGivenName();
            String familyName = playerArr[searchResult].getFamilyName();
            int gamesPlayed = playerArr[searchResult].getGamesPlayed();
            int gamesWon = playerArr[searchResult].getGamesWon();
            System.out.println(userName + ',' + givenName + ',' + familyName + ',' + gamesPlayed + " games" + ',' + gamesWon + " wins");
        }
    }
    
    private void displayPlayersArr(String[] arr)
    {
        for(int i = 0 ; i < arr.length ; i++) 
            System.out.println(arr[i]);
    }
    
    private void rankings(String sortOrder)
    {
        try
        {
            String[] statsArr = new String[arrayLengthwithValues];
            NimPlayer[] tempPlayer = playerArr;
            
            if(sortOrder.equals(ASC) || sortOrder.equals(DESC))
            {
                statsArr = rankSorter(tempPlayer,sortOrder);
                displayPlayersArr(statsArr);
            }
            else
                System.out.println("Invalid sort order");
        }
        catch(Exception e)
        {
            System.out.println("No players exist.");
        }
    }
    
    private String multipleParameterCheck(String[] commandString)
    {
        try
        {
            return commandString[1].split(",")[0]; //values contains first username among multiple parameters
        }
        catch(Exception e)
        {
            return commandString[1]; //value passed if only one parameter is present
        }
    }
    
    private void commandCheck(String commandInput , Scanner inputScan) //main function to check all the commands
    {
        String[] playerDetailsArr;
        String playerDetailsStr = "";
        String[] initialString = commandInput.split(" ");
        String command = initialString[0];
        
        try
        {
            if(command.toLowerCase().equals(ADDPLAYER) || command.toLowerCase().equals(ADDAIPLAYER)) // AddPlayer Check
            {
                try
                {
                    playerDetailsArr = initialString[1].split(","); //values contain username,familyname,givenname
                    if(playerDetailsArr.length<3)
                        throw new InvalidArgumentsException();

                    addPlayer(command,playerDetailsArr,0,0);
                }
                catch(InvalidArgumentsException f)
                {
                    System.out.println(f.getMessage());
                }

                System.out.print("\n");
            }
            else if(command.toLowerCase().equals(REMOVEPLAYER)) //RemovePlayer Check
            {
                if(initialString.length == 1) //for cases with no parameters
                {
                    System.out.println("Are you sure you want to remove all players? (y/n)");
                    if(inputScan.nextLine().toLowerCase().equals((YES)))
                        removeAllPlayers();                
                }
                else if(initialString.length > 1)
                {
                    playerDetailsStr = multipleParameterCheck(initialString);
                    removePlayer(playerDetailsStr); 
                }
                System.out.print("\n");
            }
            else if(command.toLowerCase().equals(EDITPLAYER)) //editplayer Check
            {
                try
                {
                    playerDetailsArr = initialString[1].split(","); // values contain username,family name,given name
                    if(playerDetailsArr.length < 3)
                        throw new InvalidArgumentsException();
                    editPlayer(playerDetailsArr);
                }
                catch(InvalidArgumentsException iF)
                {
                    System.out.println(iF.getMessage());
                }

                System.out.print("\n");
            }
            else if(command.toLowerCase().equals(RESETSTATS)) //resetstats Check
            {
                if(initialString.length == 1) //for cases with no parameters
                {
                    System.out.println("Are you sure you want to reset all player statistics? (y/n)");
                    if(inputScan.nextLine().toLowerCase().equals(YES))
                        resetAllStats();
                }
                else if(initialString.length > 1)
                {
                    playerDetailsStr = multipleParameterCheck(initialString);
                    resetStats(playerDetailsStr); 
                }
                System.out.print("\n");
            }
            else if(command.toLowerCase().equals(DISPLAYPLAYER)) //displayplayer Check
            {
                if(initialString.length == 1) //for cases with no parameters
                    displayAllPlayers();
                else if(initialString.length > 1)
                {
                    playerDetailsStr = multipleParameterCheck(initialString);
                    displayPlayer(playerDetailsStr); 
                }
                System.out.print("\n");
            }
            else if(command.toLowerCase().equals(RANKINGS)) //rankings Check
            {   
                if(initialString.length == 1) //for cases with no parameters
                    rankings(DESC);
                else if(initialString.length > 1)
                {
                    playerDetailsStr = multipleParameterCheck(initialString);
                    rankings(playerDetailsStr); 
                }
                System.out.print("\n");
            }
            else if(command.toLowerCase().equals(EXIT)) //exit game Check
            {
                System.out.print("\n");
                exitGame();
            }
            else if(command.toLowerCase().equals(STARTGAME)) //start game Check
            {
                try
                {
                    playerDetailsArr = initialString[1].split(",");

                    if(playerDetailsArr.length < 4)
                        throw new InvalidArgumentsException();

                    int stoneCount = Integer.parseInt(playerDetailsArr[0]);
                    int upperBound = Integer.parseInt(playerDetailsArr[1]);
                    String username1 = playerDetailsArr[2];
                    String username2 = playerDetailsArr[3];

                    int Player1exists = ArraySearch(username1);    
                    int Player2exists = ArraySearch(username2);
                    if(Player1exists == -1 || Player2exists == -1)
                        System.out.println("One of the players does not exist.");
                    else
                    {
                        NimPlayer player1 = playerArr[ArraySearch(username1)];
                        NimPlayer player2 = playerArr[ArraySearch(username2)];
                        NimGame startgame = new NimGame(player1, player2 , stoneCount , upperBound);
                        startgame.gameStart(inputScan);
                    }    
                }
                catch(InvalidArgumentsException f)
                {
                    System.out.println(f.getMessage());
                }
                System.out.print("\n");
            }
            else
                throw new InvalidCommandException();
        }
        catch(InvalidCommandException e)
        {
            System.out.println(e.getMessage(command));
            System.out.print("\n");
        }
        catch(ArrayIndexOutOfBoundsException g)
        {
            System.out.println("Incorrect number of arguments supplied to command");
            System.out.print("\n");
        }
    }
    
    private void UpdateFileData(String fileName) // updates the player.dat file upon exit
    {
        try {
            
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(Integer.toString(arrayLengthwithValues));
            bufferedWriter.newLine();
            
            for(int i = 0 ; i < arrayLengthwithValues ; i++) 
            {
                String userName = playerArr[i].getUserName();
                String familyName = playerArr[i].getFamilyName();
                String givenName = playerArr[i].getGivenName();
                int gamesPlayed = playerArr[i].getGamesPlayed();
                int gamesWon = playerArr[i].getGamesWon();
                String playerType = playerArr[i].getPlayerType();
                bufferedWriter.write(userName + "," + familyName + "," + givenName + "," + gamesPlayed + "," + gamesWon + "," + playerType);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch(IOException ex) 
        {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }
    
    private List<String> GetFileData(String fileName) // gets the data from the player.dat file upon exit
    {
        String line = null;
        List<String> dataList = new ArrayList<String>();
        try {
            
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) 
                dataList.add(line);
            
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) 
        {
            //Nothing in this, default case is new file creation
        }
        catch(IOException ex) 
        {
            System.out.println( "Error reading file '" + fileName + "'");                  
        }
        return dataList;
    }
    
    private void exitGame()
    {
        String fileName = "players.txt";
        UpdateFileData(fileName);
        System.exit(0);
    }

    private void systemInitialiser()
    {
        String fileName = "players.txt";
        List<String> OutputDataList = GetFileData(fileName);
        if(OutputDataList.size()>1)
        {
            setArrayLengthwithValues(Integer.parseInt(OutputDataList.get(0)));
            for(int i=1;i<OutputDataList.size();i++)
            {
                String[] dataOut = OutputDataList.get(i).split(",");
                addPlayer(dataOut[5],dataOut,1,i-1);
            }
        }
    }
    
    public static void main(String[] args) 
    {
        Scanner inputScan = new Scanner(System.in);
        System.out.println("Welcome to Nim\n");
        Nimsys Nim = new Nimsys();
        Nim.systemInitialiser(); //initialises the system from file or new
        while(true)
        {
            System.out.print('$');
            String commandInput = inputScan.nextLine();
            Nim.commandCheck(commandInput , inputScan);
        }
    }
}
        