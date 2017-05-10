/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.frame;

import MyGUI.MyLabel;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Purnama
 */
public class BasicItemPanel extends BasicPanel{
    
    protected JScrollPane notescrollpane;
    
    protected JPanel notepanel;
    
    protected MyLabel notelabel;
    
    public BasicItemPanel(){
        super();
        
        notelabel = new MyLabel("");
        
        notepanel = new JPanel();
        notepanel.setLayout(new BoxLayout(notepanel, BoxLayout.Y_AXIS));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notepanel.setPreferredSize(new Dimension(350, 290));
        notepanel.add(notelabel);
        
        notescrollpane = new JScrollPane();
        notescrollpane.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        notescrollpane.getViewport().add(notepanel, null);
        
        rightpanel.add(notescrollpane);
    }
}
