package entities;

import Jama.Matrix;

public interface Procedimentos {
	

	public void efeitoSoloSimplificado();
	public Matrix reducaoEquivalenteParaRaio();
	public String[][] componenteSimetrico();


}
