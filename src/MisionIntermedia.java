
import java.util.Scanner;

// Clase que representa la segunda misión del juego
public class MisionIntermedia extends Mision {

    // Scanner para leer desde consola
    private Scanner leer;

    // Constructor que recibe el Scanner como parámetro
    public MisionIntermedia(Scanner leer) {
        this.leer = leer;
    }

    // Método que ejecuta la segunda misión
    public void ejecutar(Scanner leer) {

        // Crea el mapa (de tamaño 9x9)
        Mapa mapa = new Mapa(9, 9);

        // Crea el personaje Snake, comenzando en la esquina inferior izquierda
        Snake snake = new Snake(new Posicion(8, 0));
        Posicion snakePosicion = snake.getPosicion();

        // Variable para mostrar mensajes de eventos (como encontrar una tarjeta)
        String mensaje = null;

        // Coloca a Snake en su posición inicial dentro de la matriz del mapa
        mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].setPersonaje(snake);

        // Establece la posición de C4 (bomba) y devuelve su posición
        Posicion c4Pos = mapa.establecerObjetivo(new C4());

        // Establece la posición de la puerta (hangar) y guarda su posición
        Posicion hangar = mapa.establecerObjetivo(new Puerta());

        // Coloca 4 guardias en el mapa, evitando posiciones clave
        mapa.ubicarEnemigos(4, snakePosicion, c4Pos, hangar);

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

            // Si está en la puerta pero no tiene la bomba
            if (snakePosicion.equals(hangar) && !snake.tieneBomba()) {
                mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
            }

            // Si encuentra la bomba
            if (obj instanceof C4) {
                snake.recogerBomba((C4) obj);  // Snake recoge la bomba
                mensaje = "Encontraste la bomba para abrir el hangar!";
                mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].removerObjeto(); // Se remueve del mapa
                
              // Si encuentra la puerta  
            } else if (obj instanceof Puerta) {

                // Si no tiene bomba, no puede avanzar
                if (!snake.tieneBomba()) {
                    mensaje = "Te falta la bomba para entrar!";
                    mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());

                // Si tiene la bomba, gana el juego
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
            
            // Si hay un mensaje (como encontrar la bomba), lo muestra
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
