import java.util.Random;
import java.util.Scanner;

public class Juego {
    private static final int FACTOR = 1000;
    private static final Random rnd = new Random();
    private static int misionRealizada = 0;
    private static int codigoGuardado = -1;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        iniciar(sc);
        sc.close();
    }

    public static void iniciar(Scanner sc) {
        boolean salir = true;
        while (salir) {
            System.out.println("------------------------");
            System.out.println("1- Iniciar Nueva Partida");
            System.out.println("2- Continuar Partida");
            System.out.println("3- Salir");
            System.out.println("------------------------");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    misionRealizada = 0; // reset al elegir nueva partida
                    nuevaPartida(sc);
                    break;
                case 2:
                    cargarPartida(sc);
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("Podés guardar el progreso de la partida antes de salir.");
                    System.out.println("-------------------------------------");

                    String opcionSalir;
                    do {
                        System.out.println("Querés guardarlo?");
                        System.out.println("1- SI");
                        System.out.println("2- NO");
                        opcionSalir = sc.next().trim();
                        if (!opcionSalir.equals("1") && !opcionSalir.equals("2")) {
                            System.out.println("Opción inválida. Debes ingresar 1 o 2.");
                        }
                    } while (!opcionSalir.equals("1") && !opcionSalir.equals("2"));

                    if (opcionSalir.equals("1")) {
                        guardarEstado();
                    }
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void nuevaPartida(Scanner sc) {
        // early return si ya tiene alguna mision hecha
        if (misionRealizada > 0) return;

        boolean volver = true;

        while (volver) {
            // obtener la cantidad de misiones a mostrar
            int maxMisiones = Math.min(misionRealizada + 1, 3);

            System.out.println("----------------MISION----------------");

            for (int i = 1; i <= maxMisiones; i++) {
                System.out.print(i + "- ");
                switch (i) {
                    case 1:
                        System.out.println("Hangar de Entrada");
                        break;
                    case 2:
                        System.out.println("Almacén de Armas");
                        break;
                    case 3:
                        System.out.println("Hangar de Metal Gear (Batalla Final)");
                        break;
                }
            }
            System.out.println((maxMisiones + 1) + "- Volver");
            System.out.println("--------------------------------------");

            int eleccion = sc.nextInt();

            // la ultima opcion siempre es volver
            if (eleccion == maxMisiones + 1) {
                volver = false;
            } else if (eleccion == 1) {
                Mapa mapa1   = new Mapa();
                Snake snake1 = new Snake(new Posicion(0, 6));
                mapa1.colocarPersonaje(snake1);
                Mision m1 = new MisionIntermedia(1, mapa1, snake1, sc);
                m1.iniciar();
                if (m1.isMisionCompletada()) {
                    misionRealizada = Math.max(misionRealizada, 1);
                }
            } else if (eleccion == 2 && maxMisiones >= 2) {
                Mapa mapa2   = new Mapa(9, 9);
                Snake snake2 = new Snake(new Posicion(0, 8));
                mapa2.colocarPersonaje(snake2);
                Mision m2 = new MisionIntermedia(2, mapa2, snake2, sc);
                m2.iniciar();
                if (m2.isMisionCompletada()) {
                    misionRealizada = Math.max(misionRealizada, 2);
                }
            } else if (eleccion == 3 && maxMisiones >= 3) {
                Snake snake3 = new Snake(new Posicion(0, 0));
                Mision m3 = new MisionFinal(null, snake3, sc);
                m3.iniciar();
                if (m3.isMisionCompletada()) {
                    misionRealizada = Math.max(misionRealizada, 3);
                }
            } else {
                System.out.println("Opción inválida. Ingresa de 1 a " + (maxMisiones + 1));
            }
        }
    }

    public static void cargarPartida(Scanner sc) {
        System.out.print("Escribir el código de guardado: ");
        int codigo = sc.nextInt();
        if (codigo == codigoGuardado) {
            misionRealizada = codigo / FACTOR;
            System.out.println("Partida cargada, misiones completadas: " + misionRealizada);
            reanudarPartida(sc); // se reanuda la partida de manera automatica
        } else {
            System.out.println("Código inválido");
        }
    }

    private static void guardarEstado() {
        int aleatorio = rnd.nextInt(FACTOR);
        int codigo = misionRealizada * FACTOR + aleatorio;
        codigoGuardado = codigo;
        System.out.println("Partida guardada. Tu código es:");
        System.out.printf("> %06d%n", codigo);
    }

    private static void reanudarPartida(Scanner sc) {
        if (misionRealizada >= 2) {
            System.out.println("Reanudando en Batalla Final");
            Snake snake = new Snake(new Posicion(0, 0));
            new MisionFinal(null, snake, sc).iniciar();
        } else if (misionRealizada == 1) {
            System.out.println("Reanudando en Almacén de Armas");
            Mapa mapa = new Mapa(9, 9);
            Snake snake = new Snake(new Posicion(0, 8));
            mapa.colocarPersonaje(snake);
            new MisionIntermedia(2, mapa, snake, sc).iniciar();
            misionRealizada = Math.max(misionRealizada, 2);
        }
    }
}
