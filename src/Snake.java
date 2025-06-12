import java.util.Scanner;

// Clase que representa al personaje Snake, hereda de Personaje
public class Snake extends Personaje {

    private final Scanner sc = new Scanner(System.in);  // Para leer entrada del usuario
    private Tarjeta tarjeta;  // Tarjeta que puede recoger el personaje
    private C4 bomba;  // C4 que puede recoger el personaje

    // Constructor con posición inicial
    public Snake(Posicion p) {
        super(p);
        this.tarjeta = null;
        this.bomba = null;
    }

    // Guarda una tarjeta que el personaje ha recogido
    public void recogerTarjeta(Tarjeta t) {
        this.tarjeta = t;
    }

    // Indica si el personaje tiene una tarjeta
    public boolean tieneTarjeta() {
        return this.tarjeta != null;
    }

    // Guarda una bomba que el personaje ha recogido
    public void recogerBomba(C4 b) {
        this.bomba = b;
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
        boolean eleccion = esquivar();
        int movimiento = (int) (Math.random() * 2);  // 0 = ataca, 1 = esquiva
        if (movimiento == 0) {
            eleccion = ataque();
        }
        return eleccion;
    }

    // Combate controlado por el usuario: elige si atacar o esquivar
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

    // Método que permite mover a Snake en el mapa
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
                return;
        }

        // Verifica si la nueva posición está dentro de los límites del mapa
        if (!mapa.posicionValida(nuevoX, nuevoY)) {
            System.out.println("¡Movimiento fuera del mapa!");
            return;
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
