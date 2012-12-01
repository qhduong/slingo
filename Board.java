import java.util.*;

public class Board {

	private Tile[] board; // Stores board info
	private boolean gameWon; // If there's a winning path
	private static final int SIZE = 5; // Size x Size board
	private static final Random RAND = new Random(); // Random generator

	/**
	* Creates a SIZE * SIZE game board. Fills the board with random unique tiles.
	*/
	public Board() {
		board = new Tile[SIZE * SIZE];
		gameWon = false;

		Set<Integer> numbers = new HashSet<Integer>();
		while(numbers.size() < board.length) {
			numbers.add(RAND.nextInt(100) + 1);
		}

		int index = 0;
		for(Integer num : numbers) {
			board[index] = new Tile(num);
			index++;
		}
	}

	/**
	* Simulates one round of the game. Updates game status if a number is found.
	*/
	public void play() {
		for(int i = 0; i < SIZE; i++) {
			int number = RAND.nextInt(100) + 1;
			if(search(i, number)) { // Searches the column
				updateStatus();
			}
		}
	}

	/**
  * Searches and returns whether or not value is found in column.
  *
  * @param     column the column to search in.
  * @param		 value the value to look for.
  */
	private boolean search(int column, int value) {
		for(int i = 0; i < SIZE; i++) {
			Tile tile = board[column + i * SIZE];
			if(tile.value() == value) {
				tile.found(true);
				return true;
			}
		}
		return false; // Value not in this column
	}

	/**
	* Prints out the current game board.
	*/
	public void print() {
		for(Tile tile : board) {
			System.out.print(tile.value());
			if(tile.found()) {
				System.out.print("*");
			}
			System.out.print(" ");
		}
		System.out.println();
	}

	/**
	*	Check to see if there's a winning path. Does nothing if one already exists.
	*/
	private void updateStatus() {
		if(gameWon) { // Exit if already found winning path before.
			return;
		}

		if(checkDiagonals()) { // Exit if winning path is one of the diagonals.
			gameWon = true;
			return;
		}

		for(int col = 0; col < SIZE; col++) {
			boolean foundCol = true;
			boolean foundRow = true;
			for(int row = 0; row < SIZE; row++) {
				if (foundCol) { // This column can still win.
					Tile vertical = board[col * SIZE + row];
					foundCol = vertical.found();
				}

				if(foundRow) { // This row can still win.
					Tile horizontal = board[row * SIZE + col];
					foundRow = horizontal.found();
				}
			}

			if(foundCol || foundRow) { // Winning path found.
				gameWon = true;
				return;
			}
		}
	}

	private boolean checkDiagonals() {
		boolean foundLeft = true;
		boolean foundRight = true;
		for(int i = 0; i < SIZE; i++) {
			if(foundLeft) {
				Tile tile = board[i * SIZE + i];
				foundLeft = tile.found();
			}

			if(foundRight) {
				Tile tile = board[(i + 1) * (SIZE - 1)];
				foundRight = tile.found();
			}
		}

		return foundLeft || foundRight;
	}

	public boolean gameWon() {
		return gameWon;
	}

}