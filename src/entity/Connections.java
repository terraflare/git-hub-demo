/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Purnama
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "connections")
public class Connections {
    @XmlElement(name = "connection", type = Connection.class)
    private List<Connection> connections = new ArrayList<Connection>();
    
    public Connections(){
        
    }
    
    public Connections(List<Connection> connections){
        this.connections = connections;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
