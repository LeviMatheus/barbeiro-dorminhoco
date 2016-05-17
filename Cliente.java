/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.barbeiroDorminhoco;


/**
 *
 * @author jonathalima
 */
class Cliente extends Thread{    
    
    public void customers() {
        Barbeiro.mutex.P();
        
        if(Barbeiro.waiting < Barbeiro.CADEIRAS) {
            Barbeiro.waiting ++;
            Barbeiro.cliente.V();
            Barbeiro.mutex.V();
            //barbeiro.P();
        } else {
            Barbeiro.mutex.V();
        }
    }
    @Override
    public void run() {
        while(true) {
            customers();
            
            try {
                System.out.println("Chegou 1");
                Thread.sleep((long) (Math.random() * 8000));
            } catch (InterruptedException ex) {
                System.err.print(ex);
            }
        }
    }
}
