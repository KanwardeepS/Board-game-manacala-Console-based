package fun.com.game.code;

import java.util.Scanner;

public class ConsoleMancala {

	
	public static void main(String[] args) {
		System.out.println("Enter the max stones per pit");
		Scanner sc = new Scanner(System.in);
		int maxStones = sc.nextInt();
		MancalaGameManager gameManager = new MancalaGameManager();
		GameBoard gameBoard = initializeGameBoardForGameStart(maxStones);
		gameBoard.getMancalaPlayerOne().setInTurn(true);
		gameBoard.getMancalaPlayertwo().setInTurn(false);
		gameManager.printMancalaBoard(gameBoard);
		gameManager.handleGameConsoleInteraction(gameBoard,sc);
	}

	private static GameBoard initializeGameBoardForGameStart(int maxStonesPerPit) {
		GameBoard gameBoard = new GameBoard(2, maxStonesPerPit, 6);
		return gameBoard;
	}

}
