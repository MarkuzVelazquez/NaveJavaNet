/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uaem.ui.MGame;

/**
 *
 * @author UAEM-ING07
 */
public class Cliente  extends Thread {
    private Socket cliente;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Mensaje mensaje;
    MGame s;
    
    public Cliente(String ip, int puerto, String nick, MGame s) {
        this.s = s;
        
        try {
            cliente = new Socket(InetAddress.getByName(ip), puerto);
            System.out.println("Conectado a: "+cliente.getInetAddress().getHostAddress());

            // Crea un buffer de salida
            salida = new ObjectOutputStream(cliente.getOutputStream());
            // Crea un buffer de entrada
            entrada = new ObjectInputStream(cliente.getInputStream());
            
            mensaje = new Mensaje();
            mensaje.setTipo(1);
            enviarMensaje(mensaje);
        } catch (Exception ex) {
        }
    }
    
        //Método principal que ejecuta las instrucciones que halla en el hilo
    public void run(){
        while(true) {
            try {
                recibeMensajes();
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
    }
    
    public void recibeMensajes() throws IOException, ClassNotFoundException{
        Mensaje msg = (Mensaje) entrada.readObject(); // bytes es el byte[]
        s.procesaMensaje(msg);
        System.out.println(msg);
        /*
        try {
            mensaje = (Mensaje) entrada.readObject();
            System.out.println(mensaje.getRemitente());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void enviarMensaje(Mensaje mensaje) throws IOException{
        salida.writeObject(mensaje);
        salida.flush();
        /*
        try {
            salida.writeObject(mensaje);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void cerrarSesion(){
        try {
            entrada.close();
            salida.close();
            cliente.close();
            mensaje = new Mensaje();
            mensaje.setTipo(2);
            enviarMensaje(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
