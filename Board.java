import java.util.*;

public class Board {

	private Tile[] board; // Stores board info
	private boolean gameWon; // If there's a winning path
	private static final int SIZE = 5; // Size x Size board

	/**
	* Creates a SIZE * SIZE game board. Fills the board with random unique tiles.
	*/
	public Board() {
		board = new Tile[SIZE * SIZE];
		gameWon = false;

		Set<Integer> numbers = new HashSet<Integer>();
		Random rand = new Random();
		while(numbers.size() < board.length) {
			numbers.add(rand.nextInt(100) + 1);
		}

		int index = 0;
		for(Integer num : numbers) {
			board[index] = new Tile(num);
			index++;
		}
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