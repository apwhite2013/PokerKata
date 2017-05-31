public class Main{	
	
	protected static Player[] players;
	
	public static void main(String[] args) {
		//initialize cards and hands
		players = PokerInitializer.GameInitialization(players);
		
		//analyze the poker hands to find a winner
		Winner winner = PokerInitializer.DetermineWinner(players);
		
		//output winner
		PokerInitializer.OutputWinner(winner);
	}
	
}
