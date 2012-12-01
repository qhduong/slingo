public class Tile {

	private int value;
	private boolean found;
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 100;

	/**
  * Constructs a tile with given value.
  *
  * @param     value the value of this tile.
  * @exception IllegalArgumentException if value is less than MIN_VALUE or greater than MAX_VALUE.
  */
	public Tile(int value) {
		if(value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		this.value = value;
		found = false;
	}

	/**
	* Returns the value of this tile.
	*/
	public int value() {
		return value;
	}

	/**
	* Mark this tile as found.
	*/
	public void found() {
		found = true;
	}

}