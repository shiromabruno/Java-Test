package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

// garante que os testes sejam executadas em ordem
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTestes {

	private static int contador = 0;
	
	// devera estar em ordem alfabetica
	@Test
	public void init() {
		contador++;
	}
	
	// devera estar em ordem alfabetica
	@Test
	public void sequencia() {
		assertEquals(contador, 1);
	}
}
