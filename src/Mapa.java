
import java.util.Random;

/**
 * @author balbisergio
 */
public class Mapa {

    /**
     * Matriz bidimensional de objetos tipo Celda
     */
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

    public void ubicarEnemigos(int cantidad, Posicion snakePosicion, Posicion llavePosicion, Posicion hangarPosicion) {
        Random random = new Random();
        int colocados = 0;

        while (colocados < cantidad) {
            int x = random.nextInt(matriz[0].length);
            int y = random.nextInt(matriz.length);
            Posicion pos = new Posicion(x, y);

            // Validaciones: no sobre Snake, ni adyacente a él
            // ni sobre llave/hangar, ni adyacente a ellos
            if (!pos.equals(snakePosicion)
                    && !esAdyacente(pos, snakePosicion)
                    && !pos.equals(llavePosicion)
                    && !esAdyacente(pos, llavePosicion)
                    && !pos.equals(hangarPosicion)
                    && !esAdyacente(pos, hangarPosicion)
                    && matriz[y][x].getTipo() == '.') {

                matriz[y][x].setTipo('G');
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

    public boolean posicionValida(int x, int y) {
        return y >= 0 && y < matriz.length && x >= 0 && x < matriz[0].length;
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

    public boolean esAdyacente(Posicion p1, Posicion p2) {
        if (p1 == null || p2 == null) {
            return false;  // no son adyacentes si alguna posición es null
        }
        int dx = Math.abs(p1.getX() - p2.getX());
        int dy = Math.abs(p1.getY() - p2.getY());

        return (dx <= 1 && dy <= 1) && !(dx == 0 && dy == 0);
    }

    

}
