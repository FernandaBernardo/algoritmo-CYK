package main;

public class Tabela {
	
	String cadeia;
	String[][] tabela;
	boolean aceita;
	
	public Tabela(String cadeia) {
		this.cadeia = cadeia;
		tratarCadeia();
		tabela = new String [this.cadeia.length()+1][this.cadeia.length()+1];
		preencheCadeiaNaTabela();
	}

	private void tratarCadeia() {
		this.cadeia = cadeia.replaceAll(" ", "");
	}

	private void preencheCadeiaNaTabela() {
		for (int i = 1; i < tabela.length; i++) {
			tabela[0][i] = cadeia.charAt(i-1) + "";
			tabela[i][0] = cadeia.charAt(i-1) + "";
		}
		
		for (int i = 0; i < tabela.length; i++) {
			for (int j = 0; j < tabela.length; j++) {
				System.out.print(tabela[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
