package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Leitura {
	
	private int [] numeros; //número de [0] variaveis, [1] simbolos terminais, [2] regras de substituicao
	private String [] variaveis; //[0] eh a variavel inicial
	private String [] terminais;
	private String [] regras;
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
		regras = new String [numeros[2]];
		for (int i = 0; i < regras.length; i++) {
			regras[i] = sc.nextLine();
		}
	}
	
	private void leituraCadeia() {
		int numCadeias = sc.nextInt();
		
		sc.nextLine(); //apenas para conseguir ir para a próxima linha
		
		cadeias = new String[numCadeias];
		for (int i = 0; i < cadeias.length; i++) {
			cadeias[i] = sc.nextLine();
		}
	}
	
	public String[] getVariaveis() {
		return variaveis;
	}
	
	public void setVariaveis(String[] variaveis) {
		this.variaveis = variaveis;
	}
	
	public String[] getTerminais() {
		return terminais;
	}
	
	public void setTerminais(String[] terminais) {
		this.terminais = terminais;
	}
	
	public String[] getRegras() {
		return regras;
	}
	
	public void setRegras(String[] regras) {
		this.regras = regras;
	}
	
	public String[] getCadeias() {
		return cadeias;
	}
	
	public void setCadeias(String[] cadeias) {
		this.cadeias = cadeias;
	}
}
