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
public class GeneralItemTab extends JPanel{
    
    private JPanel buttonpanel;
    
    private JButton itemoutbutton, iteminbutton, merkbutton, packagingbutton,
            qualitybutton, categorybutton, cnbutton;
    
    public GeneralItemTab(){
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(5, 0, 0, 5)
            ));
        
        itemoutbutton = new JButton("Barang Produksi");
        iteminbutton = new JButton("Barang Curah");
        merkbutton = new JButton("Merek");
        packagingbutton = new JButton("Kemasan");
        qualitybutton = new JButton("Kualitas");
        categorybutton = new JButton("Kategori");
        cnbutton = new JButton("CN");
        
        buttonpanel.add(itemoutbutton);
        buttonpanel.add(iteminbutton);
        buttonpanel.add(cnbutton);
        buttonpanel.add(merkbutton);
        buttonpanel.add(packagingbutton);
        buttonpanel.add(qualitybutton);
        buttonpanel.add(categorybutton);
        
        add(buttonpanel);
        add(new ItemOutTab());

        itemoutbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ItemOutTab());
            revalidate();
            repaint();
        });
        
        iteminbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ItemInTab());
            revalidate();
            repaint();
        });
        
        cnbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new CnTab());
            revalidate();
            repaint();
        });
        
        merkbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new MerkTab());
            revalidate();
            repaint();
        });
        
        merkbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new MerkTab());
            revalidate();
            repaint();
        });
        
        packagingbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new PackagingTab());
            revalidate();
            repaint();
        });
        
        qualitybutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new QualityTab());
            revalidate();
            repaint();
        });
        
        categorybutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new CategoryTab());
            revalidate();
            repaint();
        });
    }
}
