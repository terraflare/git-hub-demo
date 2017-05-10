/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.frame;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Purnama
 */
public class BasicPanel extends JPanel{
    
    protected JPanel leftpanel, rightpanel;
    protected JPanel searchpanel;
    
    protected JTable maintable;
    
    protected JScrollPane mainscrollpane;
    
    protected MyTextField searchtextfield;
    
    protected MyButton searchbutton;
    
    public BasicPanel(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
        
        maintable = new JTable();
        maintable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        maintable.setRowHeight(maintable.getRowHeight() + 10);
        maintable.getTableHeader().setReorderingAllowed(false);
        
        mainscrollpane = new JScrollPane();
        mainscrollpane.getViewport().add(maintable, null);
        mainscrollpane.setPreferredSize(new Dimension(600, 420));
        
        searchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchpanel.setBorder(BorderFactory.createTitledBorder(" Cari "));
        searchtextfield = new MyTextField("", 250);
        searchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        
        searchpanel.add(searchtextfield);
        searchpanel.add(searchbutton);
        
        leftpanel.add(mainscrollpane);
        
        rightpanel.add(searchpanel);
        
        add(leftpanel);
        add(rightpanel);
    }
}
