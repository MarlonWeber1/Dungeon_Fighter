package modelDominio;

public class Armadilha {
    // armadilha simples, tira 10 pontos de vida sempre
    protected double dano;

    public Armadilha() {
        this.dano = 10;
    }

    public double getDano() {
        return this.dano;
    }

    public void imprimeDano() {
        System.out.println(this.getDano());
    }
}
