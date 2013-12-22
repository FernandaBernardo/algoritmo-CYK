/*
 * Fernanda Moraes Bernardo - 7971991
 * Barbara Carvalho Campacci - 7972672
*/ 

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class glc {
	
	private static Leitura leitura;
	private static Tabela[] tabelas;
	private static String[][] regras;
	
	public static void main(String[] args) throws FileNotFoundException {
		File glc = new File("src/files/inp-glc.txt");
		File cadeias = new File("src/files/inp-cadeias.txt");
		leitura = new Leitura(glc, cadeias); //le arquivos para pegar as informações
		tabelas = new Tabela[leitura.getCadeias().length]; //monta a tabela com as informações necessárias
		criarTabelas();
		regras = leitura.getRegras(); //pega as regras que fizemos a leitura do arquivo
		
		//para cada uma das cadeias, monte a tabela
		for(int i = 0; i<tabelas.length; i++) {
			cyk(tabelas[i]);
		}
		
		//escrever no arquivo o status e a tabela
		escreveStatus();
		escreveTabela();
	}
	
	private static void escreveTabela() throws FileNotFoundException {
		System.setOut(new PrintStream(new FileOutputStream("src/files/out-tabela.txt")));
		
		System.out.println(tabelas.length);
		
		for (int k = 0; k < tabelas.length; k++) {
			System.out.println(tabelas[k].cadeia.replace("", " ").replaceFirst(" ", ""));
			if(!"&".equals(tabelas[k].cadeia)) { 
				for (int i = 1; i < tabelas[k].tabela.length; i++) {
					for (int j = i; j < tabelas[k].tabela.length; j++) {
						String aux = tabelas[k].tabela[i][j];
						System.out.println(i + " " + j + " " + aux.replaceFirst(" ", ""));
					}
				}
			}
		}
	}
	
	private static void escreveStatus() throws FileNotFoundException {
		System.setOut(new PrintStream(new FileOutputStream("src/files/out-status.txt")));
		for (int i = 0; i < tabelas.length; i++) {
			if (tabelas[i].aceita) System.out.print("1 ");
			else System.out.print("0 ");
		}
	}
	
	//cria as tabelas para todas as cadeias de entrada
	public static void criarTabelas() {
		String[] cadeias = leitura.getCadeias();
		for (int i = 0; i < cadeias.length; i++) {
			tabelas[i] = new Tabela(cadeias[i]);
		}
	}
	
	public static void cyk (Tabela tabela) {
		//se a cadeia for vazia e ela estiver nas regras, aceite
		if ("&".equals(tabela.cadeia) && cadeiaVaziaEhRegra()) {
			tabela.aceita = true;
			return;
		}
		
		int n = tabela.tabela.length;
		
		//preenche a diagonal principal da tabela
		for (int i = 1; i < n; i++) {
			String variavel = verificaMenorSubcadeia(tabela, i);
			if (variavel != null) tabela.tabela[i][i] = variavel;
		}
		
		//preenche o restante da tabela
		for (int tamanhoSubcadeia = 2; tamanhoSubcadeia < n; tamanhoSubcadeia++) { 
			for (int posInicial = 1; posInicial < n-tamanhoSubcadeia+1; posInicial++) {
				int posFinal = posInicial + tamanhoSubcadeia -1;
				for (int posDivisao = posInicial; posDivisao < posFinal	; posDivisao++) {
					String variavel = verificaSubcadeia(tabela, posInicial, posFinal, posDivisao);
					if (!tabela.tabela[posInicial][posFinal].contains(variavel))  {
						tabela.tabela[posInicial][posFinal] += variavel;
					}
				}
			}
		}
		verificaAceitacao(tabela);
	}
	
	//verifica se a regra aceita a cadeia
	private static void verificaAceitacao(Tabela tabela) {
		if(tabela.tabela[1][tabela.tabela.length-1].contains(regras[0][0])) tabela.aceita = true;
		else tabela.aceita = false;
	}
	
	private static String verificaMenorSubcadeia(Tabela tabela, int caracterCadeia) {
		String resp = "";
		for (int i = 0; i < regras.length; i++) {
			if (tabela.tabela[0][caracterCadeia].equals(regras[i][2])) {
				resp = resp + " " + regras[i][0];
			}
		}
		return resp;
	}
	
	public static String verificaSubcadeia(Tabela tabela, int posInicial, int posFinal, int posDivisao) {
		String resp = "";
		for (int i = 0; i < regras.length; i++) {
			String b = regras[i][2];
			String c = regras[i][3];
			
			boolean first = tabela.tabela[posInicial][posDivisao].contains(b);
			boolean second = tabela.tabela[posDivisao+1][posFinal].contains(c);
			if(first && second) {
				resp = resp + " " + regras[i][0];
			}
		}
		return resp;
	}
	
	public static boolean cadeiaVaziaEhRegra() {
		for (int i = 0; i < regras.length; i++) {
			if (("&".equals(regras[i][2]))) return true;
		}
		return false;
	}
}
