public class SlingoMain {

	public static void main(String[] args) {
		Board b = new Board();
		b.print();

		int i = 0;
		while(!b.gameWon()) {
			System.out.println("Round number: " + (i + 1));
			b.play();
			b.print();
			System.out.println(b.gameWon());
			i++;
		}
	}

}