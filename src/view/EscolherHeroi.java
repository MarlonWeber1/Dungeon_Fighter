package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelDominio.Barbaro;
import modelDominio.Guerreiro;
import modelDominio.Heroi;
import modelDominio.Paladino;

/**
 * @author marlon
 *
 */
public class EscolherHeroi extends JFrame implements ActionListener {

    private final JRadioButton rbHeroi1;
    private final JRadioButton rbHeroi2;
    private final JRadioButton rbHeroi3;
    private final JButton btnContinuar;
    private final JLabel lblNomeUsuario;
    private Heroi heroiSelecionado;
    private JLabel lblGuerreiro;
    private JLabel lblPaladino;
    private JLabel lblBarbaro;

    public EscolherHeroi(String nomeUsuario) {
        // Configuração da janela
        setTitle("Escolher Herói");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Construtor que recebe o nome do usuário
        lblNomeUsuario = new JLabel("Bem-vindo, " + nomeUsuario);
        lblNomeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeUsuario.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        lblNomeUsuario.setForeground(Color.WHITE); // Configura a cor do texto do JLabel
        lblNomeUsuario.setOpaque(true); // Necessário para que a cor de fundo funcione
        lblNomeUsuario.setBackground(Color.DARK_GRAY);

        configuraImagem();

        // Configura o layout dos botões
        JPanel pEscolherHeroi = new JPanel();
        pEscolherHeroi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(110, 110, 110, 110);
        pEscolherHeroi.setBackground(Color.DARK_GRAY); // Configura a cor de fundo do painel

        // Declaração dos componentes
        rbHeroi1 = new JRadioButton("Guerreiro");
        rbHeroi2 = new JRadioButton("Paladino");
        rbHeroi3 = new JRadioButton("Barbaro");

        // Configura a cor dos textos e do fundo dos JRadioButtons
        rbHeroi1.setForeground(Color.WHITE);
        rbHeroi2.setForeground(Color.WHITE);
        rbHeroi3.setForeground(Color.WHITE);
        rbHeroi1.setBackground(Color.DARK_GRAY);
        rbHeroi2.setBackground(Color.DARK_GRAY);
        rbHeroi3.setBackground(Color.DARK_GRAY);

        // Agrupamento dos radio buttons em um ButtonGroup
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbHeroi1);
        grupo.add(rbHeroi2);
        grupo.add(rbHeroi3);

        // Adiciona os radio buttons ao painel com GridBagConstraints
        c.gridx = 0;
        c.gridy = 0;
        pEscolherHeroi.add(rbHeroi1, c);

        c.gridx = 1;
        pEscolherHeroi.add(rbHeroi2, c);

        c.gridx = 2;
        pEscolherHeroi.add(rbHeroi3, c);

        // Configuração das ações dos radio buttons
        rbHeroi1.addActionListener(e -> {
            // Se "Guerreiro" for selecionado
            heroiSelecionado = new Guerreiro(90, 70, 140, nomeUsuario);
        });

        rbHeroi2.addActionListener(e -> {
            // Se "Paladino" for selecionado
            heroiSelecionado = new Paladino(60, 60, 180, nomeUsuario);
        });

        rbHeroi3.addActionListener(e -> {
            // Se "Barbaro" for selecionado
            heroiSelecionado = new Barbaro(50, 100, 150, nomeUsuario);
        });

        // Painéis principais para imagem e botões
        JPanel pPrincipal = new JPanel();
        pPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);
        pPrincipal.setBackground(Color.DARK_GRAY); // Configura a cor de fundo do painel principal

        // Adiciona as imagens e os botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        pPrincipal.add(lblGuerreiro, gbc);

        gbc.gridx = 1;
        pPrincipal.add(lblPaladino, gbc);

        gbc.gridx = 2;
        pPrincipal.add(lblBarbaro, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        pPrincipal.add(rbHeroi1, gbc);

        gbc.gridx = 1;
        pPrincipal.add(rbHeroi2, gbc);

        gbc.gridx = 2;
        pPrincipal.add(rbHeroi3, gbc);

        // Adiciona o painel principal ao frame
        add(pPrincipal, BorderLayout.CENTER);

        // Botão Continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setForeground(Color.black); // Configura a cor do texto do botão
        btnContinuar.setBackground(Color.white); // Configura a cor de fundo do botão
        btnContinuar.addActionListener(this);

        // Painel para o botão Continuar
        JPanel pBotao = new JPanel();
        pBotao.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pBotao.add(btnContinuar);
        pBotao.setBackground(Color.DARK_GRAY); // Configura a cor de fundo do painel do botão

        // Adiciona o painel com o botão ao frame
        add(pBotao, BorderLayout.SOUTH);

        // Adiciona ao painel o Bem-vindo, lblNomeUsuario
        add(lblNomeUsuario, BorderLayout.NORTH);

        setVisible(true);
    }

    public void configuraImagem() {
        try {
            // Carrega as imagens e escala-as
            ImageIcon imagemPaladino = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
            ImageIcon imagemGuerreiro = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
            ImageIcon imagemBarbaro = new ImageIcon(getClass().getResource("/view/img/barbaro.png"));

            Image paladino = imagemPaladino.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            Image guerreiro = imagemGuerreiro.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            Image barbaro = imagemBarbaro.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);

            // Atualiza os ImageIcons com as imagens escaladas
            imagemPaladino = new ImageIcon(paladino);
            imagemGuerreiro = new ImageIcon(guerreiro);
            imagemBarbaro = new ImageIcon(barbaro);

            // Inicialize os JLabels com as imagens escaladas
            lblPaladino = new JLabel(imagemPaladino);
            lblGuerreiro = new JLabel(imagemGuerreiro);
            lblBarbaro = new JLabel(imagemBarbaro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnContinuar) {
                if (rbHeroi1.isSelected() || rbHeroi2.isSelected() || rbHeroi3.isSelected()) {
                    // Se algum radio button estiver selecionado, abre a nova tela
                    DetalhesHeroi detalhesHeroi = new DetalhesHeroi(heroiSelecionado);
                    detalhesHeroi.setVisible(true);

                    System.out.println("" + heroiSelecionado);
                    this.dispose(); // Fecha a janela atual
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, selecione um herói.");
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao processar a ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
