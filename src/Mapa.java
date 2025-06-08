import java.util.Random;

public class Mapa {

    // Matriz bidimensional que representa el mapa compuesto por celdas
    private final Celda[][] matriz;

    // Cantidad de filas del mapa
    private final int filas;

    // Cantidad de columnas del mapa
    private final int columnas;

    // Constructor por defecto (misión 1), crea un mapa de 7x7
    public Mapa() {
        this.filas = 7;
        this.columnas = 7;
        matriz = new Celda[filas][columnas];
        inicializar(); // Inicializa todas las celdas
    }

    // Constructor personalizado 
    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = new Celda[filas][columnas];
        inicializar(); // Inicializa todas las celdas
    }

    // Inicializa la matriz del mapa asignando una nueva celda vacía a cada posición
    private void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new Celda(); // Cada celda inicia sin personaje ni objeto
            }
        }
    }

    // Muestra el mapa en la consola
    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j].getRepresentacion() + " "); // Muestra el símbolo de cada celda
            }
            System.out.println();
        }
    }

    // Ubica enemigos (guardias) aleatoriamente, evitando zonas sensibles
    public void ubicarEnemigos(int cantidad, Posicion snakePosicion, Posicion llavePosicion, Posicion hangarPosicion) {
        Random random = new Random();
        int colocados = 0;

        while (colocados < cantidad) {
            int x = random.nextInt(matriz[0].length); // columna aleatoria
            int y = random.nextInt(matriz.length);    // fila aleatoria
            Posicion pos = new Posicion(x, y);        // nueva posición candidata

            // Verifica que la celda no sea una zona prohibida o cercana a Snake, llave u objetivo
            if (!pos.equals(snakePosicion)
                    && !esAdyacente(pos, snakePosicion)
                    && !pos.equals(llavePosicion)
                    && !esAdyacente(pos, llavePosicion)
                    && !pos.equals(hangarPosicion)
                    && !esAdyacente(pos, hangarPosicion)
                    && !matriz[y][x].estaOcupada()) {

                Guardia guardia = new Guardia(pos);           // Crea nuevo guardia
                matriz[y][x].setPersonaje(guardia);           // Lo coloca en la celda
                colocados++;                                  // Aumenta el contador
            }
        }
    }

    // Coloca un objeto (como la tarjeta o hangar) en una celda vacía al azar
    public Posicion establecerObjetivo(Objeto objeto) {
        Random rand = new Random();

        while (true) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            // Si la celda está libre, coloca el objeto y devuelve su posición
            if (!matriz[fila][columna].estaOcupada()) {
                matriz[fila][columna].setObjeto(objeto);
                return new Posicion(columna, fila);
            }
        }
    }

    // Verifica si una coordenada está dentro de los límites del mapa
    public boolean posicionValida(int x, int y) {
        return y >= 0 && y < matriz.length && x >= 0 && x < matriz[0].length;
    }

    // Verifica si hay un guardia adyacente (arriba, abajo, izquierda o derecha) a una posición dada
    public boolean hayGuardiaCerca(Posicion posicion) {
        int x = posicion.getX();
        int y = posicion.getY();

        // Verifica arriba
        if (y - 1 >= 0) {
            Celda arriba = matriz[y - 1][x];
            if (arriba.tienePersonaje() && arriba.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        // Verifica abajo
        if (y + 1 < filas) {
            Celda abajo = matriz[y + 1][x];
            if (abajo.tienePersonaje() && abajo.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        // Verifica izquierda
        if (x - 1 >= 0) {
            Celda izquierda = matriz[y][x - 1];
            if (izquierda.tienePersonaje() && izquierda.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        // Verifica derecha
        if (x + 1 < columnas) {
            Celda derecha = matriz[y][x + 1];
            if (derecha.tienePersonaje() && derecha.getPersonaje() instanceof Guardia) {
                return true;
            }
        }

        return false; // No encontró ningún guardia alrededor
    }

    // Retorna la matriz completa de celdas del mapa
    public Celda[][] getMatriz() {
        return matriz;
    }

    // Determina si dos posiciones están adyacentes, es decir, una al lado de la otra
    public boolean esAdyacente(Posicion p1, Posicion p2) {
        if (p1 == null || p2 == null) {
            return false;
        }

        int dx = Math.abs(p1.getX() - p2.getX()); // Diferencia en columnas
        int dy = Math.abs(p1.getY() - p2.getY()); // Diferencia en filas

        // Devuelve true si están al lado, pero no si son iguales
        return (dx <= 1 && dy <= 1) && !(dx == 0 && dy == 0);
    }

    // Mueve todos los guardias en el mapa
    public void moverGuardias() {
        // Array temporal para guardar las posiciones actuales de los guardias
        Posicion[] posiciones = new Posicion[filas * columnas];
        int cantidadGuardias = 0;

        // Recorre toda la matriz y guarda las posiciones donde hay guardias
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                Celda celda = matriz[y][x];
                if (celda.tienePersonaje() && celda.getPersonaje() instanceof Guardia) {
                    posiciones[cantidadGuardias++] = new Posicion(x, y);
                }
            }
        }

        // Mueve cada guardia desde su posición original
        for (int i = 0; i < cantidadGuardias; i++) {
            Posicion pos = posiciones[i];
            Celda celda = matriz[pos.getY()][pos.getX()];

            // Confirma que sigue habiendo un guardia en esa posición (no se movió aún)
            if (celda.tienePersonaje() && celda.getPersonaje() instanceof Guardia) {
                Guardia guardia = (Guardia) celda.getPersonaje();
                guardia.mover(this); // Llama al método mover() del guardia, pasando el mapa
            }
        }
    }
}
