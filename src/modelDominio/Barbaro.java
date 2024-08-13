package modelDominio;

public class Barbaro extends Heroi {
    private double danoDoAtaqueEspecial = this.getAtaque()*1.5; // 50% maior que o ataque base

    public Barbaro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    @Override
    public void ataqueEspecial() {
        // Golpe furioso: desfere um ataque que da 50% a mais de dano
        this.setAtaque(danoDoAtaqueEspecial);
    }
}
