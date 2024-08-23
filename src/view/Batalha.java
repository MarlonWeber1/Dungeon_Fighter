package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelDominio.Barbaro;
import modelDominio.Heroi;
import modelDominio.Paladino;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Batalha extends JFrame {

    private JButton Atacar;
    private JButton Elixir;
    private JButton Habilidade;
    private JPanel BatalhaPanel;
    private JLabel imgHeroiLabel;
    private JLabel imgMonstroLabel;
    private Heroi heroi;
    private Monstro monstro;

    public Batalha(Heroi heroiSelecionado, Monstro monstro) {
        setContentPane(BatalhaPanel);
        setTitle("Batalha");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(Batalha.this, "Você deve abater o monstro para voltar ao tabuleiro!");
            }
        });

        // seleciona a imagem de acordo com o heroi selecionado
        ImageIcon iHeroi;
        if (heroiSelecionado instanceof Barbaro) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/barbaro.png"));
        } else if (heroiSelecionado instanceof Paladino) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        } else {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
        }

        // Redimensiona a imagem para 200x200 pixels
        Image imagemHeroi  = iHeroi.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        imgHeroiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon iMonstro;

        // seleciona o tipo de monstro para a imagem
        if (monstro instanceof MonstroComum) {
            iMonstro = new ImageIcon(getClass().getResource("/view/img/goblin.png"));
        } else {
            iMonstro = new ImageIcon(getClass().getResource("/view/img/pekka.png"));
        }

        // Redimensiona a imagem para 200x200 pixels
        Image imagemMonstro = iMonstro.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);


        // Cria um novo ImageIcon com a imagem redimensionada
        iMonstro = new ImageIcon(imagemMonstro);
        iHeroi = new ImageIcon(imagemHeroi);

        imgMonstroLabel.setIcon(iMonstro);
        imgMonstroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imgHeroiLabel.setIcon(iHeroi);
        imgMonstroLabel.setIcon((iMonstro));

        this.heroi = heroiSelecionado;
        this.monstro = monstro;

        Atacar.addActionListener(e -> {
            acaoHeroi(0, monstro);
        });

        Elixir.addActionListener(e -> {
            JOptionPane.showMessageDialog(Batalha.this, "Bebendo elixir");
            acaoHeroi(1, monstro);
        });

        Habilidade.addActionListener(e -> {
            JOptionPane.showMessageDialog(Batalha.this, "Habilidade usada");
            acaoHeroi(2, monstro);
        });

    }


    public void acaoHeroi (int identificaAcao, Monstro monstro) {

        // 0 -> atacar
        // 1 -> tomar elixir
        // 2 -> habilidade especial

        if (identificaAcao == 0) {
            heroi.atacar(monstro);
        }
        else if (identificaAcao == 1) {
            heroi.tomarElixir();
        }
        else if (identificaAcao == 2) {
            heroi.ataqueEspecial();
        }


        if (monstro.estaVivo()) {
            monstro.atacar(heroi);
        }
        else if (!monstro.estaVivo()) {
            // MONSTRO DERROTADO

            // popup -> voce matou monstro

            // fecha a tela quando fechar o popup
            // a funcao mover continua (o heroi vai pra celula do monstro)
            this.dispose();
            return;
        }

        if (!heroi.estaVivo()) {
            // GAME OVER

            // popup o mostro abateu voce
            // tela de batalha fecha junto com o popup

            // new gameOver (tela)
                // tela com 3 opcoes
                // novo jogo, tentar novamente, sair

        }
        else if (!monstro.estaVivo()) {
            // MONSTRO DERROTADO

            // popup -> voce matou monstro

            // fecha a tela quando fechar o popup
            // a funcao mover continua (o heroi vai pra celula do monstro)
        }
    }




    public static void main(String[] args) {
        MonstroComum monstro = new MonstroComum();
        Barbaro heroi = new Barbaro(150, 150, 150, "jvtips");

        new Batalha(heroi,monstro);
    }
}
