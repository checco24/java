package socket.in.java;
import java.util.Scanner;

/**
 * @author franc
 */
public class SocketInJava {

    /**
     * @param args the command line arguments
     */
    
    private static final int PORT = 2345;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        AvviaFermaServer server = new AvviaFermaServer(PORT);
        AvviaFermaClient client = new AvviaFermaClient(PORT);
        stampa ("avvio");
        
        //avvio del server e del client
        //il server è un thread perchè(a quanto ho capito il server.accept()) ferma l'esecuzione tipo il comando joint della classe thread
        server.start();
        stampa(client.avvia());
        
        stampa(client.ferma());
        stampa(server.ferma());
        
    }
    
    public static void stampa(String s){
        System.out.println(s);
    }
    
}
