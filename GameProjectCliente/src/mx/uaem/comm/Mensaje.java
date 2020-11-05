/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.comm;

import java.io.Serializable;

/**
 *
 * @author UAEM-ING07
 */
public class Mensaje implements Serializable{
    private int tipo;
    private String remitente;
    private String cuerpo;
    
    //metodos encanrgados de recibir y enviar
    
    
    //tipo de mensaje
    public static final int MENSSAGE_INICIO = 1;
    public static final int MENSSAGE_CIERRE = 2; 
    public static final int MENSSAGE_LISTA = 3;
    public static final int MENSSAGE_NORMAL = 4;
    public static final int MENSSAGE_START = 5;
    public static final int MENSSAGE_STOP = 6;
    public static final int MENSSAGE_WIN  =7;

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the remitente
     */
    public String getRemitente() {
        return remitente;
    }

    /**
     * @param remitente the remitente to set
     */
    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    /**
     * @return the cuerpo
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
}
