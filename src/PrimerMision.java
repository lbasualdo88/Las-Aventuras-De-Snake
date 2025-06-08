
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

        String mensaje = null;

        mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].setPersonaje(snake);
        Posicion llavePos = mapa.establecerObjetivo(new Tarjeta());
        Posicion hangar = mapa.establecerObjetivo(new Puerta());
        mapa.ubicarEnemigos(4, snakePosicion, llavePos, hangar);
        mapa.mostrarMapa();

        int hangarX = hangar.getX();
        int hangarY = hangar.getY();

        while (true) {
            System.out.println("---------------------------------");
            System.out.print("Mover (w:\u2191 - a:\u2190 - s:\u2193 - d:\u2192): ");
            String d = leer.next();

            // Mover snake (void)
            snake.mover(mapa, d);
            snakePosicion = snake.getPosicion();

            // Obtener objeto en la nueva posición de Snake
            Objeto obj = mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].getObjeto();

            // Si Snake está en el hangar sin tarjeta, aseguramos que la puerta esté visible
            if (snakePosicion.equals(hangar) && !snake.tieneTarjeta()) {
                mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
            }

            if (obj instanceof Tarjeta) {
                snake.recogerTarjeta((Tarjeta) obj);
                mensaje = "Encontraste la llave para el hangar!";
                mapa.getMatriz()[snakePosicion.getY()][snakePosicion.getX()].removerObjeto();
            } else if (obj instanceof Puerta) {
                if (!snake.tieneTarjeta()) {
                    mensaje = "Te falta la llave para entrar!";
                    mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
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

            mapa.moverGuardias();
            System.out.println("----------------------------------------");
            System.out.println("CUIDADO!- Los guardias te estan buscando");
            System.out.println("----------------------------------------");
            mapa.mostrarMapa();

            if (mapa.hayGuardiaCerca(snakePosicion)) {
                System.out.println(" ");
                System.out.println("////////////////////////////////////");
                System.out.println("//Fuiste capturado por un guardia!//");
                System.out.println("////////////////////////////////////");
                System.out.println(" ");
                break;
            }

            if (mensaje != null) {
                System.out.println(" ");
                System.out.println(mensaje);
                System.out.println("------------------------------------");
                System.out.println(" ");
                mensaje = null;
            }
        }
    }

}

