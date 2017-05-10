/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import MyGUI.MyButton;
import MyGUI.MyFrame;
import MyGUI.MyLabel;
import MyGUI.MyPasswordField;
import MyGUI.MyTextField;
import controller.ConnectionController;
import entity.Connection;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ConnectionManagement {
    
    MyFrame connectionframe;
    
    Box mainbox;
    
    JPanel leftpanel;
    JPanel rightpanel;
    JPanel namepanel;
    JPanel connectionpanel;
    JPanel schemapanel;
    JPanel usernamepanel;
    JPanel passwordpanel;
    JPanel cpasswordpanel;
    JPanel buttonpanel;
    
    MyButton savebutton;
    MyButton quitbutton;
    
    MyLabel namelabel;
    MyLabel usernamelabel;
    MyLabel passwordlabel;
    MyLabel cpasswordlabel;
    MyLabel connectionlabel;
    MyLabel schemalabel;
    
    JList connectionlist;
    
    MyTextField namefield;
    MyTextField connectionfield;
    MyTextField schemafield;
    MyTextField usernamefield;
    
    MyPasswordField passwordfield;
    MyPasswordField cpasswordfield;
    
    ConnectionController connectionController;
    
    DefaultListModel listmodel;
    
    public ConnectionManagement(){
        
        connectionController = new ConnectionController();
        
        connectionframe = new MyFrame("Koneksi");
        
        mainbox = Box.createHorizontalBox();
        
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel(new GridLayout(7, 0));
        namepanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        schemapanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        connectionpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        usernamepanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        passwordpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cpasswordpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        leftpanel.setBorder(BorderFactory.createTitledBorder(" Daftar Koneksi "));
        rightpanel.setBorder(BorderFactory.createTitledBorder(" Properti "));
        namepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        usernamepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        schemapanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        connectionpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        passwordpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        cpasswordpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        buttonpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        
        mainbox.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        listmodel = new DefaultListModel();
        
        for(Object o : GlobalFields.CONNECTIONLIST){
            listmodel.addElement(o);
        }
        
        connectionlist = new JList(listmodel);
        connectionlist.setCellRenderer(new ConnectionCellRenderer());
        connectionlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        connectionlist.setLayoutOrientation(JList.VERTICAL);
        connectionlist.setVisibleRowCount(-1);
        connectionlist.setPreferredSize(new Dimension(250, 200));
        
        schemalabel = new MyLabel("Database : ");
        namelabel = new MyLabel("Nama : ");
        usernamelabel = new MyLabel("Username : ");
        passwordlabel = new MyLabel("Kata kunci : ");
        cpasswordlabel = new MyLabel("Konfirmasi : ");
        connectionlabel = new MyLabel("Koneksi : ");
        
        connectionfield = new MyTextField("", 250);
        schemafield = new MyTextField("", 250);
        namefield = new MyTextField("", 250);
        usernamefield = new MyTextField("", 250);
        passwordfield = new MyPasswordField("", 250);
        cpasswordfield = new MyPasswordField("", 250);
        
        savebutton = new MyButton("Simpan");
        quitbutton = new MyButton("Keluar");
        
        leftpanel.add(connectionlist);
        
        namepanel.add(namelabel);
        namepanel.add(namefield);
        
        connectionpanel.add(connectionlabel);
        connectionpanel.add(connectionfield);
        
        usernamepanel.add(usernamelabel);
        usernamepanel.add(usernamefield);
        
        passwordpanel.add(passwordlabel);
        passwordpanel.add(passwordfield);
        
        cpasswordpanel.add(cpasswordlabel);
        cpasswordpanel.add(cpasswordfield);
        
        schemapanel.add(schemalabel);
        schemapanel.add(schemafield);
        
        buttonpanel.add(savebutton);
        buttonpanel.add(quitbutton);
        
        rightpanel.add(namepanel);
        rightpanel.add(connectionpanel);
        rightpanel.add(schemapanel);
        rightpanel.add(usernamepanel);
        rightpanel.add(passwordpanel);
        rightpanel.add(cpasswordpanel);
        rightpanel.add(buttonpanel);
        
        mainbox.add(leftpanel);
        mainbox.add(rightpanel);
        
        connectionframe.add(mainbox);
        
        connectionframe.setResizable(false);
        connectionframe.display(connectionframe);
        connectionframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        connectionframe.pack();
        connectionframe.setLocationToCenter(connectionframe);
        
        connectionlist.addListSelectionListener(new ListSelectionListener (){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                Connection connection = (Connection)connectionlist.getSelectedValue();
                
                namefield.setText(connection.getName());
                usernamefield.setText(connection.getUsername());
                connectionfield.setText(connection.getConnection());
                schemafield.setText(connection.getSchema());
                passwordfield.setText(connection.getPassword());
                cpasswordfield.setText(connection.getPassword());
                
            }
        });
        
        savebutton.addActionListener((ActionEvent e) -> {
            
            if(namefield.getText().equals("")){
                JOptionPane.showMessageDialog(connectionframe, "Nama koneksi tidak boleh kosong");
            }
            else if(!passwordfield.getText().equals(cpasswordfield.getText())){
                JOptionPane.showMessageDialog(connectionframe, "Isi kata kunci dan konfirmasi tidak boleh berbeda");
            }
            else{
                int index = connectionlist.getSelectedIndex();
                
                ListModel lm = connectionlist.getModel();

                Connection connection = (Connection)lm.getElementAt(index);

                connection.setName(namefield.getText());
                connection.setConnection(connectionfield.getText());
                connection.setSchema(schemafield.getText());
                connection.setUsername(usernamefield.getText());
                connection.setPassword(passwordfield.getText());
                
                List<Connection> connections = GlobalFields.CONNECTIONLIST;
                
                connections.set(index, connection);
                
                try {
                    connectionController.write(connections);
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionManagement.class.getName()).log(Level.SEVERE, null, ex);
                }

                connectionlist.invalidate();
                connectionlist.repaint();
                
                JOptionPane.showMessageDialog(connectionframe, "Koneksi disimpan");
            }
        });
        
        quitbutton.addActionListener((ActionEvent e) -> {
            connectionframe.dispose();
            new Login();
        });
    }
}