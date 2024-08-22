package modelDominio;

public class Barbaro extends Heroi {
    private final double danoDoAtaqueEspecial = this.getAtaque()*1.5; // dano para habilidade especial: um ataque com 50% de dano a mais

    public Barbaro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }


    @Override
    public void ataqueEspecial() {
        // Golpe furioso: desfere um ataque que da 50% a mais de dano
        this.setAtaque(danoDoAtaqueEspecial);
    }
}
