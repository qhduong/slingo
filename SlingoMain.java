import java.util.*;

public class SlingoMain {

	public static void main(String[] args) {
		Board b = new Board();
		b.print();

		for(int i = 0; i < 20; i++) {
			System.out.print("Round number: " + (i + 1));
			b.play();
			b.print();
		}

		int x = 0;
		int y = 0;
		Scanner console = new Scanner(System.in);
		for(;;) {
			System.out.print("Give me an x and a y, or -1 to quit: ");
			x = console.nextInt();
			if(x == -1) {
				break;
			}
			y = console.nextInt();
			System.out.println();
			System.out.println(solve(b, x, y));
		}
	}

  /**
	* Returns if there is a winning path on this board if the tile at this coordinate is found.
	*
	* @param b the board to check on.
	* @param x x coordinate of desired tile.
	* @param y y coordinate of desired tile.
	*/
	public static boolean solve(Board b, int x, int y) {
		Tile tile = b.find(x, y);
		boolean old = tile.status();
		tile.setStatus(true); // Sets this tile as found.
		boolean result = b.getStatus();

		tile.setStatus(old); // Undo status change.
		return result;
	}

}