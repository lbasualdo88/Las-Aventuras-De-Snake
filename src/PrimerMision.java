import java.util.Scanner;

public class PrimerMision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ejecutar(sc);
        sc.close();
    }

    public static void ejecutar(Scanner sc) {
        Mapa mapa = new Mapa();       // 7x7
        Snake snake = new Snake(new Posicion(0, 6));
        Posicion snakePosicion = snake.getPosicion();
        boolean tieneLlave = false;
        boolean sobreHangarSinLlave = false;
        String mensaje = null;

        mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].setTipo('S');
        mapa.ubicarEnemigos(4, snakePosicion);
        mapa.establecerObjetivo('L');
        Posicion hangar = mapa.establecerObjetivo('H');
        mapa.mostrarMapa();

        int hangarX = hangar.getX();
        int hangarY = hangar.getY();


        while (true) {
            System.out.print("Mover (w/a/s/d): ");
            String d = sc.next();
            char tipoCelda = mapa.moverSnake(snake, d);

            if (sobreHangarSinLlave) {
                mapa.getMatriz()[hangarY][hangarX].setTipo('H');
                sobreHangarSinLlave = false;
            }

            if (mapa.hayGuardiaCerca(snakePosicion)) {
                System.out.println("Fuiste capturado por un guardia!");
                break;
            }
            if (tipoCelda == 'L') {
                tieneLlave = true;
                mensaje = "Encontraste la llave para el hangar!";
            }
            if (tipoCelda == 'H' && !tieneLlave) {
                sobreHangarSinLlave = true;
                mensaje = "Te falta la llave para entrar!";
            } else if (tipoCelda == 'H' && tieneLlave) {
                System.out.println("Llegaste al hangar. Misión completada!");
                mapa.mostrarMapa();
                break;
            }
            mapa.mostrarMapa();
            if (mensaje != null) {
                System.out.println(mensaje);
                mensaje = null;
            }
        }
    }
}
