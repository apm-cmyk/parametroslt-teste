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
		
		/*Logica do projeto:
		 * 3 - Classes parametro (resistencia, reatanciaIndutiva e reatanciaCapacitiva)
		 * Posteriormente a gente pode pensar o que
		 * elas tem em comum e criar uma classe pai tipo paramentro
		 * 1 - Classe calcularParamento:
		 * Recebe qualquer tipo de parametro e tem 3 metodos principais
		 * herdados da interface procedimentos
		 * 1 - Main:
		 * Eu vou criar um objeto parametro para cada circuito
		 * colocar em um Array de objetos
		 * e chamar a classe calcularParametro para receber a saida
		 * 1 clasee Complexo:
		 * Classe utilitária criada para fazer opeação com numeros complexos
		 * 
		*/
		
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
