package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelDominio.Guerreiro;
import modelDominio.Heroi;

public class EscolherHeroi extends JFrame implements ActionListener {

    private JRadioButton rbHeroi1;
    private JRadioButton rbHeroi2;
    private JRadioButton rbHeroi3;
    private JButton btnContinuar;
    private JLabel lblNomeUsuario;

    public EscolherHeroi(String nomeUsuario) {
        // Construtor que recebe o nome do usuário
        lblNomeUsuario = new JLabel("Bem-vindo, " + nomeUsuario);
        lblNomeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);

        // Configuração da janela
        setTitle("Escolher Herói");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 

        // Carrega as imagens e cria JLabels para exibi-las
        ImageIcon imagemPaladino = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        ImageIcon imagemGuerreiro = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        ImageIcon imagemBarbaro = new ImageIcon(getClass().getResource("/view/img/paladino.png"));

        JLabel lblPaladino = new JLabel(imagemPaladino);
        JLabel lblGuerreiro = new JLabel(imagemGuerreiro);
        JLabel lblBarbaro = new JLabel(imagemBarbaro);

        // Painéis para as imagens
        JPanel pPaladinoImg = new JPanel();
        JPanel pGuerreiroImg = new JPanel();
        JPanel pBarbaroImg = new JPanel();

        // Configurações visuais dos painéis
        pPaladinoImg.setPreferredSize(new Dimension(150, 150)); 
        pGuerreiroImg.setPreferredSize(new Dimension(150, 150)); 
        pBarbaroImg.setPreferredSize(new Dimension(150, 150)); 

        // Adiciona as imagens aos painéis correspondentes
        pPaladinoImg.add(lblPaladino);
        pGuerreiroImg.add(lblGuerreiro);
        pBarbaroImg.add(lblBarbaro);

        // Configura o layout dos botões
        JPanel pEscolherHeroi = new JPanel();
        pEscolherHeroi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(110, 110, 110, 110); 

        // Declaração dos componentes
        rbHeroi1 = new JRadioButton("Guerreiro");
        rbHeroi2 = new JRadioButton("Paladino");
        rbHeroi3 = new JRadioButton("Barbaro");

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

        // Painéis principais para imagem e botões
        JPanel pPrincipal = new JPanel();
        pPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30); 

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
        btnContinuar.addActionListener(this);

        // Painel para o botão Continuar
        JPanel pBotao = new JPanel();
        pBotao.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
        pBotao.add(btnContinuar);

        // Adiciona o painel com o botão ao frame
        add(pBotao, BorderLayout.SOUTH);

        // Adiciona ao painel o Bem-vindo, lblNomeUsuario
        add(lblNomeUsuario, BorderLayout.NORTH);

        // Torna a janela visível
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnContinuar) {
            System.out.println("botao clicado");
            if (rbHeroi1.isSelected() || rbHeroi2.isSelected() || rbHeroi3.isSelected()) {
                // Se algum radio button estiver selecionado, abre a nova tela
                System.out.println("Herói selecionado");
                // Implementar a lógica para abrir a nova tela
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um herói.");
            }
        }
    }
}
