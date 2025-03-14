package view;

import modelDominio.ComecarJogo;
import modelDominio.Guerreiro;
import modelDominio.Paladino;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import view.Dungeon_Fighter;

/**
 * @author marlon
 **/

public class Iniciar extends JFrame implements ActionListener {
    
    private JButton btnJogar;
    private JButton btnSair;
    private JButton btnDebug;

    public void mostrarJanela(){
    
        // Características da Janela Principal
        setTitle("Inicio");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Definição das restrições do GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        
        // Painel
        JPanel pBotoes = new JPanel(new GridBagLayout());

        // Configura os botões
        btnJogar = new JButton("Jogar");
        btnDebug = new JButton("Debug");
        btnSair = new JButton("Sair");
        
        btnJogar.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
        btnDebug.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
        btnSair.setFont(new Font("Palatino Linotype", Font.BOLD, 12));

        // Configura as restrições dos botões no GridBagLayout
        c.insets = new Insets(20, 15, 20, 15); // Espaçamento entre os botões

        c.gridx = 0;
        c.gridy = 0;
        pBotoes.add(btnJogar, c);

        c.gridx = 1;
        c.gridy = 0;
        pBotoes.add(btnDebug, c);

        c.gridx = 2;
        c.gridy = 0;
        pBotoes.add(btnSair, c);
                  
        // Insere painel na janela
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;  // Centralizar o painel de botões
        add(pBotoes, c);
        
        // Adiciona ActionListener aos botões
        btnJogar.addActionListener(this);
        btnDebug.addActionListener(this);
        btnSair.addActionListener(this);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == btnSair) {
                this.dispose();  // Fecha a janela
            } else if (e.getSource() == btnJogar) {
                this.dispose();
                Dungeon_Fighter dungeonFighter = new Dungeon_Fighter();
                dungeonFighter.mostrarJanela(); // Abre a nova tela
            } else if (e.getSource() == btnDebug) {
                // Fecha a tela atual
                this.dispose();

                // Configura o herói de forma padrao para funcao debug
                Paladino heroi = new Paladino(150, 150, 150, "Debug");
                Jogo jogo = new Jogo(true, heroi, null);

                // Cria a janela de debug
                jogo.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // Reabre a tela inicial quando a janela de debug for fechada
                        Iniciar iniciar = new Iniciar();
                        iniciar.mostrarJanela();
                    }
                });
                jogo.setVisible(true);
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao processar a ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Iniciar iniciar = new Iniciar();
        iniciar.mostrarJanela();
    }
}