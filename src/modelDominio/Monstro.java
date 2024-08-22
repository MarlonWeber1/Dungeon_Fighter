package modelDominio;

public abstract class Monstro extends Entidade{
    // classe abstrata que e pai das classes "monstro comum" e "chefão"

    public Monstro(double defesa, double ataque, double saude) {
        super(defesa, ataque, saude);
    }

    public void imprimeMonstro () {
        System.out.println(this.getAtaque());
        System.out.println(this.getDefesa());
        System.out.println(this.getSaude());
    }
}
