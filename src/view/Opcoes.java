package view;

import modelDominio.ComecarJogo;
import modelDominio.Guerreiro;
import modelDominio.Heroi;
import modelDominio.Paladino;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author marlon
 *
 */
public class Opcoes extends JFrame implements ActionListener {

    private JButton btnNovoJogo;
    private JButton btnReiniciarJogo;
    private JButton btnSair;
    private Heroi heroi;

    public Opcoes(Heroi heroi) {
        // Características da Janela Principal
        setTitle("Opções");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        this.heroi = heroi;

        // Definição das restrições do GridBagLayout
        GridBagConstraints c = new GridBagConstraints();

        // Painel
        JPanel pBotoes = new JPanel(new GridBagLayout());

        // Configura os botões
        btnNovoJogo = new JButton("Novo Jogo");
        btnReiniciarJogo = new JButton("Reiniciar Jogo");
        btnSair = new JButton("Sair");

        btnNovoJogo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReiniciarJogo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Configura as restrições dos botões no GridBagLayout
        c.insets = new Insets(20, 15, 20, 15); // Espaçamento entre os botões

        c.gridx = 0;
        c.gridy = 0;
        pBotoes.add(btnNovoJogo, c);

        c.gridx = 1;
        c.gridy = 0;
        pBotoes.add(btnReiniciarJogo, c);

        c.gridx = 2;
        c.gridy = 0;
        pBotoes.add(btnSair, c);

        // Insere painel na janela
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;  // Centralizar o painel de botões
        add(pBotoes, c);

        // Adiciona ActionListener aos botões
        btnNovoJogo.addActionListener(this);
        btnReiniciarJogo.addActionListener(this);
        btnSair.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnSair) {
                this.dispose();  // Fecha a janela
            } else if (e.getSource() == btnNovoJogo) {
                this.dispose();
                Iniciar dungeonFighter = new Iniciar();
                dungeonFighter.mostrarJanela(); // Abre a nova tela para iniciar um novo jogo
            } else if (e.getSource() == btnReiniciarJogo) {
                // Lógica para reiniciar o jogo
                new EscolherHeroi(heroi.getNome());
                dispose();
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao processar a ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }
}
