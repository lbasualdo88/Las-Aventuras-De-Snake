// Clase que representa una celda del mapa en el juego
public class Celda {

    // La celda puede contener un personaje (como Snake o un Guardia)
    private Personaje personaje;

    // También puede tener un objeto (como una Tarjeta, Puerta o C4)
    private Objeto objeto;

    // Constructor por defecto: crea una celda vacía (sin personaje ni objeto)
    public Celda() {
        this.personaje = null;
        this.objeto = null;
    }

    // Devuelve true si la celda tiene un personaje o un objeto
    public boolean estaOcupada() {
        return personaje != null || objeto != null;
    }

    // Devuelve true si hay un personaje en la celda
    public boolean tienePersonaje() {
        return personaje != null;
    }

    // Devuelve true si hay un objeto en la celda
    public boolean tieneObjeto() {
        return objeto != null;
    }

    // Devuelve el personaje actual de la celda (puede ser null)
    public Personaje getPersonaje() {
        return personaje;
    }

    // Asigna un personaje a la celda
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    // Elimina al personaje de la celda
    public void removerPersonaje() {
        this.personaje = null;
    }

    // Devuelve el objeto actual de la celda (puede ser null)
    public Objeto getObjeto() {
        return objeto;
    }

    // Asigna un objeto a la celda
    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    // Elimina el objeto de la celda
    public void removerObjeto() {
        this.objeto = null;
    }

    // Representa gráficamente lo que hay en la celda:
    // S para Snake, G para Guardia, L para Tarjeta (llave), H para Puerta, C para C4, . si está vacía
    public char getRepresentacion() {
        if (personaje != null) {
            if (personaje instanceof Snake) return 'S';
            if (personaje instanceof Guardia) return 'G';
        }
        if (objeto != null) {
            if (objeto instanceof Tarjeta) return 'L';
            if (objeto instanceof Puerta) return 'H';
            if (objeto instanceof C4) return 'B';
        }
        // Si no hay ni personaje ni objeto, la celda se representa con un punto
        return '.';
    }

    // Sobrescribe el método toString para mostrar la representación de la celda como texto
    @Override
    public String toString() {
        return String.valueOf(getRepresentacion());
    }
}
