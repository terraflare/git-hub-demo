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
public class GeneralReportTab extends JPanel {
    private JPanel buttonpanel;
    
    private JButton reportoutbutton, reportinbutton, reportcnbutton, uploadbutton;
    
    public GeneralReportTab(){
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(5, 0, 0, 5)
            ));
        
        reportoutbutton = new JButton("Laporan Barang Produksi");
        reportinbutton = new JButton("Laporan Barang Curah");
        reportcnbutton = new JButton("Laporan CN");
        uploadbutton = new JButton("Unggah ke excel");
        
        buttonpanel.add(reportoutbutton);
        buttonpanel.add(reportinbutton);
        buttonpanel.add(reportcnbutton);
        buttonpanel.add(uploadbutton);
        
        add(buttonpanel);
        add(new ReportOutTab());
        
        reportoutbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ReportOutTab());
            revalidate();
            repaint();
        });
        
        reportinbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ReportInTab());
            revalidate();
            repaint();
        });
        
        reportcnbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ReportCnTab());
            revalidate();
            repaint();
        });
        
        uploadbutton.addActionListener((ActionEvent e) -> {
            remove(this.getComponent(1));
            add(new ExportTab());
            revalidate();
            repaint();
        });
    }
}
