/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import entity.User;
import gui.inner.GeneralInvoiceTab;
import gui.inner.GeneralItemTab;
import gui.inner.GeneralProductionTab;
import gui.inner.GeneralReportTab;
import gui.inner.GeneralSetTab;
import gui.inner.GeneralWarehouseTab;
import gui.inner.PlasticTab;
import gui.inner.SetPlasticTab;
import gui.inner.UserTab;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Navigation extends JPanel{
    
    MyButton userbutton;
    MyButton warehousebutton;
    MyButton itembutton;
    MyButton invoicebutton;
    MyButton productionbutton;
    MyButton logbutton;
    MyButton reportbutton;
    
    public Navigation(String type){
        super(new GridLayout(7, 0, 5, 5));
        
        userbutton = new MyButton(new MyImageIcon().getImage("gui/image/User.png"), "Pengguna", 200, 64);
        warehousebutton = new MyButton(new MyImageIcon().getImage("gui/image/Warehouse.png"), "Gudang", 200, 64);
        itembutton = new MyButton(new MyImageIcon().getImage("gui/image/Item.png"), "Barang", 200, 64);
        invoicebutton = new MyButton(new MyImageIcon().getImage("gui/image/Invoice.png"), "Faktur", 200, 64);
        productionbutton = new MyButton(new MyImageIcon().getImage("gui/image/Production.png"), "Produksi", 200, 64);
        reportbutton = new MyButton(new MyImageIcon().getImage("gui/image/Report.png"), "Laporan", 200, 64);
        logbutton = new MyButton(new MyImageIcon().getImage("gui/image/Set.png"), "Set", 200, 64);
        
        add(userbutton);
        add(warehousebutton);
        add(itembutton);
        add(productionbutton);
        add(invoicebutton);
        add(reportbutton);
        add(logbutton);
        
        User user = GlobalFields.USER;
        
        if(user.getRole().equals("ADMIN")){
            userbutton.setEnabled(false);
            logbutton.setEnabled(false);
        }
        else if(user.getRole().equals("USER")){
            userbutton.setEnabled(false);
            warehousebutton.setEnabled(false);
            reportbutton.setEnabled(false);
            logbutton.setEnabled(false);
        }
        
        userbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new UserTab());
            panel.revalidate();
            panel.repaint();
        });
        
        warehousebutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralWarehouseTab());
            panel.revalidate();
            panel.repaint();
        });
        
        itembutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            if(type.equals("GULA")){
                //panel.add(new ItemOutTab());
                panel.add(new GeneralItemTab());
            }
            else{
                panel.add(new PlasticTab());
            }
            panel.revalidate();
            panel.repaint();
        });
        
        productionbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralProductionTab(1));
            panel.revalidate();
            panel.repaint();
        });
        
        invoicebutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralInvoiceTab(1));
            panel.revalidate();
            panel.repaint();
        });
        
        reportbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
//            panel.add(new ReportOutTab());
            panel.add(new GeneralReportTab());
            panel.revalidate();
            panel.repaint();
        });
        
        logbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            if(type.equals("GULA")){
                panel.add(new GeneralSetTab());
            }
            else{
                panel.add(new SetPlasticTab());
            }
            panel.revalidate();
            panel.repaint();
        });
    }
}