public abstract class Personaje {
// Clase abstracta que representa un personaje en el juego

    public Posicion posicion;

    // Todos los personajes empiezan con 100 puntos de vida
    public int Vida = 100;

    // Constructor que recibe una posición al crear el personaje
    public Personaje(Posicion posicion) {
        this.posicion = posicion;
    }

    // Constructor vacío (permite crear un personaje sin posición inicial)
    public Personaje() {
    }

    // Método que devuelve la posición actual del personaje
    public Posicion getPosicion() {
        return posicion;
    }

    // Método que devuelve la vida actual del personaje
    public int getVida() {
        return Vida;
    }

    // Método para modificar la vida del personaje.
    public void setVida(int Vida) {
        this.Vida += Vida;
    }

    // Método abstracto que define cómo se mueve un personaje en el mapa
    public abstract void mover(Mapa mapa, String direccion);

    // Método abstracto para decidir aleatoriamente si el personaje ataca o esquiva
    public abstract boolean combateRandom();

    // Método abstracto que devuelve un carácter para representar al personaje en el mapa
    public abstract char getRepresentacion();

    public void recibirGolpe(int golpe) {
        Vida = Math.max(0, Vida - golpe);
    }

}
