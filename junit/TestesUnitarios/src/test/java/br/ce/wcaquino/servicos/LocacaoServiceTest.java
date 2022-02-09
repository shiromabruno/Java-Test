package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import br.com.shiroma.exceptions.FilmeSemEstoqueException;
import br.com.shiroma.exceptions.LocadoraException;
import junit.framework.Assert;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	private static int contadorEstaticoParaContarMethods;
	
	// usamos o ErrorCollector para "coletar" todos os erros que os ASSERTS der dentro do metodo teste
	//Dessa forma o numero de falhas aumenta (nao ira parar no primeiro q acha)
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	// Outra forma de tratar Excecao, terceira forma
	//exception.expect(Exception.class); e exception.expectMessage("Filme sem estoque");
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void init() {
		//System.out.println("antes");
		 service = new LocacaoService();
		 contadorEstaticoParaContarMethods++;
		 System.out.println("Teste numero STATIC (nao inicializa): " +  contadorEstaticoParaContarMethods);
	}
	
	@After
	public void termino() {
		//System.out.println("depois");
	}
	
	@BeforeClass
	public static void initClass() {
		System.out.println("BeforeClass iniciando...");
	}
	
	@AfterClass
	public static void terminoClass() {
		System.out.println("TerminoClass acabou os testes...");
	}

	
	@Test
	public void testeAlocacao() {

		//System.out.println("testeAlocacao");
		
		Usuario usuario = new Usuario("Usuario1");
		//Filme filme = new Filme("Filme1", 2, 5.0);
		List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 5.0));

		try {
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
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
	// vai dar ERRO pois nao foi tratado
/*	@Test
	public void testeAlocacaoComThrowsExceptionEstoqueFilmeZero() throws Exception {   


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
*/
		//Eh esperado uma exception, poderia deixar com o FilmeSemEstoqueExceptionn
		// Primeira forma
		@Test(expected =  Exception.class)
		public void testeAlocacaoComExpectExceptionEstoqueFilmeZero() throws Exception {       
			
			//System.out.println("testeAlocacaoComExpectExceptionEstoqueFilmeZero");

			Usuario usuario = new Usuario("Usuario1");
			//Filme filme = new Filme("Filme1", 0, 5.0);
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 0, 5.0));
		
				Locacao locacao = service.alugarFilme(usuario, filmes);
				
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
		
		//Eh esperado uma exception
		// para evitar POSITIVO-FALSO, colocamos o Assert.fail para garantir que tenha execao.
		// (cenario onde o estoque de filme eh maior que 1)
		// Segunda forma
		@Test
		public void testeAlocacaoComTryCatchEstoqueFilmeZero() {  
			
			//System.out.println("testeAlocacaoComTryCatchEstoqueFilmeZero");
				
			Usuario usuario = new Usuario("Usuario1");
			//Filme filme = new Filme("Filme1", 0, 5.0);
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 0, 5.0));
		
				Locacao locacao;
				try {
					locacao = service.alugarFilme(usuario, filmes);
					
					Assert.fail("OPA, deveria ter dado uma excecao");
					
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
		
		//Terceira forma
		//exception.expect(Exception.class); e exception.expectMessage("Filme sem estoque");
		@Test
		public void testeAlocacaoComExceptionMethodEstoqueFilmeZero() throws Exception {   
			
			//System.out.println("testeAlocacaoComExceptionMethodEstoqueFilmeZero");
				
			Usuario usuario = new Usuario("Usuario1");
			//Filme filme = new Filme("Filme1", 0, 5.0);
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 0, 5.0));
			
				exception.expect(Exception.class);
				exception.expectMessage("Filme sem estoque");
		
				Locacao locacao = service.alugarFilme(usuario, filmes);
				
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
		
		// Deixamos com throws FilmeSemEstoqueException pois caso venha um  FilmeSemEstoqueException, o Junit trata.
		// No catch teremos apenas o LocadoraException (que esse sim ira tratar usuario vazio)
		@Test
		public void testeLocacaoUsuarioVazio() throws FilmeSemEstoqueException {
			
			//System.out.println("testeLocacaoUsuarioVazio");
			
			//Filme filme = new Filme("Filme2", 3, 5.0);
			//Usuario usuario = new Usuario("Usuario1");
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 5.0));
			
			try {
				Locacao locacao = service.alugarFilme(null, filmes);
				//Locacao locacao = service.alugarFilme(usuario, filme);
				Assert.fail("Opa, deveria ter caido no LocadoraException");
			} 
			catch (LocadoraException e) {
				assertEquals(e.getMessage(), "Usuario vazio");
			}
		}
		
		@Test
		public void testeLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
			
			//System.out.println("testeLocacaoFilmeVazio");
			
			//Filme filme = new Filme("Filme2", 3, 5.0);
			Usuario usuario = new Usuario("Usuario1");
			
			exception.expect(LocadoraException.class);
			exception.expectMessage("Filme vazio");
			
			Locacao locacao = service.alugarFilme(usuario, null);
			
		}
		
		@Test
		public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
			Usuario usuario = new Usuario("Usuario1");
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0), new Filme("Filme2", 2, 4.0), new Filme("Filme3", 2, 4.0));
			
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
			assertEquals(locacao.getValor(), 11.0, 0.001);
		}
		
		@Test
		public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
			Usuario usuario = new Usuario("Usuario1");
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0), new Filme("Filme2", 2, 4.0), new Filme("Filme3", 2, 4.0),
					new Filme("Filme4", 2, 4.0)			
					);
			
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
			assertEquals(locacao.getValor(), 13.0, 0.001);
		}
		
		@Test
		public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
			Usuario usuario = new Usuario("Usuario1");
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0), new Filme("Filme2", 2, 4.0), new Filme("Filme3", 2, 4.0),
					new Filme("Filme4", 2, 4.0), new Filme("Filme5", 2, 4.0)			
					);
			
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
			assertEquals(locacao.getValor(), 14.0, 0.001);
		}
		
		@Test
		public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
			Usuario usuario = new Usuario("Usuario1");
			List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0), new Filme("Filme2", 2, 4.0), new Filme("Filme3", 2, 4.0),
					new Filme("Filme4", 2, 4.0), new Filme("Filme5", 2, 4.0), new Filme("Filme6", 2, 4.0)		
					);
			
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
			assertEquals(locacao.getValor(), 14.0, 0.001);
		}
		
}
