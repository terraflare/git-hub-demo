/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyGUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Purnama
 */
public class MyTextField extends JTextField{
    public MyTextField(String title, int width){
        super(title);
        setPreferredSize(new Dimension(width, 22));
    }
}
