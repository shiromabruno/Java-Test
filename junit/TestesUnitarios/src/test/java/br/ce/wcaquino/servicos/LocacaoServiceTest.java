package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class LocacaoServiceTest {
	
	// usamos o ErrorCollector para "coletar" todos os erros que os ASSERTS der dentro do metodo teste
	//Dessa forma o numero de falhas aumenta (nao ira parar no primeiro q acha)
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	
	@Test
	public void testeAlocacao() {


		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme1", 2, 5.0);

		try {
			Locacao locacao = service.alugarFilme(usuario, filme);
			
			assertTrue(locacao.getValor() == 5.0); // ou fazer igual ao de baixo
			assertEquals(locacao.getValor(), 5.0, 0.001);
			assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
			assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
			
			// no check that , por ser mais generico (Nao eh assertTrue ou assertEquals), usamos o CoreMatchers (Hamcrest-core-1.3.jar traz uns Matchers proprios)
			// repare q segundo metodo tem um equalTo, is(not...
			error.checkThat(locacao.getValor(), equalTo(5.0)); // 
			error.checkThat(locacao.getValor(), is(not(4.0))); // 
			  
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
			
			}
			catch(Exception ex){
				System.out.println("Erro: " + ex);
				Assert.fail("Nao deveria dar excecao");
			}

		//assertTrue(locacao.getValor() == 4.0); da erro pois eh esperado 5
		//assertTrue(locacao.getValor() == 5.0); // ou fazer igual ao de baixo
		//assertEquals(locacao.getValor(), 5.0, 0.001);
		//assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		//assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		//assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(-1))); da erro, eh esperado dia +1
		
		//usando assertTHAT (mais abstrato/vago que os outros Asserts)
		// usando o CoreMatchers (Hamcrest-core-1.3.jar traz uns Matchers proprios)
		/*
		assertThat("1991", is(algumValor)); // sendo que algumValor é uma String
		assertThat("1991", is(not("2019")));
		assertEquals("1991", algumValor);
		*/
		
			//Trocando todos os assertThat por error.checkThat (Error Collector) - Dessa forma o numero de falhas aumenta (nao ira parar no primeiro q acha)
		  /*
		  assertThat(locacao.getValor(), is(5.0)); // 
		  assertThat(locacao.getValor(), equalTo(5.0)); // 
		  assertThat(locacao.getValor(), is(not(4.0))); // 
		  
		  assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		  assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		  */
		
//		  error.checkThat(locacao.getValor(), equalTo(3.0)); 
//		  error.checkThat(locacao.getValor(), is(not(2.0))); 
//		  
//		  error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(false));
//		  error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}
	
	//aqui ira gerar 1 ERRO e nao 1 FALHA. Throws Exception
	@Test
	public void testeAlocacaoComThrowsException() throws Exception {   


		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme1", 0, 5.0);
	
			Locacao locacao = service.alugarFilme(usuario, filme);
			
			assertTrue(locacao.getValor() == 5.0); // ou fazer igual ao de baixo
			assertEquals(locacao.getValor(), 5.0, 0.001);
			assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
			assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
			
			// no check that , por ser mais generico (Nao eh assertTrue ou assertEquals), usamos o CoreMatchers (Hamcrest-core-1.3.jar traz uns Matchers proprios)
			// repare q segundo metodo tem um equalTo, is(not...
			error.checkThat(locacao.getValor(), equalTo(5.0)); // 
			error.checkThat(locacao.getValor(), is(not(4.0))); // 
			  
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
			
	}	

	
		@Test(expected = Exception.class)
		public void testeAlocacaoComExpectException() throws Exception {       
				

			LocacaoService service = new LocacaoService();
			Usuario usuario = new Usuario("Usuario1");
			Filme filme = new Filme("Filme1", 0, 5.0);
		
				Locacao locacao = service.alugarFilme(usuario, filme);
				
				assertTrue(locacao.getValor() == 5.0); // ou fazer igual ao de baixo
				assertEquals(locacao.getValor(), 5.0, 0.001);
				assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
				assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
				
				// no check that , por ser mais generico (Nao eh assertTrue ou assertEquals), usamos o CoreMatchers (Hamcrest-core-1.3.jar traz uns Matchers proprios)
				// repare q segundo metodo tem um equalTo, is(not...
				error.checkThat(locacao.getValor(), equalTo(5.0)); // 
				error.checkThat(locacao.getValor(), is(not(4.0))); // 
				  
				error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
				error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
				
		}	
		
		
		@Test
		public void testeAlocacaoComTryCatch() {       
				
			LocacaoService service = new LocacaoService();
			Usuario usuario = new Usuario("Usuario1");
			Filme filme = new Filme("Filme1", 0, 5.0);
		
				Locacao locacao;
				try {
					locacao = service.alugarFilme(usuario, filme);
					assertTrue(locacao.getValor() == 5.0); // ou fazer igual ao de baixo
					assertEquals(locacao.getValor(), 5.0, 0.001);
					assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
					assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
					
					// no check that , por ser mais generico (Nao eh assertTrue ou assertEquals), usamos o CoreMatchers (Hamcrest-core-1.3.jar traz uns Matchers proprios)
					// repare q segundo metodo tem um equalTo, is(not...
					error.checkThat(locacao.getValor(), equalTo(5.0)); // 
					error.checkThat(locacao.getValor(), is(not(4.0))); // 
					  
					error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
					error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
					
				} catch (Exception e) {
					assertEquals(e.getMessage(), "Filme sem estoque");
					error.checkThat(e.getMessage(), is("Filme sem estoque"));
				}
	
		}	
	
	
}
