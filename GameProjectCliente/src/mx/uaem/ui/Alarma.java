/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mark-uz Velazquez
 */
public class Alarma extends Thread {
    int time;
    public Alarma(int time) {
        this.time = time;
    }
    public void run() {
        try {
            sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Alarma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
