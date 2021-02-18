/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdown;

import static java.lang.Thread.sleep;
import java.net.ServerSocket;

/**
 *
 * @author franc
 */
public class countdown implements Runnable{
    
    int tempo=0;
    ServerSocket s;
    int i;
    
    public countdown(int cd){
        tempo=cd;
    }

    @Override
    public void run() {
        
        for(i=tempo/1000; i>0;i--){ 
            try {
                System.out.println("Tempo rimasto: "+i+" secondi");
                sleep(1000);
            } catch (InterruptedException ex) {
            }
            
        }

//        try {
//            for(int i =0; i<s.getSoTimeout();i++){
//                System.out.println(i);
//            }   
//        } catch (IOException ex) {        }
        
    }

    public void reset() {
        i=tempo/1000;
    }
    
}
