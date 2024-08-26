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
class DetalhesHeroi extends JFrame {

    private final JLabel lblNomeUsuario;
    private final JLabel lblClasseUsuario;
    private final JLabel lblAtaqueUsuario;
    private final JLabel lblSaudeUsuario;
    private final JLabel lblDefesaUsuario;
    private final JButton btnContinuar;

    private JLabel lblImagemHeroi;
    private final JPanel pImagemHeroi;

    private JLabel lblValorAtaque;
    private JLabel lblValorSaude;
    private JLabel lblValorDefesa;
    private JLabel lblPontosRestantes;

    private int pontosRestantes = 100; // Pontos que podem ser distribuídos

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
        pEsquerdo.setPreferredSize(new Dimension(250, 100));
        pEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pEsquerdo.setBackground(Color.DARK_GRAY);

        // Adicionando padding interno ao painelEsquerdo
        pEsquerdo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Painel para a imagem do herói com borda
        pImagemHeroi = new JPanel();

        configuraImagem(heroiSelecionado);

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
        lblNomeUsuario.setFont(new Font("Palatino LinoType", Font.BOLD, 14));
        lblClasseUsuario.setForeground(Color.WHITE);
        lblClasseUsuario.setFont(new Font("Palatino LinoType", Font.PLAIN, 14));
        lblAtaqueUsuario.setForeground(Color.WHITE);
        lblAtaqueUsuario.setFont(new Font("Palatino LinoType", Font.PLAIN, 14));
        lblSaudeUsuario.setForeground(Color.WHITE);
        lblSaudeUsuario.setFont(new Font("Palatino LinoType", Font.PLAIN, 14));
        lblDefesaUsuario.setForeground(Color.WHITE);
        lblDefesaUsuario.setFont(new Font("Palatino LinoType", Font.PLAIN, 14));

        // Centralizar os textos
        lblNomeUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblClasseUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAtaqueUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaudeUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDefesaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionar espaçamento entre os labels
        lblNomeUsuario.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        lblClasseUsuario.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        lblAtaqueUsuario.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        lblSaudeUsuario.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        lblDefesaUsuario.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        // Adiciona o painel da imagem e os labels ao painel esquerdo
        pEsquerdo.add(Box.createVerticalStrut(10)); // Espaço superior
        pEsquerdo.add(pImagemHeroi);
        pEsquerdo.add(Box.createVerticalStrut(10)); // Espaço entre a imagem e o texto
        pEsquerdo.add(lblNomeUsuario);
        pEsquerdo.add(lblClasseUsuario);
        pEsquerdo.add(lblAtaqueUsuario);
        pEsquerdo.add(lblSaudeUsuario);
        pEsquerdo.add(lblDefesaUsuario);

        // Painel direito para os botões e atributos
        JPanel pDireita = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Painel adicional para "Pontos Restantes" no topo superior direito
        JPanel painelPontosRestantes = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinha à direita
        lblPontosRestantes = new JLabel("Pontos Restantes: " + pontosRestantes);
        lblPontosRestantes.setFont(new Font("Palatino LinoType", Font.BOLD, 20));
        painelPontosRestantes.add(lblPontosRestantes);

        // Adiciona o painel ao topo (NORTH) do painel principal
        painelPrincipal.add(painelPontosRestantes, BorderLayout.NORTH);

        // Botões e labels de Ataque
        JLabel lblAtaque = new JLabel("Ataque");
        lblAtaque.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        pDireita.add(lblAtaque, gbc);

        JButton btnAtaqueMenos = new JButton("-");
        btnAtaqueMenos.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        pDireita.add(btnAtaqueMenos, gbc);

        lblValorAtaque = new JLabel(String.valueOf(heroiSelecionado.getAtaque()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        pDireita.add(lblValorAtaque, gbc);

        JButton btnAtaqueMais = new JButton("+");
        btnAtaqueMais.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 2;
        gbc.gridy = 2;
        pDireita.add(btnAtaqueMais, gbc);

        // Botões e labels de Saúde
        JLabel lblSaude = new JLabel("Saúde");
        lblSaude.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        pDireita.add(lblSaude, gbc);

        JButton btnSaudeMenos = new JButton("-");
        btnSaudeMenos.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        pDireita.add(btnSaudeMenos, gbc);

        lblValorSaude = new JLabel(String.valueOf(heroiSelecionado.getSaude()));
        gbc.gridx = 1;
        gbc.gridy = 4;
        pDireita.add(lblValorSaude, gbc);

        JButton btnSaudeMais = new JButton("+");
        btnSaudeMais.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 2;
        gbc.gridy = 4;
        pDireita.add(btnSaudeMais, gbc);

        // Botões e labels de Defesa
        JLabel lblDefesa = new JLabel("Defesa");
        lblDefesa.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 5;
        pDireita.add(lblDefesa, gbc);

        JButton btnDefesaMenos = new JButton("-");
        btnDefesaMenos.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        pDireita.add(btnDefesaMenos, gbc);

        lblValorDefesa = new JLabel(String.valueOf(heroiSelecionado.getDefesa()));
        gbc.gridx = 1;
        gbc.gridy = 6;
        pDireita.add(lblValorDefesa, gbc);

        JButton btnDefesaMais = new JButton("+");
        btnDefesaMais.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
        gbc.gridx = 2;
        gbc.gridy = 6;
        pDireita.add(btnDefesaMais, gbc);

        // Painel inferior para botões "Continuar" 
        JPanel pInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinhado à esquerda
        pInferior.setPreferredSize(new Dimension(100, 50));
        pInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botão Continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Palatino LinoType", Font.PLAIN, 16));
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
                try {
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos. Verifique os dados de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAtaqueMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos. Verifique os dados de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSaudeMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos. Verifique os dados de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSaudeMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos. Verifique os dados de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDefesaMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos. Verifique os dados de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDefesaMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos. Verifique os dados de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Continuar"
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Jogo jogo = new Jogo(false, heroiSelecionado, null);
                    jogo.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DetalhesHeroi.this, "Ocorreu um erro ao processar a ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

    }

    private void configuraImagem(Heroi heroiSelecionado) {
        try {
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void verificarPontosDistribuidos() {
        btnContinuar.setEnabled(pontosRestantes == 0);
    }
}
