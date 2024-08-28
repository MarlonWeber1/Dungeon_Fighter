package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * @author marlon
 **/

public final class Dungeon_Fighter extends JFrame implements ActionListener {

    private JButton btnLogin;
    private JTextField textoNomeUsuario;
    private ImageIcon logoJogo;

    public void mostrarJanela() {

        // Características da Janela Principal
        setTitle("Dungeon Fighter");
        setSize(600, 400); // Tamanho aumentado para ajustar melhor o layout
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.white);

        // Definição das restrições do GridBagLayout
        GridBagConstraints c = new GridBagConstraints();

        // Configura a imagem
        configuraImagem(c);

        // Declaração de componentes
        JLabel nomeUsuario = new JLabel("Nome");

        textoNomeUsuario = new JTextField(15); 
        textoNomeUsuario.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
        textoNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Palatino Linotype", Font.BOLD, 16));

        // Configurações dos outros componentes (centro)
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 10, 0); // Espaçamento entre os componentes
        add(nomeUsuario, c);
        nomeUsuario.setFont(new Font("Palatino Linotype", Font.BOLD, 16));

        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL; // Preenche horizontalmente
        add(textoNomeUsuario, c);

        c.gridy = 3;
        c.fill = GridBagConstraints.NONE; // Sem preenchimento
        c.insets = new Insets(20, 0, 20, 0); // Aumenta o espaçamento acima do botão
        add(btnLogin, c);

        // Adiciona o ActionListener ao botão
        btnLogin.addActionListener(this);

        textoNomeUsuario.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });

        setVisible(true);
    }

    private void configuraImagem(GridBagConstraints c) {
        try {
            JPanel pJogo = new JPanel();
            pJogo.setLayout(new GridBagLayout());

            // Carrega a imagem, redimensiona e cria o JLabel para exibi-la
            logoJogo = new ImageIcon(getClass().getResource("/view/img/logo.png"));
            Image imagemRedimensionada = logoJogo.getImage().getScaledInstance(380, 180, Image.SCALE_SMOOTH); // Ajusta o tamanho da imagem
            JLabel lbllogoJogo = new JLabel(new ImageIcon(imagemRedimensionada));

            // Centraliza o JLabel dentro do JPanel
            lbllogoJogo.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Adiciona a imagem ao painel correspondente
            pJogo.add(lbllogoJogo);

            // Configura o visual do painel
            pJogo.setBackground(Color.WHITE); // Nova cor de fundo

            // Adiciona o painel ao JFrame
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;
            c.insets = new Insets(10, 0, 30, 0); // Espaçamento inferior para distanciar da label "Nome"
            add(pJogo, c);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnLogin) {
                if (textoNomeUsuario.getText() != null && !textoNomeUsuario.getText().isEmpty()) {
                    this.dispose();
                    String nomeUsuario = textoNomeUsuario.getText();
                    new EscolherHeroi(nomeUsuario); // Chamada simplificada
                } else {
                    JOptionPane.showMessageDialog(this, "Verifique se as informações estão preenchidas corretamente.");
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao processar a ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
