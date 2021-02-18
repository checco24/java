/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avvioserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

/**
 *
 * @author franc
 */
public class threadServer extends Thread{
    
    ServerSocket server;
    Socket socket;
    Timestamp timestamp ;
    
    
    @Override
    public void run(){        
        if(avvia(2345)){
            while(true){
                try {
                    socket=server.accept();
                    System.out.println("connesso ad un client");
                    String t="ciao";
                    timestamp = new Timestamp(System.currentTimeMillis());
                    
                    this.socket.setKeepAlive(true);

                    System.out.println("scrivo al client");
                    
                    scrivi(t+"\nla nuova porta di comunicazione è : "+String.valueOf(cercaPortaLibera()));
                    System.out.println("leggo dal client");
                    leggi();

                } catch (IOException ex) {        }

            }
        
        }
        
        
    }
    
    /**
     * avvio il server su una porta specifica
     */
    public boolean avvia(int port){
        try {
            server = new ServerSocket(port);
            System.out.println( "Server avviato con successo");
            return true;
        } catch (IOException ex) {}
        System.out.println( "impossibile avviare il server");
        return false;
    }
    
        
    /**
     * cerco la prima porta libera da comunicare al client per effettuare il cambio  
     */
    private int cercaPortaLibera() {
        while(true){
            try {
                
                ServerSocket serverSocket = new ServerSocket(0);
                               
                return serverSocket.getLocalPort();
                
            } catch (IOException ex) {}
        }
    }

    /**
     * leggo i messaggi inviati dal client
     */
    private void leggi() {
        InputStream inputStream ;
        try {
            inputStream = this.socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message = dataInputStream.readUTF();
            
             if(message.contains("sync")){
                scrivi("timestamp per sync "+timestamp.getTime());
                System.out.println("invio timestamp");
            
            }
        } catch (IOException ex) {        }

    }

    private void scrivi(String s) {
        try {
            System.out.println("invio: "+s);
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
            dos.writeUTF(s);
            dos.flush();
        } catch (IOException ex) {        }
        
    }


}
