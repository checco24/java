/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avvioclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author franc
 */
public class threadClient extends Thread{
    private final int port;
    Socket socket;
    BufferedReader reader;
    BufferedWriter bw;
    
     @Override
    public void run(){
        
        try {
            socket = new Socket("127.0.0.1",2345);
            System.out.println("connesso al server");
           
            
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            
            
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

    private void avvia(String linea) {
        System.out.println("connessione spostata alla porta: "+linea);
        //try {
            System.out.println("porta prima di cambiare canale: "+this.socket.getPort());
           // this.socket.bind(new InetSocketAddress("127.0.0.1",Integer.parseInt(linea)));
            System.out.println("avvia");
            System.out.println("porta dopo aver cambiato canale: "+this.socket.getPort());
            //this.socket.close();
            //this.socket = new Socket("127.0.0.1",Integer.parseInt(linea));
            //this.run();
        //} catch (IOException ex) {        }
    }
    
    
    private void leggi() {
        
        InputStream inputStream;
        try {
            inputStream = this.socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            String messaggio = dis.readUTF();
//            String[] dati = messaggio.split(":");
//            String s = dati[1];
//            
//            try{
//                if(Integer.valueOf(separa(messaggio))>1024){
//                    
//                    avvia(separa(messaggio));
//                } 
//            }catch(NumberFormatException e){ 
//                    //System.out.println(e.toString());
//                }
            System.out.println(messaggio);
        } catch (IOException ex) {        }
        
        
        /*
        try {
            String linea;
            
            while(true){
                linea = reader.readLine();
                if(linea==null)
                    break;
                System.out.println(linea);
                try{
                if(Integer.parseInt(linea)>1024){
                    avvia(linea);
                }
                //per trovare NumberFormatException ho inizialmente inserito Exception 
                }catch(NumberFormatException e){ 
                    //System.out.println(e.toString());
                }
                    
            }
            //reader.close();
        } catch (IOException ex) {        }*/
        
        
    }

    private void scrivi(String s) {
        try {
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
            dos.writeUTF("sync");
            dos.flush();
            
        } catch (IOException ex) {        }
        
        /*try {
            
            System.out.println(s);
            bw.write("Sync\n");
            bw.write(s);
            bw.flush();
            System.out.println(s);
            //bw.close();
        } catch (IOException ex) {        }*/
        
    }

    private String separa(String messaggio) {
         String[] dati = messaggio.split(":");
         //System.out.println("a"+dati[1].substring(1)+"a");
         return dati[1].substring(1);
    }
    
}
