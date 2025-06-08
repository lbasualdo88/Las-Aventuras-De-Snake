
import java.util.Scanner;

// Clase que representa al personaje Snake, hereda de Personaje
public class Snake extends Personaje {

    private Scanner leer;      // Para leer entrada del usuario
    private Tarjeta tarjeta;  // Tarjeta que puede recoger el personaje
    private C4 bomba;  // C4 que puede recoger el personaje

    // Constructor con posición inicial
    public Snake(Posicion posicion) {
        super(posicion);
        this.leer = new Scanner(System.in);
        this.tarjeta = null;
        this.bomba = null;
    }

    // Constructor sin posición inicial
    public Snake() {
        this.leer = new Scanner(System.in);
    }

    // Guarda una tarjeta que el personaje ha recogido
    public void recogerTarjeta(Tarjeta t) {
        this.tarjeta = t;
    }

    // Devuelve la tarjeta que tiene el personaje
    public Tarjeta getTarjeta() {
        return this.tarjeta;
    }

    // Indica si el personaje tiene una tarjeta
    public boolean tieneTarjeta() {
        return this.tarjeta != null;
    }

    // Guarda una bomba que el personaje ha recogido
    public void recogerBomba(C4 b) {
        this.bomba = b;
    }

    // Devuelve la c4 que tiene el personaje
    public C4 getC4() {
        return this.bomba;
    }

    // Indica si el personaje tiene la bomba
    public boolean tieneBomba() {
        return this.bomba != null;
    }

    // Indica que Snake siempre ataca (comportamiento fijo)
    public boolean ataque() {
        return true;
    }

    // Indica que Snake nunca esquiva (comportamiento fijo)
    public boolean esquivar() {
        return false;
    }

    // Combate aleatorio: elige aleatoriamente entre atacar y esquivar
    @Override
    public boolean combateRandom() {
        boolean eleccion = esquivar(); // Por defecto esquiva (false)
        int movimiento = (int) (Math.random() * 2);  // Genera 0 o 1 aleatoriamente

        if (movimiento == 0) {
            // Si el número aleatorio es 0, ataca
            eleccion = ataque();
        }
        // Devuelve la elección aleatoria (true si ataca, false si esquiva)
        return eleccion;
    }

    // Combate controlado por el usuario: elige si atacar o esquivar
    public boolean combate() {
        Scanner leer = new Scanner(System.in);
        boolean eleccionUsuario = false;

        // Muestra las opciones al usuario
        System.out.println("-Selecciona tu jugada-");
        System.out.println("----------------------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Esquivar");
        System.out.println("----------------------");

        int opcion = leer.nextInt();

        switch (opcion) {
            case 1:
                // Usuario elige atacar
                eleccionUsuario = ataque();
                break;
            case 2:
                // Usuario elige esquivar
                eleccionUsuario = esquivar();
                break;
        }

        // Devuelve la elección del usuario
        return eleccionUsuario;
    }

    // Método que permite mover a Snake en el mapa
    @Override
    public void mover(Mapa mapa) {
        int x = posicion.getX();     // Posición actual en X
        int y = posicion.getY();     // Posición actual en Y
        int nuevoX = x;              // Nueva posición tentativa en X
        int nuevoY = y;              // Nueva posición tentativa en Y

        // Solicita dirección al usuario
        System.out.println("---------------------------------");
        System.out.print("Mover (w:↑ - a:← - s:↓ - d:→): ");
        String direccion = leer.next();

        // Actualiza coordenadas según la dirección ingresada
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
                // Entrada inválida, no se mueve
                System.out.println("Tecla inválida.");
                return;
        }

        // Verifica si la nueva posición está dentro de los límites del mapa
        if (!mapa.posicionValida(nuevoX, nuevoY)) {
            System.out.println("¡Movimiento fuera del mapa!");
            return; // No se actualiza la posición
        }

        // Remueve el personaje de la celda actual
        mapa.getMatriz()[y][x].removerPersonaje();

        // Coloca el personaje en la nueva celda
        mapa.getMatriz()[nuevoY][nuevoX].setPersonaje(this);

        // Actualiza la posición interna del personaje
        this.posicion.setX(nuevoX);
        this.posicion.setY(nuevoY);
    }

    // Representación del personaje en el mapa
    @Override
    public char getRepresentacion() {
        return 'S';
    }

}
