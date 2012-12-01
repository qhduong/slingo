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
		for(int i = 0; i < board.length; i++) {
			Tile tile = board[i];
			if(i % SIZE == 0) {
				System.out.println();
				System.out.println("-------------------------------");
				System.out.print("|");
			}

			String number = " " + tile.value();
			if(tile.found()) {
				number += "*";
			}
			while(number.length() < 5) {
				number += " ";
			}
			System.out.print(number + "|");
		}
		System.out.println();
		System.out.println("-------------------------------");
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

		for(int i = 0; i < SIZE; i++) {
			boolean foundCol = checkColumn(i);
			boolean foundRow = checkRow(i);

			if(foundCol || foundRow) { // Winning path found.
				gameWon = true;
				return;
			}
		}
	}

	/**
	*	Returns whether this column is a winning path.
	* @param col column to look in.
	*/
	private boolean checkColumn(int col) {
		boolean valid = true;
		for(int i = 0; i < SIZE; i++) {
			if (valid) { // This column can still win.
				Tile tile = board[i * SIZE + col];
				valid = tile.found();
			}
		}
		return valid;
	}

	/**
	*	Returns whether this row is a winning path.
	* @param row row to look in.
	*/
	private boolean checkRow(int row) {
		boolean valid = true;
		for(int i = 0; i < SIZE; i++) {
			if (valid) { // This column can still win.
				Tile tile = board[row * SIZE + i];
				valid = tile.found();
			}
		}
		return valid;
	}

	/**
	*	Returns whether there is a winning path diagonally.
	*/
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

	/**
	*	Returns whether or not there exists a winning path.
	*/
	public boolean gameWon() {
		return gameWon;
	}

	/**
	*	@param x x-coordinate on board.
	* @param y y-coordinate on board.
	*/
	public boolean check(int x, int y) {
		if(gameWon) { // Don't need to check if already exist winning path.
			return gameWon;
		}

		x--; // Convert to Java index.
		y--;

		Tile tile = board[y * SIZE + x];
		boolean oldStatus = tile.found();
		tile.found(true); // Sets this tile as found.
		boolean result = checkColumn(x) || checkRow(y);

		if(x == y || (x + y == SIZE - 1)) {
			result = result || checkDiagonals();
		}
		tile.found(oldStatus); // Undo change.
		return result;
	}
}