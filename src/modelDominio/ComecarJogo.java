package modelDominio;
import view.Tabuleiro;

public class ComecarJogo {
    private Heroi heroi;
    private Tabuleiro tabuleiro;
    private MonstroComum monstro;
    private Chefao chefao;
    private Armadilha arma;
    private ArmadilhaRandom armaR;
    private int dicas = 3;

    public ComecarJogo(Heroi heroi, Tabuleiro tabuleiro, MonstroComum monstro, Chefao chefao, Armadilha armaN, ArmadilhaRandom armaR) {
        this.heroi = heroi;
        this.tabuleiro = tabuleiro;
        this.monstro = monstro;
        this.chefao = chefao;
        this.arma = armaN;
        this.armaR = armaR;
        posicaoInicial();
    }

    // metodos para o inicio do jogo

    // posiciona o heroi na posicao inicial
    public void posicaoInicial () {
        heroi.setPosColuna(tabuleiro.getColunaInicial());
        heroi.setPosLinha(0);
    }

    public void danoArmadilha (double dano) { // saude diminui 10
        heroi.setSaude(heroi.getSaude() - dano);
    }



    // verifica se o movimento é possivel
    // se sim, ira verificar oque a celula contem
    // se nao, deve mostrar uma mensagem para o usuario

    // retorna a proxima prosicao para que a funcao de movimento do heroi funcione corretamente

    public int verificaCol(int proxPosicao) {
        if (proxPosicao > 9 || proxPosicao < 0) {
            proxPosicao -= 1;
            System.out.println("movimento nao permitido");
            return proxPosicao;
        }
        else if (tabuleiro.tabuleiro[heroi.getPosLinha()][proxPosicao] == 'e') {
            heroi.achouElixir();
            System.out.println("elixir adicionado a bolsa");
        }
        else if (tabuleiro.tabuleiro[heroi.getPosLinha()][proxPosicao] == 'M') {
            System.out.println("monstro!!!");
        }
        else if (tabuleiro.tabuleiro[heroi.getPosLinha()][proxPosicao] == 'A') {
            this.danoArmadilha(arma.getDano());
            System.out.println("armadilha normal");
        }
        else if (tabuleiro.tabuleiro[heroi.getPosLinha()][proxPosicao] == 'R') {
            this.danoArmadilha(armaR.gerarDanoAleatorio());
            System.out.println("armadilha random");
            System.out.println(heroi.getSaude());
        }
        return proxPosicao;
    }

    public int verificaLin(int proxPosicao) {
        if (proxPosicao > 4 || proxPosicao < 0) {
            proxPosicao -= 1;
            System.out.println("movimento nao permitido");
            return proxPosicao;
        }
        else if (tabuleiro.tabuleiro[proxPosicao][heroi.getPosColuna()] == 'e') {
            heroi.achouElixir();
            System.out.println("elixir adicionado a bolsa");
        }
        else if (tabuleiro.tabuleiro[proxPosicao][heroi.getPosColuna()] == 'M') {
            System.out.println("monstro!!!!");
        }
        else if (tabuleiro.tabuleiro[proxPosicao][heroi.getPosColuna()] == 'A') {
            this.danoArmadilha(arma.getDano());
            System.out.println("armadilha normal");
            System.out.println(heroi.getSaude());
        }
        else if (tabuleiro.tabuleiro[proxPosicao][heroi.getPosColuna()] == 'R') {
            this.danoArmadilha(armaR.gerarDanoAleatorio());
            System.out.println("armadilha random");
            System.out.println(heroi.getSaude());
        }
        return proxPosicao;
    }

    // movimentacao para do heroi
    public void moveCima () {
        int prox = (heroi.getPosLinha() + 1);
        prox = verificaLin(prox);
        heroi.setPosLinha(prox);
        tabuleiro.tabuleiro[heroi.getPosLinha()][heroi.getPosColuna()] = 'H';
    }

    public void moveBaixo () {
        int prox = (heroi.getPosLinha() + 1);
        prox = verificaLin(prox);
        heroi.setPosLinha(prox);
        tabuleiro.tabuleiro[heroi.getPosLinha()][heroi.getPosColuna()] = 'H';
    }

    public void moveDir () {
        int prox = (heroi.getPosColuna() + 1);
        prox = verificaCol(prox);
        heroi.setPosColuna(prox);
        tabuleiro.tabuleiro[heroi.getPosLinha()][heroi.getPosColuna()] = 'H';
    }

    public void moveEsq () {
        int prox = (heroi.getPosColuna() + 1);
        prox = verificaCol(prox);
        heroi.setPosColuna(prox);
        tabuleiro.tabuleiro[heroi.getPosLinha()][heroi.getPosColuna()] = 'H';
    }


    // metodo de usar dica
    // retorna numero de armadilhas
    public void usaDica(int coluna) {
        if (this.dicas >= 1) {
            this.dicas--;
            int arms = tabuleiro.dica(coluna);
            // devera aparecer uma mensagem para o usuario com o numero de armadilhas
            System.out.println("Armadilhas na coluna " + coluna + ": " + arms);
        } else
        {
            // devera aparecer uma mensagem que o usuario usou todas as dicas
            System.out.println("você usou todas as dicas!");
        }
    }
}
