package main;

public class Tabela {
	
	String cadeia;
	String[][] tabela;
	boolean aceita;
	
	public Tabela(String cadeia) {
		this.cadeia = cadeia;
		tratarCadeia();
		//inicializa a tabela com o tamanho da cadeia
		tabela = new String [this.cadeia.length()+1][this.cadeia.length()+1]; 
		
		preencheTabela();
		preencheCadeiaNaTabela();
	}

	//inicializa toda a tabela com string vazia
	private void preencheTabela() {
		for (int i = 0; i < tabela.length; i++) {
			for (int j = 0; j < tabela[0].length; j++) {
				tabela[i][j] = "";
			}
		}
	}

	//deixa a cadeia sem nenhum espaço
	private void tratarCadeia() {
		this.cadeia = cadeia.replaceAll(" ", "");
	}

	//coloca a cadeia na primeira linha e coluna da tabela
	private void preencheCadeiaNaTabela() {
		for (int i = 1; i < tabela.length; i++) {
			tabela[0][i] = cadeia.charAt(i-1) + "";
			tabela[i][0] = cadeia.charAt(i-1) + "";
		}
	}
}
