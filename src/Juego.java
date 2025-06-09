import java.util.Scanner;

public class Juego {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        iniciar(leer);
        leer.close();
    }

    public static void iniciar(Scanner leer) {
        boolean salir = true;

        while (salir) {
            System.out.println("------------------------");
            System.out.println("1- Iniciar Nueva Partida");
            System.out.println("2- Continuar Partida");
            System.out.println("3- Salir");
            System.out.println("------------------------");

            int opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    nuevaPartida(leer);
                    break;
                case 2:
                    cargarPartida();
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("Si sales, pierdes la partida guardada");
                    System.out.println("-------------------------------------");
                    System.out.println("Quieres salir?:");
                    System.out.println("1- SI");
                    System.out.println("2- NO");
                    if (leer.nextInt() == 1) {
                        salir = false;
                    }
                    break;
            }
        }

    }

    public static void nuevaPartida(Scanner leer) {
        boolean volver = true;

        while (volver) {
            System.out.println("----------------MISION----------------");
            System.out.println("1- Hangar de Entrada");
            System.out.println("2- Almacén de Armas");
            System.out.println("3- Hangar de Metal Gear(Batalla Final)");
            System.out.println("4- Volver");
            System.out.println("--------------------------------------");

            switch (leer.nextInt()) {
                case 1:
                    Mapa mapa1 = new Mapa();
                    Snake snake1 = new Snake(new Posicion(0, 6));
                    mapa1.colocarPersonaje(snake1);
                    new MisionIntermedia(1, mapa1, snake1, leer).iniciar();
                    break;
                case 2:
                    Mapa mapa2 = new Mapa(9, 9);
                    Snake snake2 = new Snake(new Posicion(0, 8));
                    mapa2.colocarPersonaje(snake2);
                    new MisionIntermedia(2, mapa2, snake2, leer).iniciar();
                    break;
                case 3:
                    Snake snake3 = new Snake(new Posicion(0, 8));
                    new MisionFinal(null, snake3, leer).iniciar();
                    break;
                case 4:
                    volver = false;
                    break;
            }
        }
    }

    public static void cargarPartida() {
        System.out.println("Desde metodo cargar partida");
    }
}
