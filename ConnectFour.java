// connectfour.java
// Simon Swanson
// provides logic for running the game turns

import java.util.Scanner;

// game controller, uses Board class for validating moves
public class ConnectFour {

	public static void main(String[] args) {
		// gets the game started

		Board board;
		int currentPlayer;
		Scanner input;
		int rows = 6;
		int columns = 7;

		do {
			System.out.print("How many rows should your board have? Leave blank for default. ");
			input = new Scanner(System.in);
			if (input.hasNextInt) rows = input.nextInt;
			else System.out.print("Using default value of 6 rows because you did not include a number.");
			System.out.print("How many columns should your board have? Leave blank for default. ");
			// input = new Scanner(System.in);
			if (input.hasNextInt) rows = input.nextInt;
			else System.out.print("Using default value of 7 columns because you did not include a number.");
			// board = new Board(rows, columns);
			player.start(new Board(rows, columns))
			do {
				currentPlayer = nextPlayer(currentPlayer);
				input = new Scanner(System.in);
				board = board.addDisk(input, currentPlayer);
			} while(!gameOver(board));
		} while(input.nextLine());
	}
}
