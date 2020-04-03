package fun.com.game.code;

public class MancalaPlayer {
	private String playerName;
	private Integer playerNumber;
	private int[] mancalaPlayerPits = new int[6];
	private int[] mancalaCaptured = new int []{0} ;
	private boolean inTurn = false;
	private int indexOfLastModifiedPit;
	private int indexOfpitToEmpty;
	private boolean isWinner;
	

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public MancalaPlayer(String playerName, Integer playerNumber){
		this.playerName = playerName;
		this.playerNumber =  playerNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(Integer playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int[] getMancalaPlayerPits() {
		return mancalaPlayerPits;
	}

	public void setMancalaPlayerPits(int[] mancalaPlayerPits) {
		this.mancalaPlayerPits = mancalaPlayerPits;
	}

	public int[] getMancalaCaptured() {
		return mancalaCaptured;
	}

	public void setMancalaCaptured(int [] mancalaCaptured) {
		this.mancalaCaptured = mancalaCaptured;
	}

	public boolean isInTurn() {
		return inTurn;
	}

	public void setInTurn(boolean inTurn) {
		this.inTurn = inTurn;
	}

	public int getIndexOfLastModifiedPit() {
		return indexOfLastModifiedPit;
	}

	public void setIndexOfLastModifiedPit(int indexOfLastModifiedPit) {
		this.indexOfLastModifiedPit = indexOfLastModifiedPit;
	}
	
	public int getIndexOfpitToEmpty() {
		return indexOfpitToEmpty;
	}

	public void setIndexOfpitToEmpty(int indexOfpitToEmpty) {
		this.indexOfpitToEmpty = indexOfpitToEmpty;
	}	
	
}
