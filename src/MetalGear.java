// Clase que representa al enemigo Metal Gear Rex, hereda de Personaje e implementa la interfaz Enemigo
public class MetalGear extends Personaje implements Enemigo {

    // Constructor que recibe una posición inicial en el mapa (No se usa)
    public MetalGear(Posicion posicion) {
        super(posicion);
    }

    @Override
    public void mover(Mapa mapa, String direccion) {
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
        boolean esMisil = Math.random() < 0.5;
        if (esMisil) {
            System.out.println("Rex lanza misil");
            return -40;
        } else {
            System.out.println("Rex dispara láser");
            return -15;
        }
    }

    // Método que devuelve el carácter que representa a Rex en el mapa (No se usa)
    @Override
    public char getRepresentacion() {
        return 'R';
    }

    @Override
    public void atacar(Personaje objetivo) {
        int golpe = tipoDeAtaque();
        objetivo.recibirGolpe(golpe);
        System.out.printf("MetalGear hizo %d de daño a %s%n",
                golpe, objetivo.getClass().getSimpleName());
    }

    // Método que define el comportamiento aleatorio de combate
    @Override
    public boolean combateRandom() {
        // 50% de probabilidad de atacar
        return Math.random() < 0.5;
    }
}
