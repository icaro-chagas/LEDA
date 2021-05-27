package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.linearSorting.CountingSort;
import sorting.linearSorting.ExtendedCountingSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] vetorNegativo;
	private Integer[] vetorNaoPositivo;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
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
		// TODO O aluno deve instanciar sua implementação abaixo ao invés de
		// null
		//this.implementation = new CountingSort();
		this.implementation = new ExtendedCountingSort();
		//Assert.fail("Implementation not provided");
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
	public void testSortArrayFraction05() {
		if (this.implementation instanceof ExtendedCountingSort) {
			for (int i = 0; i <= vetorNegativo.length - 1; i++) {
				for (int j = i; j <= vetorNegativo.length - 1; j++) {
					//System.out.println("i = " + i + ", j = " + j);
					//System.out.println(Arrays.toString(vetorNegativo));
					genericTestArrayFraction(vetorNegativo, i, j);
					//System.out.println(Arrays.toString(vetorNegativo) + "\n");
					populaVetorNegativo(new Integer[] { -30, -28, -7, -29, -11, -26, -4, -22, -23, -31 });
				}
			}
		}
	}
	
	@Test
	public void testSortArrayFraction06() {
		if (this.implementation instanceof ExtendedCountingSort) {
			for (int i = 0; i <= vetorNaoPositivo.length - 1; i++) {
				for (int j = i; j <= vetorNaoPositivo.length - 1; j++) {
					//System.out.println("i = " + i + ", j = " + j);
					//System.out.println(Arrays.toString(vetorNaoPositivo));
					genericTestArrayFraction(vetorNaoPositivo, i, j);
					//System.out.println(Arrays.toString(vetorNaoPositivo) + "\n");
					populaVetorNaoPositivo(new Integer[] { 0 , -6, -41, -32, -7, -26, -4, -37, -49, 0, -11, -18, -36 });
				}
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

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
}