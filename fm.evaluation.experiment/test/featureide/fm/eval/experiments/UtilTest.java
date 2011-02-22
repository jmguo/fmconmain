package featureide.fm.eval.experiments;

import java.util.ArrayList;
import java.util.Random;

public class UtilTest {

	public static void main(String[] args) {
		Random rand = new Random();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			list.add(rand.nextInt(20));
		}
		for (int i = 0; i < 20; i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		int size = list.size();
		int high = size;
		for (int i = 0; i < high;) {
			int compound = list.get(i);
			if (compound > 5) {
				int temp = list.get(high - 1);
				list.set(high - 1, compound);
				list.set(i, temp);
				--high;
			} else {
				i++;
			}
		}

		System.out.println("high: " + high);
		for (int i = 0; i < high; i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		for (int i = high; i < size; i++) {
			System.out.print(list.get(i) + " ");
		}
	}

}
