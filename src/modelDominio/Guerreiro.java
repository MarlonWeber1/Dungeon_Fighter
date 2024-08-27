package modelDominio;
import javax.swing.JOptionPane;

public class Guerreiro extends Heroi {
    private int rodadasHabilidadeEspecial; // Contador de rodadas

    public Guerreiro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
        this.rodadasHabilidadeEspecial = 0; // Inicializa o contador
    }

    public int getRodadasHabilidadeEspecial() {
        return rodadasHabilidadeEspecial;
    }

    // implementa metodo abstrato da classe heroi
    @Override
    public void ataqueEspecial() {
        // Verifica se a habilidade já foi usada
        if (isHabilidadeUsada()) {
            JOptionPane.showMessageDialog(null, "A habilidade especial já foi usada e ainda está ativa.");
            return;
        }

        // aumenta a defesa em 50% e marca como usada
        this.setDefesa(this.getDefesa() * 1.5);
        setHabilidadeUsada(true);
        rodadasHabilidadeEspecial = 2; // duração de duas rodadas

        // Mensagem de confirmação
        JOptionPane.showMessageDialog(null, "Postura Defensiva ativada! Defesa aumentada por duas rodadas.");
    }

    // metodo para verificar e ajustar a defesa ao final das 2 rodadas da habilidade ativa
    public void reduzirRodadasHabilidadeEspecial() {
        if (rodadasHabilidadeEspecial > 0) {
            rodadasHabilidadeEspecial--;

            if (rodadasHabilidadeEspecial == 0) {
                // volta a defesa ao normal
                this.setDefesa(this.getDefesa() / 1.5);
                JOptionPane.showMessageDialog(null, "Postura Defensiva terminou! A defesa voltou ao normal.");
            }
        }
    }
}
