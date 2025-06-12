// Clase que representa una posición en un plano cartesiano (2D)
public class Posicion {

    // Coordenadas x e y de la posición
    private int x, y;

    // Constructor: permite crear una posición con valores específicos
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter para obtener el valor de X
    public int getX() {
        return x;
    }

    // Setter para modificar el valor de X
    public void setX(int x) {
        this.x = x;
    }

    // Getter para obtener el valor de Y
    public int getY() {
        return y;
    }

    // Setter para modificar el valor de Y
    public void setY(int y) {
        this.y = y;
    }
}
