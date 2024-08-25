package modelDominio;

import javax.swing.*;

public abstract class Heroi extends Entidade implements Atacar{
    private String nome;
    private int bolsaDeElixir = 0;
    private int posLinha;
    private int posColuna;
    private final double saudeTotal;
    private boolean habilidadeUsada;

    public Heroi(double defesa, double ataque, double saude, String nome) {
        super(defesa,ataque,saude);
        this.nome = nome;
        this.saudeTotal = saude;
        this.habilidadeUsada = false;
    }

    public boolean isHabilidadeUsada() {
        return habilidadeUsada;
    }

    public void setHabilidadeUsada(boolean habilidadeUsada) {
        this.habilidadeUsada = habilidadeUsada;
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

    public void achouElixir () {
        setBolsaDeElixir(getBolsaDeElixir()+1);
    }

    public void tomarElixir () {
        if (this.getBolsaDeElixir()<1)
            return;
        setSaude(getSaude()+40);

        if (getSaude() > saudeTotal) {
            System.out.println("vida maxima alcancada");
            setSaude(saudeTotal);
        }
    }

    public double getSaudeTotal() {
        return saudeTotal;
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
