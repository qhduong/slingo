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
			System.out.print("Give me an x and y: ");
			x = console.nextInt();
			y = console.nextInt();
			if(x == -1 || y == -1) {
				break;
			}
			System.out.println();
			System.out.println(b.check(x, y));
		}
	}
}