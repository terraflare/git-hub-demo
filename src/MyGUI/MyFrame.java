/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyGUI;

import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Purnama
 */
public class MyFrame extends JFrame{
    
    public MyFrame(String title){
        super(title);
        setDefaultLookAndFeelDecorated(true);
    }
    
    public void setLocationToCenter(Component c){
        int width = (int)c.getSize().getWidth();
        int height = (int)c.getSize().getHeight();
        
        Toolkit tool = Toolkit.getDefaultToolkit();
        
        setLocation((int)(tool.getScreenSize().getWidth()-width)/2, (int)(tool.getScreenSize().getHeight()-height)/2);
    }
    
    public void display(Component c){
        c.setVisible(true);
    }
    
    public void hide(Component c){
        c.setVisible(false);
    }
}
