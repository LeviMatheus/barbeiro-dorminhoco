package br.com.barbeiroDorminhoco;

import javax.swing.JOptionPane;

/**
 *
 * @author jonathalima
 */
class Barbeiro extends Thread{

    static final int CADEIRAS = 5;
    static Semaforo cliente = new Semaforo(0);//Esperando para cortar
    static Semaforo barbeiro = new Semaforo(0);//Cortando ou dormindo
    static Semaforo mutex = new Semaforo(1);//Exclusão mútua
    static int waiting = 0;
    //int i = 0;
    static boolean cortando = false;
    static boolean dormindo = false;
    //FrmBarbeiroDorminhoco fbd = new FrmBarbeiroDorminhoco();
    
    public void cortarCabelo() {
        System.out.println("Cortando cabelo");
        cortando = true;
        try {
            Thread.sleep(6000); 
            System.out.println("Terminou");
            cortando = false;
            Thread.sleep(1000);
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    public void barbers() {
            while(true) {
                System.out.println(waiting);
                if(waiting <= 0) {
                    System.out.println("Dormindo");
                    dormindo = true;
                    cliente.P();
                } else {
                    mutex.P();
                    waiting --;
                    barbeiro.V();
                    mutex.V();
                    dormindo = false;
                    cortarCabelo();
                }
            }
    }

//    public void customers() {
//        mutex.P();
//        
//        if(waiting < CADEIRAS) {
//            waiting ++;
//            cliente.V();
//            mutex.V();
//            //barbeiro.P();
//        } else {
//            mutex.V();
//        }
//    }
    
    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "Barber is open!");
        try{ Thread.sleep(2000);}catch(Exception e) {}
        while(true) {
            //customers();
            barbers();
        }
    }
}
