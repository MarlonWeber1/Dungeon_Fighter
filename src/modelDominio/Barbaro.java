package modelDominio;

public class Barbaro extends Heroi {

    public Barbaro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    @Override
    public void ataqueEspecial() {
        // Golpe furioso: desfere um ataque que da 50% a mais de dano
        this.setAtaque(this.getAtaque() * 1.5);
    }
}
