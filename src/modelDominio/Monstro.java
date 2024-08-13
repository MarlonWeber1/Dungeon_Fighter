package modelDominio;

public abstract class Monstro extends Entidade{
    // classe abstrata que ira ser pai das classes "monstro comum" e "chefão"

    public Monstro(double defesa, double ataque, double saude) {
        super(defesa, ataque, saude);
    }
}
