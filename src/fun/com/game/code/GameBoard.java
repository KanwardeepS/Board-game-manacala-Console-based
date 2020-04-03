package fun.com.game.code;

public class GameBoard {
	private int numberOfPlayers = 2;
	private int maxStonesPerPit = 6;
	private int numberOfPits = 6;
	private MancalaPlayer mancalaPlayerOne = new MancalaPlayer("Player 1", 1);
	private MancalaPlayer mancalaPlayertwo = new MancalaPlayer("Player 2", 2);
	private int pitChoosen;
	private int lastPitModifiedCount;
	private MancalaPlayer playerInTurn;
	
	

	public GameBoard(int numberOfPlayers, int maxStonesPerPit, int numberOfPits ) {
		this.numberOfPlayers = numberOfPlayers;
		this.maxStonesPerPit = maxStonesPerPit;
		this.numberOfPits = numberOfPits;
		mancalaPlayerOne = new MancalaPlayer("Player 1", 1);
		mancalaPlayertwo = new MancalaPlayer("Player 2", 2);
		populatePitsToFull();
	}

	private void populatePitsToFull() {
		int [] mancalaPlayerOnePits = mancalaPlayerOne.getMancalaPlayerPits();
		int [] mancalaPlayerTwoPits = mancalaPlayertwo.getMancalaPlayerPits();
		for(int i=0;i<=numberOfPits-1;i++){
			mancalaPlayerOnePits[i] = maxStonesPerPit;
			mancalaPlayerTwoPits[i] = maxStonesPerPit;
		}
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getMaxStonesPerPit() {
		return maxStonesPerPit;
	}

	public MancalaPlayer getMancalaPlayerOne() {
		return mancalaPlayerOne;
	}

	public void setMancalaPlayerOne(MancalaPlayer mancalaPlayerOne) {
		this.mancalaPlayerOne = mancalaPlayerOne;
	}

	public MancalaPlayer getMancalaPlayertwo() {
		return mancalaPlayertwo;
	}

	public void setMancalaPlayertwo(MancalaPlayer mancalaPlayertwo) {
		this.mancalaPlayertwo = mancalaPlayertwo;
	}

	public void setMaxStonesPerPit(int maxStonesPerPit) {
		this.maxStonesPerPit = maxStonesPerPit;
	}

	public int getNumberOfPits() {
		return numberOfPits;
	}

	public void setNumberOfPits(int numberOfPits) {
		this.numberOfPits = numberOfPits;
	}
	
	public int getPitChoosen() {
		return pitChoosen;
	}

	public void setPitChoosen(int pitChoosen) {
		this.pitChoosen = pitChoosen;
	}

	public int getLastPitModifiedCount() {
		return lastPitModifiedCount;
	}

	public void setLastPitModifiedCount(int lastPitModifiedCount) {
		this.lastPitModifiedCount = lastPitModifiedCount;
	}
	
	public MancalaPlayer getPlayerInTurn() {
		if(mancalaPlayerOne.isInTurn()){
			return mancalaPlayerOne;
		}else{
			return mancalaPlayertwo;
		}
	}
	
	public MancalaPlayer getOpponentPlayer() {
		if(!mancalaPlayerOne.isInTurn()){
			return mancalaPlayerOne;
		}else{
			return mancalaPlayertwo;
		}
	}

	public void setPlayerInTurn(MancalaPlayer playerInTurn) {
		this.playerInTurn = playerInTurn;
	}

	
}
