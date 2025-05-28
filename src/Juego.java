
import java.util.Scanner;

public class Juego {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
      

        iniciar();
    }

    public static void iniciar() {
        boolean salir = true;

        do {
            System.out.println("------------------------");
            System.out.println("1- Iniciar Nueva Partida");
            System.out.println("2- Continuar Partida");
            System.out.println("3- Salir");
            System.out.println("------------------------");
            int opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    //inicia nueva partida
                    break;
                case 2:
                    //continua partida guardada
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("Si sales, pierdes la partida guardada");
                    System.out.println("-------------------------------------");
                    System.out.println("Quieres salir?:");
                    System.out.println("1- SI");
                    System.out.println("2- NO");
                    int opcion2 = leer.nextInt();
                    if (opcion2 == 1) {
                        salir = false;
                    }
                    break;
            }
        } while (salir);

    }
}
