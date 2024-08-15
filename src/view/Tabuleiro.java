package view;

// import java.awt.GridLayout;
// import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Pedro mota
 */

public class Tabuleiro extends JPanel {

    private final char[][] tabuleiro = new char[5][10];

    public Tabuleiro () {
        criarTabuleiro();
    }

    /*  ALFABETO PARA IDENTIFICAÇÃO DAS CELULAS:
    *   I = spawn do jogador
    *   e = elixir
    *   H = posicao atual do heroi (ainda não implementado)
    *   * = celula sem monstros ou armadilhas
    *   A = armadilha comum
    *   R = armadilha com dano aleatorio (random)
    *   M = monstro comum
    *   C = chefao
     */

    private void criarTabuleiro() {
        for (int i = 0; i<5; i++) {
            for (int x = 0; x<10; x++)
            {
                tabuleiro[i][x] = '*';
            }
        }
        tabuleiro[4][colunaRandom()] = 'C'; // posiciona o Chefão em uma coluna aleatoria da ultima linha
        tabuleiro[0][colunaRandom()] = 'I'; // posiciona o jogador em uma coluna aleatoria da primeira linha
        posicionaMonstro();

        // 3 armadilhas com dano fixo em posicoes aleatorias
        posicionaArmadilhaN();
        posicionaArmadilhaN();
        posicionaArmadilhaN();

        // 2 armadilhas com dano aleatorio em posicoes aleatorias
        posicionaArmadilhaRandom();
        posicionaArmadilhaRandom();

        // 3 elixires para o jogador coletar
        posicionaElixir();
        posicionaElixir();
        posicionaElixir();
    }

    // numero aleatorio entre 0 e 9
    private int colunaRandom() {
        return (int) (Math.random() * 10);
    }

    // numero aleatorio entre 0 e 4
    private int linhaRandom() {
        return (int) (Math.random() * 5);
    }

    private void posicionaMonstro() {
        // posiciona 5 monstros, 1 em cada linha
        for (int i = 0; i<5; i++) {
            int col = colunaRandom();
            // garante que o monstro nao ira sobrepor o spawn do jogador ou do chefao
            while (tabuleiro[i][col] != '*') {
                col = colunaRandom();
            }
            tabuleiro[i][colunaRandom()] = 'M';
        }
    }

    // posiciona armadilhas em local aleatorio, sem sobrepor outras entidades
    private void posicionaArmadilhaN() {
        int col = colunaRandom();
        int lin = linhaRandom();
        while(tabuleiro[lin][col] != '*'){
            col = colunaRandom();
            lin = linhaRandom();
        }
        tabuleiro[lin][col] = 'A';
    }
    private void posicionaArmadilhaRandom() {
        int col = colunaRandom();
        int lin = linhaRandom();
        while(tabuleiro[lin][col] != '*'){
            col = colunaRandom();
            lin = linhaRandom();
        }
        tabuleiro[lin][col] = 'R';
    }

    private void posicionaElixir() {
        int col = colunaRandom();
        int lin = linhaRandom();
        while(tabuleiro[lin][col] != '*'){
            col = colunaRandom();
            lin = linhaRandom();
        }
        tabuleiro[lin][col] = 'e';
    }


    // funcao para exibir o tabuleiro no console (debug)
    public void imprimirTabuleiro() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}
