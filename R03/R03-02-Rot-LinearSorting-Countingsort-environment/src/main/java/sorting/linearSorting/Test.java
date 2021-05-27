package sorting.linearSorting;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		Integer[] vetorTamanhoPar1 = new Integer[] { -45, -30, -7, -1, -1, -4, -29, -12, -26, -31, -1, -23, -15, -40, };
		Integer[] vetorTamanhoPar2 = new Integer[] { -45, -30, -7, -1, -1, -4, -29, -12, -26, -31, -1, -23, -15, -40, };
		//Integer[] vetorTamanhoPar1 = new Integer[] { 45, 30, 0, 7, 1, 0, -1, 4, 29, -12, 26, 31, 1, 23, -15, 40, };
		//Integer[] vetorTamanhoPar2 = new Integer[] { 45, 30, 0, 7, 1, 0, -1, 4, 29, -12, 26, 31, 1, 23, -15, 40, };
		
		//Integer[] vetorRepetido = new Integer[] { 4, 9, 99, 4, 0, 5, 1, 4 };
		
		//CountingSort cs = new CountingSort();
		ExtendedCountingSort ecs = new ExtendedCountingSort();
		//cs.sort(vetorTamanhoPar1, 2, 6);
		Arrays.sort(vetorTamanhoPar2);
		
		ecs.sort(vetorTamanhoPar1);
		String str1 = Arrays.toString(vetorTamanhoPar1);
		String str2 = Arrays.toString(vetorTamanhoPar2);
		
		System.out.println("CountingSort: " + str1);
		System.out.println("Arrays.sort:  " + str2);
		
		System.out.println(str1.equals(str2));
		
		
	}
}
