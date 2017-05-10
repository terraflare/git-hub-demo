/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyGUI;

import java.awt.Dimension;
import javax.swing.JPasswordField;

/**
 *
 * @author Purnama
 */
public class MyPasswordField extends JPasswordField{
    public MyPasswordField(String title, int width){
        super(title);
        setPreferredSize(new Dimension(width, 22));
    }
}
