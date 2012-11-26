package br.com.palerique.programacaoParalelaMundoJ56.javaAntesDo7;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.palerique.programacaoParalelaMundoJ56.QuickSort;

public class QSJava5 implements Callable<Void> {

	ExecutorService pool;
	QuickSort quickSort;

	public QSJava5(double[] vetor, int inicio, int fim, ExecutorService pool) {
		this.quickSort = new QuickSort(vetor, inicio, fim);
		this.pool = pool;
	}

	@Override
	public Void call() throws Exception {

		if (quickSort.getInicio() < quickSort.getFim()) {

			int meio = quickSort.particao();

			Future<?> f1 = pool.submit(new QSJava5(quickSort.getVetor(),
					quickSort.getInicio(), meio - 1, pool));

			Future<?> f2 = pool.submit(new QSJava5(quickSort.getVetor(),
					meio + 1, quickSort.getFim(), pool));

			f1.get();
			f2.get();
		}

		return null;
	}

	public static void main(String[] args) {

		Random random = new Random();
		int n = 2000000;
		//int n = 20000;

		double[] vetor = new double[n];

		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = random.nextDouble();
		}

		ExecutorService pool = Executors.newCachedThreadPool();

		long inicio = System.currentTimeMillis();

		Future<?> result = pool
				.submit(new QSJava5(vetor, 0, vetor.length, pool));
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		long fim = System.currentTimeMillis();

		System.out.println(fim - inicio);

		pool.shutdownNow();

	}
}
