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

	public void play() {
		for(int i = 0; i < SIZE; i++) {
			int number = RAND.nextInt(100) + 1;
			search(i, number);
		}
	}

	private boolean search(int column, int value) {
		for(int i = 0; i < SIZE; i++) {
			Tile tile = board[column + i * SIZE];
			if(tile.value() == value) {
				tile.found();
				return true;
			}
		}
		return false; // Value not in this column
	}

	/**
	* Prints out the current game board.
	*/
	public void print() {
		for(Tile t : board) {
			System.out.print(t.value() + " ");
		}
	}

}