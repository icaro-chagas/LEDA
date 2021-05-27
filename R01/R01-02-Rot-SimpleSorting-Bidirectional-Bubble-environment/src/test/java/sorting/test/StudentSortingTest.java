package sorting.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.simpleSorting.BubbleSort;
import sorting.simpleSorting.InsertionSort;
import sorting.simpleSorting.SelectionSort;
import sorting.variationsOfBubblesort.BidirectionalBubbleSort;
import sorting.variationsOfBubblesort.RecursiveBubbleSort;
import sorting.variationsOfSelectionsort.RecursiveSelectionSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		getImplementation();
	}

	// // METODOS AUXILIARES DA INICIALIZACAO
	/**
	 * Metodo que inicializa a implementacao a ser testada com a implementacao
	 * do aluno
	 */
	private void getImplementation() {
		// O aluno deve instanciar sua implementacao abaixo ao inves de
		// null

		//this.implementation = new BubbleSort<Integer>();
		//this.implementation = new InsertionSort<Integer>();
		//this.implementation = new SelectionSort<Integer>();
		//this.implementation = new BidirectionalBubbleSort<Integer>();
		this.implementation = new RecursiveBubbleSort<Integer>();
		//this.implementation = new RecursiveSelectionSort<Integer>();
		//Assert.fail("Implementation not provided");
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZACAO

	// METODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	public void genericTestArrayFraction(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);
		}
		implementation.sort(array, leftIndex, rightIndex);
		Arrays.sort(copy1, leftIndex, rightIndex + 1);
		Assert.assertArrayEquals(copy1, array);

	}

	@Test
	public void testSortArrayFraction01() {
		for (int i = 0; i <= vetorTamPar.length - 1; i++) {
			for (int j = i; j <= vetorTamPar.length - 1; j++) {
				//System.out.println("i = " + i + ", j = " + j);
				//System.out.println(Arrays.toString(vetorTamPar));
				genericTestArrayFraction(vetorTamPar, i, j);
				//System.out.println(Arrays.toString(vetorTamPar) + "\n");
				populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 });
			}
		}
	}

	@Test
	public void testSortArrayFraction02() {
		for (int i = 0; i <= vetorTamImpar.length - 1; i++) {
			for (int j = i; j <= vetorTamImpar.length - 1; j++) {
				//System.out.println("i = " + i + ", j = " + j);
				//System.out.println(Arrays.toString(vetorTamImpar));
				genericTestArrayFraction(vetorTamImpar, i, j);
				//System.out.println(Arrays.toString(vetorTamImpar) + "\n");
				populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 });
			}
		}
	}

	@Test
	public void testSortArrayFraction03() {
		for (int i = 0; i <= vetorValoresRepetidos.length - 1; i++) {
			for (int j = i; j <= vetorValoresRepetidos.length - 1; j++) {
				//System.out.println("i = " + i + ", j = " + j);
				//System.out.println(Arrays.toString(vetorValoresRepetidos));
				genericTestArrayFraction(vetorValoresRepetidos, i, j);
				//System.out.println(Arrays.toString(vetorValoresRepetidos) + "\n");
				populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
			}
		}
	}

	@Test
	public void testSortArrayFraction04() {
		for (int i = 0; i <= vetorValoresIguais.length - 1; i++) {
			for (int j = i; j <= vetorValoresIguais.length - 1; j++) {
				//System.out.println("i = " + i + ", j = " + j);
				//System.out.println(Arrays.toString(vetorValoresIguais));
				genericTestArrayFraction(vetorValoresIguais, i, j);
				//System.out.println(Arrays.toString(vetorValoresIguais) + "\n");
				populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });
			}
		}
	}

	@Test
	public void testSortArrayFraction01WithInvalidInput() {
		//-------------------------------------------------------------------------
		Integer[] arrayTeste1 = Arrays.copyOf(vetorTamPar, vetorTamPar.length);

		implementation.sort(null,2,5);
		implementation.sort(vetorTamPar,5, 2);
		implementation.sort(vetorTamPar,-1, 6);
		implementation.sort(vetorTamPar,3, 10);
		implementation.sort(vetorTamPar,3, 3);

		Assert.assertArrayEquals(arrayTeste1, vetorTamPar);
		//-------------------------------------------------------------------------

		Integer[] arrayTeste2 = Arrays.copyOf(vetorTamImpar, vetorTamImpar.length);

		implementation.sort(null,2,5);
		implementation.sort(vetorTamImpar,5, 2);
		implementation.sort(vetorTamImpar,-1, 6);
		implementation.sort(vetorTamImpar,3, 11);
		implementation.sort(vetorTamImpar,3, 3);

		Assert.assertArrayEquals(arrayTeste2, vetorTamImpar);
		//-------------------------------------------------------------------------

		Integer[] arrayTeste3 = Arrays.copyOf(vetorValoresRepetidos, vetorValoresRepetidos.length);

		implementation.sort(null,2,5);
		implementation.sort(vetorValoresRepetidos,5, 2);
		implementation.sort(vetorValoresRepetidos,-1, 6);
		implementation.sort(vetorValoresRepetidos,3, 8);
		implementation.sort(vetorValoresRepetidos,3, 3);

		Assert.assertArrayEquals(arrayTeste3, vetorValoresRepetidos);
		//-------------------------------------------------------------------------

		Integer[] arrayTeste4 = Arrays.copyOf(vetorValoresIguais, vetorValoresIguais.length);

		implementation.sort(null,2,5);
		implementation.sort(vetorValoresIguais,5, 2);
		implementation.sort(vetorValoresIguais,-1, 6);
		implementation.sort(vetorValoresIguais,3, 6);
		implementation.sort(vetorValoresIguais,3, 3);

		Assert.assertArrayEquals(arrayTeste4, vetorValoresIguais);
	}

	// METODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENACAO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDACO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS METODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
}