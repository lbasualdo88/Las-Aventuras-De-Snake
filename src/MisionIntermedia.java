import java.util.Scanner;

public class MisionIntermedia extends Mision {
    private final int numero; // 1 = Hangar de Entrada, 2 = Almacén de Armas

    public MisionIntermedia(int numero, Mapa mapa, Snake snake, Scanner sc) {
        super(mapa, snake, sc);
        this.numero = numero;
    }

    @Override
    public void iniciar() {
        mapa.colocarPersonaje(snake);

        if (numero == 1) {
            Tarjeta tarjeta = new Tarjeta();
            Puerta puerta = new Puerta();
            Posicion llavePos = mapa.establecerObjetivo(tarjeta);
            Posicion hangarPos = mapa.establecerObjetivo(puerta);

            mapa.ubicarEnemigos(2, snake.getPosicion(), llavePos, hangarPos);
            mapa.mostrar();

            boolean sobreHangar = false;
            String mensaje = null;

            int hangarX = hangarPos.getX();
            int hangarY = hangarPos.getY();

            while (true) {
                System.out.println("---------------------------------");
                System.out.print("Mover (w:↑ - a:← - s:↓ - d:→): ");
                String d = sc.next();

                snake.mover(mapa, d);
                Posicion pos = snake.getPosicion();

                if (mapa.hayGuardiaCerca(pos,1)) {
                    System.out.println();
                    System.out.println("////////////////////////////////////");
                    System.out.println("// ¡Fuiste capturado por un guardia! //");
                    System.out.println("////////////////////////////////////");
                    System.out.println();
                    misionCompletada = false;
                    mapa.mostrar();
                    break;
                }

                // si el usuario pasa sin la llave por el hangar, seteo el hangar para que no se borre
                if (sobreHangar) {
                    mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
                    sobreHangar = false;
                }

                // get del objeto de la celda
                Objeto obj = mapa.getMatriz()[pos.getY()][pos.getX()].getObjeto();

                if (obj instanceof Tarjeta) {
                    snake.recogerTarjeta((Tarjeta) obj);
                    mensaje = "Encontraste la llave para el hangar!";
                    mapa.getMatriz()[pos.getY()][pos.getX()].removerObjeto();
                } else if (obj instanceof Puerta) {
                    if (!snake.tieneTarjeta()) {
                        mensaje = "Te falta la llave para entrar!";
                        mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
                        sobreHangar = true;
                    } else {
                        System.out.println();
                        System.out.println("--------------------------------------");
                        System.out.println("- Llegaste al hangar. Misión completada! -");
                        System.out.println("--------------------------------------");
                        System.out.println();
                        misionCompletada = true;
                        
                        mapa.mostrar();
                        break;
                    }
                }
                mapa.mostrar();
                mapa.moverGuardias();

                System.out.println("----------------------------------------");
                System.out.println("CUIDADO: ¡Los guardias se están moviendo!");
                System.out.println("----------------------------------------");
                mapa.mostrar();

                if (mapa.hayGuardiaCerca(pos,1)) {
                    System.out.println();
                    System.out.println("////////////////////////////////////");
                    System.out.println("// ¡Fuiste capturado por un guardia! //");
                    System.out.println("////////////////////////////////////");
                    System.out.println();
                    misionCompletada = false;
                    break;
                }

                //fallback al usuario
                if (mensaje != null) {
                    System.out.println();
                    System.out.println(mensaje);
                    System.out.println("------------------------------------");
                    System.out.println();
                    mensaje = null;
                }
            }
        } else {
            C4 c4 = new C4();
            Puerta puerta = new Puerta();
            Posicion c4Pos = mapa.establecerObjetivo(c4);
            Posicion hangarPos = mapa.establecerObjetivo(puerta);

            mapa.ubicarEnemigos(2, snake.getPosicion(), c4Pos, hangarPos);
            mapa.mostrar();

            boolean sobreHangar = false;
            String mensaje = null;
            int hangarX = hangarPos.getX();
            int hangarY = hangarPos.getY();

            while (true) {
                System.out.println("---------------------------------");
                System.out.print("Mover (w:↑ - a:← - s:↓ - d:→): ");
                String d = sc.next();

                snake.mover(mapa, d);
                Posicion pos = snake.getPosicion();

                if (mapa.hayGuardiaCerca(pos,1)) {
                    System.out.println();
                    System.out.println("// ¡Fuiste capturado por un guardia! //");
                    System.out.println();
                    misionCompletada = false;
                    mapa.mostrar();
                    break;
                }

                // pisé la puerta sin C4, repinto la puerta
                if (sobreHangar) {
                    mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
                    sobreHangar = false;
                }

                // obtengo el objeto de la celda
                Objeto obj = mapa.getMatriz()[pos.getY()][pos.getX()].getObjeto();

                // chequeo de objeto de la celda
                if (obj instanceof C4) {
                    snake.recogerBomba((C4) obj);
                    mensaje = "Encontraste la bomba para abrir el hangar!";
                    mapa.getMatriz()[pos.getY()][pos.getX()].removerObjeto();
                } else if (obj instanceof Puerta) {
                    if (!snake.tieneBomba()) {
                        mensaje = "Te falta la bomba para entrar!";
                        mapa.getMatriz()[hangarY][hangarX].setObjeto(new Puerta());
                        sobreHangar = true;
                    } else {
                        if (!mapa.hayGuardiaCerca(pos, 3)) {
                            System.out.println();
                            System.out.println("--------------------------------------");
                            System.out.println("- ¡Llegaste al hangar. Misión completada! -");
                            System.out.println("--------------------------------------");
                            System.out.println();
                            misionCompletada = true;

                            break;
                        } else {
                            // si hay guardia muy cerca, se advierte
                            System.out.println("Hay un guardia demasiado cerca, esperá un momento!");
                        }

                    }
                    
                }

                mapa.mostrar();
                mapa.moverGuardias();
                System.out.println("CUIDADO: ¡Los guardias se están moviendo!");
                mapa.mostrar();

                if (snake.tieneBomba() && mapa.hayGuardiaCerca(pos, 3)) {
                    System.out.println("¡Hay un guardia cerca, ten cuidado!");
                }

                if (mapa.hayGuardiaCerca(pos,1)) {
                    System.out.println();
                    System.out.println("// ¡Fuiste capturado por un guardia! //");
                    System.out.println();
                    misionCompletada = false;
                    break;
                }

                if (mensaje != null) {
                    System.out.println(mensaje);
                    mensaje = null;
                }
            }
        }
    }
}
