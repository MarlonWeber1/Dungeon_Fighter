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
 **/

class DetalhesHeroi extends JFrame {

    private final JLabel lblNomeUsuario;
    private final JLabel lblClasseUsuario;
    private final JLabel lblAtaqueUsuario;
    private final JLabel lblSaudeUsuario;
    private final JLabel lblDefesaUsuario;
    private final JLabel lblImagemHeroi;
    private final JButton btnContinuar;

    private JLabel lblValorAtaque;
    private JLabel lblValorSaude;
    private JLabel lblValorDefesa;
    private JLabel lblPontosRestantes;

    private int pontosRestantes = 0; // Pontos que podem ser distribuídos
    
    

    DetalhesHeroi(Heroi heroiSelecionado) {

        setTitle("Detalhes Herói");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Valores iniciais dos atributos
        final double ataqueInicial = heroiSelecionado.getAtaque();
        final double saudeInicial = heroiSelecionado.getSaude();
        final double defesaInicial = heroiSelecionado.getDefesa();

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel pEsquerdo = new JPanel();
        pEsquerdo.setLayout(new BoxLayout(pEsquerdo, BoxLayout.Y_AXIS));
        pEsquerdo.setPreferredSize(new Dimension(250, getHeight()));
        pEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pEsquerdo.setBackground(Color.DARK_GRAY);

        // Adicionando padding interno ao painelEsquerdo
        pEsquerdo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Painel para a imagem do herói com borda
        JPanel pImagemHeroi = new JPanel();
        pImagemHeroi.setPreferredSize(new Dimension(200, 200));
        pImagemHeroi.setMaximumSize(new Dimension(200, 200));
        pImagemHeroi.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pImagemHeroi.setAlignmentX(Component.CENTER_ALIGNMENT);
        pImagemHeroi.setBackground(Color.LIGHT_GRAY);

        // Determine a imagem com base no herói selecionado
        ImageIcon iHeroi;
        if (heroiSelecionado instanceof Barbaro) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/barbaro.png"));
        } else if (heroiSelecionado instanceof Paladino) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        } else {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
        }

        // Ajuste na Imagem do Herói
        Image i = iHeroi.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        iHeroi = new ImageIcon(i);
        lblImagemHeroi = new JLabel(iHeroi);
        lblImagemHeroi.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona a imagem do herói ao painel da imagem
        pImagemHeroi.add(lblImagemHeroi);

        // Preenchendo os labels com as informações do herói selecionado
        lblNomeUsuario = new JLabel("Nome: " + heroiSelecionado.getNome());
        lblClasseUsuario = new JLabel("Classe: " + heroiSelecionado.getClass().getSimpleName());
        lblAtaqueUsuario = new JLabel("Ataque: " + heroiSelecionado.getAtaque());
        lblSaudeUsuario = new JLabel("Saúde: " + heroiSelecionado.getSaude());
        lblDefesaUsuario = new JLabel("Defesa: " + heroiSelecionado.getDefesa());
        
        // Configura a fonte e cor do texto dos JLabel
        lblNomeUsuario.setForeground(Color.WHITE);
        lblNomeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblClasseUsuario.setForeground(Color.WHITE); 
        lblClasseUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAtaqueUsuario.setForeground(Color.WHITE); 
        lblAtaqueUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSaudeUsuario.setForeground(Color.WHITE); 
        lblSaudeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDefesaUsuario.setForeground(Color.WHITE); 
        lblDefesaUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Centralizar os textos
        lblNomeUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblClasseUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAtaqueUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaudeUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDefesaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionar espaçamento entre os labels
        lblNomeUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblClasseUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblAtaqueUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblSaudeUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblDefesaUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));

        // Adiciona o painel da imagem e os labels ao painel esquerdo
        pEsquerdo.add(Box.createVerticalStrut(10)); // Espaço superior
        pEsquerdo.add(pImagemHeroi);
        pEsquerdo.add(Box.createVerticalStrut(20)); // Espaço entre a imagem e o texto
        pEsquerdo.add(lblNomeUsuario);
        pEsquerdo.add(lblClasseUsuario);
        pEsquerdo.add(lblAtaqueUsuario);
        pEsquerdo.add(lblSaudeUsuario);
        pEsquerdo.add(lblDefesaUsuario);

        // Painel direito para os botões e atributos
        JPanel pDireita = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Pontos Restantes no canto superior direito
        lblPontosRestantes = new JLabel("Pontos Restantes: " + pontosRestantes);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        pDireita.add(lblPontosRestantes, gbc);

        // Botões e labels de Ataque
        JLabel lblAtaque = new JLabel("Ataque");
        gbc.gridx = 1;
        gbc.gridy = 1;
        pDireita.add(lblAtaque, gbc);

        JButton btnAtaqueMenos = new JButton("-");
        gbc.gridx = 0;
        gbc.gridy = 2;
        pDireita.add(btnAtaqueMenos, gbc);

        lblValorAtaque = new JLabel(String.valueOf(heroiSelecionado.getAtaque()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        pDireita.add(lblValorAtaque, gbc);

        JButton btnAtaqueMais = new JButton("+");
        gbc.gridx = 2;
        gbc.gridy = 2;
        pDireita.add(btnAtaqueMais, gbc);

        // Botões e labels de Saúde
        JLabel lblSaude = new JLabel("Saúde");
        gbc.gridx = 1;
        gbc.gridy = 3;
        pDireita.add(lblSaude, gbc);

        JButton btnSaudeMenos = new JButton("-");
        gbc.gridx = 0;
        gbc.gridy = 4;
        pDireita.add(btnSaudeMenos, gbc);

        lblValorSaude = new JLabel(String.valueOf(heroiSelecionado.getSaude()));
        gbc.gridx = 1;
        gbc.gridy = 4;
        pDireita.add(lblValorSaude, gbc);

        JButton btnSaudeMais = new JButton("+");
        gbc.gridx = 2;
        gbc.gridy = 4;
        pDireita.add(btnSaudeMais, gbc);

        // Botões e labels de Defesa
        JLabel lblDefesa = new JLabel("Defesa");
        gbc.gridx = 1;
        gbc.gridy = 5;
        pDireita.add(lblDefesa, gbc);

        JButton btnDefesaMenos = new JButton("-");
        gbc.gridx = 0;
        gbc.gridy = 6;
        pDireita.add(btnDefesaMenos, gbc);

        lblValorDefesa = new JLabel(String.valueOf(heroiSelecionado.getDefesa()));
        gbc.gridx = 1;
        gbc.gridy = 6;
        pDireita.add(lblValorDefesa, gbc);

        JButton btnDefesaMais = new JButton("+");
        gbc.gridx = 2;
        gbc.gridy = 6;
        pDireita.add(btnDefesaMais, gbc);
        
        // Painel inferior para botões "Continuar" 
        JPanel pInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinhado à esquerda
        pInferior.setPreferredSize(new Dimension(100, 50));
        pInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botão Continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setOpaque(true);
        btnContinuar.setEnabled(false); // Inicialmente desabilitado
        pInferior.add(btnContinuar);

        // Adicionando o painel inferior ao painel direito
        painelPrincipal.add(pInferior, BorderLayout.SOUTH);

        // Adiciona o painel esquerdo e direito ao painel principal
        painelPrincipal.add(pEsquerdo, BorderLayout.WEST);
        painelPrincipal.add(pDireita, BorderLayout.CENTER);
        getContentPane().add(painelPrincipal);

        // Método para verificar se todos os pontos foram distribuídos
        verificarPontosDistribuidos();

        // Listeners de Botão
        btnAtaqueMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pontosRestantes > 0) {
                    heroiSelecionado.incrementaAtaque();
                    lblValorAtaque.setText(String.valueOf(heroiSelecionado.getAtaque()));
                    pontosRestantes = pontosRestantes - 10;
                    lblPontosRestantes.setText("Pontos Restantes: " + pontosRestantes);
                    double ataqueDistribuido = Double.parseDouble(lblValorAtaque.getText()) 
                        - heroiSelecionado.getAtaque();
                    heroiSelecionado.setAtaque(heroiSelecionado.getAtaque() + ataqueDistribuido);
                    lblAtaqueUsuario.setText("Ataque: " + heroiSelecionado.getAtaque());
                    verificarPontosDistribuidos();
                }
            }
        });
        

        btnAtaqueMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (heroiSelecionado.getAtaque() > ataqueInicial) {
                    heroiSelecionado.decrementaAtaque();
                    lblValorAtaque.setText(String.valueOf(heroiSelecionado.getAtaque()));
                    pontosRestantes = pontosRestantes + 10;
                    lblPontosRestantes.setText("Pontos Restantes: " + pontosRestantes);
                    double ataqueDistribuido = Double.parseDouble(lblValorAtaque.getText()) 
                        - heroiSelecionado.getAtaque();
                    heroiSelecionado.setAtaque(heroiSelecionado.getAtaque() + ataqueDistribuido);
                    lblAtaqueUsuario.setText("Ataque: " + heroiSelecionado.getAtaque());
                    verificarPontosDistribuidos();
                }
            }
        });

        btnSaudeMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pontosRestantes > 0) {
                    heroiSelecionado.incrementaSaude();
                    lblValorSaude.setText(String.valueOf(heroiSelecionado.getSaude()));
                    pontosRestantes = pontosRestantes - 10;
                    lblPontosRestantes.setText("Pontos Restantes: " + pontosRestantes);
                    double saudeDistribuida = Double.parseDouble(lblValorSaude.getText()) 
                        - heroiSelecionado.getSaude();
                    heroiSelecionado.setSaude(heroiSelecionado.getSaude() + saudeDistribuida);
                     lblSaudeUsuario.setText("Saúde: " + heroiSelecionado.getSaude());
                     verificarPontosDistribuidos();
                }
            }
        });

        btnSaudeMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (heroiSelecionado.getSaude() > saudeInicial) {
                    heroiSelecionado.decrementaSaude();
                    lblValorSaude.setText(String.valueOf(heroiSelecionado.getSaude()));
                    pontosRestantes = pontosRestantes + 10;
                    lblPontosRestantes.setText("Pontos Restantes: " + pontosRestantes);
                    double saudeDistribuida = Double.parseDouble(lblValorSaude.getText()) 
                        - heroiSelecionado.getSaude();
                    heroiSelecionado.setSaude(heroiSelecionado.getSaude() + saudeDistribuida);
                     lblSaudeUsuario.setText("Saúde: " + heroiSelecionado.getSaude());
                     verificarPontosDistribuidos();
                }
            }
        });

        btnDefesaMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pontosRestantes > 0) {
                    heroiSelecionado.incrementaDefesa();
                    lblValorDefesa.setText(String.valueOf(heroiSelecionado.getDefesa()));
                    pontosRestantes = pontosRestantes - 10;
                    lblPontosRestantes.setText("Pontos Restantes: " + pontosRestantes);
                    double defesaDistribuida = Double.parseDouble(lblValorDefesa.getText()) 
                        - heroiSelecionado.getDefesa();
                    heroiSelecionado.setDefesa(heroiSelecionado.getDefesa() + defesaDistribuida);
                     lblDefesaUsuario.setText("Defesa: " + heroiSelecionado.getDefesa());
                     verificarPontosDistribuidos();
                }
            }
        });

        btnDefesaMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (heroiSelecionado.getDefesa() > defesaInicial) {
                    heroiSelecionado.decrementaDefesa();
                    lblValorDefesa.setText(String.valueOf(heroiSelecionado.getDefesa()));
                    pontosRestantes = pontosRestantes + 10;
                    lblPontosRestantes.setText("Pontos Restantes: " + pontosRestantes);
                    double defesaDistribuida = Double.parseDouble(lblValorDefesa.getText()) 
                        - heroiSelecionado.getDefesa();
                    heroiSelecionado.setDefesa(heroiSelecionado.getDefesa() + defesaDistribuida);
                     lblDefesaUsuario.setText("Defesa: " + heroiSelecionado.getDefesa());
                     verificarPontosDistribuidos();
                }
            }
        });
        
         // Ação do botão "Continuar"
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Jogo jogo = new Jogo(false, heroiSelecionado);
                jogo.setVisible(true);
            }
        });
     
    }

    private void verificarPontosDistribuidos() {
        btnContinuar.setEnabled(pontosRestantes == 0);
    }
}
