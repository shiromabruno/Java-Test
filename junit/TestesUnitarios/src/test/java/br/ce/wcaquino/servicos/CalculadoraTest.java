package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.shiroma.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void init() {
		calc = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		int a = 5;
		int b = 3;
		
		int resultado = calc.somar(a, b);
		
		assertEquals(resultado, 8);
		
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		int a = 4;
		int b = 2;
		
		int resultado = calc.subtrair(a,b);
		
		assertEquals(resultado, 2);
	}
	
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		int a = 9;
		int b = 3;
		
		int resultado = calc.dividir(a,b);
		
		assertEquals(resultado, 3);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		int a = 10;
		int b = 0;
		
		calc.dividir(a, b);
	 
	}
	
	
}
