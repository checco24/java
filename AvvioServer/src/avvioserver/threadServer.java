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
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class threadServer extends Thread{
        
    private int port;
    ServerSocket server;
    Socket socket;
    //ArrayList<Socket> connessioniPorte = new ArrayList<>();
    Timestamp timestamp ;
    
    

    
    @Override
    public void run(){
        //System.out.println(avvia(2345));
        
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
                    leggi(socket);



                } catch (IOException ex) {        }

            }
        
        }
        
        
    }
        public boolean avvia(int port){
        try {
            server=new ServerSocket(port);
            System.out.println( "Server avviato con successo");
            return true;
        } catch (IOException ex) {}
        System.out.println( "impossibile avviare il server");
        return false;
    }
    
    private int cercaPortaLibera() {
        while(true){
            try {
                
                ServerSocket serverSocket = new ServerSocket(0);
                
                //connessioniPorte.add(serverSocket.accept());
                //connessioniPorte.get(connessioniPorte.size() - 1).;
                
                return serverSocket.getLocalPort();
                
            } catch (IOException ex) {}
        }
    }

    private void leggi(Socket socket) {
        InputStream inputStream ;
        try {
            inputStream = this.socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message = dataInputStream.readUTF();
            
             if(message.contains("sync")){
                scrivi("timestamp per sync"+timestamp.getTime());//new Date().toString());
                System.out.println("invio timestamp");
            
            }
        } catch (IOException ex) {        }
        //System.out.println("prova");
        
        
        
        
        
        
        //return;
        /*try {
            String line;
            InputStream input;
            input = this.socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            System.out.println("prova1");
            System.out.println(socket.getInetAddress().isReachable(5));
            System.out.println("contenuto: "+br.readLine());
            while ((line = br.readLine()) != null) {
                System.out.println("prova4");
                System.out.println("data");
                scrivi(new Date().toString());
                
            }
            
        } catch (IOException ex) {   System.out.println(ex.toString());     }*/
        
        
    }

    private void scrivi(String s) {
        try {
            System.out.println("invio: "+s);
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
            dos.writeUTF(s);
            dos.flush();
        } catch (IOException ex) {        }
        
        
        
        /*BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            bw.write(s);
            //this.socket.setKeepAlive(true);
            bw.flush();
            //this.socket.setKeepAlive(true);
            //bw.close();
            //this.socket.setKeepAlive(true);
        } catch (IOException ex) {        }*/
        
    }


}


//https://stackoverflow.com/questions/2675362/how-to-find-an-available-port