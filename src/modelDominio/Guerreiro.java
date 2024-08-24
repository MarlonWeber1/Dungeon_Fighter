package modelDominio;
import javax.swing.JOptionPane;

public class Guerreiro extends Heroi {
    private final double defesaAumentada = this.getDefesa() * 1.5;
    private int rodadasHabilidadeEspecial; // Contador de rodadas

    public Guerreiro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
        this.rodadasHabilidadeEspecial = 0; // Inicializa o contador
    }

    @Override
    public void ataqueEspecial() {
        // Verifica se a habilidade já foi usada
        if (isHabilidadeUsada()) {
            JOptionPane.showMessageDialog(null, "A habilidade especial já foi usada e ainda está ativa.");
            return;
        }

        // Aumenta a defesa em 50% e marca como usada
        this.setDefesa(defesaAumentada);
        setHabilidadeUsada(true);
        rodadasHabilidadeEspecial = 2; // Duração de duas rodadas

        // Mensagem de confirmação
        JOptionPane.showMessageDialog(null, "Postura Defensiva ativada! Defesa aumentada por duas rodadas.");
    }

    // Método para verificar e ajustar a defesa ao final das rodadas
    public void reduzirRodadasHabilidadeEspecial() {
        if (rodadasHabilidadeEspecial > 0) {
            rodadasHabilidadeEspecial--;

            if (rodadasHabilidadeEspecial == 0) {
                // Volta a defesa ao normal
                this.setDefesa(this.getDefesa() / 1.5);
                JOptionPane.showMessageDialog(null, "Postura Defensiva terminou! A defesa voltou ao normal.");
            }
        }
    }
}
