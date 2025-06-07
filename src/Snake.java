
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
        return Math.random() < 0.5;  // 50% ataca, 50% esquiva
    }

    public boolean combate() {
        System.out.println("-Selecciona tu jugada-");
        System.out.println("1 - Atacar");
        System.out.println("2 - Esquivar");
        int opcion = leer.nextInt();
        return opcion == 1;
    }

    @Override
    public char mover(Mapa mapa, String direccion) {

        System.out.println("---------------------------------");

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
                return mapa.getMatriz()[y][x].getTipo();  // sin moverse
        }

        // Verifica que no se salga del mapa
        if (!mapa.posicionValida(nuevoX, nuevoY)) {
            System.out.println("¡Movimiento fuera del mapa!");
            return mapa.getMatriz()[y][x].getTipo();
        }

        // Obtener tipo de la celda destino antes de mover
        char tipoDestino = mapa.getMatriz()[nuevoY][nuevoX].getTipo();

        // Actualiza el mapa
        mapa.getMatriz()[y][x].setTipo('.');  // limpia posición anterior
        mapa.getMatriz()[nuevoY][nuevoX].setTipo('S');

        // Actualiza posición de Snake
        this.posicion.setX(nuevoX);
        this.posicion.setY(nuevoY);

        return tipoDestino;
    }
}
