package modelDominio;

public class Barbaro extends Heroi {

    public Barbaro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    // implementa metodo abstrato da classe heroi
    @Override
    public void ataqueEspecial() {
        // Golpe furioso: desfere um ataque que da 50% a mais de dano, so pode ser usado em batalha
        this.setAtaque(this.getAtaque() * 1.5);
    }
}
