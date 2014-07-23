// connectfour.java
// Simon Swanson
// provides logic for running the game turns

import java.util.Scanner;


// game controller, uses Board class for validating moves
public class ConnectFour {

	private static boolean fourInRow(Board board, int row, int column, int player) {
		// logic for checking for a connect 4 in a row, starting at the leftmost disk
		// System.out.println("Running method fourInRow");
		int disksInLine = 0;

		for( int i = 0 ; (i < 4) && (i + column < board.width()) && (board.diskAt(row, column + i) == player) ; i++ ) {
			disksInLine++;
		}

		return disksInLine == 4;
	}

	private static boolean fourInColumn(Board board, int row, int column, int player) {
		// logic for checking for a connect 4 in a column, starting at the topmost disk
		// System.out.println("Running method fourInColumn");
		int disksInLine = 0;

		for( int i = 0 ; (i < 4) && (i + row < board.height()) && (board.diskAt(row + i, column) == player) ; i++ ) {
			disksInLine++;
		}

		return disksInLine == 4;
	}

	private static boolean fourDownBackDiag(Board board, int row, int column, int player) {
		// logic for checking for a connect 4 in a backwards diagonal, starting at the toprightmost disk
		// System.out.println("Running method fourDownBackDiag");
		int disksInLine = 0;

		for( int i = 0 ; (i < 4) && (i + row < board.height()) && (column - i > 0) && (board.diskAt(row + i, column - i) == player) ; i++ ) {
			disksInLine++;
		}

		return disksInLine == 4;
	}

	private static boolean fourDownFrontDiag(Board board, int row, int column, int player) {
		// logic for checking for a connect 4 in a forwards diagonal, starting at the topleftmost disk
		// System.out.println("Running method fourDownFrontDiag");
		int disksInLine = 0;

		for( int i = 0 ; (i < 4) && (i + row < board.height()) && (column + i < board.width()) && (board.diskAt(row + i, column + i) == player) ; i++ ) {
			disksInLine++;
		}

		return disksInLine == 4;
	}	

	private static int winningPlayer(Board board) {
		// determines whether or not a player has won
		// returns the player number if that player has won the game or 0
		// System.out.println("Running method isGameOver");

		for(int row = 0 ; row < board.height() ; row++) {
			for(int column = 0 ; column < board.width() ; column++) {
				for(int player = 1 ; player <= 2 ; player++) {

					if (fourInRow(board, row, column, player) || fourInColumn(board, row, column, player) || fourDownBackDiag(board, row, column, player) || fourDownBackDiag(board, row, column, player)) return player;
				}
			}
		}
		return 0;
	}

	private static boolean gameOver(Board board) {
		// logic for determining if the game is over
		// System.out.println("Running method gameOver");
		
		if (winningPlayer(board) == 0) {
			if (board.fullBoard()) {
				System.out.println("It's a draw!");
				return true;
			}
			return false;
		}

		board.boardprint();
		System.out.format("Player %d wins!%n", winningPlayer(board));
		return true;
	}

	public static void main(String[] args) {

		Board board = new Board(6, 7);
		int currentPlayer = 1;
		Scanner input;
		boolean gameEnd;

		do {
			board.boardprint();
			System.out.format("Player %d's turn. Enter the column you want to drop your disk down: ", currentPlayer);
			input = new Scanner(System.in);
			board = board.addDisk(input, currentPlayer);
			
			if (currentPlayer == 1) 
				currentPlayer++;
			else if (currentPlayer == 2)
				currentPlayer--;
			else
				System.out.println("Something went wrong. Recommend hitting CTRL+C to exit! ");
			
			gameEnd = gameOver(board);
		} while(!gameEnd);

		System.out.println("Game over.")
	}
}
