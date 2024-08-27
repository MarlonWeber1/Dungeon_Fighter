package modelDominio;
import view.Batalha;
import view.Jogo;
import view.Tabuleiro;
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
    private Jogo jogo;

    public ComecarJogo(Heroi heroi, boolean debug, Jogo jogo, Tabuleiro tabuleiro) {
        this.heroi = heroi;
        this.armaR = new ArmadilhaRandom();
        this.arma = new Armadilha();
        this.monstro = new MonstroComum();
        this.chefao = new Chefao();
        if (tabuleiro == null) {
            this.tabuleiro = new Tabuleiro(debug);
        }
        else {
            this.tabuleiro = tabuleiro;
        }

        this.jogo = jogo;

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
        // Aqui vamos processar a verificação de movimentação em um SwingWorker
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'e') {
                    heroi.achouElixir();
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(tabuleiro, "Elixir adicionado a sua bolsa.", "Você encontrou um Elixir", JOptionPane.INFORMATION_MESSAGE));
                } else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'M') {
                    JOptionPane.showMessageDialog(tabuleiro, "Você encontrou um monstro", "Monstro!!", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> new Batalha(heroi, monstro, jogo));
                } else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'A') {
                    danoArmadilha(arma.getDano());
                    if (heroi.estaVivo()) {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(tabuleiro, "Você caiu em uma armadilha normal, sua vida caiu para: " + heroi.getSaude() + ".", "Armadilha", JOptionPane.INFORMATION_MESSAGE));
                    } else {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Você caiu em uma armadilha e morreu!", "Game Over", JOptionPane.ERROR_MESSAGE));
                    }
                } else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'R') {
                    double damage = armaR.gerarDanoAleatorio();
                    danoArmadilha(damage);
                    if (heroi.estaVivo()) {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(tabuleiro, "Você caiu em uma armadilha de dano aleatório, o dano foi de " + damage + " e sua vida caiu para:" + heroi.getSaude() + ".", "Armadilha", JOptionPane.INFORMATION_MESSAGE));
                    } else {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Você caiu em uma armadilha e morreu!", "Game Over", JOptionPane.ERROR_MESSAGE));
                    }
                } else if (tabuleiro.tabuleiro[novaLinha][novaColuna] == 'C') {
                    JOptionPane.showMessageDialog(tabuleiro, "Você chegou no Boss Final!", "Final BOSS", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> new Batalha(heroi, chefao, jogo));
                }
                return null;
            }

            @Override
            protected void done() {
                tabuleiro.tabuleiro[linhaAtual][colunaAtual] = '*';
                tabuleiro.tabuleiro[novaLinha][novaColuna] = 'H';
                atualizarBotoesTab();
            }
        }.execute();
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

            // mostra oque tem na celula clickada antes do popup
            if (!tabuleiro.isDebugging()) {
                atualizaBotaoClicado(novaLinha, novaColuna);
            }

            // so vai verificar oque tem na nova posicao se o movimento e valido
            verificaMov(novaLinha, novaColuna, linhaAtual, colunaAtual);

            // atuliza posicao na instancia do heroi
            heroi.setPosLinha(novaLinha);
            heroi.setPosColuna(novaColuna);
        }
        atualizarBotoesTab();
    }

    // cria os botoes
    // usando a variavel de debug para determinar se as celulas serao mostradas
    public void criarBotoesTab() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro.botoes[i][j] = new JButton(); // cria um novo botão
                tabuleiro.botoes[i][j].setPreferredSize(new Dimension(80, 80));  // Define o tamanho de cada botão
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

            JOptionPane.showMessageDialog(null,
                    "Armadilhas na coluna " + coluna + ": " + arms,
                    "Dica Usada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Você usou todas as dicas!",
                    "Sem Dicas Restantes",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
