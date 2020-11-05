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
public class Nave {
    private int x = 400;
    private int tamW = 50;
    private int tamH = 60;
    private int vidas = 1;
    private String nick;
    
    // Creamos las alarmas
    private Alarma alarma = new Alarma(0); // parpadeo
    private Alarma alarma2 = new Alarma(0); // lento
    
    public Nave(String nick, int x) {
        this.nick = nick;
        this.x = x;
        
    }
    
    public int getX() {
        return x;
    }
    
    public Alarma getAlarma() {
        return alarma;
    }
    
    public Alarma getAlarma2() {
        return alarma2;
    }
    
    public int getVidas() {
        return vidas;
    }
    
     public int getTamW() {
        return tamW;
    }
    
    public int getTamH() {
        return tamH;
    }
    
    public String getNick() {
        return nick;
    }
}
