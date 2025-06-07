
import java.util.Scanner;

public class Juego {


    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        iniciar(leer);
        leer.close();
    }

    public static void iniciar(Scanner leer) {
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
                    nuevaPartida(leer);
                    break;
                case 2:
                    //continua partida guardada
                    cargarPartida();
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

    public static void nuevaPartida(Scanner leer) {
        boolean volver = true;
        do {
            System.out.println("----------------MISION----------------");
            System.out.println("1- Hangar de Entrada");
            System.out.println("2- Almacén de Armas");
            System.out.println("3- Hangar de Metal Gear(Batalla Final)");
            System.out.println("4- Volver");
            System.out.println("--------------------------------------");
            int opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    PrimerMision.ejecutar(leer);
                    break;
                case 2:
                    System.out.println("Desde Almacén de Armas");
                    break;
                case 3:
                    MisionFinal m3 = new MisionFinal();
                    m3.iniciarCombateFinal();
                    break;
                case 4:  
                    volver = false;
                    break;
            }

        } while (volver);
    }

    public static void cargarPartida() {
        System.out.println("Desde metodo cargar partida");
    }
}
