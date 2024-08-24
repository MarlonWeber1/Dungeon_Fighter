package modelDominio;

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

    public void atacar(Entidade adversario) {

        // W deve ser o valor de ataque do atacante
        // número aleatório de 0 a W para ataque e defesa

        double ataqueFinal = this.ataque + (Math.random() * (50));
        double defesaAdversario = adversario.getDefesa() + (Math.random() * (30));

        // calcula o dano
        double dano = ataqueFinal - defesaAdversario;

        // se o dano for positivo, o adversário perde vida, caso contrário, quem perde vida é o atacante
        if (dano > 0) {
            adversario.setSaude(adversario.getSaude() - dano);
            System.out.println(this.getClass().getSimpleName() + " causou dano a: " + adversario.getClass().getSimpleName() + "(" + dano + ")");
            System.out.println(this.getClass().getSimpleName() + " " +this.getSaude());
            System.out.println(adversario.getClass().getSimpleName()+ " " + adversario.getSaude());
        } else {
            this.setSaude(this.getSaude() + dano); // '+ dano' porque dano' é negativo
            System.out.println(this.getClass().getSimpleName() + " causou dano a: " + adversario.getClass().getSimpleName() + "(" + dano + ")");
            System.out.println(this.getClass().getSimpleName()+ " " + this.getSaude());
            System.out.println(adversario.getClass().getSimpleName() + " "+ adversario.getSaude());
        }
    }

    public boolean estaVivo() {
        return this.saude >= 0;
    }
}
