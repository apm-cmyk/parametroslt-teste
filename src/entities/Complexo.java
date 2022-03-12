package entities;

public class Complexo {


	private double real;
	private double real1;
	private double real2;
	private double imaginario;
	private double imaginario1;
	private double imaginario2;
	private Complexo[][] matrizComplexo;
	private Complexo[][] C;


	public Complexo(double real1, double img1, double real2, double img2) {
		this.real1 = real1;
		this.real2 = real2;
		this.imaginario1 = img1;
		this.imaginario2 = img2;
	}

	public Complexo(double real, double imaginario) {
		this.real = real;
		this.imaginario = imaginario;
	}

	public Complexo() {
	
	}

	public Complexo(Complexo elemento) {
		this.real = 0.0;
		this.imaginario = 0.0;
	}

	public Complexo produtoNumerosComplexos() {

		real = (real1 * real2) - (imaginario1 * imaginario2);
		imaginario = (real1 * imaginario2) + (imaginario1 * real2);

		return new Complexo(real, imaginario);
	}

	public void addElementoMatriz(Complexo elemento) {
		matrizComplexo = new Complexo[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrizComplexo[i][j] = elemento;

			}
		}

	}

	public Complexo[][] produtoMatrizesComplexas(Complexo[][] A, Complexo[][] B) {
		C = new Complexo[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				C[i][j] = new Complexo(0,0);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					
					//C[i][j] = new Complexo(1,2);
					C[i][j].setReal((A[i][k].getReal() * B[k][j].getReal())
							- A[i][k].getImaginario() * B[k][j].getImaginario());
					C[i][j].setImaginario((A[i][k].getReal() * B[k][j].getImaginario())
							+ (A[i][k].getImaginario() * B[k][j].getReal()));
		
				}
				
			}
		}
		return C;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginario() {
		return imaginario;
	}

	public void setImaginario(double imaginario) {
		this.imaginario = imaginario;
	}

	public Complexo[][] getMatrizComplexo() {
		return matrizComplexo;
	}

	public void setMatrizComplexo(Complexo[][] matrizComplexo) {
		this.matrizComplexo = matrizComplexo;
	}

	public Complexo[][] getC() {
		return C;
	}

	public void setC(Complexo[][] c) {
		C = c;

	}

	@Override
	public String toString() {
		return real + " + j " + imaginario;

	}

}
