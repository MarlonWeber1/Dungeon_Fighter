package modelDominio;

public abstract class Heroi extends Entidade{
    private String nome;
    private int bolsaDeElixir = 0;
    private int posLinha;
    private int posColuna;

    public Heroi(double defesa, double ataque, double saude, String nome, int inicioLin, int inicioCol) {
        super(defesa,ataque,saude);
        this.nome = nome;
        this.posLinha = inicioLin;
        this.posColuna =  inicioCol;
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

    public int getPosLinha() {
        return posLinha;
    }

    public void setPosLinha(int posLinha) {
        this.posLinha = posLinha;
    }

    public int getPosColuna() {
        return posColuna;
    }

    public void setPosColuna(int posColuna) {
        this.posColuna = posColuna;
    }

    public void posicaoInicial (int colunaInicial) {
        setPosColuna(colunaInicial);
        setPosLinha(0);
    }

    public void achouElixir () {
        setBolsaDeElixir(getBolsaDeElixir()+1);
    }

    public void tomarElixir () {
        setSaude(getSaude()+40);
    }
}
