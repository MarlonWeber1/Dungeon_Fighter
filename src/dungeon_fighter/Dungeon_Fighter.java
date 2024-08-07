package dungeon_fighter;

/**
 *
 * @author marlo
 */
public class Dungeon_Fighter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Paladino pedro = new Paladino(10,10,10,"pedro");
        System.out.println(pedro.getSaude());
        pedro.setSaude(1);
        pedro.ataqueEspecial();
        System.out.println(pedro.getSaude());
    }

}
