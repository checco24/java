/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import countdown.countdown;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class client extends Thread{
    private final int port;
    Socket socket;
    InputStream inputStream;
    DataInputStream dis;
    DataOutputStream dos;
    countdown cd;
    
    
     @Override
    public void run(){
        try {
            socket = new Socket("127.0.0.1",2345);
            System.out.println("connesso al server");
            assegna();
            String[] a=separa(leggi(),": ");
            avviaCD(a[1]);
            System.out.println(a[1]);
            leggi();
            leggi();leggi();
            
            
            
            
            
            
        } catch (IOException ex) {  
            System.err.println("errore: "+ex.toString());
        
        }
    }
    
    public client(int port) {
        this.port=port;
    }
    
    private String leggi() {
        
        try {
            String messaggio = dis.readUTF();
            System.out.println(messaggio);
            return messaggio;
        } catch (IOException ex) { 
            return "impossibile leggere il messaggio";
        }
        

    }
    
    /**
     * invio la stringa sync al server per richiedere la sincronizzazione 
     */
    private void scrivi(String s) {
        try {
            
            dos.writeUTF("sync");
            dos.flush();
            cd.reset();
        } catch (IOException ex) {        }
        
    }
    
    private void assegna() {
        try {
            inputStream = this.socket.getInputStream();
            dis = new DataInputStream(inputStream);
            dos = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {        }
        
    }
        
        
    private String[] separa(String messaggio, String split) {
        String[] dati = messaggio.split(split);
        return dati;
    }
    
    
    private void avviaCD(String tempo){
        try {
            this.socket.setSoTimeout(Integer.parseInt(tempo));
            cd = new countdown(Integer.parseInt(tempo));
            Thread c = new Thread(cd);
            c.start();
        } catch (NumberFormatException | SocketException ex) {       }
        
    }
        
}