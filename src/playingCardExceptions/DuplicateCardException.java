package playingCardExceptions;

@SuppressWarnings("serial")
public class DuplicateCardException extends Exception {
	// Parameterless Constructor
    public DuplicateCardException() {}

    // Constructor that accepts a message
    public DuplicateCardException(String message)
    {
       super(message);
    }
    
    public String getMessage(){
  	  return "The card you are trying to add has already been added. There can only be one of each card per round.";
    }
}
