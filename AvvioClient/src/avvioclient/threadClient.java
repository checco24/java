/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avvioclient;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author franc
 */
public class threadClient extends Thread{
    private final int port;
    Socket socket;
    InputStream inputStream;
    DataInputStream dis;
    DataOutputStream dos;
    
     @Override
    public void run(){
        
        try {
            socket = new Socket("127.0.0.1",2345);
            System.out.println("connesso al server");
            assegna();
          
            System.out.println("Leggo il messaggio dal server");
            leggi();
            scrivi("sycn");
            leggi();
            
        } catch (IOException ex) {  
            System.err.println("errore: "+ex.toString());
        
        }
        
        
    }
    
    public threadClient(int port) {
        this.port=port;
    }

    /**
     *metodo futuro per cambiare la porta di comunicazione tra client-server 
     */
    private void avvia(String linea) {
        System.out.println("connessione spostata alla porta: "+linea);
        System.out.println("porta prima di cambiare canale: "+this.socket.getPort());
        System.out.println("avvia");
        System.out.println("porta dopo aver cambiato canale: "+this.socket.getPort());

    }
    
    
    /**
     * leggo i messaggi inviati dal server
     */
    private void leggi() {
        
        try {
            
            String messaggio = dis.readUTF();
            
            try{
                System.out.println("timestamp to date: "+timestampToDate(messaggio));
            }catch(Exception e){}
            
            System.out.println(messaggio);
        } catch (IOException ex) {        }

    }
    
    /**
     * invio la stringa sync al server per richiedere la sincronizzazione 
     */
    private void scrivi(String s) {
        try {
            
            dos.writeUTF("sync");
            dos.flush();
            
        } catch (IOException ex) {        }
        
    }

    /**
     * divido la stringa che contiene il timestamp passata dal server 
     * e la trasformo nel formato richiesto
    */
    
    private String timestampToDate(String messaggio) {
        String[] dati = messaggio.split("sync ");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.parseLong(dati[1]));
        return sf.format(date);
    }
    
    /**
     * istanzio tutti gli oggetti di lettura/scrittura
     */
    
    private void assegna() {
        try {
            inputStream = this.socket.getInputStream();
            dis = new DataInputStream(inputStream);
            dos = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {        }
        
    }
    
}
