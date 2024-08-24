package modelDominio;

import javax.swing.*;

public class Paladino extends Heroi {

    public Paladino(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    @Override
    public void ataqueEspecial() {
        double saudeAnterior = this.getSaude();

        this.setSaude(saudeAnterior + (this.getSaudeTotal() / 2));

        if (this.getSaude() > this.getSaudeTotal()) {
            this.setSaude(this.getSaudeTotal());
        }

        double saudeRecuperada = this.getSaude() - saudeAnterior;

        JOptionPane.showMessageDialog(null, "Habilidade especial usada! Você recuperou " + saudeRecuperada + " pontos de vida.");
        this.setHabilidadeUsada(true);
    }


}
