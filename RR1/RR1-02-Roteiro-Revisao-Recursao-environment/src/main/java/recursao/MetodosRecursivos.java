package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;

		if (array.length > 0) {
			return calcularSomaArray(array, 0);
		}
		return result;
	}

	public int calcularSomaArray(int[] array, int indice){
		int result = 0;

		if (indice == array.length-1) {
			result = array[indice];
		}
		else {
			result = array[indice] + calcularSomaArray(array, indice+1);
		}
		return result;
	}
	public long calcularFatorial(int n) {
		long result = 1;
		if (n == 0) {
		}
		else {
			result = n * calcularFatorial(n - 1);
		}
		System.out.println(n + "! = " + result);

		return result;
	}

	public int calcularFibonacci(int n) {
		int result = n;
		if (n < 2) {
		}
		else {
			result = calcularFibonacci(n-1) + calcularFibonacci(n-2);
		}

		return result;
	}

	public int countNotNull(Object[] array) {
		int result = 0;

		if (array.length > 0) {
			return countNotNull(array, 0);
		}

		return result;
	}

	public int countNotNull(Object[] array, int indice) {

		int cont = 0;
		if (indice == array.length-1) {
			if(array[indice] != null) {
				cont = 1;
			}
		}
		else {
			if (array[indice] != null) {
				cont = 1;
			}
			cont = cont + countNotNull(array, indice+1);
		}

		return cont;
	}

	public long potenciaDe2(int expoente) {
		long result = 2;

		if (expoente == 1) {
		}
		else {
			result = result	* potenciaDe2(expoente - 1);
		}


		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = termoInicial;

		if (n == 1) {
		}
		else {
			result = razao + progressaoAritmetica(termoInicial, razao, n-1);
		}

		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = termoInicial;

		if (n == 1) {
		}
		else {
			result = razao * progressaoGeometrica(termoInicial, razao, n-1);
		}

		return result;
	}
}
