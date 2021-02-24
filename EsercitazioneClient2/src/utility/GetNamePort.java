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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class GetNamePort {

     public static void main(String arg[]){
         for (int port = 1; port < 1024; port++){
             try {
                 Socket socket = new Socket();
                 socket.connect(new InetSocketAddress("localhost", port), 10);
                 socket.close();
                 System.out.println("Port "+ port +" is open");
                 ottieniNome();
                 Thread t1 = new Thread();
                 t1.start();
                 
             }catch(IOException e3){}
         } 
    }
     
     
     //http://www.networksorcery.com/enp/protocol/ip/ports06000.htm

    private static void ottieniNome() {
        FileReader fr = null;
         try {
             //Process p1=Runtime.getRuntime().exec("grep -w " + port + " C:\\Windows\\System32\\drivers\\etc");
             fr = new FileReader("C:/Windows/System32/drivers/etc/services");
             BufferedReader br = new BufferedReader(fr);
         } catch (FileNotFoundException ex) {
         } finally {
             try {
                 fr.close();
             } catch (IOException ex) {             }
         }
    }
    
}
