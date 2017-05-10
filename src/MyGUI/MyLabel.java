/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyGUI;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Purnama
 */
public class MyLabel extends JLabel{
    
    public MyLabel(String title){
        super(title);
    }
    
    public MyLabel(ImageIcon imageicon, int width, int height){
        super(imageicon);
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10, 10 ,10 ,10));
        setPreferredSize(new Dimension(width, height));
    }
}
