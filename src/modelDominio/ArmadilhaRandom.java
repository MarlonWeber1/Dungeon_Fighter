package modelDominio;
import java.util.Random;

public class ArmadilhaRandom extends Armadilha{

    // Armadilha com perda 'aleatoria'
    private static final double[] valoresDeDano = {10, 15, 15, 20, 20, 25, 25, 30, 30, 35, 40, 45, 50};

    public ArmadilhaRandom () {
        this.dano = gerarDanoAleatorio();
    }

    public double gerarDanoAleatorio() {
        // Seleciona valor aleatorio do array
        Random random = new Random();
        int indice = random.nextInt(valoresDeDano.length);
        return valoresDeDano[indice];
    }
}
