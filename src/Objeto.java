public abstract class Objeto {

    public abstract String getTipo();

    public char getRepresentacion() {
        switch (getTipo()) {
            case "Tarjeta":
                return 'L';
            case "Puerta":
                return 'H';
            case "C4":
                return 'C';
            default:
                return '?';
        }
    }
}
