package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.com.shiroma.exceptions.FilmeSemEstoqueException;
import br.com.shiroma.exceptions.LocadoraException;

public class LocacaoService {
	
	// o LocacaoServiceTest estando na mesma estrutura (nome) de pacote mesmo estando em src/test/java, consegue enxergar as variaveis publica, protegida e default
	// pois LocacaoService e LocacaoServiceTest estao na mesma estrutura (nome) de pacote
	// se a LocacaoServiceTest estivesse em outro pacote (nome), entao so enxergaria a publica.
	// por isso eh importante deixar os TESTS na mesma estrutura nome de pacote, mesmo q um esteja em src/main/java e src/test/java
	public String vPublica;
	protected String vProtegida;
	private String vPrivada;
	String vDefault;

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {
		
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		
		if(filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException("Filme sem estoque");
		}

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
	 
}