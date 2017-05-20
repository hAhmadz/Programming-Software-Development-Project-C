package projectc;
public class InvalidCommandException extends Exception 
{
    public InvalidCommandException()
    {
        super();
    }
    
    public String getMessage(String InvalidCommand) //overriding the getMessage function
    {
        String message = "\'" + InvalidCommand + "\'" + " is not a valid command.";
        return message;
    }
}
