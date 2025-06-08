
import java.util.Scanner;

// Clase que representa la primera misión del juego
public class PrimerMision extends Mision{

    // Scanner para leer desde consola
    private Scanner leer;

    // Constructor que recibe el Scanner como parámetro
    public PrimerMision(Scanner leer) {
        super(false);
        this.leer = leer;
    }

    // Método que ejecuta la misión principal
    public void ejecutar(Scanner leer) {
        // Crea el mapa (de tamaño 7x7)
        Mapa mapa = new Mapa();

        // Crea el personaje Snake, comenzando en la esquina inferior izquierda
        Snake snake = new Snake(new Posicion(0, 6));
        Posicion snakePosicion = snake.getPosicion();

        // Variable para mostrar mensajes de eventos (como encontrar una tarjeta)
        String mensaje = null;

        // Coloca a Snake en su posición inicial dentro de la matriz del mapa
        mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].setPersonaje(snake);

        // Establece la posición de la tarjeta (llave) y devuelve su posición
        Posicion llavePos = mapa.establecerObjetivo(new Tarjeta());

        // Establece la posición de la puerta (hangar) y guarda su posición
        Posicion hangar = mapa.establecerObjetivo(new Puerta());

        // Coloca 4 guardias en el mapa, evitando posiciones clave
        mapa.ubicarEnemigos(4, snakePosicion, llavePos, hangar);

        // Muestra el mapa inicial
        mapa.mostrarMapa();

        // Guarda coordenadas del hangar para referencia posterior
        int hangarX = hangar.getX();
        int hangarY = hangar.getY();

        // Bucle principal del juego
        while (true) {

            // Snake realiza su movimiento
            snake.mover(mapa);
            snakePosicion = snake.getPosicion();

            // Obtiene el objeto actual donde está Snake
            Objeto obj = mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].getObjeto();

            // Si está en la puerta pero no tiene tarjeta
            if (snakePosicion.equals(hangar) && !snake.tieneTarjeta()) {
                mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
            }

            // Si encuentra la tarjeta
            if (obj instanceof Tarjeta) {
                snake.recogerTarjeta((Tarjeta) obj);  // Snake recoge la tarjeta
                mensaje = "Encontraste la llave para el hangar!";
                mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].removerObjeto(); // Se remueve del mapa

            // Si encuentra la puerta
            } else if (obj instanceof Puerta) {

                // Si no tiene tarjeta, no puede avanzar
                if (!snake.tieneTarjeta()) {
                    mensaje = "Te falta la llave para entrar!";
                    mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());

                // Si tiene la tarjeta, gana el juego
                } else {
                    System.out.println(" ");
                    System.out.println("--------------------------------------");
                    System.out.println("-Llegaste al hangar. Misión completada!-");
                    System.out.println("--------------------------------------");
                    System.out.println(" ");
                    break; // Fin del juego
                }
            }

            // Muestra el estado actual del mapa después del movimiento de Snake
            mapa.mostrarMapa();

            // Turno de los guardias para moverse
            mapa.moverGuardias();

            // Mensaje de advertencia tras mover guardias
            System.out.println("----------------------------------------");
            System.out.println("CUIDADO!- Los guardias te estan buscando");
            System.out.println("----------------------------------------");
            mapa.mostrarMapa();

            // Verifica si hay algún guardia cerca de Snake
            if (mapa.hayGuardiaCerca(snakePosicion)) {
                System.out.println(" ");
                System.out.println("////////////////////////////////////");
                System.out.println("//Fuiste capturado por un guardia!//");
                System.out.println("////////////////////////////////////");
                System.out.println(" ");
                break; // Fin del juego por captura
            }

            // Si hay un mensaje (como encontrar la tarjeta), lo muestra
            if (mensaje != null) {
                System.out.println(" ");
                System.out.println(mensaje);
                System.out.println("------------------------------------");
                System.out.println(" ");
                mensaje = null; // Se limpia para el siguiente turno
            }
        }
    }
}
