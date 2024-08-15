package modelDominio;


public class Paladino extends Heroi {
    private double saudeTotal; // guarda a vida total do paladino para seu ataque especial

    public Paladino(double defesa, double ataque, double saude, double saudeTotal, String nome, int posLinha, int posCol) {
        super(defesa, ataque, saude, nome, posLinha, posCol);
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
