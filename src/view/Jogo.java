package view;

import modelDominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jogo extends JFrame {
    private Heroi heroi;
    private final ComecarJogo comecarJogo;
    private final JLabel lblNomeUsuario;
    private final JLabel lblClasseUsuario;
    private final JLabel lblAtaqueUsuario;
    private final JLabel lblSaudeUsuario;
    private final JLabel lblDefesaUsuario;
    private final JPanel pImagemHeroi;
    private final JLabel lblBolsaElixir;
    private JLabel lblImagemHeroi;
    public JButton botaoHabilidade;
    public Tabuleiro mesmoTabuleiro;

    public Jogo(boolean debug, Heroi heroiSelecionado, Tabuleiro tabuleiroInicial) {
        // Inicializa o herói e o jogo
        this.heroi = heroiSelecionado;

        if (tabuleiroInicial != null) {
            tabuleiroInicial.revalidate();
            tabuleiroInicial.repaint();
        }
        this.comecarJogo = new ComecarJogo(heroiSelecionado,debug,this,tabuleiroInicial);

        this.mesmoTabuleiro = new Tabuleiro(false);
        for (int i = 0; i<5; i++) {
            for (int x = 0; x<10; x++)
            {
                this.mesmoTabuleiro.tabuleiro[i][x] = this.comecarJogo.tabuleiro.tabuleiro[i][x];
            }
        }
        this.mesmoTabuleiro.setDebugging(this.comecarJogo.tabuleiro.isDebugging()); // seta o modo de debug igual ao tabuleiro atual

        // Configura o JFrame
        setTitle("Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
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
        botaoHabilidade = new JButton("Habilidade");
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
        pImagemHeroi = new JPanel();

        configuraImagem(heroiSelecionado);

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
                if (heroi.getBolsaDeElixir() > 0) {

                    if (heroi.getSaude() == heroi.getSaudeTotal()) {
                        JOptionPane.showMessageDialog(Jogo.this, "Sua vida esta completa!");
                        atualizaStatus();
                    }
                    else {
                        heroi.tomarElixir();
                        heroi.setBolsaDeElixir(heroi.getBolsaDeElixir() - 1);
                        JOptionPane.showMessageDialog(Jogo.this, "Você tomou um elixir!");
                        atualizaStatus();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(Jogo.this, "Você não possui mais elixires!");
                    atualizaStatus();
                }
            }
        });

        // desativa a habilidade do barbaro no tabuleiro, pois ele so pode usa-la ao atacar
        if (heroi instanceof Barbaro) {
            botaoHabilidade.setEnabled(false);
        }

        // adiciona o ActionListener ao botaoDica
        botaoDica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desativarTodosBotoes(); // desativa todos ActionListeners dos botoes do tabuleiro
                for (int x = 0; x < 5; x++) {
                    for (int j = 0; j < 10; j++) {
                        int finalJ = j;
                        // cria o ActionListener uso da dica para cada botao da matriz
                        ActionListener listener = new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                comecarJogo.usaDica(finalJ);
                                desativarTodosBotoes(); // desativa todos ActionListener para a funcao dica
                                ativarBotoes(); // ativa novamente os ActionListener para a funcao mover heroi
                            }
                        };

                        // adiciona o ActionListener ao botao
                        comecarJogo.tabuleiro.botoes[x][j].addActionListener(listener);
                    }
                }
            }

            // funcoes para que o botao de dica funcione corretamente
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

        // utiliza a habilidade, e desativa o botao
        botaoHabilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heroi.ataqueEspecial();
                botaoHabilidade.setEnabled(false);
                atualizaStatus();
            }
        });

        // botao que leva a tela com opcoes de novo jogo, reniciar e fechar o jogo
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra um diálogo de confirmação
                int resposta = JOptionPane.showConfirmDialog(
                        Jogo.this,
                        "Tem certeza de que deseja sair?",
                        "Confirmar saída",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                // Verifica a resposta do usuário
                if (resposta == JOptionPane.YES_OPTION) {
                    new Opcoes(Jogo.this.getHeroi(), Jogo.this.mesmoTabuleiro);
                    Jogo.this.dispose();  // Fecha a janela atual
                }
            }
        });
        pack();
        setVisible(true);
    }

    // funcao que usa try/catch e configurar imagem do heroi selecionado e monstros
    public void configuraImagem(Heroi heroiSelecionado){
        try {
            pImagemHeroi.setPreferredSize(new Dimension(200, 200));
            pImagemHeroi.setMaximumSize(new Dimension(200, 200));
            pImagemHeroi.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            pImagemHeroi.setAlignmentX(Component.CENTER_ALIGNMENT);
            pImagemHeroi.setBackground(Color.LIGHT_GRAY);

            // determine a imagem com base no herói selecionado
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

    public Heroi getHeroi() {
        return heroi;
    }

    // funcao para atualizar status do heroi, tambem verifica se a habilidade foi usada e desativa o botao se necessario
    public void atualizaStatus() {
        lblNomeUsuario.setText("Nome: " + heroi.getNome());
        lblClasseUsuario.setText("Classe: " + heroi.getClass().getSimpleName());
        lblAtaqueUsuario.setText("Ataque: " + heroi.getAtaque());
        lblSaudeUsuario.setText("Saúde: " + Math.ceil(heroi.getSaude()));
        lblDefesaUsuario.setText("Defesa: " + heroi.getDefesa());
        lblBolsaElixir.setText("Bolsa de elixir: " + heroi.getBolsaDeElixir());
        if (heroi.isHabilidadeUsada()) {
            botaoHabilidade.setEnabled(false);
        }
    }

}
