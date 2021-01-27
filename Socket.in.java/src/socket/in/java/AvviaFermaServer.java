package socket.in.java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author franc
 */
public class AvviaFermaServer extends Thread {
    
    private final int port;
    ServerSocket server;
    Socket socket;
    
    public AvviaFermaServer(int PORT){
        this.port=PORT;
    }
    @Override
    public void run(){
        System.out.println(avvia());
        try {
            socket=server.accept();
        } catch (IOException ex) {        }
        
    }
    
    public String avvia(){
        try {
            server=new ServerSocket(port);
            return "Server avviato con successo";
        } catch (IOException ex) {}
        return "impossibile avviare il server";
    }
    
    public String ferma(){
        try {
            socket.close();
            return "Server arrestato con successo";
        } catch (IOException ex) { }
        return "Impossibile arrestare il server";
    }
    
}
