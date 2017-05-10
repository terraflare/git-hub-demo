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
public class GeneralInvoiceTab extends JPanel {
    private JPanel buttonpanel;
    
    private JButton invoicesalesbutton, invoicesales2button, invoicepurchasebutton,
            returnsalesbutton, returnpurchasebutton, itemexchangebutton;
    
    public GeneralInvoiceTab(int index){
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(5, 0, 0, 5)
            ));
        
        invoicesalesbutton = new JButton("Penjualan Produksi");
        invoicesales2button = new JButton("Penjualan Curah");
        invoicepurchasebutton = new JButton("Pembelian Curah");
        returnsalesbutton = new JButton("Retur Produksi");
        returnpurchasebutton = new JButton("Retur Curah");
        itemexchangebutton = new JButton("Tukar Guling");
        
        buttonpanel.add(invoicesalesbutton);
        buttonpanel.add(invoicesales2button);
        buttonpanel.add(invoicepurchasebutton);
        buttonpanel.add(returnsalesbutton);
        buttonpanel.add(returnpurchasebutton);
        buttonpanel.add(itemexchangebutton);
        
        add(buttonpanel);
        
        if(index == 6){
            add(new ItemExchangeTab());
        }else{
            add(new InvoiceSalesTab());
        }
        
        invoicesalesbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new InvoiceSalesTab());
            revalidate();
            repaint();
        });
        
        invoicesales2button.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new InvoiceSales2Tab());
            revalidate();
            repaint();
        });
        
        invoicepurchasebutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new InvoicePurchaseTab());
            revalidate();
            repaint();
        });
        
        returnsalesbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ReturnSalesTab());
            revalidate();
            repaint();
        });
        
        returnpurchasebutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ReturnPurchaseTab());
            revalidate();
            repaint();
        });
        
        itemexchangebutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ItemExchangeTab());
            revalidate();
            repaint();
        });
    }
}
