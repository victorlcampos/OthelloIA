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

		verificarValidadeJogada(i, j);

		executarJogada(i, j);
		
		jogadorVez = jogadorVez == StatusCasa.PECA_BRANCA ? StatusCasa.PECA_PRETA
				: StatusCasa.PECA_BRANCA;
		
		atualizarJogadasDisponiveis();

		tabuleiro.update(casas, jogadorVez);
	}

	private void verificarValidadeJogada(int i, int j) {
		if (casas[i][j] != StatusCasa.JOGADA_POSSIVEL) {
			tabuleiro.alertaJogadaInvalida();
		}
	}

	private void executarJogada(int i, int j) {
		casas[i][j] = jogadorVez;
		for (int dir = 0; dir < 8; dir++)
			atualizarPecas(i, j, dir, jogadorVez);
	}

	private boolean atualizarPecas(int i, int j, int direcao, StatusCasa jogadorVez) {
		int nextI = 0;
		int nextJ = 0;
		boolean validacaoTamanhoTabuleiro = false;
		System.out.println(i);

		switch (direcao) {
		case 0:
			nextI = i-1;
			nextJ = j;
			validacaoTamanhoTabuleiro = (i == 0 );
			break;
		case 1:
			nextI = i-1;
			nextJ = j+1;
			validacaoTamanhoTabuleiro = (i == 0 || j == 7);
			break;
		case 2:
			nextI = i;
			nextJ = j+1;
			validacaoTamanhoTabuleiro = (j == 7 );
			break;
		case 3:
			nextI = i+1;
			nextJ = j+1;
			validacaoTamanhoTabuleiro = (i == 7 || j == 7);
			break;
		case 4:
			nextI = i+1;
			nextJ = j;
			validacaoTamanhoTabuleiro = (i == 7 );	
			break;
		case 5:
			nextI = i+1;
			nextJ = j-1;
			validacaoTamanhoTabuleiro = (i == 7 || j == 0);	
			break;
		case 6:
			nextI = i;
			nextJ = j-1;
			validacaoTamanhoTabuleiro = (j == 0 );
			break;
		case 7:
			nextI = i-1;
			nextJ = j-1;
			validacaoTamanhoTabuleiro = (i == 0 || j == 0);
			break;
		}
		
		if(validacaoTamanhoTabuleiro || casas[nextI][nextJ] == StatusCasa.JOGADA_POSSIVEL || casas[nextI][nextJ] == StatusCasa.JOGADA_NAO_POSSIVEL){
			return false;
		} else if (casas[nextI][nextJ] == jogadorVez || atualizarPecas(nextI, nextJ, direcao, jogadorVez)){
			casas[i][j] = jogadorVez;
			return true;
		}
		return false;
	}

	private void atualizarJogadasDisponiveis() {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				boolean achou = false;
				for (int dir = 0; dir < 8; dir++) {
					if(casas[i][j] == StatusCasa.JOGADA_NAO_POSSIVEL || casas[i][j] == StatusCasa.JOGADA_POSSIVEL){
						if(acharJogadaPossivel(i, j, dir, jogadorVez, false)){
							casas[i][j] = StatusCasa.JOGADA_POSSIVEL;
							achou = true;
						}else if(!achou){
							casas[i][j] = StatusCasa.JOGADA_NAO_POSSIVEL;
						}
					}
				}
			}
		}

	}

	private boolean acharJogadaPossivel(int i, int j, int direcao,
			StatusCasa jogadorVez, boolean achouOponente) {
		int nextI = 0;
		int nextJ = 0;
		boolean validacaoTamanhoTabuleiro = false;
		System.out.println(i);
		

		switch (direcao) {
		case 0:
			nextI = i-1;
			nextJ = j;
			validacaoTamanhoTabuleiro = (i == 0 );
			break;
		case 1:
			nextI = i-1;
			nextJ = j+1;
			validacaoTamanhoTabuleiro = (i == 0 || j == 7);
			break;
		case 2:
			nextI = i;
			nextJ = j+1;
			validacaoTamanhoTabuleiro = (j == 7 );
			break;
		case 3:
			nextI = i+1;
			nextJ = j+1;
			validacaoTamanhoTabuleiro = (i == 7 || j == 7);
			break;
		case 4:
			nextI = i+1;
			nextJ = j;
			validacaoTamanhoTabuleiro = (i == 7 );	
			break;
		case 5:
			nextI = i+1;
			nextJ = j-1;
			validacaoTamanhoTabuleiro = (i == 7 || j == 0);	
			break;
		case 6:
			nextI = i;
			nextJ = j-1;
			validacaoTamanhoTabuleiro = (j == 0 );
			break;
		case 7:
			nextI = i-1;
			nextJ = j-1;
			validacaoTamanhoTabuleiro = (i == 0 || j == 0);
			break;
		}
		
		if(validacaoTamanhoTabuleiro || casas[nextI][nextJ] == StatusCasa.JOGADA_POSSIVEL || casas[nextI][nextJ] == StatusCasa.JOGADA_NAO_POSSIVEL){
			return false;
		}else if(casas[nextI][nextJ] == jogadorVez && !achouOponente) {
			return false;
		}
		else if ((casas[nextI][nextJ] == jogadorVez) || acharJogadaPossivel(nextI, nextJ, direcao, jogadorVez, true)){
			return true;
		}
		return false;
	}

}
