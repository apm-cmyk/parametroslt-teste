package entities;

import java.util.List;

import Jama.Matrix;

public class CalcularParametro implements Procedimentos {

	private double frequencia;
	private List<Resistencia> resistencia;
	private Matrix matrizRff;
	private Matrix matrizRrf;
	private Matrix matrizRrr;
	private Matrix matrizRfr;
	private Matrix matrizEquivalente;
	private final double CORRECAO_SOLO = 0.000988;
	private Complexo complexo;
	private Complexo[][] matrizComplexo;
	private double real;
	private double imaginario;

	public CalcularParametro(double frequencia, List<Resistencia> resistencia) {
		this.frequencia = frequencia;
		this.resistencia = resistencia;
		if (resistencia.size() >= 2) {
			matrizCircuitoDuplo();
		} else {
			matrizCircuitoSimples();
		}

	}

	public void matrizCircuitoSimples() {
		matrizRff = resistencia.get(0).getMatrizRff();
		matrizRrr = resistencia.get(0).getMatrizRrr();
		matrizRfr = resistencia.get(0).getMatrizRfr();
		matrizRrf = resistencia.get(0).getMatrizRrf();
	}

	public void matrizCircuitoDuplo() {

		Matrix matrizRff1 = resistencia.get(0).getMatrizRff();
		Matrix matrizRrr1 = resistencia.get(0).getMatrizRrr();
		Matrix matrizRfr1 = resistencia.get(0).getMatrizRfr();
		Matrix matrizRrf1 = resistencia.get(0).getMatrizRrf();
		Matrix matrizRff2 = resistencia.get(1).getMatrizRff();
		Matrix matrizRrr2 = resistencia.get(1).getMatrizRrr();
		Matrix matrizRfr2 = resistencia.get(1).getMatrizRfr();
		Matrix matrizRrf2 = resistencia.get(1).getMatrizRrf();

		matrizRff = matrizRff1.plus(matrizRff2);
		matrizRrr = matrizRrr1.plus(matrizRrr2);
		matrizRfr = matrizRfr1.plus(matrizRfr2);
		matrizRrf = matrizRrf1.plus(matrizRrf2);

	}

	@Override
	public void efeitoSoloSimplificado() {
		Matrix matrizFator1 = fatorCorrecao(matrizRff);
		Matrix matrizFator2 = fatorCorrecao(matrizRrr);
		Matrix matrizFator3 = fatorCorrecao(matrizRfr);
		Matrix matrizFator4 = fatorCorrecao(matrizRrf);

		matrizRff = matrizRff.plus(matrizFator1);
		matrizRrr = matrizRrr.plus(matrizFator2);
		matrizRfr = matrizRfr.plus(matrizFator3);
		matrizRrf = matrizRrf.plus(matrizFator4);

		System.out.println("TESTE RESISTENCIA");
		System.out.println("MATRIZ RFF");
		matrizRff.print(6, 5);
		System.out.println("MATRIZ RRF");
		matrizRrf.print(6, 5);
		System.out.println("MATRIZ RRR");
		matrizRrr.print(6, 5);
		System.out.println("MATRIZ RFR");
		matrizRfr.print(6, 5);
		reducaoEquivalenteParaRaio();
	}

	@Override
	public Matrix reducaoEquivalenteParaRaio() {
		System.out.println("Reduzida equivalente");

		matrizEquivalente = matrizRff
				.minus(produtoMatrizes(produtoMatrizes(matrizRfr, matrizRrr.inverse()), matrizRrf));

		matrizEquivalente.print(6, 5);
		return matrizEquivalente;
	}

	@Override
	public String[][] componenteSimetrico() {

		Complexo a2 = new Complexo(-0.5, 0.866, -0.5, 0.866);
		Complexo a = new Complexo(-0.5, 0.866);
		Complexo b = new Complexo(1, 0);
		a2 = a2.produtoNumerosComplexos();
		Complexo[][] matrizTransformação = { { b, b, b }, { b, a2, a }, { b, a, a2 } };

		// VERIFICAR SE A TRANSPOSTA ESTA CORRETA!!!
		Complexo[][] matrizTransformaçãoTransposta = { { b, b, b }, { b, a, a2 }, { b, a2, a } };
		double[][] matriz1 = matrizEquivalente.getArray();
		double elementoMatrizEquivalente;
		complexo = new Complexo();
		for (int i = 0; i < matrizEquivalente.getRowDimension(); i++) {
			for (int j = 0; j < matrizEquivalente.getColumnDimension(); j++) {
				elementoMatrizEquivalente = matriz1[i][j];
				complexo.addElementoMatriz(new Complexo(elementoMatrizEquivalente, 0));
			}
		}

		matrizComplexo = complexo.produtoMatrizesComplexas(complexo.getMatrizComplexo(), matrizTransformação);
		matrizComplexo = complexo.produtoMatrizesComplexas(matrizComplexo, matrizTransformaçãoTransposta);

		String[][] matrizComponenteSimetrico;
		matrizComponenteSimetrico = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				real = matrizComplexo[i][j].getReal();
				imaginario = matrizComplexo[i][j].getImaginario();

				matrizComponenteSimetrico[i][j] = real + " j " + imaginario + " ";

			}
		}

		System.out.println("Matriz Componente Simetrico");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(matrizComponenteSimetrico[i][j]);
			}
			System.out.println();
		}

		return matrizComponenteSimetrico;
	}

	private Matrix fatorCorrecao(Matrix matriz) {

		double fator = CORRECAO_SOLO * frequencia;

		double[][] mat = new double[matriz.getRowDimension()][matriz.getColumnDimension()];

		for (int i = 0; i < matriz.getRowDimension(); i++) {
			for (int j = 0; j < matriz.getColumnDimension(); j++) {
				mat[i][j] = fator;
			}
		}
		Matrix mat1 = new Matrix(mat);
		return mat1;
	}

	private Matrix produtoMatrizes(Matrix a, Matrix b) {

		double[][] A = a.getArray();
		double[][] B = b.getArray();

		int i;
		int j;
		int k;

		double C[][] = new double[a.getRowDimension()][b.getColumnDimension()];

		for (i = 0; i < a.getRowDimension(); i++) {
			for (j = 0; j < b.getColumnDimension(); j++) {
				for (k = 0; k < a.getColumnDimension(); k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		Matrix matrizResultante = new Matrix(C);
		return matrizResultante;

	}

}
