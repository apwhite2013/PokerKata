package playingCardExceptions;

@SuppressWarnings("serial")
public class UnacceptedValueException extends Exception
{
      // Parameterless Constructor
      public UnacceptedValueException() {}

      // Constructor that accepts a message
      public UnacceptedValueException(String message)
      {
         super(message);
      }
      
      public String getMessage(){
    	  return "An incompatable value has been input. Please make sure all card values are typed correctly and within a valid range of 2 through 14, the first letter of a face card, or the name of a face card.";
      }
 }