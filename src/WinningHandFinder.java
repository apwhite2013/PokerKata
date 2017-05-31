import java.util.Iterator;

public class WinningHandFinder {
	
	public Winner FindWinner(Player[] players){
		Winner winner = null;
		
		/*//straightChecker
		for(int i=0; i<players.length; i++){
			if((straightFlushHighCardValue == 0 || players[i].HasStraightFlush()) 
					&& (players[i].GetStraightFlushHighCard() > straightFlushHighCardValue)){
				handType = "straight flush";
				//fourOfAKindValue = players[i].GetStraightFlushHighCard();
				runningWinner = players[i];
			}
		}*/
		
		//straight checker
		winner = StraightFlushChecker(players);
		if(winner != null){
			return winner;
		}
		
		//four of a kind checker
		winner = FourOfAKindChecker(players);
		if(winner != null){
			return winner;
		}
		
		//full house checker
		winner = FullHouseChecker(players);
		if(winner != null){
			return winner;
		}		
		
		//flush checker
		winner = FlushChecker(players);
		if (winner != null){
			return winner;
		}
		
		//straight Checker
		winner = StraightChecker(players);
		if (winner != null){
			return winner;
		}
				
		//three of a kind checker
		winner = ThreeOfAKindChecker(players);
		if (winner != null){
			return winner;
		}
		
		//two pair checker
		winner = TwoPairChecker(players);
		if (winner != null){
			return winner;
		}
		
		//one pair checker
		winner = OnePairChecker(players);		
		if (winner != null){
			return winner;
		}
		
		//high card checker
		winner = HighCardChecker(players);
		return winner;
	}	
	
	private Winner StraightFlushChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;
		
