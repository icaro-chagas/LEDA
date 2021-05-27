package problems.test;

import java.util.Arrays;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import orderStatistic.KLargestOrderStatisticsImpl;

public class TestKLargestOrderStatisticsImpl {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] vetorNegativo;
	private Integer[] vetorNaoPositivo;

	KLargestOrderStatisticsImpl<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });
		
		populaVetorNegativo(new Integer[] { -30, -28, -7, -29, -11, -26, -4, -22, -23,
				-31 });
		
		populaVetorNaoPositivo(new Integer[] { 0 , -6, -41, -32, -7, -26, -4, -37, -49, 0, -11, 
				-18, -36 });

		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		implementation = new KLargestOrderStatisticsImpl<>();

	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
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

	public void genericTest(Integer[] array, int k) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		
		if (k < 1 || k > array.length) {
			/*System.out.println("k: " + k);
			System.out.println("orderStatistics: " + implementation.orderStatistics(array, k) + "\n");*/
			Assert.assertNull(implementation.orderStatistics(array, k));
		}
		else {
			Arrays.sort(copy1);
			/*System.out.println("k: " + k);
			System.out.println("Teste:       " + copy1[k-1]);
			System.out.println("orderStatistics: " + implementation.orderStatistics(array, k) + "\n");*/
			
			Assert.assertEquals(copy1[k-1], implementation.orderStatistics(array, k));
		}
	}
	
	public void genericTest2(Integer[] array, int k) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		
		if (k < 1 || k > array.length) {
			System.out.println("k: " + k);
			System.out.println("getKLargest: " + Arrays.toString(implementation.getKLargest(array, k)) + "\n");
			Assert.assertEquals("[]", Arrays.toString(implementation.getKLargest(array, k)));
		}
		else {
			Arrays.sort(copy1);
			Integer[] copy2 = Arrays.copyOfRange(copy1, copy1.length-k, copy1.length);			
			System.out.println("k: " + k);
			System.out.println("Teste:       " + Arrays.toString(copy2));
			System.out.println("getKLargest: " + Arrays.toString(implementation.getKLargest(array, k)) + "\n");		
			Assert.assertEquals(Arrays.toString(copy2), Arrays.toString(implementation.getKLargest(array, k)));
		}
	}
	

	@Test
	public void testOrderStatistics01() {
		/*System.out.println("----------");
		System.out.println("testSort01");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorTamPar, vetorTamPar.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorTamPar.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorTamPar, i);
		}
	}

	@Test
	public void testOrderStatistics02() {
		/*System.out.println("----------");
		System.out.println("testSort02");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorTamImpar, vetorTamImpar.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorTamImpar.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorTamImpar, i);
		}
	}

	@Test
	public void testOrderStatistics03() {
		/*System.out.println("----------");
		System.out.println("testSort03");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorVazio, vetorVazio.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorVazio.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorVazio, i);
		}
	}

	@Test
	public void testOrderStatistics04() {
		/*System.out.println("----------");
		System.out.println("testSort04");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorValoresIguais, vetorValoresIguais.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorValoresIguais.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorValoresIguais, i);
		}
	}

	@Test
	public void testOrderStatistics05() {
		/*System.out.println("----------");
		System.out.println("testSort05");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorValoresRepetidos, vetorValoresRepetidos.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorValoresRepetidos.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorValoresRepetidos, i);
		}
	}
	
	@Test
	public void testOrderStatistics06() {
		/*System.out.println("----------");
		System.out.println("testSort06");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorNegativo, vetorNegativo.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorNegativo.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorNegativo, i);
		}
	}
	
	@Test
	public void testOrderStatistics07() {
		/*System.out.println("----------");
		System.out.println("testSort06");
		System.out.println("----------\n");
		Integer[] copy = Arrays.copyOf(vetorNaoPositivo, vetorNaoPositivo.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorNaoPositivo.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest(vetorNaoPositivo, i);
		}
	}
	
	@Test
	public void testGetKLargest01() {
		Integer[] copy = Arrays.copyOf(vetorTamPar, vetorTamPar.length);
		Arrays.sort(copy);
		for(int i = -2; i <= vetorTamPar.length + 2; i++) {
			System.out.println(Arrays.toString(copy));
			genericTest2(vetorTamPar, i);
		}
	}
	
	@Test
	public void testGetKLargest02() {
		/*Integer[] copy = Arrays.copyOf(vetorTamImpar, vetorTamImpar.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorTamImpar.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorTamImpar, i);
		}
	}
	
	@Test
	public void testGetKLargest03() {
		/*Integer[] copy = Arrays.copyOf(vetorVazio, vetorVazio.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorVazio.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorVazio, i);
		}
	}
	
	@Test
	public void testGetKLargest04() {
		/*Integer[] copy = Arrays.copyOf(vetorValoresRepetidos, vetorValoresRepetidos.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorValoresRepetidos.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorValoresRepetidos, i);
		}
	}
	
	@Test
	public void testGetKLargest05() {
		/*Integer[] copy = Arrays.copyOf(vetorValoresIguais, vetorValoresIguais.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorValoresIguais.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorValoresIguais, i);
		}
	}
	
	@Test
	public void testGetKLargest06() {
		/*Integer[] copy = Arrays.copyOf(vetorNegativo, vetorNegativo.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorNegativo.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorNegativo, i);
		}
	}
	
	@Test
	public void testGetKLargest07() {
		/*Integer[] copy = Arrays.copyOf(vetorNegativo, vetorNegativo.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorNegativo.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorNegativo, i);
		}
	}
	
	@Test
	public void testGetKLargest08() {
		/*Integer[] copy = Arrays.copyOf(vetorNaoPositivo, vetorNaoPositivo.length);
		Arrays.sort(copy);*/
		for(int i = -2; i <= vetorNaoPositivo.length + 2; i++) {
			//System.out.println(Arrays.toString(copy));
			genericTest2(vetorNaoPositivo, i);
		}
	}
	
}


