package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		assertTrue(5 > 3);
		assertFalse(3 > 5);
		
		assertEquals(1, 1);
		//assertEquals("Mensagem de erro de comparacao para exibir", 1, 2); PARA Exibir msg de erro customizada
		assertEquals(0.512345, 0.513, 0.001); // terceiro elemento eh margem de erro pra mais ou pra menos dos 2 elementos double
		assertEquals(Math.PI, 3.14, 0.01);
		//assertEquals(1, "1"); ERRO
		
		int i = 5;
		Integer i2 = 5;
		
		//assertEquals(i, i2); // da ERRO
		assertEquals(Integer.valueOf(i), i2); // assim funciona. Tipo primitivo para objeto
		assertEquals(i, i2.intValue()); // assim funciona. Objeto para tipo primitivo
		
		assertEquals("teste", "teste");
		//assertEquals("teste", "TESTE"); // ERRO, maiusxulo x minusculo
		assertTrue("teste".equalsIgnoreCase("TESTE")); // TEVE QUE TROCAR para assertTRUE + equalsIgnoreCase
		assertTrue("teste".startsWith("te"));
		
		Usuario u1 = new Usuario("Usuario A");
		Usuario u2 = new Usuario("Usuario A");
		Usuario u3 = new Usuario("Usuario 555");
		Usuario u4 = u1;
		Usuario u5 = null;
		
		//assertEquals(u1,u2); //da ERRO (antes de implementar metodo EQUALS na classe Usuario. 
							//Quem fala se o objeto eh igual ao outro, eh o proprio objeto (precisa do metodo EQUAL implementado). 
		                       //Se a classe nao tiver o metodo, procurara nas classes superiores. 
		                       //Se nao houver superior, entao a extensao eh da classe OBJECT (messe caso, a classe OBJECT apenas compara se o objeto eh O MESMO q o outro, mesma instancia)
		
		//assertEquals(u1,u1); // ai eh OK pois sao mesma instancia (isso antes de implementar o metodo EQUALS em USUARIO)
		
		assertEquals(u1,u2); // apos implementado metodo EQUALS na classe USUARIO. Compara conteudo NOME
		//assertEquals(u1,u3); // ERRO CONTEUDO 'nome' DIFERENTE 
		
		assertSame(u1,u1); // apos implementado metodo EQUALS na classe USUARIO. Compara se a INSTANCIA eh a mema
		//assertSame(u1,u2); //ERRO. apos implementado metodo EQUALS na classe USUARIO. ERRO, Instnacias diferentes
		assertSame(u1,u4); // OK pois o u4 aponta para o u1
		
		assertTrue(u5 == null);
		assertNull(u5);
	}
	

}
