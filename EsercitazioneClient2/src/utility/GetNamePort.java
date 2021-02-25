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
public class GetNamePort {
 ArrayList<String> ports = new ArrayList(); 
    public GetNamePort(){    }
    
    
     public void avvia(){
         for (int port = 1; port < 65535; port++){
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
        FileReader fr = null;
         try {
            
            fr = new FileReader("C:/Windows/System32/drivers/etc/services");
            BufferedReader br = new BufferedReader(fr);
            String s;
             
            try {
                while((s = br.readLine())!= null ){
                    if(s.contains(port)){
                        return s;
                    }
                }
            } catch (IOException ex) { }
         } catch (FileNotFoundException ex) {
         } finally {
             try {
                fr.close();
             } catch (IOException ex) { }
             
         }
         return "non identificato";
    }
    
    
     @Override
    public String toString(){
        String s=null;
        for(String a : ports){
            s+=a+"\n";
        }
        return s;
    } 
}
