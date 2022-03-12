package entities;

import Jama.Matrix;

public class Resistencia {

	private double resistenciaFase;
	private double resistenciaParaRaio;
	private int qtParaRaio;
	private Matrix matrizRff;
	private Matrix matrizRrr;
	private Matrix matrizRfr;
	private Matrix matrizRrf;

	public Resistencia(double resistenciaFase, double resistenciaParaRaio, int qtParaRaio) {
		this.resistenciaFase = resistenciaFase;
		this.resistenciaParaRaio = resistenciaParaRaio;
		this.qtParaRaio = qtParaRaio;
		matrizResistenciaPropriaFase();
		matrizResistenciaPropriaParaRaio();
		matrizResistenciaMutuaFaseParaRaio();

	}

	public void matrizResistenciaPropriaFase() {

		double[][] valoresRff = { { resistenciaFase, 0, 0 }, { 0, resistenciaFase, 0 }, { 0, 0, resistenciaFase } };

		matrizRff = new Matrix(valoresRff);
	}

	public void matrizResistenciaPropriaParaRaio() {

		if (qtParaRaio == 1) {
			double[][] valoresRrr = { { resistenciaParaRaio } };
			matrizRrr = new Matrix(valoresRrr);
		} else {
			double[][] valoresRrr = { { resistenciaParaRaio, 0 }, { 0, resistenciaParaRaio } };
			matrizRrr = new Matrix(valoresRrr);
		}
	}

	public void matrizResistenciaMutuaFaseParaRaio() {

		if (qtParaRaio == 1) {
			double[][] valoresRfr = { { 0 }, { 0 }, { 0 } };
			matrizRfr = new Matrix(valoresRfr);
			matrizRrf = matrizRfr.transpose();
		}else {
			double[][] valoresRfr = { { 0 ,0}, { 0,0 }, { 0,0 } };
			matrizRfr = new Matrix(valoresRfr);
			matrizRrf = matrizRfr.transpose();
		}
	}

	public double getResistenciaFase() {
		return resistenciaFase;
	}

	public void setResistenciaFase(double resistenciaFase) {
		this.resistenciaFase = resistenciaFase;
	}

	public double getResistenciaParaRaio() {
		return resistenciaParaRaio;
	}

	public void setResistenciaParaRaio(double resistenciaParaRaio) {
		this.resistenciaParaRaio = resistenciaParaRaio;
	}

	public Matrix getMatrizRff() {
		return matrizRff;
	}

	public Matrix getMatrizRrr() {
		return matrizRrr;
	}

	public Matrix getMatrizRfr() {
		return matrizRfr;
	}

	public Matrix getMatrizRrf() {
		return matrizRrf;
	}
}