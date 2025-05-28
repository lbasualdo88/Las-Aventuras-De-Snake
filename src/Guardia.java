
import java.util.Random;

public class Guardia extends Personaje implements Enemigo {

    public Guardia(Posicion posicion) {
        super(posicion);
    }

    

    @Override
    public void mover() {
        Random rand = new Random();
        int direccion = rand.nextInt(4); // 0 a 3

        switch (direccion) {
            case 0:
                posicion.moverArriba();
                break;
            case 1:
                posicion.moverAbajo();
                break;
            case 2:
                posicion.moverIzquierda();
                break;
            case 3:
                posicion.moverDerecha();
                break;
        }
        
    }

}
