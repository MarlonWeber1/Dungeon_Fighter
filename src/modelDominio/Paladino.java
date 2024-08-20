package modelDominio;

import view.Tabuleiro;

public class Paladino extends Heroi {
    private final double saudeTotal; // guarda a vida total do paladino para seu ataque especial

    public Paladino(double defesa, double ataque, double saude, double saudeTotal, String nome) {
        super(defesa, ataque, saude, nome);
        this.saudeTotal = saude;
    }

    @Override
    public void ataqueEspecial() { // Recuperação: recupera metade dos pontos de vida totais
        this.setSaude(this.getSaude() + (this.getSaudeTotal()/2.0));

        if (this.getSaude() > this.getSaudeTotal()){
            this.setSaude(this.getSaudeTotal());
        }
    }

    public double getSaudeTotal() {
        return saudeTotal;
    }
}
