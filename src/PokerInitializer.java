import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import playingCardExceptions.*;

import playingCardExceptions.DuplicateCardException;

public class PokerInitializer {	
	static Player[] GameInitialization(Player[] players){
		Player[] mainPlayers = NewPlayers(players);		
		//HandPopulationHardcode();
		UserHandPopulation(mainPlayers);
		
		return mainPlayers;
	}	

	static Player[] NewPlayers(Player[] players){
		players = new Player[2];
		
		players[0] = new Player("Black");
		players[1] = new Player("White");
		
		return players;
	}
	
	static void UserHandPopulation(Player []mainPlayers){
		try{
			ArrayList<PlayingCard> usedCards = null;
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String delimiter = " ";
			
			System.out.println("Hello, welcome to Poker Kata! Please enter the cards of the two players. " 
					+ "\n\nThe input format is the numeric value or the first letter of the card value " 
					+ "\nfollowed by the first letter of the suit name and \na space between pairs (EXAMPLE: \"ValueSuit ValueSuit ValueSuit...\")"
					+ "\n");
					
			for(Player player: mainPlayers){		
				String handString;
				String [] hand;						
				
				System.out.print("Enter five value suit pairs for player " + player.GetName() + "'s cards: ");
				
				handString = sc.nextLine();
				System.out.println();
				
				hand = handString.split(delimiter);
				
				if(hand.length != 5){
					throw new InvalidCardCountException();
				}
				
				//getting the input set up
				for(String string: hand){
					String cardValue;
					String cardSuit;
					PlayingCard tempCard;
								
					if (string.length() != 2){
						cardValue = String.valueOf((string.charAt(0)) + String.valueOf(string.charAt(1)));
						cardSuit = String.valueOf(string.charAt(2));
					}				
					else{
						cardValue = String.valueOf(string.charAt(0));
						cardSuit = String.valueOf(string.charAt(1));
					}
					
					tempCard = new PlayingCard(cardValue, cardSuit);
					
					if (usedCards != null){
						Iterator<PlayingCard> itr = usedCards.iterator();	
						
						//while temp cards has next
						while(itr.hasNext()){
							PlayingCard currentUsedCard = itr.next();
							
							//if temp equals next
							if(tempCard.GetValue() == currentUsedCard.GetValue() && tempCard.GetSuit() == currentUsedCard.GetSuit())
								
								//throw duplicate card exception
								throw new DuplicateCardException();
						}					
					}
					else{	
						usedCards = new ArrayList<PlayingCard>(10);
					}
					
					//add in card if it makes it though the code				
					player.AddCard(tempCard);			
					usedCards.add(tempCard);
				}				
			}
		}
		catch(DuplicateCardException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		catch( InvalidCardCountException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
	}
	
	static Winner DetermineWinner(Player[] players){
		WinningHandFinder whf = new WinningHandFinder();
		Winner winner = whf.FindWinner(players);
		return winner;
	}
	
	static void OutputWinner(Winner winner){	
		
		System.out.println(winner.GetName() + " wins. - with " + winner.GetWinningMessage());
	}
}
