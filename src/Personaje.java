
public abstract class Personaje {

    public Posicion posicion;
    public int Vida = 100;

    public Personaje(Posicion posicion) {
        this.posicion = posicion;
    }

    public Personaje() {
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public int getVida() {
        return Vida;
    }

    public void setVida(int Vida) {
        this.Vida += Vida;
    }

    public abstract void mover(Mapa mapa, String direccion);

    public abstract boolean combateRandom();
    
    public abstract char getRepresentacion();

}
