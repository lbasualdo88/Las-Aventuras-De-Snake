
import java.util.Scanner;

public class Snake extends Personaje {

    private Scanner leer;
    private Tarjeta tarjeta;

    public Snake(Posicion posicion) {
        super(posicion);
        this.leer = new Scanner(System.in);
        this.tarjeta = null;
    }

    public Snake() {
        this.leer = new Scanner(System.in);
    }

    public void recogerTarjeta(Tarjeta t) {
        this.tarjeta = t;
    }

    public Tarjeta getTarjeta() {
        return this.tarjeta;
    }

    public boolean tieneTarjeta() {
        return this.tarjeta != null;
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
    public void mover(Mapa mapa, String direccion) {
        int x = posicion.getX();
        int y = posicion.getY();
        int nuevoX = x;
        int nuevoY = y;

        switch (direccion) {
            case "w":
                nuevoY--;
                break;
            case "s":
                nuevoY++;
                break;
            case "a":
                nuevoX--;
                break;
            case "d":
                nuevoX++;
                break;
            default:
                System.out.println("Tecla inválida.");
                return; // No mover
        }

        if (!mapa.posicionValida(nuevoX, nuevoY)) {
            System.out.println("¡Movimiento fuera del mapa!");
            return; // No mover
        }

        // Remover personaje de la celda actual
        mapa.getMatriz()[y][x].removerPersonaje();

        // Agregar personaje en la nueva celda
        mapa.getMatriz()[nuevoY][nuevoX].setPersonaje(this);

        // Actualizar posición
        this.posicion.setX(nuevoX);
        this.posicion.setY(nuevoY);
    }

    @Override
    public char getRepresentacion() {
        return 'S';
    }

}
