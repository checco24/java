package socket.in.java;

import java.io.IOException;
import java.net.Socket;

/**
 * @author franc
 */
public class AvviaFermaClient {
    
    private final int port;
    Socket socket;
    
    public AvviaFermaClient(int PORT){
        this.port=PORT;
    }
    
    public String avvia(){
        try {
            socket = new Socket("127.0.0.1",port);
            return "Connessione avvenuta con successo";
        } catch (IOException ex) {}
        return "Impossibile stabilire una connessione";
    }
    
    public String ferma(){
        try {
            socket.close();
            return "Connessione arrestata con successo";
        } catch (IOException ex) {}
        return "Impossibile arrestare la connessione";
    }
    

}
