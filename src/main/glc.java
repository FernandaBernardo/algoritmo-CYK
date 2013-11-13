package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

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
		for(int i = 0; i<tabelas.length; i++) {
			cyk(tabelas[i]);
			tabelas[i].imprimeTabela();
		}
	}

	public static void criarTabelas() {
		String[] cadeias = leitura.getCadeias();
		for (int i = 0; i < cadeias.length; i++) {
			tabelas[i] = new Tabela(cadeias[i]);
		}
	}

	public static void cyk (Tabela tabela) {
		if ("&".equals(tabela.cadeia) && cadeiaVaziaEhRegra()) tabela.aceita = true;
		
		int n = tabela.tabela.length;
		
		for (int i = 1; i < n; i++) {
			String variavel = verificaMenorSubcadeia(tabela, i);
			if (variavel != null) tabela.tabela[i][i] = variavel;
		}
		
		for (int tamanhoSubcadeia = 2; tamanhoSubcadeia < n; tamanhoSubcadeia++) {
			for (int posInicial = 1; posInicial < n-tamanhoSubcadeia+1; posInicial++) {
				int posFinal = posInicial + tamanhoSubcadeia -1;
				System.out.println("POSIÇÃO FINAL: " + posFinal);
				for (int posDivisao = posInicial; posDivisao < posFinal; posDivisao++) {
					String variavel = verificaSubcadeia(tabela, posInicial, posFinal, posDivisao);
					tabela.tabela[posInicial][posFinal] = variavel;
					System.out.println(posInicial + "  " + posFinal + "  " + posDivisao);
				}
			}
		}
		
		verificaAceitacao(tabela);
	}

	private static void verificaAceitacao(Tabela tabela) {
		
	}

	private static String verificaMenorSubcadeia(Tabela tabela, int caracterCadeia) {
		String resp = "";
		for (int i = 0; i < regras.length; i++) {
			if (tabela.tabela[0][caracterCadeia].equals(regras[i][2])) {
				resp = resp + regras[i][0];
			}
		}
		return resp;
	}

	public static String verificaSubcadeia(Tabela tabela, int posInicial, int posFinal, int posDivisao) {
		String resp = "";
		String[] B = (tabela.tabela[posInicial][posDivisao]).split(" ");
		String[] C = (tabela.tabela[posDivisao+1][posFinal]).split(" ");
		for (int i = 0; i < regras.length; i++) {
			
			for (int b = 0; b<B.length; b++) {
				for (int c = 0; c<C.length; c++) {
					String regra = "";
					for (int x = 2; x<4; x++) {
						regra = regra + "" +regras[i][x];
					}
					boolean first = regra.contains(B[b]);
					boolean second = regra.contains(C[c]);

					if(first && second) {
						if("".equals(resp)) resp = regras[i][0];
						else {
								resp = resp + " " + regras[i][0];								
						}
					}
				}
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
