import java.util.Random;

// Clase que representa a un guardia enemigo, hereda de Personaje e implementa la interfaz Enemigo
public class Guardia extends Personaje implements Enemigo {

    // Constructor que recibe una posición inicial
    public Guardia(Posicion posicion) {
        super(posicion);
    }

    // Constructor vacío (puede usarse para inicializar después)
    public Guardia() {
    }

    // Método para combate aleatorio (no está implementado todavía)
    @Override
    public boolean combateRandom() {
        throw new UnsupportedOperationException("Método no implementado.");
    }

    // Método que permite al guardia moverse en el mapa de forma aleatoria
    @Override
    public void mover(Mapa mapa) {
        Random rand = new Random();  

        // Posibles direcciones de movimiento (arriba, abajo, izquierda, derecha)
        int[][] direcciones = {
            {0, -1},  // arriba
            {0, 1},   // abajo
            {-1, 0},  // izquierda
            {1, 0}    // derecha
        };

        // Obtiene la posición actual del guardia
        int xActual = posicion.getX();
        int yActual = posicion.getY();

        // Busca la posición del hangar y de la llave en el mapa
        Posicion posHangar = encontrarPosicion(mapa, 'H');
        Posicion posLlave = encontrarPosicion(mapa, 'L');

        // Intenta moverse hasta 10 veces para encontrar un movimiento válido
        for (int i = 0; i < 10; i++) {
            // Elige una dirección aleatoria
            int[] dir = direcciones[rand.nextInt(direcciones.length)];
            int nuevoX = xActual + dir[0];
            int nuevoY = yActual + dir[1];

            // Si la nueva posición está fuera del mapa, intenta otra
            if (!mapa.posicionValida(nuevoX, nuevoY)) {
                continue;
            }

            // Obtiene la celda destino
            Celda celdaDestino = mapa.getMatriz()[nuevoY][nuevoX];

            // Si la celda ya tiene un personaje, no se mueve
            if (celdaDestino.estaOcupada()) {
                continue;
            }

            // Crea un objeto Posicion para verificar adyacencias
            Posicion nuevaPos = new Posicion(nuevoX, nuevoY);

            // Si está al lado del hangar o de la llave, no se mueve
            if ((posHangar != null && mapa.esAdyacente(nuevaPos, posHangar))
                    || (posLlave != null && mapa.esAdyacente(nuevaPos, posLlave))) {
                continue;
            }

            // Si pasó todas las condiciones, realiza el movimiento:
            // 1. Quita al guardia de su celda actual
            mapa.getMatriz()[yActual][xActual].removerPersonaje();

            // 2. Actualiza su posición interna
            this.posicion.setX(nuevoX);
            this.posicion.setY(nuevoY);

            // 3. Lo coloca en la nueva celda
            mapa.getMatriz()[nuevoY][nuevoX].setPersonaje(this);
            break;  
        }
    }

    // Método auxiliar: busca en el mapa una celda con un carácter específico (por ejemplo 'H' o 'L')
    private Posicion encontrarPosicion(Mapa mapa, char tipo) {
        Celda[][] celdas = mapa.getMatriz();
        for (int y = 0; y < celdas.length; y++) {
            for (int x = 0; x < celdas[0].length; x++) {
                if (celdas[y][x].getRepresentacion() == tipo) {
                    return new Posicion(x, y);
                }
            }
        }
        return null; 
    }

    // Devuelve el carácter que representa al guardia en el mapa
    @Override
    public char getRepresentacion() {
        return 'G';
    }
}