		//if either player has a straight flush
		if(players[0].HasStraightFlush() || players[1].HasStraightFlush()){
			
			//if both players have a straight flush
			if(players[0].HasStraightFlush() && players[1].HasStraightFlush()){
			
				//if high value is the same its a tie
				if(players[0].GetStraightFlushHighCard() == players[1].GetStraightFlushHighCard()){
					System.out.println("Tie.");
					System.exit(0);
				}
				
				//if different find the higher
				else{
					runningWinner = players[0].GetStraightFlushHighCard() > players[1].GetStraightFlushHighCard() ? players[0] : players[1];
				}
			}
			
			//if one player has a straight flush
			else{				
				//if player one has a straight they are the winner, else is player 2
				runningWinner = players[0].HasStraightFlush() ? players[0] : players[1];
			}
		}		
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("straight flush: " + CardNamer(runningWinner.GetStraightFlushHighCard())));
		}
		
		return winner;
	}
	
	private Winner FourOfAKindChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;
		
		//if either player has a four of a kind
		if(players[0].HasFourOfAKind() || players[1].HasFourOfAKind()){	
			
			//if both have a four of a kind
			if(players[0].HasFourOfAKind() && players[1].HasFourOfAKind()){	
				//set running winner
				runningWinner = players[0].GetFourOfAKindValue() > players[1].GetFourOfAKindValue() ? players[0] : players[1];
			}
			
			//else one has a four of a kind
			else{
				//set running winner
				runningWinner = players[0].HasFourOfAKind() ? players[0] : players[1];
			}
		}
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("four of a kind: " + CardNamer(runningWinner.GetFourOfAKindValue())));
		}
		
		return winner;
	}
	
	private Winner FullHouseChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;
		
		//if either player has a full house
		if(players[0].HasFullHouse() || players[1].HasFullHouse()){	
		
			//if both players have a full house
			if(players[0].HasFullHouse() && players[1].HasFullHouse()){	
				
				//if high card the same
				if(players[0].GetFullHouseValue() == players[1].GetFullHouseValue()){
					System.out.println("Tie.");
					System.exit(0);
				}
				
				//else high card different
				else{
					runningWinner = players[0].GetFullHouseValue() > players[1].GetFullHouseValue() ? players[0] : players[1];
				}
			}
			
			//else one player has a full house
			else{
				runningWinner = players[0].HasFullHouse() ? players[0] : players[1];	
			}
		}		
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("full house: " + CardNamer(runningWinner.GetFullHouseValue()) + " over " + CardNamer(runningWinner.GetLowCard().GetValue())));
		}
		
		return winner;
	}	
	
	private Winner FlushChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;
		
		//if either player has a flush
		if(players[0].HasFlush() || players[1].HasFlush()){		
			
			//if both have a flush
			if(players[0].HasFlush() && players[1].HasFlush()){	
				winner = HighCardChecker(players);
				winner.SetWinningMessage(("flush tie broken by " + winner.GetWinningMessage()));
				return winner;
			}

			//if one has a flush
			else{
				runningWinner = players[0].HasFlush() ? players[0] : players[1];				
			}
		}
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("flush: " + SuitNamer(runningWinner.GetFlushSuit())));
		}
		return winner;
	}
	
	private Winner StraightChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;
		
		//if either player has a straight
		if(players[0].HasStraight() || players[1].HasStraight()){
			
			//if both players have a straight
			if(players[0].HasStraight() && players[1].HasStraight()){
			
				//if high value is the same its a tie
				if(players[0].GetStraightHighCard() == players[1].GetStraightHighCard()){
					System.out.println("Tie.");
					System.exit(0);
				}
				
				//if different find the higher
				else{
					runningWinner = players[0].GetStraightHighCard() > players[1].GetStraightHighCard() ? players[0] : players[1];
				}
			}
			
			//if one player has a straight
			else{				
				//if player one has a straight they are the winner, else is player 2
				runningWinner = players[0].HasStraight() ? players[0] : players[1];
			}
		}		
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("straight: " + CardNamer(runningWinner.GetStraightHighCard())));
		}
		
		return winner;
	}

	private Winner ThreeOfAKindChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;	
		
		//if either player has 3 of a kind. no comparisons needed because there cant be any three of a kind ties
		if(players[0].HasThreeOfAKind() || players[1].HasThreeOfAKind()){
				runningWinner = players[0].GetThreeOfAKindValue() > players[1].GetThreeOfAKindValue() ? players[0] : players[1];
		}		

		if(runningWinner != null){
			winner = new Winner(runningWinner, ("three of a kind: " + CardNamer(runningWinner.GetThreeOfAKindValue())));
		}
		
		return winner;
	}
	
	private Winner TwoPairChecker(Player[] players){
		Winner winner = null;
		Player runningWinner = null;
		
		//if either player has 2 pair
		if(players[0].HasTwoPair() || players[1].HasTwoPair()){
			
			//if both players have 2 pair
			if(players[0].HasTwoPair() && players[1].HasTwoPair()){			
				
				//if higher values are the same compare lower
				if(players[0].GetOnePairValue() == players[1].GetOnePairValue()){
					
					//compare lower values. if lower values are different, compare remaining card
					if(players[0].GetTwoPairValue() == players[1].GetTwoPairValue()){
						
						//compare remaining card
						Iterator<PlayingCard> p1itr = players[0].GetCards().iterator();
						Iterator<PlayingCard> p2itr = players[1].GetCards().iterator();
						int p1leftoverValue = 0;
						int p2leftoverValue = 0;
						
						
						while(p1itr.hasNext()){
							int tempValue = p1itr.next().GetValue();
							
							// assign leftover value if it doesnt match the pairs
							if(tempValue != players[0].GetOnePairValue() && tempValue != players[0].GetTwoPairValue()){
								p1leftoverValue = tempValue;
							}
						}
						
						while(p2itr.hasNext()){
							int tempValue = p2itr.next().GetValue();
							
							// assign leftover value if it doesnt match the pairs
							if(tempValue != players[1].GetOnePairValue() && tempValue != players[1].GetTwoPairValue()){
								p2leftoverValue = tempValue;
							}
						}
						
						//compare leftover values
						if(p1leftoverValue == p2leftoverValue){
							System.out.println("Tie.");
							System.exit(0);
						}
						else{
							runningWinner = p1leftoverValue > p2leftoverValue ? players[0] : players[1];
						}
					}
					//if lower values are different, assign winner
					else{
						runningWinner = players[0].GetTwoPairValue() > players[1].GetTwoPairValue() ? players[0] : players[1];
					}
				}
				//if higher values are different, assign winner
				else{
					runningWinner = players[0].GetOnePairValue() > players[1].GetOnePairValue() ? players[0] : players[1];
				}
			}			
		}
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("two pair: " + CardNamer(runningWinner.GetOnePairValue()) + " over " + CardNamer(runningWinner.GetTwoPairValue())));
		}
		
		return winner;
	}	
	
	private Winner OnePairChecker(Player[] players){
		// if both players have a pair
		Winner winner = null;
		Player runningWinner = null;
		Iterator<PlayingCard> p1itr = players[0].GetCards().iterator();
		Iterator<PlayingCard> p2itr = players[1].GetCards().iterator();
		int[] p1leftoverCards = new int[3];
		int[] p2leftoverCards = new int[3];
		
		//enter into the checker if either player has a pair
		if(players[0].HasOnePair() || players[1].HasOnePair()){
			
			//if players both have a pair
			if(players[0].HasOnePair() && players[1].HasOnePair()){
				
				//if pair values are the same
				if(players[0].GetOnePairValue() == players[1].GetOnePairValue()){
					//iterate through cards to make an array for each hand of the non pair cards
					while(p1itr.hasNext()){	
						int counter = 0;
						int tempValue = p1itr.next().GetValue();
						
						if(tempValue != players[0].GetOnePairValue()){
							p1leftoverCards[counter] = tempValue;
							counter++;
						}
					}					
					while(p2itr.hasNext()){	
						int counter = 0;
						int tempValue = p2itr.next().GetValue();
						
						if(tempValue != players[1].GetOnePairValue()){
							p2leftoverCards[counter] = tempValue;
							counter++;
						}
					}	
					
					//compare leftover card arrays to find the next higher card	
					for (int i=0; i<3; i++){
						if(p1leftoverCards[i] != p2leftoverCards[i]){
							if(p1leftoverCards[i] > p2leftoverCards[i]){
								runningWinner = players[0];
							}
							else{
								runningWinner = players[1];
							}
						}
					}
				}
				
				//if values are different
				else{
					if(players[0].GetOnePairValue() > players[1].GetOnePairValue()){
						runningWinner = players[0];
					}
					else{
						runningWinner = players[1];
					}
				}
			}
			
			//if only one player has a pair
			else{
				runningWinner = players[0].HasOnePair() ? players[0] : players[1];
			}
		}
		
		if(runningWinner != null){
			winner = new Winner(runningWinner, ("pair: " + CardNamer(runningWinner.GetOnePairValue())));
		}
		return winner;
	}	
	
	private Winner HighCardChecker(Player[] players){
		Iterator<PlayingCard> p1itr = players[0].GetCards().iterator();
		Iterator<PlayingCard> p2itr = players[1].GetCards().iterator();
		Player runningWinner = null;
		Winner winner = null;
		
		while(p1itr.hasNext() && p2itr.hasNext()){
			int p1value = p1itr.next().GetValue();
			int p2value = p2itr.next().GetValue();
			
			if(p1value != p2value){
				runningWinner = p1value > p2value ? players[0] : players[1];							
				winner = new Winner(runningWinner, ("high card: " + CardNamer(runningWinner.GetHighCard().GetValue())));
			}			
		}
		
		if (winner == null){
			System.out.println("Tie.");
			System.exit(0);
		}
		return winner;
	}
	
	private String CardNamer(int cardInt){
		String winningValueName;
		
		switch(cardInt){
			case 11 :
				winningValueName = "Jack";
				break;
			case 12 :
				winningValueName = "Queen";
				break;
			case 13 :
				winningValueName = "King";
				break;
			case 14 :
				winningValueName = "Ace";
				break;
			default:
				winningValueName = String.valueOf(cardInt);
		}
		
		return winningValueName;
	}
	
	private String SuitNamer (char cardChar){
		String winningSuitName = null;
		
		switch(cardChar){
		case 'S' :
			winningSuitName = "Spades";
			break;
		case 'H' :
			winningSuitName = "Hearts";
			break;
		case 'D' :
			winningSuitName = "Diamonds";
			break;
		case 'C' :
			winningSuitName = "Clubs";
			break;
		}
		
		return winningSuitName;
	}
}
