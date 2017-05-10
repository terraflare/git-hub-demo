/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import MyGUI.MyButton;
import MyGUI.MyFrame;
import MyGUI.MyImageIcon;
import MyGUI.MyLabel;
import entity.User;
import gui.inner.GeneralItemTab;
import gui.inner.PlasticTab;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Mainmenu {
    
    MyFrame mainmenuframe;
    
    Box mainbox;
    
    JPanel linepanel;
    JPanel headerpanel;
    JPanel centerpanel;
    
    MyLabel linelabel;
    
    MyButton logoutbutton;
    
    JTabbedPane tabbedPane = new JTabbedPane();
    
    public Mainmenu(User user, String type){
        GlobalFields.USER = user;
        
        mainmenuframe = new MyFrame("");
        
        mainbox = Box.createVerticalBox();
        
        mainbox.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        linepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        centerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        headerpanel.setBackground(Color.DARK_GRAY);
        
        linelabel = new MyLabel(new MyImageIcon().getImage("gui/image/Header2.jpg"), 1200, 2);
        logoutbutton = new MyButton(new MyImageIcon().getImage("gui/image/Logout.png"), 64, 64);
        
        logoutbutton.setToolTipText("Logout ("+GlobalFields.USER.getUsername()+")");
        
        headerpanel.add(logoutbutton);
        
        linepanel.add(linelabel);
        
        centerpanel.add(new Navigation(type));
        if(type.equals("GULA")){
//            centerpanel.add(new ItemOutTab());
            centerpanel.add(new GeneralItemTab());
        }
        else{
            centerpanel.add(new PlasticTab());
        }
        
        mainbox.add(headerpanel);
        mainbox.add(linepanel);
        mainbox.add(centerpanel);
        
        mainmenuframe.add(mainbox);
        
        mainmenuframe.setResizable(false);
        mainmenuframe.display(mainmenuframe);
        mainmenuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainmenuframe.pack();
        mainmenuframe.setLocationToCenter(mainmenuframe);
        
        logoutbutton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mainmenuframe.dispose();
                new Login();
                
            }
        });
    }
}