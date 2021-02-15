/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avvioclient;

/**
 *
 * @author franc
 */
public class AvvioClient {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        int port=2345;
        threadClient tc = new threadClient(port);
        tc.start();
    }
    

    
}
