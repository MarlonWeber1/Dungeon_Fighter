package dungeon_fighter;

public class Guerreiro extends Heroi{
    private double defesaAumentada = this.getDefesa()*1.5;

    public Guerreiro(double defesa, double ataque, double saude, String nome) {
        super(defesa, ataque, saude, nome);
    }

    @Override
    public void ataqueEspecial() {
        // Postura Defensiva: aumenta a defesa em 50% por duas rodadas
        this.setDefesa(defesaAumentada);
    }
}