/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyGUI;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Purnama
 */
public class MyImageIcon{
    
    ImageIcon image;
    
    public ImageIcon getImage(String url){
        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageURL = cldr.getResource(url+"");
        image = new ImageIcon(imageURL);
        return image;
    }
}
