package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
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

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		
		for(Filme filme: filmes ) {
			if(filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException("Filme sem estoque");
			}
		}

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		
		Double valorTotal = 0d;
		/*for(Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
		}*/
		
		
		for(int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);
			Double valorUnitario = filme.getPrecoLocacao();
			
			switch(i) {
			case 2: valorUnitario = valorUnitario * 0.75; break;  //terceiro filme
			case 3: valorUnitario = valorUnitario * 0.5; break;  // quarto filme
			case 4: valorUnitario = valorUnitario * 0.25; break; //quinto filme
			case 5: valorUnitario = 0.0; break; //sexto filme
			}
			
			valorTotal += valorUnitario;
		}
		
		locacao.setValor(valorTotal);

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);


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