/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Lorena
 */
public class Connector {
    private String IP;
    private int port;
    
    public Connector(String IP, int port){
        this.IP=IP;
        this.port=port;
    }
    
    public String getIP(){
        return this.IP;
    }
    
    public int getPort(){
        return this.port;
    }
}
