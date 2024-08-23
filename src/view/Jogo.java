package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;

public class Jogo extends JFrame {
    private Heroi heroi;
    private ComecarJogo comecarJogo;
    private JPanel panel1;

    public Jogo() {
        // Inicializa o herói e o jogo
        Guerreiro heroi = new Guerreiro(100,100,100,"sdsd");
        comecarJogo = new ComecarJogo(heroi, false); // false se não quiser modo de depuração

        // Configura o JFrame
        setTitle("Jogo de Tabuleiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setLayout(new BorderLayout()); // Usando BorderLayout para adicionar componentes de forma organizada

        // Adiciona o tabuleiro ao centro do JFrame
        add(comecarJogo.tabuleiro, BorderLayout.CENTER);

        // Cria um painel lateral para os botões
        JPanel painelLateral = new JPanel();
        painelLateral.setLayout(new GridLayout(1, 3, 10, 10)); // 3 linhas, 1 coluna, com espaçamento

        // Cria os botões e adiciona-os ao painel lateral
        JButton botao1 = new JButton("Botão 1");
        JButton botao2 = new JButton("Botão 2");
        JButton botao3 = new JButton("Botão 3");

        painelLateral.add(botao1);
        painelLateral.add(botao2);
        painelLateral.add(botao3);

        // Adiciona o painel lateral à direita do JFrame
        add(painelLateral, BorderLayout.SOUTH);

        // Exibir a janela
        setVisible(true);
    }

    public static void main(String[] args) {
        // Executa o jogo na thread de eventos Swing para evitar problemas de concorrência
        SwingUtilities.invokeLater(() -> new Jogo());
    }
}
