/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercitazione.gestione.eccezioni;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



//esercizio 1
/*
public class NestedFileNotFound {

    public static void metodo2(){
        FileReader reader;
        try {
            reader = new FileReader("input.txt");
            BufferedReader in = new BufferedReader(reader);
            
            String line;
            line = in.readLine();
            System.out.println("Terminato metodo 2");
        } 
        catch (FileNotFoundException ex) {   
            System.err.println("File non trovato: "+ex);
        } 
        catch (IOException ex) {   
            System.err.println("I/O ex: "+ex);
        }
    }
    
    public static void metodo1(){
        System.out.println("Invocato metodo 1");
        metodo2();
        System.out.println("Terminato metodo 1");
    }
    
    public static void main(String [] args){
        System.out.println("Invocato main");
        metodo1();
        System.out.println("Terminato main");
}
    
}
*/

//esercizio 2
/*
public class NestedFileNotFound {

    public static void metodo2() {
        FileReader reader = null;
        try {
            reader = new FileReader("input.txt");
            BufferedReader in = new BufferedReader(reader);
            try {
                String line;
                line = in.readLine();
            } catch (IOException ex) {   
                System.err.println("I/O ex: "+ex);
            }
            System.out.println("Terminato metodo 2");
        } 
        catch (FileNotFoundException ex) {
            System.err.println("File non trovato: "+ex);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException ex) {  }
            }
            
        }
       
    }
    
    public static void metodo1(){
        System.out.println("Invocato metodo 1");
        metodo2();
        System.out.println("Terminato metodo 1");
    }
    
    public static void main(String [] args){
        System.out.println("Invocato main");
        metodo1();
        System.out.println("Terminato main");
}
    
}
*/

//Esercizio 3
public class NestedFileNotFound {
    
    public static String[] metodo2(){
        FileReader reader;
        String[] righe = null ;
        try {
            reader = new FileReader("input.txt");
            BufferedReader in = new BufferedReader(reader);
            
            String line;
            while((line = in.readLine()) !=  null){
                righe[righe.length]=line;
            }
            
            System.out.println("Terminato metodo 2");
        } 
        catch (FileNotFoundException ex) {   
            System.err.println("File non trovato: "+ex);
        } 
        catch (IOException ex) {   
            System.err.println("I/O ex: "+ex);
        }
        return righe;
    }
    
    public static void metodo1(){
        System.out.println("Invocato metodo 1");
        metodo2();
        System.out.println(metodo2()[1]);
        System.out.println("Terminato metodo 1");
    }
    
    public static void main(String [] args){
        System.out.println("Invocato main");
        metodo1();
        System.out.println("Terminato main");
    }
    
    
    
}