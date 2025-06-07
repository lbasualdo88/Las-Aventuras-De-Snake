
import java.util.Random;

public class MetalGear extends Personaje implements Enemigo {

    public MetalGear(Posicion posicion) {
        super(posicion);
    }

    public MetalGear() {
    }

    @Override
    public char mover(Mapa mapa, String direccion) {
        
        return 'M';
    }

    public boolean ataque() {
        return true;
    }

    public boolean esquivar() {
        return false;
    }

    public int tipoDeAtaque() {
        int tipoAtaque = (int) (Math.random() * 2);  // 0 = misil, 1 = laser

        if (tipoAtaque == 0) {
            System.out.println("Rex lanza misil");
            tipoAtaque = -40;
        } else {
            System.out.println("Rex dispara láser");
            tipoAtaque = -15;
        }
        return tipoAtaque;
    }

    @Override
    public boolean combateRandom() {
        boolean eleccion = esquivar();
        int movimiento = (int) (Math.random() * 2);  // 0 = ataca, 1 = esquiva
        if (movimiento == 0) {
            eleccion = ataque();
        }
        return eleccion;
    }
}
