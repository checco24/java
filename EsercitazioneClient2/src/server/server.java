/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import utility.countdown;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.Timestamp;
import utility.GetNamePort;
import utility.HTTP;

/**
 *
 * @author franc
 */
public class server extends Thread{
    
    ServerSocket server;
    Socket socket;
    Timestamp timestamp;
    final int TOUTSERVER=8000;//ms
    final int TOUTSOCKET=16000;//ms
    boolean ferma;
    countdown cd;
    GetNamePort ports;
    
    @Override
    public void run(){        
        if(avvia(2345)){
            scansionaPorte();
            while(true){
                try {
                    socket=server.accept();
                    //cd.reset();
                    socket.setSoTimeout(TOUTSOCKET);
                    System.out.println("connesso ad un client");
                    scrivi("ciao \ntempo rimasto: "+socket.getSoTimeout());
                    System.out.println(ports);
                    
                    //ottieniSorg("https://www.avolta.pg.it/pvw/app/PGIT0005/pvw_sito.php");
                    
                } 
                catch (SocketTimeoutException ex){
                    System.err.println("error: "+ex);
                    try {
                        server.close();
                        break;
                    } catch (IOException ex1) { }
                }
                catch (IOException ex) { 
                    System.err.println("error: "+ex);
                }
            }
        }
    }
    

    public boolean avvia(int port){
        try {
            server = new ServerSocket(port);
            System.out.println( "Server avviato con successo");
            //server.setSoTimeout(TOUTSERVER);
            //cd = new countdown(server.getSoTimeout());
            //Thread c = new Thread(cd);
            //c.start();
            
            return true;
        } catch (IOException ex) {}
        System.out.println( "impossibile avviare il server");
        return false;
    }
    
    private void scrivi(String s) {
        try {
            System.out.println("invio: "+s);
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
            dos.writeUTF(s);
            dos.flush();
        } catch (IOException ex) {        }
        
    }
    
    public String leggi(){
        InputStream inputStream ;
        try {
            inputStream = this.socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message = dataInputStream.readUTF();
            
            if(message.contains("http")){
                ottieniSorg(message);
            }
            
            
            return message;
        } catch (IOException ex) {
            return "error: " + ex;
        }
    }
    
    private int cercaPortaLibera() {
        while(true){
            try {
                ServerSocket serverSocket = new ServerSocket(0);            
                return serverSocket.getLocalPort();
            } catch (IOException ex) {}
        }
    }

    private void scansionaPorte(){
        ports = new GetNamePort();
        ports.start();
    }
    
    private String ottieniSorg(String url){
        HTTP http = new HTTP(url);
        System.out.println(http.ottieniSorgente());
        
        
        return http.ottieniSorgente();
    }
    
    
    
}
