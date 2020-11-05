/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.ui;

/**
 *
 * @author Mark-uz Velazquez
 */
public class Estrella {
    //Definir variable para las coordenadas en el eje x
    private int x;
    private double dx; // ----------------------------
    private int y;
    private double dy; // ----------------------------
    private int tam;
    private Alarma alarma = new Alarma(0);
    
    // Método constructor
    public Estrella() {
        // la posición en x será aleatoria entre
        // 50 - 750
        x = (int)(Math.random()*750+50);
        y = 50;
        dx = 5;
        // valor original de la imagen 110
        // definimos el valor inicial a la mitad
        tam = 50;
        alarma  = new Alarma(5000);
        alarma.start();
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
    
    public Alarma getAlarma() {
        return alarma;
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
            dy = -10;
        }

    }
}
