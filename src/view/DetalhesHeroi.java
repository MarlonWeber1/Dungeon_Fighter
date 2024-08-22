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

        JPanel pEsquerdo = new JPanel();
        pEsquerdo.setLayout(new BoxLayout(pEsquerdo, BoxLayout.Y_AXIS));
        pEsquerdo.setPreferredSize(new Dimension(250, getHeight()));
        pEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pEsquerdo.setBackground(Color.DARK_GRAY); // Configura a cor de fundo do painel esquerdo
    

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
        pImagemHeroi.setBackground(Color.LIGHT_GRAY); // Configura a cor de fundo do painel principal
        
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
        Image i = iHeroi.getImage().getScaledInstance(128, 165, Image.SCALE_SMOOTH);
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
        lblNomeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

        // Painel direito para modificações futuras
        JPanel pDireita = new JPanel();
        painelPrincipal.add(pDireita, BorderLayout.CENTER);

        // Adicionando os painéis ao painel principal
        painelPrincipal.add(pEsquerdo, BorderLayout.WEST);

        // Adiciona o painel principal ao frame
        add(painelPrincipal);

        // Torna a janela visível
        setVisible(true);
    }
    
}
