package br.com.palerique.programacaoParalelaMundoJ56;

public class QuickSort {

	private double[] vetor;
	private int inicio;
	private int fim;

	public QuickSort(double[] vetor, int inicio, int fim) {
		this.setVetor(vetor);
		this.setInicio(inicio);
		this.setFim(fim - 1);
	}

	public int particao() {
		double pivo = getVetor()[getFim()];

		int i = getInicio() - 1;

		for (int j = getInicio(); j < getFim(); j++) {
			if (getVetor()[j] <= pivo) {
				i++;
				troca(i, j);
			}
		}

		troca(i + 1, getFim());

		return i + 1;

	}

	private void troca(int i, int j) {
		double temp = getVetor()[i];
		getVetor()[i] = getVetor()[j];
		getVetor()[j] = temp;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFim() {
		return fim;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}

	public double[] getVetor() {
		return vetor;
	}

	public void setVetor(double[] vetor) {
		this.vetor = vetor;
	}

}
