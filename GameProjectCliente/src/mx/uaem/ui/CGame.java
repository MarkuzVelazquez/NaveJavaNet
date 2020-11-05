
package mx.uaem.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Administrador
 */
public class CGame extends JFrame implements Runnable {
    //Variable para almacenar una imagen
    private Image portada;
    private Image pantalla1;
    private Image pantalla2;
    private Image pantalla3;
    private Image pantalla4;
    private Image pantalla5;
    private Image pantalla6;
    private Image pantalla7;
    private Image nave;
    private Image nave2;
    private Image nave3;
    private Image pelota;
    private Image imgSuelo;
    private Image imgSuelo2;
    private Image imgSuelo3;
    private Image imgSuelo4;
    private Image combustible;
    private Image estrella;
    private Image calavera;
    private Image gameover;
    
    private boolean parpadeo = true;
    
    // Declarar una instancia de la clase MGame
    // definiendo un objeto mg
    MGame mg = new MGame();
    // ----------------------------------------------
    Font f = new Font("Monospaced", Font.BOLD, 20);
    // -----------------------------------------------
    
    public CGame(){
        
        setBounds(0, 0, 800, 600);
        setTitle("Game version 0.1");
        setDefaultCloseOperation(JFrame.
                EXIT_ON_CLOSE);
        // Llamamos al método para las img
        cargarImg();
        
        // Verificar las teclas presionadas
        addKeyListener(new KeyAdapter() {
            // Definir dos métodos
            // al presionar una tecla
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // llamamos al metodo para almacenar el valor
                mg.presionarTecla(e.getKeyCode());
                // Imprimimos la tecla que ha sido
                // presionada
                System.out.println("kp="+e.getKeyCode());
            }
            // al soltar la tecla
            public void keyReleased(KeyEvent e){
                super.keyReleased(e);
                // llamamos al metodo para eliminar el valor
                mg.soltarTecla(e.getKeyCode());
                // Imprimimos la tecla que se ha
                // soltado
                System.out.println("ks"+e.getKeyCode());
            }
        });
        // Creamos el objeto del hilo
        Thread t = new Thread(this);
        // y ejecutamos sus acciones
        t.start();
    }
    
    // método para la carga de imágenes
    public void cargarImg() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        portada = tk.getImage(getClass().
                getResource("Img/portada.jpg"));
        pantalla1 = tk.getImage(getClass().
                getResource("Img/img01.jpg"));
        pantalla2 = tk.getImage(getClass().
                getResource("Img/img02.jpg"));
        pantalla3 = tk.getImage(getClass().
                getResource("Img/img03.jpg"));
        pantalla4 = tk.getImage(getClass().
                getResource("Img/img04.jpg"));
        pantalla5 = tk.getImage(getClass().
                getResource("Img/img05.jpg"));
        pantalla6 = tk.getImage(getClass().
                getResource("Img/img06.jpg"));
        pantalla7 = tk.getImage(getClass().
                getResource("Img/img07.png"));
        nave = tk.getImage(getClass().
                getResource("Img/nave.png"));
        nave2 = tk.getImage(getClass().
                getResource("Img/nave2.png"));
        nave3 = tk.getImage(getClass().
                getResource("Img/nave3.png"));
        pelota = tk.getImage(getClass().
                getResource("Img/bola.png"));
        imgSuelo = tk.getImage(getClass().
                getResource("Img/suelo.png"));
        imgSuelo2 = tk.getImage(getClass().
                getResource("Img/suelo2.png"));
        imgSuelo3 = tk.getImage(getClass().
                getResource("Img/suelo3.png"));
        imgSuelo4 = tk.getImage(getClass().
                getResource("Img/suelo4.png"));
        combustible = tk.getImage(getClass().
                getResource("Img/combustible.png"));
        estrella =  tk.getImage(getClass().
                getResource("Img/estrella.png"));
        calavera =   tk.getImage(getClass().
                getResource("Img/calavera.png"));
        calavera =   tk.getImage(getClass().
                getResource("Img/calavera.jpg"));
        
    }
    
    // Dibujar la imagen en la ventana
    @Override
    public void paint(Graphics g) {
        switch(mg.getEstado()) {
            case 0:
                g.drawImage(portada,0,0,this);
                g.setColor(Color.cyan);
                g.setFont(f);
                g.drawString("PRESIONE UNA TECLA PARA CONTINUAR...", 190, 480);
            break;
            case 1:
                pintarPantalla(g);
                pintarSuelo(g);
                pintarNaves(g);
                pintarPelota(g);
                pintarDisparo(g);
                pintarCombustible(g);
                pintarEstrella(g);
                pintarCalavera(g);
            break;
            case 2:
                g.drawImage(gameover,0,0,this);
                g.setColor(Color.cyan);
                g.setFont(f);
                g.drawString("PRESIONE UNA TECLA PARA CONTINUAR...", 190, 480);
            break;
        }
    }
    
    // ----------------------------------------------
    // Funcion para dibujar el fondo de acuerdo con la fase
    // al pasar la face 6 se repiten los fondos una y otra vez
    public void pintarPantalla(Graphics g) {
        switch(mg.getFase()%7) {
            case 0: g.drawImage(pantalla1,0,0,this); break;
            case 1: g.drawImage(pantalla2,0,0,this); break;
            case 2: g.drawImage(pantalla3,0,0,this); break;
            case 3: g.drawImage(pantalla4,0,0,this); break;
            case 4: g.drawImage(pantalla5,0,0,this); break;
            case 5: g.drawImage(pantalla6,0,0,this); break;
            case 6: g.drawImage(pantalla7,0,0,this); break;
            
        }
    }
    // Funcion para dibujar el suelo de acuerdo con la fase
    // al pasar la fase 3 se repiten los suelos una y otra vez
    public void pintarSuelo(Graphics g) {
        switch(mg.getFase()%4) {
            case 0: g.drawImage(imgSuelo, 0, 540, this); break;
            case 1: g.drawImage(imgSuelo2, 0, 540, this); break;
            case 2: g.drawImage(imgSuelo3, 0, 540, this); break;
            case 3: g.drawImage(imgSuelo4, 0, 540, this); break;
        }
    }
    
    public void pintarDisparo(Graphics g) {      
        int dis = mg.getDisDisparo();
        if(dis > 0) {   
            float ang = mg.getAngDisparo();
            int xI = mg.getXDisparo();
            int yI = mg.getDisSuelo();
            int xF = xI;
            int yF = yI - dis;
            
            for(int n = 0; n < 7; n++) {
                if(n%2 == 0)
                    g.setColor(Color.magenta);
                else
                    g.setColor(Color.red);
                g.drawLine(xI+n-3, yI, xF, yF);
            }
        }
    }
 
    public void pintarNaves(Graphics g) {
        ArrayList<Nave> naves = mg.getNave();
        // Recorremos el arreglo para obtener el numero
        // y pintar pelotas según corresponda el valor
        for(Nave b: naves) {
            // obteniendo el valo de x
            int x = b.getX();
            // Definir el valor en el eje x de acuerdo
            // al valor obtenido
            if (b.getAlarma().isAlive() == true) {
                switch(b.getVidas()%4) {
                    case 1: case 0: if (parpadeo) g.drawImage(nave, x-b.getTamW(), 540-(b.getTamH()*2), b.getTamW()*2, b.getTamH()*2, this); break;
                    case 2: if (parpadeo) g.drawImage(nave2, x-b.getTamW(), 540-(b.getTamH()*2), b.getTamW()*2, b.getTamH()*2, this); break;
                    case 3: if (parpadeo) g.drawImage(nave3, x-b.getTamW(), 540-(b.getTamH()*2), b.getTamW()*2, b.getTamH()*2, this); break;
                }
                parpadeo = !parpadeo;
            }
            else {
                switch(b.getVidas()%4) {
                    case 1: case 0: g.drawImage(nave, x-b.getTamW(), 540-(b.getTamH()*2), b.getTamW()*2, b.getTamH()*2, this); break;
                    case 2: g.drawImage(nave2, x-b.getTamW(), 540-(b.getTamH()*2), b.getTamW()*2, b.getTamH()*2, this); break;
                    case 3: g.drawImage(nave3, x-b.getTamW(), 540-(b.getTamH()*2), b.getTamW()*2, b.getTamH()*2, this); break;
                }

            }
            if (b.getAlarma2().isAlive() == true) {
                g.drawImage(calavera, x-50, 540-50, 100, 100, this); 
            }
            g.drawString(b.getNick(), x, 540-10);
            g.setColor(Color.red);
            g.drawOval(x, 475, 3, 3);
        }
    }
    
    public void pintarNave(Graphics g) {
        // obteniendo el valo de x
        int x = mg.getX();
        // Definir el valor en el eje x de acuerdo
        // al valor obtenido
        if (mg.getAlarma().isAlive() == true) {
            switch(mg.getVidas()%4) {
                case 1: case 0: if (parpadeo) g.drawImage(nave, x-mg.getTamW(), 540-(mg.getTamH()*2), mg.getTamW()*2, mg.getTamH()*2, this); break;
                case 2: if (parpadeo) g.drawImage(nave2, x-mg.getTamW(), 540-(mg.getTamH()*2), mg.getTamW()*2, mg.getTamH()*2, this); break;
                case 3: if (parpadeo) g.drawImage(nave3, x-mg.getTamW(), 540-(mg.getTamH()*2), mg.getTamW()*2, mg.getTamH()*2, this); break;
            }
            parpadeo = !parpadeo;
        }
        else {
            switch(mg.getVidas()%4) {
                case 1: case 0: g.drawImage(nave, x-mg.getTamW(), 540-(mg.getTamH()*2), mg.getTamW()*2, mg.getTamH()*2, this); break;
                case 2: g.drawImage(nave2, x-mg.getTamW(), 540-(mg.getTamH()*2), mg.getTamW()*2, mg.getTamH()*2, this); break;
                case 3: g.drawImage(nave3, x-mg.getTamW(), 540-(mg.getTamH()*2), mg.getTamW()*2, mg.getTamH()*2, this); break;
            }
            
        }
        if (mg.getAlarma2().isAlive() == true) {
            g.drawImage(calavera, x-50, 540-50, 100, 100, this); 
        }
        g.drawString(mg.getNick(), x, 540-10);
        g.setColor(Color.red);
        g.drawOval(x, 475, 3, 3);
    }
    
    // Método encargado de pintar la pelota
    public void pintarPelota(Graphics g) {
        // utilizamos los atributos de Bola a
        // través de la clase MGame
        // Definimos el arrayList de Bola para
        // saber cuantas pelotas deben mostrarse
        ArrayList<Bola> bolas = mg.getBola();
        // Recorremos el arreglo para obtener el numero
        // y pintar pelotas según corresponda el valor
        for(Bola b: bolas) {
            int tam = b.getTam();
        
            // Cambiando la posicion de x
            g.drawImage(pelota, b.getX()-tam, b.getY()-tam, tam*2, tam*2, this); 
        }
        // pintar la coordenada de la pelota
        //g.setColor(Color.red); 
        //g.drawOval(b.getX()+55, b.getY()+55,3,3); 
    }
    
    // Método encargado de pintar la combustible
    public void pintarCombustible(Graphics g) {
        // utilizamos los atributos de Bola a
        // través de la clase MGame
        // Definimos el arrayList de Bola para
        // saber cuantas pelotas deben mostrarse
        ArrayList<Combustible> combustibles = mg.getCombustible();
        // Recorremos el arreglo para obtener el numero
        // y pintar pelotas según corresponda el valor
        for(Combustible c: combustibles) {
            int tam = c.getTam();
        
            // Cambiando la posicion de x
            g.drawImage(combustible, c.getX()-tam, c.getY()-tam, tam*2, tam*2, this); 
        }
    }
    
    // Método encargado de pintar la estrella
    public void pintarEstrella(Graphics g) {
        // utilizamos los atributos de Bola a
        // través de la clase MGame
        // Definimos el arrayList de Bola para
        // saber cuantas pelotas deben mostrarse
        ArrayList<Estrella> estrellas = mg.getEstrella();
        // Recorremos el arreglo para obtener el numero
        // y pintar pelotas según corresponda el valor
        for(Estrella e: estrellas) {
            int tam = e.getTam();
        
            // Cambiando la posicion de x
            g.drawImage(estrella, e.getX()-tam, e.getY()-tam, tam*2, tam*2, this); 
        }
    }
    
    // Método encargado de pintar la calavera
    public void pintarCalavera(Graphics g) {
        // utilizamos los atributos de Bola a
        // través de la clase MGame
        // Definimos el arrayList de Bola para
        // saber cuantas pelotas deben mostrarse
        ArrayList<Calavera> calaveras = mg.getCalavera();
        // Recorremos el arreglo para obtener el numero
        // y pintar pelotas según corresponda el valor
        for(Calavera c: calaveras) {
            int tam = c.getTam();
        
            // Cambiando la posicion de x
            g.drawImage(calavera, c.getX()-tam, c.getY()-tam, tam*2, tam*2, this); 
        }
    }
    
    // Actualizar los graficos
    public void update(Graphics g) {
        paint(g);
    }
    
    public static void main(String[] args) {
        new CGame().setVisible(true);
    }
    
    // metodo principal del hilo
    public void run() {
        while(true) {
            try {
                // milisegundos
                Thread.sleep(100);
            } catch(Exception e){ }
            mg.ejecutarFrame();
            // actualizar gráficos
            repaint();
        }
    }
   
}










//int xF = (int)(xI+Math.cos(ang)*dis);
//int yF = (int)(yI+Math.sin(ang)*dis);

// Bola b = mg.getBola();
// g.drawImage(pelota, b.getX(), b.getY(), this);