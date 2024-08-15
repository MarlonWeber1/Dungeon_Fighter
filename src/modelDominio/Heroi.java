package modelDominio;

public abstract class Heroi extends Entidade{
    private String nome;
    private int bolsaDeElixir = 0;

    public Heroi(double defesa, double ataque, double saude, String nome) {
        super(defesa,ataque,saude);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getBolsaDeElixir() {
        return bolsaDeElixir;
    }

    public void setBolsaDeElixir(int bolsaDeElixir) {
        this.bolsaDeElixir = bolsaDeElixir;
    }

    public abstract void ataqueEspecial ();

    public void danoArmadilha (Armadilha dano) { // saude diminui 10
        this.setSaude(this.getSaude() - dano.getDano());
    }

    public void mover () {

    }

    public void morreu () {
        if (this.getSaude() <= 0) {
            // fimDeJogo();
        }
    }
}
