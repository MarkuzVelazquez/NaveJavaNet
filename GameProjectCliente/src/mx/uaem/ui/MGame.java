/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaem.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.uaem.comm.Cliente;
import mx.uaem.comm.IMensajeListener;
import mx.uaem.comm.Mensaje;

/**
 *
 * @author Administrador
 */
public class MGame implements IMensajeListener {
    private String ip;
    private String puerto;
    private int numeroFase = 1;
    private int nivel = 1;
    private Cliente cliente;
    private String nick;
    private Mensaje mensaje;
    
    // Definir variables para la presion de teclas
    private int ESPACIO = 32;
    private int ENTER = 10;
    private int IZQ = 37;
    private int DER = 39;
    private int ESC = 27;
    
    private int UP = 38;
    // alamacenar los códigos de la teclas pulsadas
    Hashtable hsTable = new Hashtable();
    
    // Creamos las alarmas
    private Alarma alarma = new Alarma(0); // parpadeo
    private Alarma alarma2 = new Alarma(0); // lento
    
    // 0 -> portada, 1-> juego (play) 2-> fin game 3-> menu
    private int nEstado = 0;
    // ---------------------------------------------------------

    //Crear la instancia y objeto a la clase Bola
    //Bola b = new Bola();
    // Definimos un arrayList de la clase Bola
    private ArrayList<Bola> bolas = new ArrayList();
    
    
    private ArrayList<Nave> naves = new ArrayList();
    
    
    // Definimos un arrayList de la clase Combistible
    private ArrayList<Combustible> combustibles = new ArrayList();
    
    // Definimos un arrayList de la clase Estrella
    private ArrayList<Estrella> estrellas = new ArrayList();
    
    // Definimos un arrayList de la clase Calavera
    private ArrayList<Calavera> calaveras = new ArrayList();
    
    // Definimos una variable para saber la fase en
    // la que estamos y definir el numero de pelotas
    private int fase = 0;
    
    private int xDisparo = 0;
    private int disDisparo = 0;
    private float angDisparo = 0;
    private int disSuelo = 540;
    private int hspeed = 10;
    
    private int pelotasContador = 0;
    
    public int getXDisparo() {
        return xDisparo;
    }
    
    public int getDisDisparo() {
        return disDisparo;
    }
    
    public float getAngDisparo() {
        return angDisparo;
    }
    
    public int getDisSuelo() {
        return disSuelo;
    }
    
    public String getNick() {
        return nick;
    }
    
    // Obtener el numero de bolas
    public ArrayList<Bola> getBola() {
        return bolas; 
    }
    
    // Obtener el numero de combustible
    public ArrayList<Combustible> getCombustible() {
        return combustibles; 
    }
    
    // Obtener el numero de estrella
    public ArrayList<Estrella> getEstrella() {
        return estrellas; 
    }
    
    // Obtener el numero de calavera
    public ArrayList<Calavera> getCalavera() {
        return calaveras; 
    }
    
    // Obtener el numero de naves
    public ArrayList<Nave> getNave() {
        return naves; 
    }
    
    private int x = 400;
    private int tamW = 50;
    private int tamH = 60;
    private int vidas = 1;
    
    // Obtenemos el valor de la cordenada en el eje x
    public int getX() {
        return x;
    }

    // metodo para obtener el valor de estado
    public int getEstado() {
        return nEstado;
    }
    
    // metodo para obtener el numero de face
    public int getFase() {
        return fase;
    }
    public int getTamW() {
        return tamW;
    }
    
