package application;

import java.util.ArrayList;
import java.util.List;

import entities.CalcularParametro;
import entities.Resistencia;

public class Main {

	public static void main(String[] args) {
		

		Resistencia resistenciaFase1 = new Resistencia(0.03215, 2.92,1);

		
		

		//Resistencia resistenciaFase2 = new Resistencia(300,100);
		
		List<Resistencia> resistencia = new ArrayList<>();
		resistencia.add(resistenciaFase1);
		//resistencia.add(resistenciaFase2);
		
		CalcularParametro parametro = new CalcularParametro(60,resistencia);
		
		parametro.efeitoSoloSimplificado();
		parametro.reducaoEquivalenteParaRaio();
		parametro.componenteSimetrico();
	
		//TESTE NUMERO COMPLEXO
		/*Complexo complexo = new Complexo(-0.5,0.866,-0.5,0.866);
		complexo.produtoNumerosComplexos();
		System.out.println(complexo.toString());
		*/

	}
}
