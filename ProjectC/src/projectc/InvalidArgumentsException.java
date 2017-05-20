package projectc;
public class InvalidArgumentsException extends Exception 
{
    public InvalidArgumentsException()
    {
        super();
    }
    
    public String getMessage() //overriding the getMessage function
    {
        String message = "Incorrect number of arguments supplied to command";
        return message;
    }
}
