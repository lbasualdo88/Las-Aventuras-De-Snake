
import java.util.Scanner;

public class Snake extends Personaje {

    public Snake(Posicion posicion) {
        super(posicion);
    }

    public Snake() {
    }

    public boolean ataque() {
        return true;
    }

    public boolean esquivar() {
        return false;
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

    public boolean combate() {
        Scanner leer = new Scanner(System.in);
        boolean eleccionUsuario = false;
        System.out.println("-Selecciona tu jugada-");
        System.out.println("----------------------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Esquivar");
        System.out.println("----------------------");
        int opcion = leer.nextInt();
        switch (opcion) {
            case 1:
                eleccionUsuario = ataque();
                break;
            case 2:
                eleccionUsuario = esquivar();
                break;
        }
        return eleccionUsuario;
    }

    @Override
    public void mover() {
        Scanner leer = new Scanner(System.in);
        String direccion = leer.next();

        switch (direccion) {
            case "w":
                posicion.moverArriba();
                break;
            case "s":
                posicion.moverAbajo();
                break;
            case "a":
                posicion.moverIzquierda();
                break;
            case "d":
                posicion.moverDerecha();
                break;
        }
    }

}
