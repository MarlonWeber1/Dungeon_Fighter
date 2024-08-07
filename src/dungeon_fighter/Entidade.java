package dungeon_fighter;

public abstract class Entidade {
    private double ataque;
    private double defesa;
    private double saude;


    public Entidade(double defesa, double ataque, double saude) {
        this.defesa = defesa;
        this.ataque = ataque;
        this.saude = saude;
    }

    public double getDefesa() {
        return defesa;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    public double getSaude() {
        return saude;
    }

    public void setSaude(double saude) {
        this.saude = saude;
    }

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }
}
