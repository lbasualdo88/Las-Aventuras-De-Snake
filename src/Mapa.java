
import java.util.Random;

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
                System.out.print(matriz[i][j].getRepresentacion() + " ");
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
                    && !matriz[y][x].estaOcupada()) {
                Guardia guardia = new Guardia(pos);
                matriz[y][x].setPersonaje(guardia);
                colocados++;
            }
        }
    }

    public Posicion establecerObjetivo(Objeto objeto) {
        Random rand = new Random();

        while (true) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (!matriz[fila][columna].estaOcupada()) {
                matriz[fila][columna].setObjeto(objeto);
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
            Celda arriba = matriz[y - 1][x];
            if (arriba.tienePersonaje() && arriba.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        // verificar celda abajo
        if (y + 1 < filas) {
            Celda abajo = matriz[y + 1][x];
            if (abajo.tienePersonaje() && abajo.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        // verificar celda izquierda
        if (x - 1 >= 0) {
            Celda izquierda = matriz[y][x - 1];
            if (izquierda.tienePersonaje() && izquierda.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        // verificar celda derecha
        if (x + 1 < columnas) {
            Celda derecha = matriz[y][x + 1];
            if (derecha.tienePersonaje() && derecha.getPersonaje() instanceof Guardia) {
                return true;
            }
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

    public void moverGuardias() {
        Posicion[] posiciones = new Posicion[filas * columnas];
        int cantidadGuardias = 0;

        // Guardamos primero las posiciones de los guardias
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                Celda celda = matriz[y][x];
                if (celda.tienePersonaje() && celda.getPersonaje() instanceof Guardia) {
                    posiciones[cantidadGuardias++] = new Posicion(x, y);
                }
            }
        }

        // Luego los movemos desde sus posiciones originales
        for (int i = 0; i < cantidadGuardias; i++) {
            Posicion pos = posiciones[i];
            Celda celda = matriz[pos.getY()][pos.getX()];

            if (celda.tienePersonaje() && celda.getPersonaje() instanceof Guardia) {
                Guardia guardia = (Guardia) celda.getPersonaje();
                guardia.mover(this, null);
            }

        }
    }

}