/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.inner;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Purnama
 */
public class LogTab extends JTabbedPane {
    
    JPanel loglist;
    
    public LogTab(){
        super();
        
        loglist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loglist.setPreferredSize(new Dimension(1000, 450));
        
        addTab("Log", loglist);
    }
    
    
}
