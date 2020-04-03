package fun.com.game.code.mancala.service;

import fun.com.game.code.GameBoard;
import fun.com.game.code.MancalaPlayer;

public class MancalaRuleCheckService {
	
	/**
	 * 
	 * @param mancala : one of the player's Mancala pits 
	 * @return True is all the pits are empty; False if it even one of the pit is having a stone
	 */
	public boolean isMancalaEmpty(int [] mancala){
		boolean mancalaEmpty = true;
		for(int i = 0;i<=5;i++){
			if(mancala[i]!=0){
				mancalaEmpty = false;	
				break;
			}
		}
		return mancalaEmpty;
	}
	
	public boolean doCaptureIfApplicable(GameBoard gameBoard){
		boolean captureApplicable = false;
		MancalaPlayer playerInTurn = gameBoard.getPlayerInTurn();
		MancalaPlayer opponentPlayer = gameBoard.getOpponentPlayer();
		if(playerInTurn.getMancalaPlayerPits()[playerInTurn.getIndexOfLastModifiedPit()]==1){
			int stoneInOpponentPit = opponentPlayer.getMancalaPlayerPits()[playerInTurn.getIndexOfLastModifiedPit()];
			opponentPlayer.getMancalaPlayerPits()[playerInTurn.getIndexOfLastModifiedPit()]=0;
			playerInTurn.getMancalaPlayerPits()[playerInTurn.getIndexOfLastModifiedPit()]=0;
			int [] capturedMancala = new int [] {playerInTurn.getMancalaCaptured()[0]+stoneInOpponentPit+playerInTurn.getMancalaPlayerPits()[playerInTurn.getIndexOfLastModifiedPit()]};
			playerInTurn.setMancalaCaptured(capturedMancala);
			captureApplicable = true;
		}
		return captureApplicable;
		
	}
	
	public boolean isLastPit(int indexOfpitToEmpty) {
		if(indexOfpitToEmpty==5){
			return true;
		}
		return false;
	}
	
	public boolean isMoveFinished(int countOfStones){
		return countOfStones == 0;
	}

	public boolean isGameFinished(MancalaPlayer player) {
		boolean gameFinished = true;
		for(int i=0;i<=5;i++){
			if(player.getMancalaPlayerPits()[i]!=0){
				gameFinished=false;
				break;
			}
		}
		return gameFinished;
	}
	
	
	


}
