package projectc;
public class InvalidMoveException extends Exception 
{
    public InvalidMoveException()
    {
        super();
    }
    
    public String getMessage(int lowerLimit, int upperLimit) //overriding the getMessage function
    {
        String message = "Invalid Move. You must remove between " + lowerLimit + " and " + upperLimit + "stones.";
        return message;
    }
}
