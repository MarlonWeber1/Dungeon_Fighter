package modelDominio;

public abstract class Heroi extends Entidade{
    private String nome;
    private int bolsaDeElixir = 0;
    private int posLinha;
    private int posColuna;

    public Heroi(double defesa, double ataque, double saude, String nome, int colInicial) {
        super(defesa,ataque,saude);
        this.nome = nome;
        posicaoInicial(colInicial);
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

    public void incrementaSaude() {
        this.setSaude(this.getSaude()+10);
    }
    public void decrementaSaude() {
        this.setSaude(this.getSaude()-10);
    }

    public void incrementaDefesa() {
        this.setDefesa(this.getDefesa()+10);
    }
    public void decrementaDefesa() {
        this.setDefesa(this.getDefesa()-10);
    }

    public void incrementaAtaque() {
        this.setAtaque(this.getAtaque()+10);
    }
    public void decrementaAtaque() {
        this.setAtaque(this.getAtaque()-10);
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

    // funcoes para atualizar a posicao do heroi
    public void moveCima () {
        this.setPosLinha(this.getPosLinha() + 1);
    }

    public void moveBaixo () {
        this.setPosLinha(this.getPosLinha() - 1);
    }

    public void moveDir () {
        this.setPosColuna(this.getPosColuna() + 1);
    }

    public void moveEsq () {
        this.setPosColuna(this.getPosColuna() - 1);
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
