/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.uaem.comm.IMensajeListener;
import mx.uaem.comm.Servidor;
import mx.uaem.comm.Mensaje;

/**
 *
 * Vargas Salazar Yuritzi Yetzul
 */
public class ServidorConsola implements IMensajeListener {
    //Definir variables globales
    private String nombre;
    private Servidor myServer; //crear instancia
    private Socket conexion;
    private String numPartidas;//
    private String puerto;
    private ServerSocket socket;
    private ArrayList<Servidor> servers = new ArrayList();
    private int contador = 1;
    
    //metodo constructor
    public ServidorConsola(String nombre){
        try {
            this.nombre = nombre;
            puerto=JOptionPane.showInputDialog(null,"Ingresa el puerto del servidor"," Iniciando el servidor...",
                    JOptionPane.QUESTION_MESSAGE);
            numPartidas=JOptionPane.showInputDialog(null,"Ingresa el numero de partidas a jugar"," Iniciando el servidor...",
                    JOptionPane.QUESTION_MESSAGE);
            socket = new ServerSocket(Integer.valueOf(puerto));
            while(true) {
                System.out.println("Servidor activo. Esperando una conexion..\n");
                conexion = socket.accept(); // permitir al servidor aceptar la conexion
                myServer = new Servidor(conexion, contador, this);
                myServer.start();
                servers.add(myServer);
                contador++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorConsola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método que iniciara la ejecución del hilo en la clase Servidor
    public void start(){
        
    }
    //Metodo principal
    public static void main(String[] args){
        //Generamos el objeto
        new ServidorConsola("Servidor cux");
    }
    
    public void procesaMensaje(Mensaje mensaje){
        switch(mensaje.getTipo()){
            case Mensaje.MENSSAGE_INICIO:
                System.out.println("Iniciado "+mensaje.getRemitente());
                System.out.println("Numero "+contador);
                
            break;
            case Mensaje.MENSSAGE_CIERRE:
                System.out.println("Cerrado "+mensaje.getCuerpo());
                System.out.println("Numero "+contador);
                
                contador--;
            break;
            case Mensaje.MENSSAGE_LISTA:
            break;
            case Mensaje.MENSSAGE_NORMAL:
                // Manda a todos los mensajes
                if (contador > 1) {
                    for(int n = servers.size()-1; n >= 0; n--) { // ---------
                        Servidor b = servers.get(n);
                        try {
                            b.enviarMensaje(mensaje);
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorConsola.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                }
            break;
            case Mensaje.MENSSAGE_START:
            break;
            case Mensaje.MENSSAGE_STOP:
            break;
            case Mensaje.MENSSAGE_WIN:
            break;
        }
    }
}
