import java.util.Scanner;

public abstract class Mision {
    protected final Mapa mapa;
    protected final Snake snake;
    protected final Scanner sc;

    public Mision(Mapa mapa, Snake snake, Scanner sc) {
        this.mapa = mapa;
        this.snake = snake;
        this.sc = sc;
    }

    public abstract void iniciar();
}
