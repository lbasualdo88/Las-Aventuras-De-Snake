


public class Celda {

    private char tipo;  // 'S' = Snake, 'G' = Guardia, 'M' = MetalGear, 'H' = Hangar, '.' = vacío
    private boolean ocupada;

    public Celda() {
        this.tipo = '.';  // Por defecto, celda vacía
        this.ocupada = false;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
        this.ocupada = (tipo != '.'); // Si se asigna un tipo, se marca ocupada
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
        if (!ocupada) {
            this.tipo = '.';  // Si se desocupa, vuelve a tipo vacío
        }
    }

    @Override
    public String toString() {
        return String.valueOf(tipo);
    }
}

