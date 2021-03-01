/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class GetNamePort extends Thread {
 ArrayList<String> ports = new ArrayList(); 
    public GetNamePort(){    }
    
    
 @Override
    public void run(){ 
     try {
            avvia();
            sleep(60000);
     } catch (InterruptedException ex) {
     }
    }
    
    
     public void avvia(){
        ports.clear();
        for (int port = 1; port < 1024; port++){
            try {
               Socket socket = new Socket();
               socket.connect(new InetSocketAddress("localhost", port), 1);
               socket.close();
               ports.add("Port "+ port +" is open; servizio in ascolto: "+ottieniNome(String.valueOf(port))); 
            }catch(IOException ex){}
        }
    }
    //http://www.networksorcery.com/enp/protocol/ip/ports06000.htm

    private String ottieniNome(String port) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Windows/System32/drivers/etc/services"));
            String s;
            try {
                while((s = br.readLine())!= null ){
                    if(s.contains(port)){
                        return s;
                    }
                }
            } catch (IOException ex) { }
        } catch (FileNotFoundException ex) { }
        return "non identificato";
    }
    
    
     @Override
    public String toString(){
        String s="";
        for(String a : ports){
            s+=a+"\n";
        }
        return s;
    } 
}