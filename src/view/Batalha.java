package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Batalha extends JFrame {

    private JButton Atacar;
    private JButton Elixir;
    private JButton Habilidade;
    private JPanel BatalhaPanel;

    public Batalha(Heroi heroiSelecionado, Monstro monstro) {
        setContentPane(BatalhaPanel);
        setTitle("Batalha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        Atacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Batalha.this, "Hello world");
            }
        });

        ImageIcon imagemHeroi;

        if (heroiSelecionado instanceof Barbaro) {
            imagemHeroi = new ImageIcon(getClass().getResource("view/img/barbaro.png"));
        } else if (heroiSelecionado instanceof Paladino) {
            imagemHeroi = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        } else {
            imagemHeroi = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
        }

        Image imagem = imagemHeroi.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        imagemHeroi = new ImageIcon(imagem);

    }

    public static void main(String[] args) {
        MonstroComum monstro = new MonstroComum();
        Guerreiro heroi = new Guerreiro(100, 100, 100, "jvtips");


        new Batalha(heroi , monstro);
    }
}
