
public class MisionFinal extends Mision {

    Snake usuario = new Snake();
    MetalGear pc = new MetalGear();

    public void iniciarCombateFinal() {
        int partida = 1;
        System.out.println("-----------------------");
        System.out.println("Inicia el combate final");
        System.out.println("-----------------------");
        System.out.println("Vida Rex: " + pc.getVida());
        System.out.println("Vida Snake: " + usuario.getVida());
        System.out.println("-----------------------");

        do {
            System.out.println(" ");
            System.out.println("################ PARTIDA NUMERO "+ partida++ +" ################");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("/////////////////////////- Turno de Snake -/////////////////////////");
            System.out.println(" ");
            turnoSnake();
            quienGano();
            System.out.println(" ");
            System.out.println("//////////////////////////- Turno de Rex -//////////////////////////");
            System.out.println(" ");
            turnoMetalGear();

        } while (quienGano());
            System.out.println(" ");
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////-- Final del combate --//////////////////////");
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println(" ");
    }

    public void turnoSnake() {
        //Primero Snake (elije el usuario)
        boolean eleccionUsuario = usuario.combate();
        //Segundo MetalGear
        boolean eleccionPc = pc.combateRandom();

        if (eleccionUsuario && eleccionPc) {
            int impacto = -20;
            pc.setVida(impacto);
            System.out.println("--------------------------------------------------");
            System.out.println("Le diste! (-20hp)" + " Vida de Rex: " + pc.getVida());
            System.out.println("--------------------------------------------------");
        } else if (!eleccionUsuario && !eleccionPc) {
            System.out.println("------------");
            System.out.println("Ambos eligieron esquivar");
            System.out.println("------------");
        } else if (eleccionUsuario && !eleccionPc) {
            System.out.println("------------");
            System.out.println("Rex Esquivo el ataque");
            System.out.println("------------");
        } else {
            System.out.println("------------");
            System.out.println("Rex disparo pero fallo");
            System.out.println("------------");
        }
    }

    public void turnoMetalGear() {
        //Primero MetalGear
        boolean eleccionPc = pc.combateRandom();
        //Segundo Snake eleccion random
        boolean eleccionUsuario = usuario.combateRandom();

        if (eleccionPc && eleccionUsuario) {
            int impacto = pc.tipoDeAtaque();
            usuario.setVida(impacto);
            System.out.println("--------------------------------------------------");
            System.out.println("Te dieron! (" + impacto + "hp) " + " Tu vida: " + usuario.getVida());
            System.out.println("--------------------------------------------------");
        } else if (!eleccionPc && !eleccionUsuario) {
            System.out.println("------------");
            System.out.println("Ambos eligieron esquivar");
            System.out.println("------------");
        } else if (eleccionPc && !eleccionUsuario) {
            System.out.println("------------");
            System.out.println("Esquivaste el ataque de Rex");
            System.out.println("------------");
        } else {
            System.out.println("------------");
            System.out.println("Disparaste sin punteria");
            System.out.println("------------");
        }
    }

    public boolean quienGano() {
        boolean fin = true;
        if (usuario.getVida() <= 0) {
            fin = false;
            System.out.println(" ");
            System.out.println("------------------");
            System.out.println("--- Game Over ----");
            System.out.println("------------------");
            System.out.println(" ");
        } else if (pc.getVida() <= 0) {
            fin = false;
            System.out.println(" ");
            System.out.println("------------------");
            System.out.println("---- Ganaste -----");
            System.out.println("------------------");
            System.out.println(" ");
        }
        return fin;
    }

}
