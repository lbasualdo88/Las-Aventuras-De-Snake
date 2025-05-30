

import java.util.Random;
/**
 * @author balbisergio
 */
public class Mapa {

    /** Matriz bidimensional de objetos tipo Celda */
    private Celda[][] matriz;
    private int filas;
    private int columnas;

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

        // se valida si la posición [filas-1][0] existe (para Snake)
        if (filas > 0 && columnas > 0) {
            matriz[filas - 1][0].setTipo('S'); // Snake en esquina inferior izquierda
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

    public void ubicarEnemigos(int cantidad) {
        Random rand = new Random();

        // Posición inicial de Snake
        int snakeFila = filas - 1;
        int snakeColumna = 0;

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

    // Getter opcional x si necesitamos  acceder al mapa desde otras clases
    public Celda[][] getMatriz() {
        return matriz;
    }
}
