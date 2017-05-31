class Winner extends Player{
	private String handType;
	private PlayingCard winningCard;
	private String winningMessage;

	public Winner(Player winner, String winningMessage){
		super(winner.GetName());
		this.winningMessage = winningMessage;		
	}
	
	public String GetWinningHandType(){
		return handType;
	}
	
	public PlayingCard GetWinningCard(){
		return winningCard;
	}
	
	public String GetWinningMessage(){
		return winningMessage;
	}
	
	public void SetWinningMessage(String winningMessage){
		this.winningMessage = winningMessage;
	}
}
