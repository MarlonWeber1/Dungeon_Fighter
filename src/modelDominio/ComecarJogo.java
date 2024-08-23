package modelDominio;
import view.Batalha;
import view.Tabuleiro;
import view.Jogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComecarJogo {
    private Heroi heroi;
    public Tabuleiro tabuleiro;
    private MonstroComum monstro;
    private Chefao chefao;
    private Armadilha arma;
    private ArmadilhaRandom armaR;
    private int dicas = 3;

    public ComecarJogo(Heroi heroi, boolean debug) {
        this.heroi = heroi;
        this.armaR = new ArmadilhaRandom();
        this.arma = new Armadilha();
        this.monstro = new MonstroComum();
        this.chefao = new Chefao();
        this.tabuleiro = new Tabuleiro(debug);
        posicaoInicial();
        criarBotoesTab();
        atualizarBotoesTab();
    }


    // posiciona o heroi na posicao inicial
    public void posicaoInicial () {
        heroi.setPosColuna(tabuleiro.getColunaInicial());
        heroi.setPosLinha(0);
    }

    // diminui a saude do heroi baseado no tipo de armadilha
    public void danoArmadilha (double dano) {
        heroi.setSaude(heroi.getSaude() - dano);
    }


    public void verificaMov(int novaLinha, int novaColuna, int linhaAtual, int colunaAtual) {
        if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'e') {
            heroi.achouElixir();
            System.out.println("elixir adicionado a bolsa");

            // popup para avisar que achou um elixir
            JOptionPane.showMessageDialog(tabuleiro, "Elixir adicionado a sua bolsa.", "Voce encontrou um Elixir", JOptionPane.INFORMATION_MESSAGE);


        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'M') {
            System.out.println("monstro!!!");

            // comeca batalha
            // se ganhar batalha se move para a celula
            JOptionPane.showMessageDialog(tabuleiro, "Você encontrou um monstro", "Monstro!!", JOptionPane.INFORMATION_MESSAGE);

            // abre uma nova janela
            new Batalha(heroi , monstro);

        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'A') {
            this.danoArmadilha(arma.getDano());
            System.out.println("armadilha normal");
            JOptionPane.showMessageDialog(tabuleiro, "Você caiu em uma armadilha normal, sua vida caiu para: " + heroi.getSaude() + ".", "Armadilha", JOptionPane.INFORMATION_MESSAGE);


            // popup avisando que caiu em uma armadilha
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'R') {
            double damage = armaR.gerarDanoAleatorio();
            this.danoArmadilha(damage);
            System.out.println("armadilha random");
            System.out.println(heroi.getSaude());
            // popup avisando que caiu em uma armadilha
            JOptionPane.showMessageDialog(tabuleiro, "Você caiu em uma armadilha, o dano foi de " + damage + " e sua vida caiu para:" + heroi.getSaude() + ".", "Armadilha de dano aleatorio", JOptionPane.INFORMATION_MESSAGE);



        }
        else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'C') {
            System.out.println("Boss battle!!!");

            JOptionPane.showMessageDialog(tabuleiro, "Voce chegou no Boss Final!", "Final BOSS", JOptionPane.INFORMATION_MESSAGE);

            // batalha comeca
            // se ganhar tela de jogo vencido
            // se perder tela de game over

            new Batalha(heroi , chefao);
        }
        tabuleiro.tabuleiro[linhaAtual][colunaAtual] = '*';
        tabuleiro.tabuleiro[novaLinha][novaColuna] = 'H';
    }


    // movimentacao para do heroi
    public void moverHeroi(int novaLinha, int novaColuna) {

        int linhaAtual = heroi.getPosLinha();
        int colunaAtual = heroi.getPosColuna();

        if (novaColuna == colunaAtual + 1 && novaLinha == linhaAtual ||
                novaColuna == colunaAtual - 1 && novaLinha == linhaAtual ||
                novaLinha == linhaAtual + 1 && novaColuna == colunaAtual ||
                novaLinha == linhaAtual - 1 && novaColuna == colunaAtual ||
                novaLinha == linhaAtual + 1 && novaColuna == colunaAtual + 1 ||
                novaLinha == linhaAtual - 1 && novaColuna == colunaAtual - 1 ||
                novaLinha == linhaAtual - 1 && novaColuna == colunaAtual + 1 ||
                novaLinha == linhaAtual + 1 && novaColuna == colunaAtual - 1) {

            // mostra oque tem na celula clickada
            if (!tabuleiro.isDebugging()) {
                atualizaBotaoClicado(novaLinha, novaColuna);
            }

            // so vai verificar oque tem na nova posicao se o movimento e valido
            verificaMov(novaLinha, novaColuna, linhaAtual, colunaAtual);

            heroi.setPosLinha(novaLinha);
            heroi.setPosColuna(novaColuna);
        }
        atualizarBotoesTab();
    }


    private void criarBotoesTab() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro.botoes[i][j] = new JButton(); // cria um novo botão
                final int lin = i;
                final int col = j;
                if (tabuleiro.isDebugging())
                {
                    // mostra oque tem em cada celula
                    if (tabuleiro.tabuleiro[i][j] == 'M') {
                        tabuleiro.botoes[i][j].setBackground(Color.red);
                        tabuleiro.botoes[i][j].setText("Monstro");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == '*') {
                        tabuleiro.botoes[i][j].setBackground(Color.white);
                        tabuleiro.botoes[i][j].setText(String.valueOf(tabuleiro.tabuleiro[i][j]));
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'A' || tabuleiro.tabuleiro[i][j] == 'R') {
                        tabuleiro.botoes[i][j].setBackground(Color.gray);
                        tabuleiro.botoes[i][j].setText("Armadilha");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'e') {
                        tabuleiro.botoes[i][j].setBackground(Color.cyan);
                        tabuleiro.botoes[i][j].setText("Elixir");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'H') {
                        tabuleiro.botoes[i][j].setBackground(Color.magenta);
                        tabuleiro.botoes[i][j].setText("Heroi");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'C') {
                        tabuleiro.botoes[i][j].setBackground(Color.black);
                        tabuleiro.botoes[i][j].setText("Boss");
                        tabuleiro.botoes[i][j].setForeground(Color.white);
                    }
                }
                else {
                    // esconde todos os elementos fora a posicao do heroi e do chefão
                    if (tabuleiro.tabuleiro[i][j] == '*' ||
                            tabuleiro.tabuleiro[i][j] == 'M' ||
                            tabuleiro.tabuleiro[i][j] == 'A' ||
                            tabuleiro.tabuleiro[i][j] == 'R' ||
                            tabuleiro.tabuleiro[i][j] == 'e' ) {
                        tabuleiro.botoes[i][j].setBackground(Color.gray);
                        tabuleiro.botoes[i][j].setText("*");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'H') {
                        tabuleiro.botoes[i][j].setBackground(Color.white);
                        tabuleiro.botoes[i][j].setText("Você");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'C') {
                        tabuleiro.botoes[i][j].setBackground(Color.black);
                        tabuleiro.botoes[i][j].setText("Boss");
                        tabuleiro.botoes[i][j].setForeground(Color.white);
                    }
                }
                tabuleiro.botoes[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        moverHeroi(lin, col); // mover o herói para a posição clicada
                    }
                });
                tabuleiro.add(tabuleiro.botoes[i][j]); // adiciona o botão ao painel
            }
        }
    }

    public void atualizaBotaoClicado(int novaLinha, int novaCol) {
        if (tabuleiro.tabuleiro[novaLinha][novaCol] == 'M') {
            tabuleiro.botoes[novaLinha][novaCol].setBackground(Color.red);
            tabuleiro.botoes[novaLinha][novaCol].setText("M");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaCol] == '*') {
            tabuleiro.botoes[novaLinha][novaCol].setBackground(Color.white);
            tabuleiro.botoes[novaLinha][novaCol].setText(String.valueOf(tabuleiro.tabuleiro[novaLinha][novaCol]));
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaCol] == 'A' || tabuleiro.tabuleiro[novaLinha][novaCol] == 'R') {
            tabuleiro.botoes[novaLinha][novaCol].setBackground(Color.gray);
            tabuleiro.botoes[novaLinha][novaCol].setText("A");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaCol] == 'e') {
            tabuleiro.botoes[novaLinha][novaCol].setBackground(Color.cyan);
            tabuleiro.botoes[novaLinha][novaCol].setText("E");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaCol] == 'H') {
            tabuleiro.botoes[novaLinha][novaCol].setBackground(Color.magenta);
            tabuleiro.botoes[novaLinha][novaCol].setText("H");
        }
        else if (tabuleiro.tabuleiro[novaLinha][novaCol] == 'C') {
            tabuleiro.botoes[novaLinha][novaCol].setBackground(Color.black);
            tabuleiro.botoes[novaLinha][novaCol].setText("Boss");
            tabuleiro.botoes[novaLinha][novaCol].setForeground(Color.white);
        }
    }

    public void atualizarBotoesTab() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                // define o texto do botão como o caractere correspondente no tabuleiro
                if (tabuleiro.isDebugging())
                {
                    // mostra oque tem em cada celula
                    if (tabuleiro.tabuleiro[i][j] == 'M') {
                        tabuleiro.botoes[i][j].setBackground(Color.red);
                        tabuleiro.botoes[i][j].setText("Monstro");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == '*') {
                        tabuleiro.botoes[i][j].setBackground(Color.white);
                        tabuleiro.botoes[i][j].setText(String.valueOf(tabuleiro.tabuleiro[i][j]));
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'A' || tabuleiro.tabuleiro[i][j] == 'R') {
                        tabuleiro.botoes[i][j].setBackground(Color.gray);
                        tabuleiro.botoes[i][j].setText("Armadilha");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'e') {
                        tabuleiro.botoes[i][j].setBackground(Color.cyan);
                        tabuleiro.botoes[i][j].setText("Elixir");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'H') {
                        tabuleiro.botoes[i][j].setBackground(Color.magenta);
                        tabuleiro.botoes[i][j].setText("Heroi");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'C') {
                        tabuleiro.botoes[i][j].setBackground(Color.black);
                        tabuleiro.botoes[i][j].setText("Boss");
                        tabuleiro.botoes[i][j].setForeground(Color.white);
                    }
                }
                else {
                    // esconde todos os elementos fora a posicao do heroi e do chefão
                    if (tabuleiro.tabuleiro[i][j] == '*' ||
                        tabuleiro.tabuleiro[i][j] == 'M' ||
                        tabuleiro.tabuleiro[i][j] == 'A' ||
                        tabuleiro.tabuleiro[i][j] == 'R' ||
                        tabuleiro.tabuleiro[i][j] == 'e' ) {
                        tabuleiro.botoes[i][j].setBackground(Color.gray);
                        tabuleiro.botoes[i][j].setText("*");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'H') {
                        tabuleiro.botoes[i][j].setBackground(Color.white);

                        tabuleiro.botoes[i][j].setForeground(Color.black);
                        tabuleiro.botoes[i][j].setText("Você");
                    }
                    else if (tabuleiro.tabuleiro[i][j] == 'C') {
                        tabuleiro.botoes[i][j].setBackground(Color.black);
                        tabuleiro.botoes[i][j].setText("Boss");
                        tabuleiro.botoes[i][j].setForeground(Color.white);
                    }
                }
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
            System.out.println("Você usou todas as dicas!");
        }
    }
}