    public int getTamH() {
        return tamH;
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
    
    // Comprueba si colisona la nave con un la pelota
    public boolean intersects(int cx, int cy, int tam) {
        int Radius = tam-10;
        float testX = cx;
        float testY = cy;
        
        // Encuentra el punto más cercano al círculo dentro del rectángulo
        if (testX < x-(tamW-10))
            testX = x-(tamW-10);
        if (testX > (x + (tamW-15)))
            testX = (x + (tamW-15));
        if (testY < 540-(tamH*2))
            testY = 540-(tamH*2);
        if (testY > (540 - tamH))
            testY = (540 - tamH);
        // Calcular la distancia entre el centro del círculo y este punto más cercano
        // Si la distancia es menor que el radio del círculo, se produce una intersección
        return ((cx - testX) * (cx - testX) + (cy - testY) * (cy - testY)) < Radius * Radius;
    }
    //--------------------
    
    // capturar y almacenar los codigos en hsTable
    public void presionarTecla(int codigo) {
        // put -> agregar o colocar codigo
        hsTable.put(codigo, this);
    }
    // eliminar codigos del hsTable
    public void soltarTecla(int codigo) {
        // eliminar el código almacenado
        hsTable.remove(codigo);
    }
    //-----------------------   inicia todas las variables del juego   -------------------------------------
    public void iniciar() {
        fase = 0;
        nivel = 2;
        xDisparo = 0;
        disDisparo = 0;
        angDisparo = 0;
        disSuelo = 540;
        x = 400;
        tamW = 50;
        tamH = 60;
        vidas = 1;
        hspeed = 10;
        pelotasContador = 0;
        alarma.stop();
        combustibles.clear();
    }
    
    //------------------------------------------------------------
    // validar estados del juego
    public void acciones() {
        switch(nEstado) {
            case 0:
                accionesEstado0();
            break;
            case 1:
                accionesEstado1();
            break;
            case 2:
                accionesEstado2();
            break;
        }
    }
    // Cambiar el estado a modo de juego
    public void accionesEstado0() {
        System.out.println("Valor nEstado" + nEstado);
        if(hsTable.containsKey(ESPACIO)) {
            puerto=JOptionPane.showInputDialog(null,"Ingresa el puerto del servidor"," Conectando con el servidor...",
            JOptionPane.QUESTION_MESSAGE);
            ip=JOptionPane.showInputDialog(null,"Ingresa la direccion ip del servidor"," Conectando con servidor...",
            JOptionPane.QUESTION_MESSAGE);
            nick=JOptionPane.showInputDialog(null,"Ingresa tu nick"," Conectando con servidor...",
            JOptionPane.QUESTION_MESSAGE);
            
            cliente = new Cliente(ip, Integer.valueOf(puerto), nick, this);
            cliente.start();
            
            nEstado = 1;
            fase = 0;
            iniciar();
            crearPantallas();
        }
    }
    
    // Método encargado de crear el número de pelotas
    // con base al numero de fase
    public void crearPantallas() {
        bolas.clear();
        for(int n = 0; n <= fase; n++)
            // Agragamos bolas al arreglo
            bolas.add(new Bola());
    }
    
    // Método encargado de mover la nave
    public void accionesEstado1() {
        // presione la tecla <- se mueve a la izquierda
        // si presiono -> se mueve a la derecha
        if(hsTable.containsKey(IZQ)) {
            x = Math.max(x-hspeed, 50);
            mensaje = new Mensaje();
            mensaje.setTipo(4);
            mensaje.setRemitente(nick);
            mensaje.setCuerpo(String.valueOf(x));
            try {
                cliente.enviarMensaje(mensaje);
            } catch (IOException ex) {
            }
        }
        if(hsTable.containsKey(DER)) {
            x = Math.min(x+hspeed, 750);
            mensaje = new Mensaje();
            mensaje.setTipo(4);
            mensaje.setRemitente(nick);
            
            //mensaje.setCuerpo(new varia(x, tamW, tamH, nick, vidas));
            try {
                cliente.enviarMensaje(mensaje);
            } catch (IOException ex) {
                Logger.getLogger(MGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(hsTable.containsKey(ESPACIO)) {
            if(disDisparo == 0) {
                disDisparo = 1;
                xDisparo = x;
                angDisparo = (float)(-Math.PI/2); 
            }
        }
    }
    
    public void ejecutarFrame() {
        acciones();
        
        if(nEstado == 1) {
            // generamos el movimiento de la bola
            // obteniendo el numero de ellas
            for(Bola b: bolas)
                b.mover(); 
            
            for(Combustible c: combustibles)
                c.mover(); 
            
            for(Estrella e: estrellas)
                e.mover(); 
            
           for(Calavera e: calaveras)
                e.mover(); 
           
            if(disDisparo > 0) {
                disDisparo+= 20;
                
                if((disSuelo - disDisparo) < 0)
                    disDisparo = 0;
            }  
            // llamar al metodo encargado de verificar
            // la colision
            detectarColisiones();
            logicaCombustible();
            if (nivel >= 2) {
                detectarColisiones2();
                logicaEstrella();
                logicaCalavera();
            }
        }
    }
    
    // Ejecutar logica combustible
    public void logicaCombustible() {
        for(int n = combustibles.size()-1; n >= 0; n--) { // ---------
            Combustible c = combustibles.get(n);
            if (c.getAlarma().isAlive() == false) {
                combustibles.remove(n); // ----------- Eliminar aquellas que han agotado su tiempo-----------------------------------
            }
        }
    }
    
    // Ejecutar logica combustible
    public void logicaEstrella() {
        for(int n = estrellas.size()-1; n >= 0; n--) { // ---------
            Estrella e = estrellas.get(n);
            if (e.getAlarma().isAlive() == false) {
                estrellas.remove(n); // ----------- Eliminar aquellas que han agotado su tiempo-----------------------------------
            }
        }
    }
    
    // Ejecutar logica combustible
    public void logicaCalavera() {
        for(int n = calaveras.size()-1; n >= 0; n--) { // ---------
            Calavera c = calaveras.get(n);
            if (c.getAlarma().isAlive() == false) {
                calaveras.remove(n); // ----------- Eliminar aquellas que han agotado su tiempo-----------------------------------
            }
        }
    }
    
    public int detectarColisiones2() {
        int res = 0;
        // Activar solo cuando estemos en fase 1
        // si es diferente no detectarColisiones
        if(nEstado != 1)
            return res;
        
        // Ciclo for para obtener el numero de estrellas
        // existentes en el arreglo hasta llegar a 0
        // recorrerlo desde el valor más alto
        for(int n = estrellas.size()-1; n >= 0; n--) { // ---------
            Estrella e = estrellas.get(n); // -------------------------
            // Validaciones para detectar cuando colisionen
            if(intersects(e.getX(), e.getY(), e.getTam())) {
                if (alarma.isAlive() == false) { // Es inmune
                    alarma = new Alarma(10000); // parpadeo  segundos
                    alarma.start();
                    estrellas.remove(n); 
                }
            }
        }
        
        // Ciclo for para obtener el numero de calaveras
        // existentes en el arreglo hasta llegar a 0
        // recorrerlo desde el valor más alto
        for(int n = calaveras.size()-1; n >= 0; n--) { // ---------
            Calavera c= calaveras.get(n); // -------------------------
            // Validaciones para detectar cuando colisionen
            if(intersects(c.getX(), c.getY(), c.getTam())) {
                if (alarma2.isAlive() == false) { // Es inmune
                    alarma2 = new Alarma(1000); // parpadeo  segundos
                    alarma2.start();
                    calaveras.remove(n); 
                }
            }
            
            // Cambia la velocidad de desplazamiento
            if (alarma2.isAlive() == true) {
                hspeed = 0;
            }
            else {
                hspeed = 10;
            }
        }
        return res;
    }
    
    //Método para calcular la distancia entre 2 objetos
    public int distancia(int x1,int y1,int x2,int y2) {
        // Calcular la distancia entre dos puntos
        // raiz((x2 - x1)2 + (y2 - y1)2)
        /*System.out.println("--- Distancia: " +
                (Math.sqrt(Math.pow(x2 - x1,2) +
                     Math.pow(y2 - y1,2))) + " -----");*/
        return (int)(Math.sqrt(Math.pow(x2 - x1,2) +
                     Math.pow(y2 - y1,2)));
    }
    
    // método para detectarColisiones
    public int detectarColisiones() {
        int res = 0;
        // Activar solo cuando estemos en fase 1
        // si es diferente no detectarColisiones
        if(nEstado != 1)
            return res;
        
        // Ciclo for para obtener el numero de pelotas
        // existentes en el arreglo hasta llegar a 0
        // recorrerlo desde el valor más alto
        for(int n = bolas.size()-1; n >= 0; n--) { // ---------
            Bola b = bolas.get(n); // -------------------------
            // Validaciones para detectar cuando colisionen
            if(intersects(b.getX(), b.getY(), b.getTam())) {
                if (alarma.isAlive() == false) { // Es inmune
                    alarma = new Alarma(3000); // parpadeo 3 segundos
                    alarma.start();
                    if (vidas == 1) {
                        tamW = tamW/2;
                        tamH = tamH/2;
                    }
                    if (vidas == 0) {
                        nEstado = 2;
                        System.out.println("--- Game over ---");
                    }
                    
                    vidas--;
                }
            }
            // Verificar si el disparo ha colisionado 
            // con la pelota
            int xDis = xDisparo;
            // modificar la distancia del suelo
            int yDis = 540; 
            // Definir una variable para saber cuando eliminar
            // la pelota
            boolean EliminarPelota = false;
            // Creamos valores imaginarios para comparar y
            // validar la colisión de la pelota con el disparo
            for(int m = 0; m < disDisparo && !EliminarPelota; m+= 5) {
                // Operaciones de Seno y Coseno
                // para validar las colisiones
                xDis = (int)(xDisparo + (Math.cos(angDisparo))*m); 
                yDis = (int)(540 + (Math.sin(angDisparo))*m);       
                if(distancia(b.getX(), b.getY(), xDis, yDis) < b.getTam()+2) {
                    //nEstado = 2;
                    EliminarPelota = true;
                    disDisparo = 0;
                }
            }
            // Comprobamos cuando se tenga que eliminar 
            // la pelota
            if(EliminarPelota) {
                int tam = b.getTam();
                switch(tam) { // --------------- Validación para no crear bolas infinitas-----------------------------
                    case 6: break;
                    case 12: 
                        pelotasContador++;
                        if (pelotasContador == 1) {
                            pelotasContador = 0;
                            switch(nivel) {
                                case 1: combustibles.add(new Combustible());  break;
                                default:
                                    switch((int) Math.round(Math.random()*3)) {
                                        default: combustibles.add(new Combustible()); break;
                                        case 2: estrellas.add(new Estrella()); break;
                                        case 1: calaveras.add(new Calavera()); break;
                                    }
                                break;
                            }
                        }
                    default:
                    tam = tam/2;
                    bolas.add(new Bola(b.getX(), b.getY(), 5, tam));
                    bolas.add(new Bola(b.getX(), b.getY(), -5, tam));
                    break;
                }
                bolas.remove(n); // ----------- Eliminar aquellas que han colisionado-----------------------------------
                if(bolas.size() == 0) {
                    fase++;
                    if (fase >= numeroFase)  nivel++;
                    crearPantallas();
                }
            }
        }
        // Ciclo for para obtener el numero de combustibles
        // existentes en el arreglo hasta llegar a 0
        // recorrerlo desde el valor más alto
        for(int n = combustibles.size()-1; n >= 0; n--) { // ---------
            Combustible c = combustibles.get(n);
            if (intersects(c.getX(), c.getY(), c.getTam())) {
                alarma.stop();
                combustibles.remove(n); // ----------- Eliminar-----------------------------------
                if (tamW != 50) {
                    tamW = tamW*2;
                    tamH = tamH*2;
                }
                if (vidas < 3) vidas++;
                if (alarma.isAlive() == false) {
                    alarma = new Alarma(3000); // parpadeo 3 segundos
                    alarma.start();
                }
            }
        }
       return res;
    }
    
    public void accionesEstado2() {
        if(hsTable.containsKey(ESPACIO)) 
            // Mandar a la pantalla de inicio
            nEstado = 0;
            mensaje = new Mensaje();
            mensaje.setTipo(2);
            mensaje.setRemitente(nick);
            try {
                cliente.enviarMensaje(mensaje);
            } catch (IOException ex) {
                Logger.getLogger(MGame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void procesaMensaje(Mensaje mensaje){
        switch(mensaje.getTipo()){
            case Mensaje.MENSSAGE_NORMAL:
                
                naves.clear();
                naves.add(new Nave(mensaje.getRemitente(), Integer.valueOf(mensaje.getCuerpo())));
                
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
 








/**
    // --------------------------------------------------------------------------------
    public int distancia(int x, int y, int x2, int y2) {
        System.out.println("------ distancia: " + (int)(Math.sqrt(Math.pow(x2-x, 2) + Math.pow(y2-y, 2))) + " --------------------------------");
        //System.out.println("Punto inicial: " + x2 + "," + y2 );
        //System.out.println("Punto final: " + x + "," + y2);
        return (int)(Math.sqrt(Math.pow(x2-x, 2) + Math.pow(y2-y, 2)));
    }
    // --------------------------------------------------------------------------------
    public int detectarColisiones() {
        int res = 0;
        if(nEstado != 1)
            return res;
        //return res;
        //System.out.println("Dis en DC " + distancia(b.getX(), b.getY(), x, getDisSuelo()));
        //System.out.println("Tam " + b.getTam());
        if(distancia(b.getX()+55, b.getY()+55, getX(), 480) < 80) {
            //System.out.println("Tam " + b.getTam());
            System.out.println("+++ GAME OVER +++");
            nEstado = 2;
        }
        
        int xDis = xDisparo;
        int yDis = 540;
        boolean bEliminarBola = false;
        for(int m = 0; m < disDisparo; m += 5) {
            xDis = (int)(xDisparo+Math.cos(angDisparo)*m);
            yDis = (int)(disSuelo+Math.sin(angDisparo)*m);
            //xDis = xDisparo;
            //yDis = disSuelo - disDisparo;
            //System.out.println("Coordenadas de Dis ****" + xDis + "," + yDis);
            //if(distancia(b.getX()+55, b.getY()+55, xDis, yDis) < 25) {
            if(distancia(b.getX()+55, b.getY()+55, xDis, yDis) < 50) {
                System.out.println("..............Colision con pelota ......................");
                nEstado = 2;
            }
        
        }
        
        return res;
    }
    
    public void accionesEstado2() {
        if(hsTable.containsKey(ESPACIO))
            nEstado = 0;
    }
}
**/



//if(Math.sin(angDisparo)*disDisparo + 540 < 0) 
//System.out.println("### disDisparo = " + (540 - disDisparo));


// Bola bola = new Bola();
// bola.mover(disSuelo);

// public Bola getBola() {
//     return bola;
// }

