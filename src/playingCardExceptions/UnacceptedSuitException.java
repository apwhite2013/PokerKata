package playingCardExceptions;

@SuppressWarnings("serial")
public class UnacceptedSuitException extends Exception {
	// Parameterless Constructor
    public UnacceptedSuitException() {}

    // Constructor that accepts a message
    public UnacceptedSuitException(String message)
    {
       super(message);
    }
    
    public String getMessage(){
  	  return "An incompatable suit has been input. Please make sure all suits are typed correctly and in the proper format of the suit name or the first letter of the suit.";
    }
}
