public abstract class Objeto {

    // Método abstracto que debe devolver el tipo del objeto, como "Tarjeta", "Puerta", "C4", etc.
    public abstract String getTipo();

    // Retorna un carácter que representa visualmente al objeto en el mapa
    public char getRepresentacion() {
        switch (getTipo()) {
            case "Tarjeta":
                return 'L';  // Representa la tarjeta (llave)
            case "Puerta":
                return 'H';  // Representa la puerta (hangar)
            case "C4":
                return 'B';  // Representa el explosivo C4
            default:
                return ' ';  // Para tipos desconocidos o no definidos
        }
    }
}
