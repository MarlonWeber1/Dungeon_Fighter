package modelDominio;

import javax.swing.*;

public class Paladino extends Heroi {

    public Paladino(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    @Override
    public void ataqueEspecial() {
        double vidaRecuperada = this.getSaudeTotal() / 2.0;

        this.setSaude(this.getSaude() + vidaRecuperada);

        if (this.getSaude() > this.getSaudeTotal()) {
            vidaRecuperada -= (this.getSaude() - this.getSaudeTotal());
            this.setSaude(this.getSaudeTotal());
        }

        JOptionPane.showMessageDialog(null, "Habilidade especial usada! Você recuperou " + vidaRecuperada + " pontos de vida.");
        this.setHabilidadeUsada(true);
    }


}
