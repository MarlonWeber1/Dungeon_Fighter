package modelDominio;

public class Guerreiro extends Heroi{
    private double defesaAumentada = this.getDefesa()*1.5;

    public Guerreiro(double defesa, double ataque, double saude, String nome, int posLinha, int posCol) {
        super(defesa, ataque, saude, nome, posLinha, posCol);
    }

    @Override
    public void ataqueEspecial() {
        // Postura Defensiva: aumenta a defesa em 50% por duas rodadas
        this.setDefesa(defesaAumentada);
    }
}