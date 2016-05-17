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
class Semaforo {

    private int value;

    public Semaforo(int v) {
        value = v;
    }

    public synchronized void P() {
        while (value <= 0) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Erro na espera");
            }
        }
        value--;
    }

    public synchronized void V() {
        ++value;
        notify(); //acorda proccesso
    }
}
