/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.comm;

import java.io.Serializable;

/**
 *
 * @author UAEM-ING09
 */
public class Mensaje implements Serializable { //
    private int tipo;
    private String remitente; //saber de quien es
    private String cuerpo; //Contenido
    
    //metodos encargados de recibir y enviar
    
    
    //Tipos de mensajes
    public static final int MENSSAGE_INICIO = 1; //Mensaje de inicio de sesion
    public static final int MENSSAGE_CIERRE = 2;//Mensaje de cierre de sesion;
    public static final int MENSSAGE_LISTA = 3; // Mensaje de usuarios conectados
    public static final int MENSSAGE_NORMAL= 4;//Mensaje de conversacion
    public static final int MENSSAGE_START = 5; // Mensaje de inicio de partida
    public static final int MENSSAGE_STOP = 6; //Finalizacion de partida
    public static final int MENSSAGE_WIN = 7; //Mensaje para informar del ganador;
    
    /*//Metodo  set y get para tipo
   public void getTipo(){
       return tipo;
   }
   
   public void setTipo(int tipo){
       this.tipo = tipo;
   }
   
   public String getRemitente(String remitente){
       this.remitente = remitente;
   }
   
   //SET Y GET PARA EL CUERPO DEL MENSAJE
   public String getCuerpo(){
       return cuerpo;
   }
   
   public void setCuerpo(String cuerpo){
        this.cuerpo = cuerpo;
   }*/

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    
}
