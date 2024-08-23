package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jogo extends JFrame {
    private Heroi heroi;
    private ComecarJogo comecarJogo;
    private JLabel lblNomeUsuario;
    private JLabel lblClasseUsuario;
    private JLabel lblAtaqueUsuario;
    private JLabel lblSaudeUsuario;
    private JLabel lblDefesaUsuario;
    private JLabel lblImagemHeroi;
    private JLabel lblBolsaElixir;

    public Jogo(boolean debug, Heroi heroiSelecionado) {
        // Inicializa o herói e o jogo
        this.heroi = heroiSelecionado;
        this.comecarJogo = new ComecarJogo(heroi, debug); // false se não quiser modo de depuração

        // Configura o JFrame
        setTitle("Jogo de Tabuleiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Usando GridBagLayout para controle fino

        // Configura as restrições do GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Margem entre componentes

        // Adiciona o tabuleiro na posição desejada (esquerda)
        gbc.gridx = 1; // coluna 0
        gbc.gridy = 0; // linha 0
        gbc.gridwidth = 1; // ocupa 1 coluna
        gbc.gridheight = 2; // ocupa 2 linhas (tabuleiro + botões)
        add(comecarJogo.tabuleiro, gbc);

        // Cria um painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 3, 10, 10)); // 1 linha, 3 colunas, com espaçamento

        // Cria os botões e adiciona-os ao painel
        JButton botaoTomarElixir = new JButton("Tomar Elixir");
        JButton botaoDica = new JButton("Dica");
        JButton botaoHabilidade = new JButton("Habilidade");
        JButton botaoSair = new JButton("Sair");

        painelBotoes.add(botaoTomarElixir);
        painelBotoes.add(botaoDica);
        painelBotoes.add(botaoHabilidade);
        painelBotoes.add(botaoSair);

        // Adiciona o painel de botões logo abaixo do tabuleiro (mesma coluna)
        gbc.gridx = 0; // mesma coluna que o tabuleiro
        gbc.gridy = 2; // linha abaixo do tabuleiro
        gbc.gridwidth = 2; // ocupa 1 coluna
        gbc.gridheight = 1; // ocupa 1 linha
        add(painelBotoes, gbc);

        // Cria o painel de atributos do herói (direita)
        JPanel pDireito = new JPanel();
        pDireito.setLayout(new BoxLayout(pDireito, BoxLayout.Y_AXIS));
        pDireito.setPreferredSize(new Dimension(250, getHeight()));
        pDireito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pDireito.setBackground(Color.DARK_GRAY);

        // Adicionando padding interno ao painelEsquerdo
        pDireito.setBorder(BorderFactory.createCompoundBorder(
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
        if (this.heroi instanceof Barbaro) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/barbaro.png"));
        } else if (this.heroi instanceof Paladino) {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        } else {
            iHeroi = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
        }

        // Ajuste na Imagem do Herói
        Image i = iHeroi.getImage().getScaledInstance(128, 165, Image.SCALE_SMOOTH);
        iHeroi = new ImageIcon(i);
        lblImagemHeroi = new JLabel(iHeroi);
        lblImagemHeroi.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona a imagem do herói ao painel da imagem
        pImagemHeroi.add(lblImagemHeroi);

        // Preenchendo os labels com as informações do herói selecionado
        lblNomeUsuario = new JLabel("Nome: " + heroi.getNome());
        lblClasseUsuario = new JLabel("Classe: " + heroi.getClass().getSimpleName());
        lblAtaqueUsuario = new JLabel("Ataque: " + heroi.getAtaque());
        lblSaudeUsuario = new JLabel("Saúde: " + heroi.getSaude());
        lblDefesaUsuario = new JLabel("Defesa: " + heroi.getDefesa());
        lblBolsaElixir = new JLabel("Bolsa de elixir: " + heroi.getBolsaDeElixir());

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
        lblBolsaElixir.setForeground(Color.WHITE);
        lblBolsaElixir.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Centralizar os textos
        lblNomeUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblClasseUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAtaqueUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaudeUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDefesaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBolsaElixir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionar espaçamento entre os labels
        lblNomeUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblClasseUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblAtaqueUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblSaudeUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblDefesaUsuario.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        lblBolsaElixir.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));

        // Adiciona o painel da imagem e os labels ao painel esquerdo
        pDireito.add(Box.createVerticalStrut(10)); // Espaço superior
        pDireito.add(pImagemHeroi);
        pDireito.add(Box.createVerticalStrut(20)); // Espaço entre a imagem e o texto
        pDireito.add(lblNomeUsuario);
        pDireito.add(lblClasseUsuario);
        pDireito.add(lblAtaqueUsuario);
        pDireito.add(lblSaudeUsuario);
        pDireito.add(lblDefesaUsuario);
        pDireito.add(lblBolsaElixir);

        // Adiciona o painel de atributos do herói na posição correta (direita)
        gbc.gridx = 0; // coluna 1
        gbc.gridy = 0; // linha 0
        gbc.gridwidth = 1; // ocupa 1 coluna
        gbc.gridheight = 2; // ocupa 2 linhas (mesma altura do tabuleiro + botões)
        add(pDireito, gbc);


        for (int x = 0; x < 5; x++) {
            for (int j = 0; j < 10; j++) {
                int finalJ = j;
                int finalX = x;
                comecarJogo.tabuleiro.botoes[x][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        comecarJogo.moverHeroi(finalX, finalJ);
                        atualizaStatus();
                    }
                });
            }
        }

        botaoTomarElixir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heroi.tomarElixir();
                atualizaStatus();
            }
        });

        // Adiciona o ActionListener ao botaoDica
        botaoDica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 5; x++) {
                    for (int j = 0; j < 10; j++) {
                        int finalJ = j;
                        // Cria o ActionListener para cada botão na matriz
                        ActionListener listener = new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                desativarTodosBotoes();
                                comecarJogo.usaDica(finalJ);
                                // Desativar todos os botões da matriz ao clicar em qualquer um deles
                                desativarTodosBotoes();
                                ativarBotoes();
                            }
                        };

                        // Adiciona o ActionListener ao botão
                        comecarJogo.tabuleiro.botoes[x][j].addActionListener(listener);
                    }
                }
            }

            private void desativarTodosBotoes() {
                for (int x = 0; x < 5; x++) {
                    for (int j = 0; j < 10; j++) {
                        JButton botao = comecarJogo.tabuleiro.botoes[x][j];

                        // Remove todos os ActionListeners de cada botão
                        for (ActionListener al : botao.getActionListeners()) {
                            botao.removeActionListener(al);
                        }
                    }
                }
            }
            private void ativarBotoes() {
                for (int x = 0; x < 5; x++) {
                    for (int j = 0; j < 10; j++) {
                        int finalJ = j;
                        int finalX = x;
                        comecarJogo.tabuleiro.botoes[x][j].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                comecarJogo.moverHeroi(finalX, finalJ);
                                atualizaStatus();
                            }
                        });
                    }
                }
            }

        });





        botaoHabilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heroi.ataqueEspecial();
                atualizaStatus();
            }
        });

        // Exibir a janela
        pack();
        setVisible(true);

    }

    public void atualizaStatus() {
        lblNomeUsuario.setText("Nome: " + heroi.getNome());
        lblClasseUsuario.setText("Classe: " + heroi.getClass().getSimpleName());
        lblAtaqueUsuario.setText("Ataque: " + heroi.getAtaque());
        lblSaudeUsuario.setText("Saúde: " + heroi.getSaude());
        lblDefesaUsuario.setText("Defesa: " + heroi.getDefesa());
        lblBolsaElixir.setText("Bolsa de elixir: " + heroi.getBolsaDeElixir());
    }

    private void removeAllActionListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    public static void main(String[] args) {
        Paladino heroi = new Paladino(150, 150, 150, "jvtips");
        new Jogo(false, heroi);
    }
}
