
public class Celda {

    private Personaje personaje;  // Snake o Guardia
    private Objeto objeto;        // Tarjeta, Puerta, C4

    public Celda() {
        this.personaje = null;
        this.objeto = null;
    }

    public boolean estaOcupada() {
        return personaje != null || objeto != null;
    }

    public boolean tienePersonaje() {
        return personaje != null;
    }

    public boolean tieneObjeto() {
        return objeto != null;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public void removerPersonaje() {
        this.personaje = null;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public void removerObjeto() {
        this.objeto = null;
    }

    public char getRepresentacion() {
        if (personaje != null) {
            if (personaje instanceof Snake) return 'S';
            if (personaje instanceof Guardia) return 'G';
            // Puedes agregar más si hay más personajes
        }

        if (objeto != null) {
            if (objeto instanceof Tarjeta) return 'L';
            if (objeto instanceof Puerta) return 'H';
            if (objeto instanceof C4) return 'C';
        }

        return '.'; // Celda vacía
    }

    @Override
    public String toString() {
        return String.valueOf(getRepresentacion());
    }
}

