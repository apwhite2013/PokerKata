import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Player{
	private String name;
	private List<PlayingCard> cards = new ArrayList<PlayingCard>();
	private PlayingCard highCard = null;
	private PlayingCard lowCard = null;
	private boolean hasOnePair = false;
	private int onePairValue;	
	private boolean hasTwoPair = false;
	private int twoPairValue;
	private boolean hasThreeOfAKind = false;
	private int threeOfAKindValue;
	private boolean hasStraight = false;
	private int straightHighCard;
	private boolean hasFlush = false;
	private char flushSuit;
	private boolean hasFullHouse = false;
	private int fullHouseValue;
	private boolean hasFourOfAKind = false;
	private int fourOfAKindValue;
	private boolean hasStraightFlush = false;
	private int straightFlushHighCard;
	
	//constructor		
	public Player(String name){
		SetName(name);
	}
	
	//hand modifiers
	public List<PlayingCard> GetCards(){
		return this.cards;
	}
	
	public void AddCard(PlayingCard newCard){	
		UpdateHandStatisticsPretest(newCard);
		cards.add(newCard);
		UpdateHandStatisticsPostTest(newCard);
			
	}
	
	//getters and setters
	public String GetName(){
		return this.name;
	}
	
	public void SetName(String name){
		this.name = name;
	}	
	
	public PlayingCard GetHighCard(){
		return this.highCard;
	}
	
	public PlayingCard GetLowCard(){
		return this.lowCard;
	}
		
	public boolean HasOnePair(){
		return hasOnePair;
	}
	
	public int GetOnePairValue(){
		return onePairValue;
	}
	
	public boolean HasTwoPair(){
		return hasTwoPair;
	}
	
	public int GetTwoPairValue(){
		return twoPairValue;
	}
	
	public boolean HasThreeOfAKind(){
		return hasThreeOfAKind;
	}
	
	public int GetThreeOfAKindValue(){
		return threeOfAKindValue;
	}
	
	public boolean HasStraight(){
		return hasStraight;
	}
	
	public int GetStraightHighCard(){
		return straightHighCard;
	}
	
	public boolean HasFlush(){
		return hasFlush;
	}
	
	public char GetFlushSuit(){
		return flushSuit;
	}
	
	public boolean HasFullHouse(){
		return hasFullHouse;
	}
	
	public int GetFullHouseValue(){
		return fullHouseValue;
	}
	
	public boolean HasFourOfAKind(){
		return hasFourOfAKind;
	}
	
	public int GetFourOfAKindValue(){
		return fourOfAKindValue;
	}
	
	public boolean HasStraightFlush(){
		return hasStraightFlush;
	}
	
	public int GetStraightFlushHighCard(){
		return straightFlushHighCard;
	} 
	
	private void UpdateHandStatisticsPretest(PlayingCard card){
		//update four of a kind
		if(hasThreeOfAKind == true && card.GetValue() == threeOfAKindValue){
			hasFourOfAKind = true;
			fourOfAKindValue = threeOfAKindValue;
		}
		
		//update three of a kind
		if(hasOnePair == true && card.GetValue() == onePairValue){
					hasThreeOfAKind = true;
					threeOfAKindValue = card.GetValue();
		}
		
		//update 2 pair
		if(hasOnePair == true && hasTwoPair == false){
			Iterator<PlayingCard> itr = cards.iterator();			
			
			while(itr.hasNext() && hasTwoPair == false){
				//if the next card value is equal to the added card value and is not equal to the onePairValue value
				if (itr.next().GetValue() == card.GetValue() 
						&& card.GetValue()!= onePairValue){
					hasTwoPair = true;
					twoPairValue = card.GetValue();
				}
			}
			
			if(twoPairValue>onePairValue){
				int tempValue = onePairValue;
				onePairValue = twoPairValue;
				twoPairValue = tempValue;
			}
		}
		//update one pair
		if(hasOnePair == false){
			Iterator<PlayingCard> itr = cards.iterator();
			while (itr.hasNext() && hasOnePair == false){
				if (itr.next().GetValue() == card.GetValue()){				
					hasOnePair = true;
					onePairValue = card.GetValue();
				}
			}
		}
	}
	//updates player hand statistics after the card has been added to the hand
	private void UpdateHandStatisticsPostTest(PlayingCard card){
		
		//sort and set high and low cards
		if(cards.size() == 5){
			//sort hands
			Collections.sort(cards);			
			highCard = cards.get(cards.size()-1);
			lowCard = cards.get(0);
		}
		
		
		//update flush
		if(cards.size() == 5){
			Iterator<PlayingCard> itr = cards.iterator();
			char suit = highCard.GetSuit();			
			
			while (itr.hasNext()){
				if(suit == itr.next().GetSuit()){
					hasFlush = true;
				}
				else{
					hasFlush = false;
					break;
				}					
			}		
			
			if(hasFlush == true){
				flushSuit = card.GetSuit();
			}
		}

		//update straight
		if(cards.size() == 5){
			//iterator to go through hands low to high checking for one value differences
			Iterator<PlayingCard> itr = cards.iterator();
			int a = lowCard.GetValue();
			int consecutiveCards = 0;
			
			for (int i=0; i<5; i++){
				if (itr.next().GetValue() == a+i){
					consecutiveCards++;					
				}
			}
			
			if(consecutiveCards == 5){
				hasStraight = true;
				straightHighCard = highCard.GetValue();
			}
		}	
		
		//update has straight flush		
		if(hasFlush == true && hasStraight == true){
			hasStraightFlush = true;
			straightFlushHighCard = straightHighCard;
		}
		
		//update full house
		if(hasTwoPair == true && hasThreeOfAKind == true && cards.size() == 5){
			hasFullHouse = true;
			fullHouseValue = threeOfAKindValue;		
		}			
	}	
}