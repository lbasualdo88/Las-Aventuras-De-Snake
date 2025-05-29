

import java.util.Random;
/**
 * @author balbisergio
 */
public class Mapa {
    
    /**Declaro  matriz bidimensional de objetos tipo Celda.*/
    private Celda[][] matriz; /**valor no debe cambiar una vez asignado*/
    private final int filas = 7;
    private final int columnas = 7;

    public Mapa() {
        matriz = new Celda[filas][columnas];
        inicializar();
    }

    private void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new Celda(); // Cada celda empieza vacía
            }
        }

        // Ubicamos a Snake en la esquina inferior izquierda (posición [6][0])
        matriz[6][0].setTipo('S');
    }

    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // luego se puede agregar métodos tales como:
    // ubicarEnemigo(), validarMovimiento(), etc.
    
    public void ubicarEnemigos(int cantidad) {
    Random rand = new Random();

    // Snake está en (6, 0)
    int snakeFila = 6;
    int snakeColumna = 0;

    int colocados = 0;
    while (colocados < cantidad) {
        int fila = rand.nextInt(filas);
        int columna = rand.nextInt(columnas);

        // Evitar la posición de Snake o demasiado cerca
        int distancia = Math.abs(fila - snakeFila) + Math.abs(columna - snakeColumna);
        boolean esZonaSegura = (distancia >= 2);

        if (!matriz[fila][columna].isOcupada() && esZonaSegura) {
            matriz[fila][columna].setTipo('G');
            colocados++;
        }
    }
}

}
   

