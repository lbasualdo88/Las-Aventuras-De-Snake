

import java.util.Random;
/**
 * @author balbisergio
 */
public class Mapa {

    /** Matriz bidimensional de objetos tipo Celda */
    private final Celda[][] matriz;
    private final int filas, columnas;

    // Constructor por defecto: Misión 1 (7x7)
    public Mapa() {
        this.filas = 7;
        this.columnas = 7;
        matriz = new Celda[filas][columnas];
        inicializar();
    }

    // Constructor para Misión 2 (9x9) o dimensiones personalizadas
    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = new Celda[filas][columnas];
        inicializar();
    }

    private void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new Celda(); // Cada celda empieza vacía
            }
        }
    }

    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void ubicarEnemigos(int cantidad, Posicion snakePos) {
        Random rand = new Random();

        int snakeFila = snakePos.getY();
        int snakeColumna = snakePos.getX();

        int colocados = 0;
        while (colocados < cantidad) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);
            int distancia = Math.abs(fila - snakeFila) + Math.abs(columna - snakeColumna);

            boolean esZonaSegura = (distancia >= 2);
            if (!matriz[fila][columna].isOcupada() && esZonaSegura) {
                matriz[fila][columna].setTipo('G');
                colocados++;
            }
        }
    }

    public Posicion establecerObjetivo(char tipo) {
        Random rand = new Random();

        while (true) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (!matriz[fila][columna].isOcupada()) {
                matriz[fila][columna].setTipo(tipo);
                return new Posicion(columna, fila);
            }
        }
    }

    public char moverSnake(Snake snake, String direccion) {
        Posicion actual = snake.getPosicion();
        int xActual = actual.getX();
        int yActual = actual.getY();

        Posicion destino = new Posicion(xActual, yActual);

        switch (direccion) {
            case "w" -> destino.moverArriba();
            case "s" -> destino.moverAbajo();
            case "a" -> destino.moverIzquierda();
            case "d" -> destino.moverDerecha();
            default -> {
                System.out.println("Se debe usar una de las siguientes teclas: w/a/s/d");
                return '.'; // no se mueve, devuelve '.' como tipo “sin cambio”
            }
        }
        int xDestino = destino.getX();
        int yDestino = destino.getY();
        if (xDestino < 0 || xDestino >= columnas || yDestino < 0 || yDestino >= filas) {
            System.out.println("Movimiento fuera del mapa. Quedas en la misma casilla.");
            return '.';
        }

        char tipoAntiguo = matriz[yDestino][xDestino].getTipo();

        matriz[yActual][xActual].setOcupada(false);

        snake.getPosicion().setX(xDestino);
        snake.getPosicion().setY(yDestino);

        matriz[yDestino][xDestino].setTipo('S');

        return tipoAntiguo;
    }

    public boolean hayGuardiaCerca(Posicion posicion) {
        int x = posicion.getX();
        int y = posicion.getY();

        // verificar celda arriba
        if (y - 1 >= 0) {
            if (matriz[y - 1][x].getTipo() == 'G') {
                return true;
            }
        }

        // verificar celda abajo
        if (y + 1 < filas) {
            if (matriz[y + 1][x].getTipo() == 'G') {
                return true;
            }
        }

        // verificar celda izquierda
        if (x - 1 >= 0) {
            if (matriz[y][x - 1].getTipo() == 'G') {
                return true;
            }
        }

        // verificar celda derecha
        if (x + 1 < columnas) {
            return matriz[y][x + 1].getTipo() == 'G';
        }

        return false;
    }

    // Getter opcional x si necesitamos  acceder al mapa desde otras clases
    public Celda[][] getMatriz() {
        return matriz;
    }
}
