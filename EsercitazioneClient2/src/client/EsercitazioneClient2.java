/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author franc
 */
public class EsercitazioneClient2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int port=2345;
        client tc = new client(port);
        tc.start();
    }
    
}
