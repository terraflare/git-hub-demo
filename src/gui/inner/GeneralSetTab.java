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
public class GeneralSetTab extends JPanel{
    
    private JPanel buttonpanel;
    
    private JButton setoutbutton, setinbutton, setcnbutton, deletebutton;
    
    public GeneralSetTab(){
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(5, 0, 0, 5)
            ));
        
        setoutbutton = new JButton("Set Manual Barang Produksi");
        setinbutton = new JButton("Set Manual Barang Curah");
        setcnbutton = new JButton("Set Manual CN");
        deletebutton = new JButton("Hapus Data Barang");
        
        buttonpanel.add(setoutbutton);
        buttonpanel.add(setinbutton);
        buttonpanel.add(setcnbutton);
        buttonpanel.add(deletebutton);
        
        add(buttonpanel);
        add(new SetOutTab());
        
        setoutbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new SetOutTab());
            revalidate();
            repaint();
        });
        
        setinbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new SetInTab());
            revalidate();
            repaint();
        });
        
        setcnbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new SetCnTab());
            revalidate();
            repaint();
        });
        
        deletebutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new DeleteTab());
            revalidate();
            repaint();
        });
    }
}
