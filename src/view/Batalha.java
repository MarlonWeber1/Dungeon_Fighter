package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;

public class Batalha extends JFrame {

    private JLabel lblImagemHeroi;
    private JLabel lblImagemMonstro;
    private Heroi heroi;
    private Monstro monstro;
    private JButton btnAtaque;
    private JButton btnElixir;
    private JButton btnHabilidade;

    public Batalha(Heroi heroiSelecionado, Monstro monstro) {
        this.heroi = heroiSelecionado;
        this.monstro = monstro;

        setTitle("Batalha");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 

        // Ajuste na Imagem do Herói
        ImageIcon iHeroi;
        if (heroiSelecionado instanceof Barbaro) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/barbaro.png"));
        } else if (heroiSelecionado instanceof Paladino) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        } else {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
        }

        Image h = iHeroi.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
        iHeroi = new ImageIcon(h);
        lblImagemHeroi = new JLabel(iHeroi);
        lblImagemHeroi.setOpaque(true);
        lblImagemHeroi.setBackground(new Color(173, 216, 230)); // Fundo azul claro para o herói


        // Ajuste na Imagem do Monstro
        ImageIcon iMonstro;
        if (monstro instanceof MonstroComum) {
            iMonstro = new ImageIcon(getClass().getResource("/view/img/goblin.png"));
        } else {
            iMonstro = new ImageIcon(getClass().getResource("/view/img/pekka.png"));
        }

        Image m = iMonstro.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
        iMonstro = new ImageIcon(m);
        lblImagemMonstro = new JLabel(iMonstro);
        lblImagemMonstro.setOpaque(true);
        lblImagemMonstro.setBackground(new Color(70, 31, 36)); // Fundo vinho para o monstro


        // Painel para as imagens do herói e monstro
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblImagemHeroi, gbc);

        gbc.gridx = 1;
        add(lblImagemMonstro, gbc);

        // Painel de Atributos do Herói
        JPanel pAtributosHeroi = new JPanel();
        pAtributosHeroi.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Atributos Herói",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)));
        pAtributosHeroi.setPreferredSize(new Dimension(10, 80));

        // Adiciona painel de atributos do herói abaixo da imagem do herói
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pAtributosHeroi, gbc);

        // Painel de Atributos do Monstro
        JPanel pAtributosMonstro = new JPanel();
        pAtributosMonstro.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Atributos Vilão",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)));
        pAtributosMonstro.setPreferredSize(new Dimension(10, 80));

        // Adiciona painel de atributos do monstro abaixo da imagem do monstro
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(pAtributosMonstro, gbc);

        // Painel de Botões
        JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Botão Ataque
        btnAtaque = new JButton("Atacar Mostro");
        btnAtaque.setPreferredSize(new Dimension(140, 30));
        btnAtaque.setFont(new Font("Arial", Font.BOLD, 12));
        pBotoes.add(btnAtaque);

        // Botão Habilidade
        btnHabilidade = new JButton("Usar Habilidade");
        btnHabilidade.setPreferredSize(new Dimension(140, 30));
        btnHabilidade.setFont(new Font("Arial", Font.BOLD, 12));
        pBotoes.add(btnHabilidade);

        // Botão Elixir
        btnElixir = new JButton("Tomar Elixir");
        btnElixir.setPreferredSize(new Dimension(140, 30));
        btnElixir.setFont(new Font("Arial", Font.BOLD, 12));
        pBotoes.add(btnElixir);

        // Adiciona o painel de botões na parte inferior da tela
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa duas colunas
        add(pBotoes, gbc);

        // Tratamento para o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(Batalha.this, "Você deve abater o monstro para voltar ao tabuleiro!");
            }
        });

        setVisible(true);
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
        
    }
}
