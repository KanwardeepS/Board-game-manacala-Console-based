package fun.com.game.code;

import java.util.Scanner;

import fun.com.game.code.mancala.service.MancalaRuleCheckService;

public class MancalaGameManager {
	MancalaRuleCheckService ruleCheckService = new MancalaRuleCheckService();

	public boolean doMove(GameBoard gameBoard) {
		boolean gameFinished = false;
		return gameFinished;
	}

	public void handleGameConsoleInteraction(GameBoard gameBoard, Scanner sc) {
		while (gameBoard.getPlayerInTurn().isInTurn()) {
			System.out
					.println(gameBoard.getPlayerInTurn().getPlayerName()
							+ " : Choose the pit/dish by index you want to move stones from");
			Integer indexOfPit = validateInput(gameBoard, sc.nextInt(), sc);
			gameBoard.getPlayerInTurn().setIndexOfpitToEmpty(indexOfPit - 1);
			boolean gameFinshed = doNextMove(gameBoard);
			printMancalaBoard(gameBoard);
		}
	}

	private boolean doNextMove(GameBoard gameBoard) {
		MancalaPlayer playerInTurn = gameBoard.getPlayerInTurn();
		MancalaPlayer opponentPlayer = gameBoard.getOpponentPlayer();
		int stonesCountInPit = playerInTurn.getMancalaPlayerPits()[playerInTurn
				.getIndexOfpitToEmpty()];
		// set current pit to empty
		playerInTurn.getMancalaPlayerPits()[playerInTurn.getIndexOfpitToEmpty()] = 0;
		boolean isFinished = false;

		// Do move the stones to own pits
		// add to own mancala big pit
		// Move stones to opponents pits
		// add to opponent's mancala big pit
		// continue
		while (stonesCountInPit > 0) {
			boolean isSelfLastPit = ruleCheckService.isLastPit(playerInTurn
					.getIndexOfpitToEmpty());
			// fill own pits if the pit is not the last one
			if (!isSelfLastPit) {
				for (int i = playerInTurn.getIndexOfpitToEmpty() + 1; i <= playerInTurn
						.getMancalaPlayerPits().length - 1; i++) {
					if (stonesCountInPit != 0) {
						int pitStoneCount = playerInTurn.getMancalaPlayerPits()[i];
						playerInTurn.getMancalaPlayerPits()[i] = pitStoneCount + 1;
						stonesCountInPit = stonesCountInPit - 1;
						playerInTurn.setIndexOfLastModifiedPit(i);
					} /*else {
						ruleCheckService.doCaptureIfApplicable(gameBoard);
						playerInTurn.setInTurn(false);
						opponentPlayer.setInTurn(true);
					}*/
				}
				
				if (ruleCheckService.isMoveFinished(stonesCountInPit)) {
					ruleCheckService.doCaptureIfApplicable(gameBoard);
					playerInTurn.setInTurn(false);
					opponentPlayer.setInTurn(true);
					if (ruleCheckService.isGameFinished(playerInTurn)) {
						isFinished = true;
						playerInTurn.setWinner(true);
						opponentPlayer.setWinner(false);
					}
				}

			}

			// fill own mancala big pit
			if (!isFinished && stonesCountInPit >= 1) {
				stonesCountInPit = addStoneToMancala(playerInTurn,
						stonesCountInPit);
				if (stonesCountInPit != 0) {
					playerInTurn.setInTurn(false);
					opponentPlayer.setInTurn(true);
				}
				if (ruleCheckService.isMoveFinished(stonesCountInPit)) {
					if (ruleCheckService.isGameFinished(playerInTurn)) {
						isFinished = true;
						playerInTurn.setWinner(true);
						opponentPlayer.setWinner(false);
					}
					break;
				}
			}

			// fill opponents pits
			if (!isFinished && stonesCountInPit <= 6) {
				int[] mancalaPits = opponentPlayer.getMancalaPlayerPits();
				for (int i = 0; i <= gameBoard.getMaxStonesPerPit(); i++) {
					if (stonesCountInPit != 0) {
						int value = mancalaPits[i];
						mancalaPits[i] = value + 1;
						stonesCountInPit = stonesCountInPit - 1;
						opponentPlayer.setIndexOfLastModifiedPit(i);
					}
				}
				if (ruleCheckService.isMoveFinished(stonesCountInPit)) {
					if (ruleCheckService.isGameFinished(playerInTurn)) {
						isFinished = true;
						playerInTurn.setWinner(true);
						opponentPlayer.setWinner(false);
					}
					break;
				}
			}

			/*
			 * // fill opponent's big pit if (stonesCountInPit <= 1) {
			 * stonesCountInPit = addStoneToMancala(opponentPlayer,
			 * stonesCountInPit);
			 * if(ruleCheckService.isMoveFinished(stonesCountInPit)){ break; } }
			 */
		}

		return isFinished;
	}

	private int addStoneToMancala(MancalaPlayer playerInTurn,
			int stonesCountInPit) {
		int capturedMancala = playerInTurn.getMancalaCaptured()[0];
		capturedMancala = capturedMancala + 1;
		playerInTurn.setMancalaCaptured(new int[] { capturedMancala });
		stonesCountInPit = stonesCountInPit - 1;
		return stonesCountInPit;
	}

	public void printMancalaBoard(GameBoard gameBoard) {
		System.out.println("______________________________");
		System.out.print("Player 1 : ");

		int[] manacalaPlayerOne = gameBoard.getMancalaPlayerOne()
				.getMancalaPlayerPits();
		for (int i = 0; i <= 5; i++) {
			System.out.print(manacalaPlayerOne[i] + " ");
		}
		System.out
				.print("["
						+ gameBoard.getMancalaPlayerOne().getMancalaCaptured()[0]
						+ "]");
		System.out.println();

		System.out.print("Player 2 : ");
		System.out.print("["
				+ gameBoard.getMancalaPlayertwo().getMancalaCaptured()[0]
				+ "] ");
		int[] manacalaPlayerTwo = gameBoard.getMancalaPlayertwo()
				.getMancalaPlayerPits();
		for (int i = 0; i <= 5; i++) {
			System.out.print(manacalaPlayerTwo[i] + " ");
		}
		System.out.println();
		System.out.println("______________________________");
	}

	private Integer validateInput(GameBoard gameBoard, Integer indexOfPit,
			Scanner sc) {
		if(gameBoard.getPlayerInTurn().isWinner()){
			System.out.println("Game finished! Winner is "+gameBoard.getPlayerInTurn().getPlayerName());
		}
		
		if (null != indexOfPit && indexOfPit > gameBoard.getMaxStonesPerPit()) {
			System.out
					.println("Invalid input! Index of pit is not valid. GameBoard has "
							+ gameBoard.getMaxStonesPerPit()
							+ " pits only. \n Your entered index of pit {"
							+ indexOfPit
							+ "} does not exit! \n Please input again");
			indexOfPit = validateInput(gameBoard, sc.nextInt(), sc);
		}
		
		
		MancalaPlayer playerInTurn = gameBoard.getPlayerInTurn();
		int stonesCountInPit = playerInTurn.getMancalaPlayerPits()[indexOfPit-1];
		
		if(stonesCountInPit==0){
			System.out
			.println("Invalid input! Index of pit is not valid. Pit has "
					+ stonesCountInPit
					+ " Stones. \n Please input again");
			indexOfPit = validateInput(gameBoard, sc.nextInt(), sc);
		}

		return indexOfPit;
	}

}
