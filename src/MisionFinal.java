// Clase que representa la misión final del juego, hereda de Mision
public class MisionFinal extends Mision {
    
    // Constructor que recibe el Scanner como parámetro
    public MisionFinal() {
        super(false);
    }

    // Instancia del personaje Snake controlado por el jugador
    Snake usuario = new Snake();

    // Instancia del enemigo final Metal Gear Rex
    MetalGear pc = new MetalGear();

    // Método principal que inicia el combate entre Snake y Rex
    public void iniciarCombateFinal() {
        int partida = 1;  // Contador de rondas

        // Mensajes iniciales del combate
        System.out.println("-----------------------");
        System.out.println("Inicia el combate final");
        System.out.println("-----------------------");
        System.out.println("Vida Rex: " + pc.getVida());
        System.out.println("Vida Snake: " + usuario.getVida());
        System.out.println("-----------------------");

        // Bucle principal del combate, continúa hasta que uno pierda toda la vida
        do {
            System.out.println(" ");
            System.out.println("################ PARTIDA NUMERO " + partida++ + " ################");
            System.out.println(" ");
            System.out.println(" ");

            System.out.println("/////////////////////////- Turno de Snake -/////////////////////////");
            System.out.println(" ");
            turnoSnake();     // Se ejecuta el turno de Snake
            quienGano();      // Se verifica si alguno ganó o perdió

            System.out.println(" ");
            System.out.println("//////////////////////////- Turno de Rex -//////////////////////////");
            System.out.println(" ");
            turnoMetalGear(); // Se ejecuta el turno de Metal Gear

        } while (quienGano());  // Se repite hasta que haya un ganador

        // Mensaje final del combate
        System.out.println(" ");
        System.out.println("///////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////-- Final del combate --//////////////////////");
        System.out.println("///////////////////////////////////////////////////////////////////");
        System.out.println(" ");
    }

    // Lógica del turno de Snake
    public void turnoSnake() {
        boolean eleccionUsuario = usuario.combate();         // Jugador elige atacar o esquivar
        boolean eleccionPc = pc.combateRandom();             // Rex elige aleatoriamente

        if (eleccionUsuario && eleccionPc) {
            // Ambos atacan, Snake acierta el disparo
            int impacto = -20;
            pc.setVida(impacto);  // Se reduce vida a Rex
            System.out.println("--------------------------------------------------");
            System.out.println("Le diste! (-20hp) Vida de Rex: " + pc.getVida());
            System.out.println("--------------------------------------------------");

        } else if (!eleccionUsuario && !eleccionPc) {
            // Ambos esquivan
            System.out.println("------------");
            System.out.println("Ambos eligieron esquivar");
            System.out.println("------------");

        } else if (eleccionUsuario && !eleccionPc) {
            // Snake ataca, pero Rex esquiva
            System.out.println("------------");
            System.out.println("Rex Esquivo el ataque");
            System.out.println("------------");

        } else {
            // Snake esquiva, Rex ataca pero falla
            System.out.println("------------");
            System.out.println("Rex disparo pero fallo");
            System.out.println("------------");
        }
    }

    // Lógica del turno de Rex
    public void turnoMetalGear() {
        boolean eleccionPc = pc.combateRandom();             // Rex elige si atacar o esquivar
        boolean eleccionUsuario = usuario.combateRandom();   // Snake elige aleatoriamente

        if (eleccionPc && eleccionUsuario) {
            // Ambos atacan, Rex acierta
            int impacto = pc.tipoDeAtaque();  // Devuelve daño entre -15 o -40
            usuario.setVida(impacto);         // Se reduce vida a Snake
            System.out.println("--------------------------------------------------");
            System.out.println("Te dieron! (" + impacto + "hp) Tu vida: " + usuario.getVida());
            System.out.println("--------------------------------------------------");

        } else if (!eleccionPc && !eleccionUsuario) {
            // Ambos esquivan
            System.out.println("------------");
            System.out.println("Ambos eligieron esquivar");
            System.out.println("------------");

        } else if (eleccionPc && !eleccionUsuario) {
            // Rex ataca, pero Snake esquiva
            System.out.println("------------");
            System.out.println("Esquivaste el ataque de Rex");
            System.out.println("------------");

        } else {
            // Rex esquiva, Snake ataca pero falla
            System.out.println("------------");
            System.out.println("Disparaste sin punteria");
            System.out.println("------------");
        }
    }

    // Método que verifica si alguno ganó la pelea
    public boolean quienGano() {
        boolean fin = true;

        // Si Snake se queda sin vida, perdió
        if (usuario.getVida() <= 0) {
            fin = false;
            System.out.println(" ");
            System.out.println("------------------");
            System.out.println("--- Game Over ----");
            System.out.println("------------------");
            System.out.println(" ");

        // Si Rex se queda sin vida, el jugador gana
        } else if (pc.getVida() <= 0) {
            fin = false;
            System.out.println(" ");
            System.out.println("------------------");
            System.out.println("---- Ganaste -----");
            System.out.println("------------------");
            System.out.println(" ");
        }

        return fin;  // true si sigue la pelea, false si terminó
    }
}
