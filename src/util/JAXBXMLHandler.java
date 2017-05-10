/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import entity.Connection;
import entity.Connections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Purnama
 */
public class JAXBXMLHandler {
    // Export
    public static void marshal(List<Connection> connections, File selectedFile)
            throws IOException, JAXBException {
        JAXBContext context;
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(Connections.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new Connections(connections), writer);
        writer.close();
    }
 
    // Import
    public static List<Connection> unmarshal(File importFile) throws JAXBException {
        Connections connections = new Connections();
 
        JAXBContext context = JAXBContext.newInstance(Connections.class);
        Unmarshaller um = context.createUnmarshaller();
        connections = (Connections) um.unmarshal(importFile);
 
        return connections.getConnections();
    }
}
