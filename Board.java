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
		Set<Integer> numbers = new HashSet<Integer>();

		while(numbers.size() < board.length) { // Creates set of SIZE^2 unique random numbers.
			numbers.add(RAND.nextInt(100) + 1);
		}

		int index = 0;
		for(Integer num : numbers) { // Fills board with random tiles.
			board[index] = new Tile(num);
			index++;
		}
	}

	/**
	* Simulates one round of the game. Updates game status if a number is found.
	*/
	public void play() {
		boolean change = false;
		for(int i = 0; i < SIZE; i++) {
			int number = RAND.nextInt(100) + 1;
			change = change || search(number);
		}
		if(change) {
			updateStatus();
		}
	}

	/**
  * Searches and returns whether or not value is found in column.
  *
  * @param		 value the value to look for.
  */
	private boolean search(int value) {
		for(Tile tile : board) {
			if(tile.value() == value) {
				tile.setStatus(true);
				return true;
			}
		}
		return false; // Value not found in board.
	}

	/**
	* Prints out the current game board.
	*/
	public void print() {
		System.out.println();
		System.out.println("-------------------------------");
		for(int i = 0; i < board.length; i++) {
			Tile tile = board[i];
			System.out.print("|" + tile);
			if(i % SIZE == 4) {
				System.out.println("|");
				System.out.println("-------------------------------");
			}
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
		gameWon = getStatus();
	}

	/**
	* Returns whether or not there's a winning path on this board.
	*/
	public boolean getStatus() {
		if(checkDiagonals()) { // Exit if winning path is one of the diagonals.
			return true;
		}

		for(int i = 0; i < SIZE; i++) {
			if(checkStraight(i, true) || checkStraight(i, false)) { // Winning path found.
				return true;
			}
		}
		return false;
	}

	/**
	*	Returns whether this column is a winning path.
	*
	* @param offset column or row to look.
	* @param column wheter to look vertically or horizontally.
	*/
	private boolean checkStraight(int offset, boolean column) {
		for(int i = 0; i < SIZE; i++) {
			Tile tile;
			if(column) {
				tile = board[i * SIZE + offset];
			} else {
				tile = board[offset * SIZE + i];
			}
			if(!tile.status()) { // Unfound tile
				// if(!((i == 0 || i == 4) && column && find(offset + 1, i).status()))
					return false;
			}
		}
		return true;
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
				foundLeft = tile.status();
			}

			if(foundRight) {
				Tile tile = board[(i + 1) * (SIZE - 1)];
				foundRight = tile.status();
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
	* Returns the Tile at this coordinate.
	*
	* @param x x coordinate of desired tile.
	* @param y y coordinate of desired tile.
	*/
	public Tile find(int x, int y) {
		x--; // Convert to Java index.
		y--;

		return board[y * SIZE + x];
	}

	/**
	* Returns the Tile at this index.
	*
	* @param i index number of desired tile.
	*/
	public Tile find(int i) {
		return board[i - 1];
	}

	/**
	* Returns number of tiles on this board.
	*/
	public int size() {
		return SIZE;
	}
}