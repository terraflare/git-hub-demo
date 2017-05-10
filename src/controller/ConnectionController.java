/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Connection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import util.GlobalFields;
import util.JAXBXMLHandler;
import util.MD5;
import util.MD5Encoder;

/**
 *
 * @author Purnama
 */
public class ConnectionController {
    
    List<Connection> connectionlist;
    
    public ConnectionController(){
        
    }
    
    public void getConnections(){
        try {
            connectionlist = JAXBXMLHandler.unmarshal(new File("connection.xml"));
            
//            MD5Encoder decoder = new MD5Encoder();
            
//            for(Connection connection : connectionlist){
//                if(connection.getPassword() == null){
//                    
//                }
//                else{
//                    System.out.println(connection.getPassword().getBytes());
//                    System.out.println(decoder.encode(connection.getPassword().getBytes()));
//                    connection.setPassword(decoder.encode(connection.getPassword().getBytes()));
//                }
//            }
            
            GlobalFields.CONNECTIONLIST = connectionlist;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public void init() throws IOException{
        List<Connection> connections = new ArrayList<Connection>();
        
        for(int i = 1; i <= 10; i++){
            Connection connection = new Connection();
            connection.setName("New Connection"+i);
            connection.setConnection("");
            connection.setPassword("");
            connection.setSchema("");
            connection.setUsername("");
            
            connections.add(connection);
        }
        
        write(connections);
    }
    
    public void write(List<Connection> connections) throws IOException{
        
//        for(Connection connection : connections){
//            if(connection.getPassword() == null){
//                
//            }
//            else{
//                connection.setPassword(MD5.crypt(connection.getPassword()));
//            }
//        }
        
        try {
            JAXBXMLHandler.marshal(connections, new File("connection.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
}
