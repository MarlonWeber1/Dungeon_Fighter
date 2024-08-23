package modelDominio;

public class Paladino extends Heroi {

    public Paladino(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    @Override
    public void ataqueEspecial() { // Recuperação: recupera metade dos pontos de vida totais
        this.setSaude(this.getSaude() + (this.getSaudeTotal()/2.0));

        if (this.getSaude() > this.getSaudeTotal()){
            this.setSaude(this.getSaudeTotal());
        }
    }

}
