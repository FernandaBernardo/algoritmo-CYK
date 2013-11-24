package main;

public class Tabela {
	
	String cadeia;
	String[][] tabela;
	boolean aceita;
	
	public Tabela(String cadeia) {
		this.cadeia = cadeia;
		tratarCadeia();
		tabela = new String [this.cadeia.length()+1][this.cadeia.length()+1];
		
		preencheTabela();
		preencheCadeiaNaTabela();
	}

	private void preencheTabela() {
		for (int i = 0; i < tabela.length; i++) {
			for (int j = 0; j < tabela[0].length; j++) {
				tabela[i][j] = "";
			}
		}
	}

	private void tratarCadeia() {
		this.cadeia = cadeia.replaceAll(" ", "");
	}

	private void preencheCadeiaNaTabela() {
		for (int i = 1; i < tabela.length; i++) {
			tabela[0][i] = cadeia.charAt(i-1) + "";
			tabela[i][0] = cadeia.charAt(i-1) + "";
		}
	}

	public void imprimeTabela() {
		System.out.println("Tabela:");
		for (int i = 1; i < tabela.length; i++) {
			for (int j = i; j < tabela.length; j++) {
				System.out.println("["+i+","+j+"] " + tabela[i][j]);
			}
		}
		System.out.println("\nAceita: " +aceita+"\n");
	}
}
