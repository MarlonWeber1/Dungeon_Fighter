package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelDominio.*;

/**
 * @author marlo
 **/
public final class Dungeon_Fighter extends JFrame {

    public void mostrarJanela() {

        // Características da Janela Principal
        setTitle("Minha primeira aplicação");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Definição das restrições do GridBagLayout
        GridBagConstraints c = new GridBagConstraints();

        // Painéis
        JPanel pLogin = new JPanel();
        JPanel pJogo = new JPanel();
        JPanel pBotao = new JPanel();
        pLogin.setLayout(new GridBagLayout());
        pJogo.setLayout(new GridBagLayout());
        pBotao.setLayout(new GridBagLayout());

        // Declaração de componentes
        JLabel nomeJogo = new JLabel("Dungeon Fighter");
        JLabel nomeUsuario = new JLabel("Nome");
        JTextField textoNomeUsuario = new JTextField(15); 
        JButton btn = new JButton("Login");

        // Configurações visuais dos painéis
        /*pJogo.setBackground(Color.green);  
        pLogin.setBackground(Color.red);  
        pBotao.setBackground(Color.blue);*/

        // Configurações do nome do jogo (topo direito)
        c.gridx = 1;  
        c.gridy = 1;  
        c.weightx = 1;  
        c.weighty = 1;  
        c.anchor = GridBagConstraints.NORTHWEST; // Alinha o nome do jogo no canto superior esquerdo
        pJogo.add(nomeJogo, c);
        nomeJogo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        // Configurações dos outros componentes (centro)
        c.gridx = 0;
        c.gridy = 0;  
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 10, 0); // Espaçamento entre os componentes
        pLogin.add(nomeUsuario, c);
        nomeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL; // Preenche horizontalmente
        pLogin.add(textoNomeUsuario, c);
        textoNomeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        c.gridy = 2;
        c.fill = GridBagConstraints.NONE; // Sem preenchimento
        pBotao.add(btn, c);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Adiciona os painéis ao JFrame
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST; // Mantém o painel pJogo no canto superior esquerdo
        add(pJogo, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;  // Definindo a largura do grid para o pLogin
        c.anchor = GridBagConstraints.CENTER;  // Centralizar o painel de login
        add(pLogin, c);

        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;  // Centralizar o painel de login
        add(pBotao, c);

        setVisible(true);
    }

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(true);
        tabuleiro.imprimirTabuleiro();
        int spawnHeroi = tabuleiro.getColunaInicial();

        System.out.println(spawnHeroi);

        Dungeon_Fighter mainFrameApp = new Dungeon_Fighter();
        mainFrameApp.mostrarJanela();
    }
}
