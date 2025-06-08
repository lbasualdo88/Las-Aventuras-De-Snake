
import java.util.Random;

public class Guardia extends Personaje implements Enemigo {

    public Guardia(Posicion posicion) {
        super(posicion);
    }

    public Guardia() {
    }

    @Override
    public boolean combateRandom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mover(Mapa mapa, String direccion) {
        Random rand = new Random();

        int[][] direcciones = {
            {0, -1}, // arriba
            {0, 1}, // abajo
            {-1, 0}, // izquierda
            {1, 0} // derecha
        };

        int xActual = posicion.getX();
        int yActual = posicion.getY();

        Posicion posHangar = encontrarPosicion(mapa, 'H');
        Posicion posLlave = encontrarPosicion(mapa, 'L');

        for (int i = 0; i < 10; i++) {
            int[] dir = direcciones[rand.nextInt(direcciones.length)];
            int nuevoX = xActual + dir[0];
            int nuevoY = yActual + dir[1];

            if (!mapa.posicionValida(nuevoX, nuevoY)) {
                continue;
            }

            Celda celdaDestino = mapa.getMatriz()[nuevoY][nuevoX];

            // No se mueve si la celda está ocupada
            if (celdaDestino.estaOcupada()) {
                continue;
            }

            Posicion nuevaPos = new Posicion(nuevoX, nuevoY);

            // No se mueve si está adyacente a Hangar o Llave
            if ((posHangar != null && mapa.esAdyacente(nuevaPos, posHangar))
                    || (posLlave != null && mapa.esAdyacente(nuevaPos, posLlave))) {
                continue;
            }

            // Movimiento válido: actualizar mapa y posición
            mapa.getMatriz()[yActual][xActual].removerPersonaje();
            this.posicion.setX(nuevoX);
            this.posicion.setY(nuevoY);
            mapa.getMatriz()[nuevoY][nuevoX].setPersonaje(this);
            break;
        }
    }

    private Posicion encontrarPosicion(Mapa mapa, char tipo) {
        Celda[][] celdas = mapa.getMatriz();
        for (int y = 0; y < celdas.length; y++) {
            for (int x = 0; x < celdas[0].length; x++) {
                if (celdas[y][x].getRepresentacion() == tipo) {
                    return new Posicion(x, y);
                }
            }
        }
        return null; // Por si no se encuentra
    }

    @Override
    public char getRepresentacion() {
        return 'G';
    }
}
