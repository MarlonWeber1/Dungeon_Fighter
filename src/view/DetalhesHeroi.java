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

class DetalhesHeroi extends JFrame{

    private JLabel lblNomeUsuario;
    private JLabel lblClasseUsuario;
    private JLabel lblAtaqueUsuario;
    private JLabel lblSaudeUsuario;
    private JLabel lblDefesaUsuario;
    private JLabel lblImagemHeroi;
    
    
    DetalhesHeroi(Heroi heroiSelecionado) {
        
        setTitle("Detalhes Herói");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel esquerdo (Imagem e Detalhes)
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.setPreferredSize(new Dimension(300, getHeight())); // Defina a largura 

        // Determine a imagem com base no herói selecionado
        ImageIcon imagemHeroi;
        if (heroiSelecionado instanceof Barbaro) {
            imagemHeroi = new ImageIcon(getClass().getResource("/view/img/barbaro.png"));
        } else if (heroiSelecionado instanceof Paladino) {
            imagemHeroi = new ImageIcon(getClass().getResource("/view/img/paladino.png"));
        } else {
            imagemHeroi = new ImageIcon(getClass().getResource("/view/img/guerreiro.png"));
        }

        // Ajuste na Imagem do Herói
        Image imagem = imagemHeroi.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        imagemHeroi = new ImageIcon(imagem);
        lblImagemHeroi = new JLabel(imagemHeroi);
        lblImagemHeroi.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImagemHeroi.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));

        // Preenchendo os labels com as informações do herói selecionado
        lblNomeUsuario = new JLabel("Nome: " + heroiSelecionado.getNome());
        lblClasseUsuario = new JLabel("Classe: " + heroiSelecionado.getClass().getSimpleName());
        lblAtaqueUsuario = new JLabel("Ataque: " + heroiSelecionado.getAtaque());
        lblSaudeUsuario = new JLabel("Saúde: " + heroiSelecionado.getSaude());
        lblDefesaUsuario = new JLabel("Defesa: " + heroiSelecionado.getDefesa());

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

        // Adiciona infos ao painel
        painelEsquerdo.add(lblImagemHeroi);
        painelEsquerdo.add(Box.createVerticalStrut(40)); // Espaço entre a imagem e o texto
        painelEsquerdo.add(lblNomeUsuario);
        painelEsquerdo.add(lblClasseUsuario);
        painelEsquerdo.add(lblAtaqueUsuario);
        painelEsquerdo.add(lblSaudeUsuario);
        painelEsquerdo.add(lblDefesaUsuario);

        // Adicionando os painéis ao painel principal
        painelPrincipal.add(painelEsquerdo, BorderLayout.WEST);
        
        // Adiciona ao frame
        add(painelPrincipal);

        // Torna a janela visível
        setVisible(true);
    }
    
}
