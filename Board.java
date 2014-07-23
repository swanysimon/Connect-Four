// Board.java
// Simon Swanson
// provides the board and logic for placing pieces

import java.util.Scanner;

// the board is an array of arrays of ints representing which player's disk is in a space
// the players are 1 or 2, 0 marks an unused space on the board
public class Board {
	private int[][] board;

	public Board(int rows, int columns) {
		// System.out.println("Making new board");

		if (rows > 0 && columns > 0) {
			System.out.format("Creating a new %dx%d board%n", rows, columns);
			this.board = new int[rows][columns];
		}
		else {
			System.out.println("Making default board 7x6 because you're stupid");
			this.board = new int[6][7];
		}
	}

	public Board addDisk(Scanner input, int player) {
		// logic for placing a new disk on the board
		// need to verify player is 1 or 2, column a valid column
		// System.out.println("Running method addDisk");
		int insertRow = -1;
		int column = verifyInput(input);
		
		for ( int row = 0 ; (row < height()) && (this.board[row][column - 1] == 0) ; row++ ) {
			insertRow = row;
		}
		
		// need to subtract 1 from the column because users have the array columns starting with 1 instead of 0
		if (insertRow >= 0) this.board[insertRow][column - 1] = player;
		
		else {
			boardprint();
			System.out.format("The column is full. Please try a different move: ", column);
			input = new Scanner(System.in);
			addDisk(input, player);
		}
		return this;
	}

	private int verifyInput(Scanner input) {
		// logic for verifying user input is an int
		// System.out.println("Running method verifyInput");
		int column = 0;
		
		if (input.hasNextInt()) {
			int testColumn = input.nextInt();
			column = verifyColumn(testColumn);
		}

		else {
			System.out.println("Enter a number, dumbotron");
			column = verifyColumn(-1);
		}

		return column;
	}

	private int verifyColumn(int column) {
		// logic for verifying column input
		// System.out.println("Running method assertColumn");

		if ((column >= 1) && (column <= width())) return column;
		
		else {
			boardprint();
			System.out.format("Column \"%d\" is an invalid choice. Please try a different column: ", column);
			Scanner input = new Scanner(System.in);
			column = verifyInput(input);
		}
		
		return column;
	}

	public boolean fullBoard() {
		// logic for determining if the board is full
		// System.out.println("Running method fullBoard");
		boolean boardFull = true;

		for(int row = 0 ; row < height() ; row++) {
			for(int column = 0 ; column < width() ; column++) {
				boardFull = boardFull && (board[row][column] == 1 || board[row][column] == 2);

				if (!boardFull) return boardFull;
			}
		}
		return boardFull;
	}

	public int diskAt(int row, int column) {
		// returns the value of the disk at a certain position or -1
		// System.out.println("Running method diskAtPos");
		return this.board[row][column];
	}

	public int width() {
		// gets the width of the board
		// System.out.println("Running method width");
		return this.board[0].length;
	}

	public int height() {
		// gets the height of the board
		// System.out.println("Running method height");
		return this.board.length;
	}

	public void boardprint() {
		// logic for printing the board
		// System.out.println("Running method boardprint");
		for(int row = 0 ; row < height() ; row++) {
			for(int column = 0 ; column < width() ; column++) {
				
				if (this.board[row][column] == 1) System.out.format("| 1 ");
				else if (this.board[row][column] == 2) System.out.format("| 2 ");
				else System.out.format("| - ");
			}

			System.out.println("|");
		}

		// prints the column numbers below each column
		for(int column = 0 ; column < width() ; column++) {
			// adds 1 to the column numbers for easier user input
			String number = "" + (column + 1);
			
			if (number.length() == 1)
				number = " " + number;

			System.out.format(" %s ", number);
		}

		System.out.println("");
	}
}