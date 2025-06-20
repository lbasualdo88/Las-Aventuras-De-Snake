// Importamos clases necesarias

import java.util.Random;
import java.util.Scanner;

// Clase principal del juego
public class Juego {

    // Constantes y variables estáticas
    private static final int FACTOR = 1000; // Factor multiplicador para generar el código de guardado
    private static final Random rnd = new Random(); // Objeto Random para números aleatorios
    private static int misionRealizada = 0; // Lleva el registro de la última misión completada
    private static int codigoGuardado = -1; // Almacena el código de guardado actual (-1 si no hay ninguno)
    private static int cantidadGuardias = 0; // Almacena el código de guardado actual (0 si no hay ninguno)

    // Método principal que inicia el programa
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Objeto Scanner para leer entradas
        iniciar(sc); // Se llama al menú principal
        sc.close(); // Cerramos el scanner al salir
    }

    // Menú principal del juego
    public static void iniciar(Scanner sc) {
        boolean seguirJugando = true; // Controla si se mantiene el bucle del menú
        while (seguirJugando) {
            // Mostrar opciones del menú principal
            System.out.println("------------------------");
            System.out.println("1- Iniciar Nueva Partida");
            System.out.println("2- Continuar Partida");
            System.out.println("3- Salir");
            System.out.println("------------------------");

            while (!sc.hasNextInt()) {
                System.out.println("Opción inválida. Debes ingresar un número del 1 al 3");
                sc.next();
            }

            int opcion = sc.nextInt();

            while (opcion < 1 || opcion > 3) {
                System.out.println("Número inválido. Ingresa 1, 2 o 3.");
                while (!sc.hasNextInt()) {
                    System.out.println("Opción inválida. Debes ingresar un número del 1 al 3");
                    sc.next();
                }
                opcion = sc.nextInt();
            }

            switch (opcion) {
                case 1:
                    cantidadGuardias = dificultad(sc);
                    misionRealizada = 0; // Reiniciar progreso
                    nuevaPartida(sc); // Comenzar una nueva partida
                    break;
                case 2:
                    boolean tieneCodigo = cargarPartida(sc); // Cargar partida con código
                    if (tieneCodigo) {
                        nuevaPartida(sc);
                    }
                    break;
                case 3:
                    seguirJugando = confirmacionDeSalida(sc);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // 👇👇 MÉTODO CLAVE: nuevaPartida 👇👇
    public static void nuevaPartida(Scanner sc) {

        boolean volver = true; // Controla si seguimos en este menú

        while (volver) {
            // Se limita la cantidad de misiones que se pueden elegir (máximo 3)
            int maxMisiones = Math.min(misionRealizada + 1, 3);

            // Mostrar las misiones disponibles
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

            // Agrega una opción para volver
            System.out.println((maxMisiones + 1) + "- Volver");
            System.out.println("--------------------------------------");

            int eleccion;

            while (true) {
                System.out.println("Elige una opción entre 1 y " + (maxMisiones + 1) + ":");
                if (!sc.hasNextInt()) {
                    System.out.println("Opción inválida. Debes ingresar un número.");
                    sc.next();
                    continue;
                }

                eleccion = sc.nextInt();

                if (eleccion >= 1 && eleccion <= maxMisiones + 1) {
                    break;
                }
                System.out.println("Opción inválida");
            }

            // elige "Volver"
            if (eleccion == maxMisiones + 1) {

                volver = procesoDeGuardado(sc);

                // elige misión 1
            } else if (eleccion == 1) {
                if (ejecutarMision1(sc)) {
                    misionRealizada = 1;
                }
            } // Misión 2 (solo disponible si hizo la 1)
            else if (eleccion == 2 && maxMisiones >= 2) {
                if (ejecutarMision2(sc)) {
                    misionRealizada = 2;
                }
            } // Misión 3 (solo disponible si hizo la 2)
            else if (eleccion == 3 && maxMisiones >= 3) {
                if (ejecutarMisionFinal(sc)) {
                    misionRealizada = 3;
                    volver = false;
                }
            } else {
                System.out.println("Opción inválida. Ingresa de 1 a " + (maxMisiones + 1));
            }
        }
    }

    private static boolean ejecutarMision1(Scanner sc) {
        Mapa mapa = new Mapa();
        Snake snake = new Snake(new Posicion(0, 6));
        mapa.colocarPersonaje(snake);
        MisionIntermedia m1 = new MisionIntermedia(1, mapa, snake, sc);
        m1.iniciar(cantidadGuardias);
        return m1.isMisionCompletada();
    }

    private static boolean ejecutarMision2(Scanner sc) {
        Mapa mapa = new Mapa(9, 9);
        Snake snake = new Snake(new Posicion(0, 8));
        mapa.colocarPersonaje(snake);
        MisionIntermedia m2 = new MisionIntermedia(2, mapa, snake, sc);
        m2.iniciar(cantidadGuardias);
        return m2.isMisionCompletada();
    }

    private static boolean ejecutarMisionFinal(Scanner sc) {
        Snake snake = new Snake(new Posicion(0, 0));
        MisionFinal m3 = new MisionFinal(null, snake, sc);
        m3.iniciar(cantidadGuardias);
        return m3.isMisionCompletada();
    }

    // Método para cargar una partida previamente guardada con código
    public static boolean cargarPartida(Scanner sc) {
        boolean conCodigo = false;
        System.out.print("Escribir el código de guardado: ");
        while (!sc.hasNextInt()) {
            System.out.println("----------------------------------------------");
            System.out.println("Opción inválida. El código debe ser un número.");
            sc.next(); // Limpia la entrada inválida
            System.out.println("----------------------------------------------");
            System.out.print("Ingrese un código numérico: ");
        }

        int codigo = sc.nextInt();

        

        if (codigo == codigoGuardado) {
            misionRealizada = codigo / FACTOR; // Se extrae la misión guardada del código
            System.out.println("-----------------------------------------");
            System.out.println("Partida cargada, misiones completadas: " + misionRealizada);
            System.out.println("-----------------------------------------");
            conCodigo = true;
        } else {
            System.out.println("Código inválido");
        }
        return conCodigo;
    }

    private static boolean procesoDeGuardado(Scanner sc) {
        boolean volver = true;
        System.out.println("-------------------------------------");
        System.out.println("Podés guardar el progreso de la partida antes de salir.");
        System.out.println("-------------------------------------");

        String opcionGuardar;
        do {
            // Preguntar si quiere guardar la partida
            System.out.println("¿Querés guardarlo?");
            System.out.println("1- SÍ");
            System.out.println("2- NO");
            opcionGuardar = sc.next().trim();
            if (!opcionGuardar.equals("1") && !opcionGuardar.equals("2")) {
                System.out.println("Opción inválida. Debes ingresar 1 o 2.");
            }
        } while (!opcionGuardar.equals("1") && !opcionGuardar.equals("2"));

        if (opcionGuardar.equals("1")) {
            guardarEstado(); // Guardar partida
        }

        volver = false; // Salimos del bucle de la partida
        return volver;
    }

    // Método para guardar el estado actual del juego
    private static void guardarEstado() {
        int aleatorio = rnd.nextInt(FACTOR); // Número aleatorio entre 0 y 999
        int codigo = misionRealizada * FACTOR + aleatorio; // Generar código único
        codigoGuardado = codigo;
        System.out.println("Partida guardada. Tu código es:");
        System.out.printf("> %06d%n", codigo); // Mostrar código con 6 dígitos
    }

    private static boolean confirmacionDeSalida(Scanner sc) {
        boolean salir = true;
        boolean seguirJugando = true;

        if (misionRealizada >= 3) {
            System.out.println("Completaste el juego! Gracias por jugar.");
            return false;
        }

        do {
            // Confirmación de salida si no se terminó el juego
            System.out.println("-------------------------------------");
            System.out.println("No has terminado el juego.");
            System.out.println("Si sales ahora, perderás el progreso guardado.");
            System.out.println("-------------------------------------");
            System.out.println("¿Seguro que querés salir?");
            System.out.println("1- NO, seguir jugando");
            System.out.println("2- SÍ, salir sin terminar el juego");
            int opcionSalir = sc.nextInt();

            switch (opcionSalir) {
                case 1:
                    salir = false;  // Volver al menú principal
                    break;
                case 2:
                    seguirJugando = false;  // Salir del bucle principal
                    salir = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (salir);
        return seguirJugando;
    }

    private static int dificultad(Scanner sc) {
        int numeroGuardias = 0;
        System.out.println("-------------------");
        System.out.println("--Elije dificultad--");
        System.out.println("-------------------");
        System.out.println("1- Facil");
        System.out.println("2- Media");
        System.out.println("3- Dificil");
        System.out.println("-------------------");

        while (!sc.hasNextInt()) {
            System.out.println("Opción inválida. Debes ingresar un número del 1 al 3");
            sc.next();
        }

        int opcion = sc.nextInt();

        while (opcion < 1 || opcion > 3) {
            System.out.println("Número inválido. Ingresa 1, 2 o 3.");
            while (!sc.hasNextInt()) {
                System.out.println("Opción inválida. Debes ingresar un número del 1 al 3");
                sc.next();
            }
            opcion = sc.nextInt();
        }
        switch (opcion) {
            case 1:
                numeroGuardias = 2;
                break;
            case 2:
                numeroGuardias = 3;
                break;
            case 3:
                numeroGuardias = 4;
                break;
            default:
                System.out.println("Opción inválida");
        }
        System.out.println(" ");
        System.out.println("------------------");
        System.out.println("Dificultad seteada");
        System.out.println("------------------");
        System.out.println(" ");
        return numeroGuardias;
    }
}
