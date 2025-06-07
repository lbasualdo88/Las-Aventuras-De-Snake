
import java.util.Scanner;

public class PrimerMision {

    private Scanner leer;

    public PrimerMision(Scanner leer) {
        this.leer = leer;
    }

    public void ejecutar(Scanner leer) {
        Mapa mapa = new Mapa();       // 7x7
        Snake snake = new Snake(new Posicion(0, 6));
        Posicion snakePosicion = snake.getPosicion();
        //boolean tieneLlave = false;
        boolean sobreHangarSinLlave = false;
        String mensaje = null;

        mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].setTipo('S');
        Posicion llavePos = mapa.establecerObjetivo('L');
        Posicion hangar = mapa.establecerObjetivo('H');
        mapa.ubicarEnemigos(4, snakePosicion, llavePos, hangar);
        mapa.mostrarMapa();

        int hangarX = hangar.getX();
        int hangarY = hangar.getY();

        while (true) {
            System.out.println("---------------------------------");
            System.out.print("Mover (w:\u2191 - a:\u2190 - s:\u2193 - d:\u2192): ");
            String d = leer.next();
            //System.out.println("---------------------------------");
            char tipoCelda = snake.mover(mapa, d);
            snakePosicion = snake.getPosicion();

            if (sobreHangarSinLlave) {
                mapa.getMatriz()[hangarY][hangarX].setTipo('H');
                sobreHangarSinLlave = false;
            }

            if (mapa.hayGuardiaCerca(snakePosicion)) {
                System.out.println(" ");
                System.out.println("--------------------------------");
                System.out.println("Fuiste capturado por un guardia!");
                System.out.println("--------------------------------");
                System.out.println(" ");
                break;
            }
            if (tipoCelda == 'L') {
                snake.recogerTarjeta(new Tarjeta());
                mensaje = "Encontraste la llave para el hangar!";
            } else if (tipoCelda == 'H') {
                if (!snake.tieneTarjeta()) {
                    sobreHangarSinLlave = true;
                    mensaje = "Te falta la llave para entrar!";
                } else {
                    System.out.println(" ");
                    System.out.println("--------------------------------------");
                    System.out.println("-Llegaste al hangar. Misión completada!-");
                    System.out.println("--------------------------------------");
                    System.out.println(" ");

                    break;
                }
            }
            mapa.mostrarMapa();
            if (mensaje != null) {
                System.out.println(" ");
                System.out.println("------------------------------------");
                System.out.println(mensaje);
                System.out.println("------------------------------------");
                System.out.println(" ");
                mensaje = null;
            }
        }
    }
}
