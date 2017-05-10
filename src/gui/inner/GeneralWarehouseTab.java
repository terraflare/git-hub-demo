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
public class GeneralWarehouseTab extends JPanel{
    private JPanel buttonpanel;
    
    private JButton warehousebutton, invoicewarehouseoutbutton, invoicewarehouseinbutton;
    
    public GeneralWarehouseTab(){
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(5, 0, 0, 5)
            ));
        
        warehousebutton = new JButton("Daftar Gudang");
        invoicewarehouseoutbutton = new JButton("Faktur Gudang Produksi");
        invoicewarehouseinbutton = new JButton("Faktur Gudang Curah");
        
        buttonpanel.add(warehousebutton);
        buttonpanel.add(invoicewarehouseoutbutton);
        buttonpanel.add(invoicewarehouseinbutton);
        
        add(buttonpanel);
        add(new WarehouseTab());
        
        warehousebutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new WarehouseTab());
            revalidate();
            repaint();
        });
        
        invoicewarehouseoutbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new InvoiceWarehouseOutTab());
            revalidate();
            repaint();
        });
        
        invoicewarehouseinbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new InvoiceWarehouseInTab());
            revalidate();
            repaint();
        });
    }
}
