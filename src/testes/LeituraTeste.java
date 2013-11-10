package testes;

import java.io.File;
import java.io.FileNotFoundException;

import main.Leitura;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeituraTeste {
	
	private Leitura leitura;

	@Before
	public void setup () throws FileNotFoundException {
		File glc = new File("src/files/inp-glc.txt");
		File cadeias = new File("src/files/inp-cadeias.txt");
		
		leitura = new Leitura(glc, cadeias);
	}
	
	@Test
	public void verificando_variaveis() {
		String[] variaveis = leitura.getVariaveis();
		Assert.assertEquals(variaveis[0], "S0");
		Assert.assertEquals(variaveis[1], "S");
		Assert.assertEquals(variaveis[2], "T");
		Assert.assertEquals(variaveis[3], "U");
		Assert.assertEquals(variaveis[4], "A");
		Assert.assertEquals(variaveis[5], "B");
	}
	
	@Test
	public void verificando_terminais() {
		String[] terminais = leitura.getTerminais();
		Assert.assertEquals(terminais[0], "a");
		Assert.assertEquals(terminais[1], "b");
	}
	
	@Test
	public void verificando_regras() {
		String[] regras = leitura.getRegras();
		Assert.assertEquals(regras[0], "S0 > &");
		Assert.assertEquals(regras[1], "S0 > A T");
		Assert.assertEquals(regras[regras.length-1], "B > b");
	}
	
	@Test
	public void verificando_cadeia() {
		String[] cadeias = leitura.getCadeias();
		Assert.assertEquals(cadeias[0], "a b a a b b");
	}
}
