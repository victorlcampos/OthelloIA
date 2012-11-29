package controller;

import view.Tabuleiro;

public class God {

	private static final int TAMANHO_TABULEIRO = 8;
	
	private static God instance;
	private static Tabuleiro tabuleiro;

	private StatusCasa jogadorVez;
	private StatusCasa[][] casas;
	
	private God() {
		casas = new StatusCasa[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
		jogadorVez = StatusCasa.PECA_PRETA;

		for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
			
			for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
				
				casas[i][j] = StatusCasa.JOGADA_NAO_POSSIVEL;
				
			}
			
		}
		
		casas[3][3] = StatusCasa.PECA_BRANCA;
		casas[4][4] = StatusCasa.PECA_BRANCA;
		casas[3][4] = StatusCasa.PECA_PRETA;
		casas[4][3] = StatusCasa.PECA_PRETA;
		casas[2][3] = StatusCasa.JOGADA_POSSIVEL;
		casas[3][2] = StatusCasa.JOGADA_POSSIVEL;
		casas[4][5] = StatusCasa.JOGADA_POSSIVEL;
		casas[5][4] = StatusCasa.JOGADA_POSSIVEL;

		tabuleiro = Tabuleiro.getInstance();
		tabuleiro.update(casas, jogadorVez);
	}


	public static God getInstance() {
		if (instance == null) {
			instance = new God();
		}
		return instance;
	}
	
	
	public void jogar(int i, int j) {
		
		verificarValidadeJogada();
		
		executarJogada(i, j);
		
		atualizarJogadasDisponiveis();
		
		tabuleiro.update(casas, jogadorVez);
		
		jogadorVez = jogadorVez == StatusCasa.PECA_BRANCA ? StatusCasa.PECA_PRETA
				: StatusCasa.PECA_BRANCA;
	}


	private void atualizarJogadasDisponiveis() {
		// TODO Auto-generated method stub
		
	}


	private void executarJogada(int i, int j) {
		casas[i][j] = jogadorVez;
		
		atualizarPecas();
	}


	private void atualizarPecas() {
		// TODO Auto-generated method stub
		
	}


	private void verificarValidadeJogada() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
