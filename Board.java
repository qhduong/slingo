public class Board {

	private Tile[] board; // Stores board info
	private boolean gameWon; // If there's a winning path
	private static final int SIZE = 5; // Size x Size board

	/**
	* Creates a SIZE * SIZE game board.
	*/
	public Board() {
		board = new Tile[SIZE * SIZE];
		gameWon = false;
	}

}