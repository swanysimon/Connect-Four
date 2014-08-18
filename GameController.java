// GameController.java
// Simon Swanson
// logic for determining game state and running turns

public class GameController {
	private int player;

	public int newPlayer(int currentPlayer) {
		if (currentPlayer == 1) this.player = 2;
		else this.player = 1;

		return this.player;
	}

	private boolean hasFour(Board board, int row, int column) {
		// checks if there are 4 disks in a line given a starting piece

		int disks = 0;
		for( int i = 0 ; (i < 4) && (i + column < board.width()) && (board.diskAt(row, column + i) == this.player) ; i++ ) disks++;
		if (disks == 4) return true;
		else disks = 0;

		for( int i = 0 ; (i < 4) && (i + row < board.height()) && (board.diskAt(row + i, column) == this.player) ; i++ ) disks++;
		if (disks == 4) return true;
		else disks = 0;

		for( int i = 0 ; (i < 4) && (i + row < board.height()) && (column - i > 0) && (board.diskAt(row + i, column - i) == this.player) ; i++ ) disks++;
		if (disks == 4) return true;
		else disks = 0;

		for( int i = 0 ; (i < 4) && (i + row < board.height()) && (column + i < board.width()) && (board.diskAt(row + i, column + i) == this.player) ; i++ ) disks++;
		if (disks == 4) return true;

		return false;
	}

	private boolean winningPlayer(Board board) {
		// determines whether or not the current player has won

		boolean game = false;
		for(int row = 0 ; row < board.height() ; row++) {
			for(int column = 0 ; column < board.width() ; column++) game = game || hasFour(board, row, column);
		}

		return game;
	}

	public boolean gameOver(Board board) {
		// logic for determining if the game is over
		
		if (winningPlayer(board)) {
			System.out.format("Player %d wins!%n", this.player);
			return true;
		}
		if (board.fullBoard()) {
				System.out.println("It's a draw!");
				return true;
		}
		return false;
	}
}