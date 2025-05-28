
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leo-h
 */
public class Snake extends Personaje{

    public Snake(Posicion posicion) {
        super(posicion);
    }

    @Override
    public void mover() {
      Scanner leer = new Scanner(System.in);
        String direccion = leer.next(); 

        switch (direccion) {
            case "w":
                posicion.moverArriba();
                break;
            case "s":
                posicion.moverAbajo();
                break;
            case "a":
                posicion.moverIzquierda();
                break;
            case "d":
                posicion.moverDerecha();
                break;
        }
    }
    
}
