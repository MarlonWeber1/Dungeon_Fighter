package modelDominio;

import javax.swing.*;

public abstract class Monstro extends Entidade implements Atacar{
    // classe abstrata que e pai das classes "monstro comum" e "chefão"

    public Monstro(double defesa, double ataque, double saude) {
        super(defesa, ataque, saude);
    }

    public void imprimeMonstro () {
        System.out.println(this.getAtaque());
        System.out.println(this.getDefesa());
        System.out.println(this.getSaude());
    }

    public void atacar(Entidade adversario) {
        // Gera valores aleatórios para ataque e defesa
        double ataqueFinal = this.getAtaque() + (Math.random() * 50);
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
}
