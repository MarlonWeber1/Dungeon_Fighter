package modelDominio;
import view.Tabuleiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        criarBotoes();
        atualizarBotoes();
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

    public void verificaMov(int novaLinha, int novaColuna, int linhaAtual, int colunaAtual) {
        if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'e') {
            heroi.achouElixir();
            System.out.println("elixir adicionado a bolsa");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'M') {
            System.out.println("monstro!!!");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'A') {
            this.danoArmadilha(arma.getDano());
            System.out.println("armadilha normal");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'R') {
            this.danoArmadilha(armaR.gerarDanoAleatorio());
            System.out.println("armadilha random");
            System.out.println(heroi.getSaude());
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'C') {
            System.out.println("Boss battle!!@!");
        }

        // limpa a posição atual do herói
        tabuleiro.tabuleiro[linhaAtual][colunaAtual] = '*';
        // move o herói para a nova posição
        tabuleiro.tabuleiro[novaLinha][novaColuna] = 'I';
    }

    // movimentacao para do heroi
    public void moverHeroi(int novaLinha, int novaColuna) {

        int linhaAtual = heroi.getPosLinha();
        int colunaAtual = heroi.getPosColuna();

        if (novaColuna == colunaAtual + 1 && novaLinha == linhaAtual ||  // movimento para direita
            novaColuna == colunaAtual - 1 && novaLinha == linhaAtual ||  // movimento pra esquerda
            novaLinha == linhaAtual + 1 && novaColuna == colunaAtual ||  // movimento para cima
            novaLinha== linhaAtual - 1 && novaColuna == colunaAtual) {   // movimento para baixo

            // so vai verificar oque tem na nova posicao se o movimento e valido
            verificaMov(novaLinha,novaColuna,linhaAtual,colunaAtual);

            heroi.setPosLinha(novaLinha);
            heroi.setPosColuna(novaColuna);
        }
        atualizarBotoes();
    }



    private void criarBotoes() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro.botoes[i][j] = new JButton(); // cria um novo botão
                final int lin = i;
                final int col = j;
                tabuleiro.botoes[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        moverHeroi(lin, col); // mover o herói para a posição clicada
                    }
                });
                tabuleiro.add(tabuleiro.botoes[i][j]); // adiciona o botão ao painel
            }
        }
    }

    public void atualizarBotoes() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                // define o texto do botão como o caractere correspondente no tabuleiro
                tabuleiro.botoes[i][j].setText(String.valueOf(tabuleiro.tabuleiro[i][j]));
            }
        }
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
