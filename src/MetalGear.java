// Clase que representa al enemigo Metal Gear Rex, hereda de Personaje e implementa la interfaz Enemigo
public class MetalGear extends Personaje implements Enemigo {

    // Constructor que recibe una posición inicial en el mapa (No se usa)
    public MetalGear(Posicion posicion) {
        super(posicion);
    }

    // Constructor vacío
    public MetalGear() {
    }

    // Método para mover a Rex (No se usa)
    @Override
    public void mover(Mapa mapa) {
        
    }

    // Método que representa el ataque de Rex.
    public boolean ataque() {
        return true;
    }

    // Método que representa una evasión.
    public boolean esquivar() {
        return false;
    }

    // Método que determina el tipo de ataque aleatoriamente
    public int tipoDeAtaque() {
        
        int tipoAtaque = (int) (Math.random() * 2);

        if (tipoAtaque == 0) {
            System.out.println("Rex lanza misil");
            tipoAtaque = -40;  // El misil causa 40 puntos de daño
        } else {
            System.out.println("Rex dispara láser");
            tipoAtaque = -15;  // El láser causa 15 puntos de daño
        }

        return tipoAtaque;
    }

    // Método que devuelve el carácter que representa a Rex en el mapa (No se usa)
    @Override
    public char getRepresentacion() {
        return 'R';     
    }

    // Método que define el comportamiento aleatorio de combate
    @Override
    public boolean combateRandom() {
        boolean eleccion = esquivar();  
        
        // Genera aleatoriamente una acción: 0 = ataca, 1 = esquiva
        int movimiento = (int) (Math.random() * 2);
        if (movimiento == 0) {
            eleccion = ataque();  
        }

        return eleccion;  
    }
}
