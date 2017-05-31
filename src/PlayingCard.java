import playingCardExceptions.*;

class PlayingCard implements Comparable<PlayingCard>{
	private int value;
	private char suit;
	
	//int value constructors
	public PlayingCard(int value, char suit){		
		try{
			char upperSuit = Character.toUpperCase(suit);
			
			if(value > 1 && value < 15){
				this.value = value;
			}
			else{
				throw new UnacceptedValueException();
			}
			
			if(upperSuit == 'D' || upperSuit == 'S' || upperSuit == 'C' || upperSuit == 'H'){
				this.suit = suit;		
			}
			else{
				throw new UnacceptedSuitException();
			}
		}
		catch(UnacceptedSuitException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		catch(UnacceptedValueException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public PlayingCard(int value, String suit){		
		try{
			if(value > 1 && value < 15){
				this.value = value;
			}
			else{
				throw new UnacceptedValueException();
			}
			
			this.suit = StringSuitConverter(suit);	
		}
		catch(UnacceptedValueException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}		
	}	
	
	//constructor for char value input
	public PlayingCard(char value, char suit){
		try{
			char upperSuit = Character.toUpperCase(suit);
			
			this.value = CharValueConverter(value);
	
			if(upperSuit == 'D' || upperSuit == 'S' || upperSuit == 'C' || upperSuit == 'H'){
				this.suit = suit;		
			}
			else{
				throw new UnacceptedSuitException();
			}
		}
		catch(UnacceptedSuitException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}		
	}
	
	public PlayingCard(char value, String suit){
		this.value = CharValueConverter(value);
		this.suit = StringSuitConverter(suit);		
	}
	
	//constructor for string values
	public PlayingCard(String value, char suit){
		try{
			char upperSuit = Character.toUpperCase(suit);
			
			this.value = StringValueConverter(value);
	
			if(upperSuit == 'D' || upperSuit == 'S' || upperSuit == 'C' || upperSuit == 'H'){
				this.suit = suit;		
			}
			else{
				throw new UnacceptedSuitException();
			}
		}
		catch(UnacceptedSuitException e){
			System.out.println(e.getMessage());
			System.exit(1);		
		}	
	}
	
	public PlayingCard(String value, String suit){
		this.value = StringValueConverter(value);
		this.suit = StringSuitConverter(suit);		
	}
	
	//getter and setter methods
	public int GetValue(){
		return value;
	}
	
	public char GetSuit(){
		return suit;
	}
	
	public String GetCardName(){
		return(value + "" + suit);
	}
	
	@Override
	public int compareTo(PlayingCard card) {
		int compareValue = ((PlayingCard)card).GetValue();
		return this.value - compareValue;
	}
	
	//unit converter methods
	private int StringValueConverter(String stringValue){
		int intValue = 0;
		
		try{		
			switch (stringValue){
			case "j":
			case "J":
			case "jack":
			case "Jack":
				intValue = 11;
				break;	
			case "q":
			case "Q":
			case "queen":
			case "Queen":
				intValue = 12;
				break;	
			case "k":
			case "K":
			case "king":
			case "King":
				intValue = 13;
				break;	
			case "a":
			case "A":
			case "ace":
			case "Ace":
				intValue = 14;
				break;
			default:
				intValue = Integer.parseInt(stringValue);
				if(intValue == 0){
					throw new UnacceptedValueException();
				}
			}
		}
		catch(UnacceptedValueException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		return intValue;
	}
	
	private int CharValueConverter(char charValue){
		int intValue = 0;
		try{
			switch (suit){
			case 'j':
			case 'J':
				intValue = 11;
				break;
			case 'q':
			case 'Q':
				intValue = 12;
				break;
			case 'k':
			case 'K':
				intValue = 13;
				break;
			case 'a':
			case 'A':
				intValue = 14;
				break;
			default:
				throw new UnacceptedValueException();
			}
		}
		catch(UnacceptedValueException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}

		return intValue;
	}
	
	
	private char StringSuitConverter(String suit){
		char charSuit = 0;
		
		try{
			switch (suit){
			case "h":
			case "H":
			case "heart":
			case "Heart":
				charSuit = 'H';
				break;
			case "d":
			case "D":
			case "diamonds":
			case "Diamonds":
				charSuit = 'D';
				break;
			case "s":
			case "S":
			case "spades":
			case "Spades":
				charSuit = 'S';
				break;
			case "c":
			case "C":
			case "clubs":
			case "Clubs":
				charSuit = 'C';
				break;
			default:
				if(charSuit == 0){
					throw new UnacceptedSuitException();
				}		
			}
		}
		catch(UnacceptedSuitException e){
			System.out.println(e.getMessage());	
			System.exit(1);
		}
		
		return charSuit;
	}
}