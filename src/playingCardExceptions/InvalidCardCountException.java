package playingCardExceptions;

@SuppressWarnings("serial")
public class InvalidCardCountException extends Exception
{
      // Parameterless Constructor
      public InvalidCardCountException() {}

      // Constructor that accepts a message
      public InvalidCardCountException(String message)
      {
         super(message);
      }
      
      public String getMessage(){
    	  return "A wrong number of cards has been input. The length of each hand must be five cards long.";
      }
 }