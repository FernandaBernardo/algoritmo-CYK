package main;

import java.io.File;
import java.io.FileNotFoundException;

public class glc {

	private static Leitura leitura;
	private static Tabela[] tabelas;
	
	public static void main(String[] args) throws FileNotFoundException {
		File glc = new File("src/files/inp-glc.txt");
		File cadeias = new File("src/files/inp-cadeias.txt");
		leitura = new Leitura(glc, cadeias);
		criarTabelas();
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
		
		for (int l = 2; l < n; l++) {
			for (int i = 1; i < n-l+1; i++) {
				int j = i + l - 1;
				for (int k = i; k < j-1; k++) {
					String variavel = verificaSubcadeia(tabela, i, j, k);
					if(variavel != null) tabela.tabela[i][j] = variavel;
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

	public static String verificaSubcadeia(Tabela tabela, int i, int j, int k) {

		return null;
	}

	public static boolean cadeiaVaziaEhRegra() {
		
		return false;
	}
}
