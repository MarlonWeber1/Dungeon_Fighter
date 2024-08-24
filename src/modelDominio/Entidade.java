package modelDominio;

import javax.swing.*;

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
        // Gera valores aleatórios para ataque e defesa
        double ataqueFinal = this.ataque + (Math.random() * 50);
        double defesaAdversario = adversario.getDefesa() + (Math.random() * 30);

        // Calcula o dano
        double dano = ataqueFinal - defesaAdversario;

        String mensagem;

        if (dano > 0) {
            adversario.setSaude(adversario.getSaude() - dano);
            mensagem = String.format(
                    "%s atacou %s e causou %.2f de dano!\n%s tem agora %.2f de saúde restante.",
                    this.getClass().getSimpleName(),
                    adversario.getClass().getSimpleName(),
                    dano,
                    adversario.getClass().getSimpleName(),
                    adversario.getSaude()
            );
        } else {
            this.setSaude(this.getSaude() + dano); // '+ dano' porque 'dano' é negativo
            mensagem = String.format(
                    "%s tentou atacar %s, mas o ataque foi ineficaz!\n%s contra-atacou e causou %.2f de dano.\n%s tem agora %.2f de saúde restante.",
                    this.getClass().getSimpleName(),
                    adversario.getClass().getSimpleName(),
                    adversario.getClass().getSimpleName(),
                    -dano, // dano é negativo, então invertemos o sinal para mostrar o valor positivo
                    this.getClass().getSimpleName(),
                    this.getSaude()
            );
        }

        // Exibe a mensagem em um JOptionPane
        JOptionPane.showMessageDialog(null, mensagem, "Resultado do Ataque", JOptionPane.INFORMATION_MESSAGE);
    }


    public boolean estaVivo() {
        return this.saude >= 0;
    }
}
