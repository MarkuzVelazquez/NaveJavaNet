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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uaem.ui.ServidorConsola;

/**
 *
 * @author UAEM-ING09
 */
public class Servidor extends Thread {
    private String nick;
    private Socket conexion;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private ServidorConsola s;
    
   // Método constructor
    public Servidor(Socket conexion, int id, ServidorConsola s)  {
        this.s = s;
        try {
            // Muestra el status de la conexion
            System.out.println("Conexion "+id+" recibida de: "+conexion.getInetAddress().getHostAddress());

            // Crea un buffer de salida
            salida = new ObjectOutputStream(conexion.getOutputStream());
            // Crea un buffer de entrada
            entrada = new ObjectInputStream(conexion.getInputStream());
        } catch (Exception ex) { }
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
        System.out.println(msg.getCuerpo());
        s.procesaMensaje(msg);
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
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
