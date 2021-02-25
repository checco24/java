/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URI;
import java.net.URL;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
/**
 *
 * @author franc
 */
public class HTTP {
    
    URL urlConn = null;
    String data = null;
    
    
    public HTTP(String url){
        try {
            urlConn=new URI(url).toURL();
        } catch (URISyntaxException | MalformedURLException ex) {        }
    }
    
    public String ottieniSorgente(){
        if(urlConn !=null){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.openStream()));
                String line;
                while ((line = br.readLine()) != null ){
                    data+=line+"\n";
                }
            }
            catch (IOException ex) {        }
            return data;
            }
        return "url non valido";
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
