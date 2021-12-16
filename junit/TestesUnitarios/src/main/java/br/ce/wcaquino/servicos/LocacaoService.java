package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import junit.framework.Assert;

public class LocacaoService {
	
	// o LocacaoServiceTest estando na mesma estrutura (nome) de pacote mesmo estando em src/test/java, consegue enxergar as variaveis publica, protegida e default
	// pois LocacaoService e LocacaoServiceTest estao na mesma estrutura (nome) de pacote
	// se a LocacaoServiceTest estivesse em outro pacote (nome), entao so enxergaria a publica.
	// por eh importante deixar os TESTS na mesma estrutura nome de pacote, mesmo q um esteja em src/main/java e src/test/java
	public String vPublica;
	protected String vProtegida;
	private String vPrivada;
	String vDefault;

	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

	/*
	  public static void main(String[] args) {
	  
	  LocacaoService service = new LocacaoService(); Usuario usuario = new
	  Usuario("Usuario1"); Filme filme = new Filme("Filme1", 2, 5.0);
	  
	  Locacao locacao = service.alugarFilme(usuario, filme);
	  
	  System.out.println(locacao.getValor() == 5.0);
	  System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new
	  Date())); System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(),
	  DataUtils.obterDataComDiferencaDias(1)));
	  System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(),
	  DataUtils.obterDataComDiferencaDias(-1)));
	  
	  }*/
	 
	@Test
	public void teste() {

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme1", 2, 5.0);

		Locacao locacao = service.alugarFilme(usuario, filme);

		//assertTrue(locacao.getValor() == 4.0); da erro pois eh esperado 5
		assertTrue(locacao.getValor() == 5.0);
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		//assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(-1))); da erro, eh esperado dia +1

	}
}