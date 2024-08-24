package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;

public class Batalha extends JFrame {

    private JLabel lblAtaqueUsuario;
    private JLabel lblSaudeUsuario;
    private JLabel lblDefesaUsuario;
    private JLabel lblElixir;
    
    private JLabel lblDefesaMonstro;
    private JLabel lblSaudeMonstro;
    private JLabel lblAtaqueMonstro;
    
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        
        lblAtaqueUsuario = new JLabel("Ataque: " + heroiSelecionado.getAtaque());
        lblSaudeUsuario = new JLabel("Saúde: " + heroiSelecionado.getSaude());
        lblDefesaUsuario = new JLabel("Defesa: " + heroiSelecionado.getDefesa());
        lblElixir = new JLabel("Bolsa de elixir: " + heroiSelecionado.getBolsaDeElixir());
        
        // Configura a fonte e cor do texto dos JLabel
        lblAtaqueUsuario.setForeground(Color.BLACK); 
        lblAtaqueUsuario.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        lblSaudeUsuario.setForeground(Color.BLACK); 
        lblSaudeUsuario.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        lblDefesaUsuario.setForeground(Color.BLACK); 
        lblDefesaUsuario.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        lblElixir.setForeground(Color.BLACK); 
        lblElixir.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
           
        // Painel de Atributos do Herói
        JPanel pAtributosHeroi = new JPanel();
        pAtributosHeroi.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Atributos Herói",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Palatino LinoType", Font.BOLD, 14)));
        pAtributosHeroi.setPreferredSize(new Dimension(10, 80));
        pAtributosHeroi.setLayout(new GridLayout(4, 2, 10, 10));
        
        // Adiciona os atributos do herói ao painel
        pAtributosHeroi.add(lblAtaqueUsuario);
        pAtributosHeroi.add(lblSaudeUsuario);
        pAtributosHeroi.add(lblDefesaUsuario);
        pAtributosHeroi.add(lblElixir);

        // Adiciona painel de atributos do herói abaixo da imagem do herói
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pAtributosHeroi, gbc);
        
        lblAtaqueMonstro = new JLabel("Ataque: " + monstro.getAtaque());
        lblSaudeMonstro = new JLabel("Saúde: " + monstro.getSaude());
        lblDefesaMonstro = new JLabel("Defesa: " + monstro.getAtaque());
        
        lblAtaqueMonstro.setForeground(Color.BLACK); 
        lblAtaqueMonstro.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        lblSaudeMonstro.setForeground(Color.BLACK); 
        lblSaudeMonstro.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        lblDefesaMonstro.setForeground(Color.BLACK); 
        lblDefesaMonstro.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        
        // Painel de Atributos do Monstro
        JPanel pAtributosMonstro = new JPanel();
        pAtributosMonstro.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Atributos Vilão",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Palatino LinoType", Font.BOLD, 14)));
        pAtributosMonstro.setPreferredSize(new Dimension(10, 80));
        pAtributosMonstro.setLayout(new GridLayout(3, 2, 10, 10)); // Configura o layout

        // Adiciona os atributos do monstro ao painel
        pAtributosMonstro.add(lblAtaqueMonstro);
        pAtributosMonstro.add(lblSaudeMonstro);
        pAtributosMonstro.add(lblDefesaMonstro);
        

        // Adiciona painel de atributos do monstro abaixo da imagem do monstro
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(pAtributosMonstro, gbc);

        // Painel de Botões
        JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Botão Ataque
        btnAtaque = new JButton("Atacar Mostro");
        btnAtaque.setPreferredSize(new Dimension(140, 30));
        btnAtaque.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        pBotoes.add(btnAtaque);

        // Botão Habilidade
        btnHabilidade = new JButton("Usar Habilidade");
        btnHabilidade.setPreferredSize(new Dimension(140, 30));
        btnHabilidade.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
        pBotoes.add(btnHabilidade);

        // Botão Elixir
        btnElixir = new JButton("Tomar Elixir");
        btnElixir.setPreferredSize(new Dimension(140, 30));
        btnElixir.setFont(new Font("Palatino LinoType", Font.BOLD, 12));
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
        
        // Listeners de Botão
        btnAtaque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoHeroi(0, monstro);
                atualizarAtributos();
            }
        });

        btnElixir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoHeroi(1, monstro);
                atualizarAtributos();
            }
        });
        
        btnHabilidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoHeroi(2, monstro);
                atualizarAtributos();
            }
        });

        setVisible(true);
    }

    private void atualizarAtributos() {
        lblAtaqueUsuario.setText("Ataque: " + heroi.getAtaque());
        lblSaudeUsuario.setText("Saúde: " + heroi.getSaude());
        lblDefesaUsuario.setText("Defesa: " + heroi.getDefesa());
        lblElixir.setText("Bolsa Elixir: " + heroi.getBolsaDeElixir());

        lblAtaqueMonstro.setText("Ataque: " + monstro.getAtaque());
        lblSaudeMonstro.setText("Saúde: " + monstro.getSaude());
        lblDefesaMonstro.setText("Defesa: " + monstro.getDefesa());
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
