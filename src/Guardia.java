
import java.util.Random;

public class Guardia extends Personaje implements Enemigo {

    public Guardia(Posicion posicion) {
        super(posicion);
    }

    public Guardia() {
    }

    @Override
    public boolean combateRandom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char mover(Mapa mapa, String noUsado) {
      
        return 'G';
    }

}
