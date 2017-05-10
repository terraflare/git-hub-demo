/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.frame;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Purnama
 */
public class BasicInvoicePanel extends BasicPanel{
    
    protected JPanel statuspanel, itempanel, notepanel, actionpanel;
    
    protected JScrollPane statusscrollpane, itemscrollpane, notescrollpane;
    
    protected MyLabel statuslabel;
    
    protected MyButton addbutton, deletebutton;
    
    public BasicInvoicePanel(){
        super();
        
        notepanel = new JPanel();
        notepanel.setLayout(new BoxLayout(notepanel, BoxLayout.Y_AXIS));
        notepanel.setPreferredSize(new Dimension(350, 105));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notescrollpane = new JScrollPane();
        notescrollpane.getViewport().add(notepanel, null);
        notescrollpane.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        itempanel = new JPanel();
        itempanel.setLayout(new BoxLayout(itempanel, BoxLayout.Y_AXIS));
        itempanel.setPreferredSize(new Dimension(350, 105));
        itempanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        itemscrollpane = new JScrollPane();
        itemscrollpane.getViewport().add(itempanel, null);
        itemscrollpane.setBorder(BorderFactory.createTitledBorder("Barang"));
        
        statuslabel = new MyLabel("");
        statuspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statuspanel.setPreferredSize(new Dimension(350, 30));
        statuspanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        statuspanel.add(statuslabel);
        statusscrollpane = new JScrollPane();
        statusscrollpane.getViewport().add(statuspanel, null);
        statusscrollpane.setBorder(BorderFactory.createTitledBorder("Status"));
        
        actionpanel = new JPanel(new GridLayout(0, 2, 5, 5));
        actionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        addbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        deletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        
        actionpanel.add(addbutton);
        actionpanel.add(deletebutton);
        
        rightpanel.add(statusscrollpane);
        rightpanel.add(itemscrollpane);
        rightpanel.add(notescrollpane);
        rightpanel.add(actionpanel);
    }
}
