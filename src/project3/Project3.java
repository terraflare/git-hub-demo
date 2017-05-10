/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project3;

import gui.Login;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Purnama
 */
public class Project3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new Login();
        });
    }
}
