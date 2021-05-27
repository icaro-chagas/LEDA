package problems.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import problems.Floor;
import problems.FloorBinarySearchImpl;

public class TestFloorBinarySearchImpl {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorNegativo;
	private Integer[] vetorNaoPositivo;

	Floor implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		
		populaVetorNegativo(new Integer[] { -30, -28, -7, -29, -11, -26, -4, -22, -23,
				-31 });
		
		populaVetorNaoPositivo(new Integer[] { 0 , -6, -41, -32, -7, -26, -4, -37, -49, -11, 
				-18, -36 });

		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {

		implementation = new FloorBinarySearchImpl();

	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	public void populaVetorNegativo(Integer[] arrayPadrao) {
		this.vetorNegativo = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	public void populaVetorNaoPositivo(Integer[] arrayPadrao) {
		this.vetorNaoPositivo = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		
		Integer[] copy1 = {};
		if(array.length > 0) {
			copy1 = Arrays.copyOf(array, array.length);
			Arrays.sort(copy1);
		
			for (int i = copy1[0] - 5; i < copy1[0]; i++) {
				Integer floor = implementation.floor(array, i);
				/*System.out.println(Arrays.toString(copy1));
				System.out.println("value: " + i);
				System.out.println("floor: " + floor + "\n");*/
				Assert.assertNull(floor);
				//assertNull(floor);
			}
			
			for (int i = 0; i < copy1.length; i++) {
				Integer floor = implementation.floor(array, array[i]);
				
				/*System.out.println(Arrays.toString(copy1));
				System.out.println("value: " + array[i]);
				System.out.println("floor: " + floor + "\n");*/
				Assert.assertEquals(floor, array[i]);
				//assertEquals(floor, array[i]);
				
				
				if (i < copy1.length -1) {
					int a = copy1[i] + 1;
					while (a < copy1[i+1]) {
						
						Integer floor2 = implementation.floor(array, a);
						
						/*System.out.println(Arrays.toString(copy1));
						System.out.println("value: " + a);
						System.out.println("floor: " + floor2 + "\n");*/
						Assert.assertEquals(floor2, copy1[i]);
						//assertEquals(floor2, copy1[i]);
						a++;
					}
				}
			}
			
			for (int i = copy1[array.length-1] + 1; i <= copy1[copy1.length-1] + 5; i++) {
				Integer floor = implementation.floor(array, i);
				
				/*System.out.println(Arrays.toString(copy1));
				System.out.println("value: " + i);
				System.out.println("floor: " + floor + "\n");*/
				Assert.assertEquals(copy1[copy1.length-1], floor);
				//assertEquals(copy1[copy1.length-1], floor);
			}
		}
		else {
			Assert.assertNull(implementation.floor(array, null));
			//assertNull(implementation.floor(array, null));
		}
		
	}
	
	@Test
	public void testFloor1() {
		genericTest(vetorTamPar);
	}
	
	@Test
	public void testFloor2() {
		genericTest(vetorTamImpar);
	}
	
	@Test
	public void testFloor3() {
		genericTest(vetorVazio);
	}
	
	@Test
	public void testFloor4() {
		genericTest(vetorNegativo);
	}
	
	@Test
	public void testFloor5() {
		genericTest(vetorNaoPositivo);
	}
}

