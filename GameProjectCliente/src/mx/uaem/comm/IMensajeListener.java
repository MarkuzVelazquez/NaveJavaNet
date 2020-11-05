/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.comm;

/**
 *
 * @author UAEM-ING09
 */
public interface IMensajeListener {
    //método para porcesar los mesajes
    //Vinculado a la clase Mensaje
    public void procesaMensaje(Mensaje mensaje);
}
