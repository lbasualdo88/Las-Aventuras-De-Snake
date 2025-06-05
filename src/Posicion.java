/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author leo-h
 */
public class Posicion {

    private int x, y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moverArriba() {
        this.y--; // fila anterior
    }

    public void moverAbajo() {
        this.y++; // fila siguiente
    }

    public void moverIzquierda() {
        this.x--; // columna anterior
    }

    public void moverDerecha() {
        this.x++; // columna siguiente
    }


}
