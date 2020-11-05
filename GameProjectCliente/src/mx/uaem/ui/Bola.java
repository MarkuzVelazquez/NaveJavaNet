/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.ui;

/**
 *
 * @author Administrador
 */
public class Bola {
    //Definir variable para las coordenadas en el eje x
    private int x;
    private double dx; // ----------------------------
    private int y;
    private double dy; // ----------------------------
    private int tam;
    
    // Método constructor
    public Bola() {
        // la posición en x será aleatoria entre
        // 50 - 750
        x = (int)(Math.random()*750+50);
        y = 50;
        dx = 5;
        // valor original de la imagen 110
        // definimos el valor inicial a la mitad
        tam = 50;
    }
    // Definimos otro método constructor ahora con param
    public Bola(int xI, int yI, int dxI, int tamI) {
        x = xI;
        y = yI;
        dx = dxI;
        tam = tamI;
    }
    
    // Devolver el valor de la posición en x
    public int getX() {
        return x;
    }
    // Devolver el valor de la posición en y
    public int getY() {
        return y;
    }
    
    // Devolver el valor del movimiento en x
    public double getDx() { // -----------------------
        return dx;
    }
    // Devolver el valor del movimiento en y
    public double getDy() { // -----------------------
        return dy;
    }
    // Devolver el valor del tamaño de la pelota
    public int getTam() {
        return tam;
    }
    
    // Método encargado de generar el movimiento
    public void mover() {
        // calcular la diferencia entre pelota y borde
        int dif = 0; // ------------------------------
        // mover el objeto en base al valor de dx
        x += dx;
        // mover el objeto en base al valor de dy
        y += dy;
        // la distancia en y debe cambiar
        dy += 1.0;
        // diferencia
        // sumando x + tam y restando el eje x 
        dif = x + tam - 799; // ----------------------
        // Si la pelota llegar al borde derecho
        if(dif >= 0 && dx > 0) { // ------------------
            x = 799 - tam; // ------------------------
            // En lugar de sumar dx ahora lo restará
            dx = -dx;
        }
        dif = x - tam; // ----------------------------
        // Si la pelota llega al borde izquierdo
        if(dif  < 0 && dx < 0) {
            // Volverá a cambiar el signo a + de dx
            x = tam;
            dx = -dx; 
        }
        // Si la pelota llega al borde inferior
        dif = y + tam - 540; // ----------------------
        if(dif > 0 && dy > 0) { // -------------------
            y = 540 - tam; // ------------------------
            // Dependiendo el tamaño de la pelota este rebotara a cierta altura 
            switch(tam) {
                case 50: dy = -30; break;
                case 25: dy = -25; break;
                case 12: dy = -20; break;
                case 6: dy = -15; break;
            }
        }

    }
}
