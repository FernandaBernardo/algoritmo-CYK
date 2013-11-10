package main;

import java.io.File;
import java.io.FileNotFoundException;

public class glc {

	private static Leitura leitura;
	private static Tabela[] tabelas;
	private static String[][] regras;
	
	public static void main(String[] args) throws FileNotFoundException {
		File glc = new File("src/files/inp-glc.txt");
		File cadeias = new File("src/files/inp-cadeias.txt");
		leitura = new Leitura(glc, cadeias);
		tabelas = new Tabela[leitura.getCadeias().length];
		criarTabelas();
		regras = leitura.getRegras();
		for(int i = 0; i<tabelas.length; i++) cyk(tabelas[i]);
	}

	public static void criarTabelas() {
		String[] cadeias = leitura.getCadeias();
		for (int i = 0; i < cadeias.length; i++) {
			tabelas[i] = new Tabela(cadeias[i]);
		}
	}

	public static void cyk (Tabela tabela) {
		if (cadeiaVaziaEhRegra()) {
			tabela.aceita = true;
			return;
		}
		int n = tabela.tabela.length;
		
		for (int i = 1; i < n; i++) {
			String variavel = verificaMenorSubcadeia(tabela, i);
			if (variavel != null) tabela.tabela[i][i] = variavel;
		}
		
		for (int tamanhoSubcadeia = 2; tamanhoSubcadeia < n; tamanhoSubcadeia++) {
			for (int posInicial = 1; posInicial < n-tamanhoSubcadeia+1; posInicial++) {
				int posFinal = posInicial + tamanhoSubcadeia - 1;
				for (int posDivisao = posInicial; posDivisao < posFinal-1; posDivisao++) {
					String variavel = verificaSubcadeia(tabela, posInicial, posFinal, posDivisao);
					if(variavel != null) tabela.tabela[posInicial][posFinal] = variavel;
				}
			}
		}
		verificaAceitacao(tabela);
	}

	private static void verificaAceitacao(Tabela tabela) {
		
	}

	private static String verificaMenorSubcadeia(Tabela tabela, int i) {

		return null;
	}

	public static String verificaSubcadeia(Tabela tabela, int posInicial, int posFinal, int posDivisao) {

		return null;
	}

	public static boolean cadeiaVaziaEhRegra() {
		for (int i = 0; i < regras.length; i++) {
			if (("&".equals(regras[i][2]))) return true;
		}
		return false;
	}
}
