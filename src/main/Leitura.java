package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Leitura {
	
	private int [] numeros; //número de [0] variaveis, [1] simbolos terminais, [2] regras de substituicao
	private String [] variaveis; //[0] eh a variavel inicial
	private String [] terminais;
	private String [][] regras;
	private String [] cadeias;
	private Scanner sc;

	public Leitura (File fileEspec, File fileCadeias) throws FileNotFoundException {
		sc = new Scanner(fileEspec);
		leituraEspec();
		sc = new Scanner(fileCadeias);
		leituraCadeia();
	}

	public void leituraEspec () {
		//lendo primeira linha do arquivo
		numeros = new int [3];
		for(int i = 0; i<3; i++) {
			numeros[i] = sc.nextInt();
		}
		
		//lendo a segunda linha, que contem todas as variaveis
		variaveis = new String [numeros[0]];
		for (int i = 0; i<variaveis.length; i++) {
			variaveis[i] = sc.next();
		}
		
		//lendo a terceira linha, que contem os terminais
		terminais = new String[numeros[1]];
		for (int i = 0; i<terminais.length; i++) {
			terminais[i] = sc.next();
		}
		
		sc.nextLine(); //apenas para conseguir ir para a próxima linha
		
		//lendo as proximas linhas, que contem as regras de substituicao
		String[] regrasAux = new String [numeros[2]];
		regras = new String [regrasAux.length][4];
		
		for (int i = 0; i < regras.length; i++) {
			for (int j = 0; j < regras[0].length; j++) {
				regras[i][j] = "";
			}
		}
		
		for (int i = 0; i < regrasAux.length; i++) {
			regrasAux[i] = sc.nextLine();
		}
		tratamentoRegras(regrasAux);
	}

	private void leituraCadeia() {
		int numCadeias = sc.nextInt();
		
		sc.nextLine(); //apenas para conseguir ir para a próxima linha
		
		cadeias = new String[numCadeias];
		for (int i = 0; i < cadeias.length; i++) {
			cadeias[i] = sc.nextLine();
		}
	}
	
	private void tratamentoRegras (String[] regrasAux) {
		for (int i = 0; i < regras.length; i++) {
			String[] aux = regrasAux[i].split(" "); 
			for (int j = 0; j<aux.length; j++) regras[i][j] = aux[j]; 
		}
	}
	
	public String[] getVariaveis() {
		return variaveis;
	}
	
	public String[] getTerminais() {
		return terminais;
	}
	
	public String[][] getRegras() {
		return regras;
	}
	
	public String[] getCadeias() {
		return cadeias;
	}
}
