/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Purnama
 */
public class GeneralProductionTab extends JPanel {
    private JPanel buttonpanel;
    
    private JButton productionbutton, production2button;
    private JButton production3button, production4button;
    
    public GeneralProductionTab(int tab){
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(5, 0, 0, 5)
            ));
        
        productionbutton = new JButton("Daftar Produksi");
        production2button = new JButton("Daftar Pindah Barang");
        production3button = new JButton("Daftar Produksi ke CN");
        production4button = new JButton("Daftar CN ke Curah");
        
        buttonpanel.add(productionbutton);
        buttonpanel.add(production2button);
        buttonpanel.add(production3button);
        buttonpanel.add(production4button);
        
        add(buttonpanel);
        
        if(tab == 1){
            add(new ProductionTab());
        }
        else if (tab == 2){
            add(new Production2Tab());
        }
        else if(tab == 3){
            add(new Production3Tab());
        }
        else{
            add(new Production4Tab());
        }
        
        
        productionbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ProductionTab());
            revalidate();
            repaint();
        });
        
        production2button.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new Production2Tab());
            revalidate();
            repaint();
        });
        
        production3button.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new Production3Tab());
            revalidate();
            repaint();
        });
        
        production4button.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new Production4Tab());
            revalidate();
            repaint();
        });
    }
}
