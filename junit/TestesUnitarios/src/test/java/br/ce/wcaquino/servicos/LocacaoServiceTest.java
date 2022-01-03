package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Test
	public void teste() {

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme1", 2, 5.0);

		Locacao locacao = service.alugarFilme(usuario, filme);

		//assertTrue(locacao.getValor() == 4.0); da erro pois eh esperado 5
		assertTrue(locacao.getValor() == 5.0); // ou fazer igual ao de baixo
		assertEquals(locacao.getValor(), 5.0, 0.001);
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		//assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(-1))); da erro, eh esperado dia +1
		
		//usando assertTHAT (mais abstrato/vago que os outros Asserts)
		// usando o CoreMatchers (Hamcrest-core-1.3.jar traz uns Matchers proprios)
		/*
		assertThat("1991", is(algumValor)); // sendo que algumValor é uma String
		assertThat("1991", is(not("2019")));
		assertEquals("1991", algumValor);
		*/
		  assertThat(locacao.getValor(), is(5.0)); // 
		  assertThat(locacao.getValor(), equalTo(5.0)); // 
		  assertThat(locacao.getValor(), is(not(4.0))); // 
		  
		  assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		  assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}
}
