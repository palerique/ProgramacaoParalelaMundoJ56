package br.com.palerique.programacaoParalelaMundoJ56.aposJava7;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import br.com.palerique.programacaoParalelaMundoJ56.QuickSort;

public class QSJava7 extends RecursiveAction {

	private static final long serialVersionUID = 2600908705021217116L;

	QuickSort quickSort;

	public QSJava7(double[] vetor, int inicio, int fim) {
		this.quickSort = new QuickSort(vetor, inicio, fim);
	}

	public static void main(String[] args) {

		Random random = new Random();
		int n = 2000000;
		// int n = 20000;

		double[] vetor = new double[n];

		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = random.nextDouble();
		}

		ForkJoinPool poolForkJoin = new ForkJoinPool();

		long inicio = System.currentTimeMillis();

		poolForkJoin.invoke(new QSJava7(vetor, 0, vetor.length));

		long fim = System.currentTimeMillis();

		System.out.println(fim - inicio);

	}

	@Override
	protected void compute() {
		if (quickSort.getInicio() < quickSort.getFim()) {

			int meio = quickSort.particao();

			invokeAll(new QSJava7(quickSort.getVetor(), quickSort.getInicio(),
					meio - 1),
					new QSJava7(quickSort.getVetor(), quickSort.getInicio(),
							meio + 1));

		}
	}
}
