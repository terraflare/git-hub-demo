/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.CnController;
import controller.ExportController;
import controller.ReportController;
import entity.Cn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tablemodel.CnTableModel;

/**
 *
 * @author Purnama
 */
public class ReportCnTab extends JPanel{
    
    CnController cnController;
    ReportController reportController;
    ExportController exportController;
    
    JPanel cnlistleftpanel;
    JPanel cnlistrightpanel;
    
    JPanel cnsearchpanel;
    JPanel cndetailpanel;
    JPanel cndetailpanel2;
    JPanel cnactionpanel;
    JPanel cnaction2panel;
    
    JPanel datebeginpanel;
    JPanel dateendpanel;
    
    JScrollPane cnscrollpane;
    JScrollPane cnscrollpane2;
    JScrollPane cnscrollpane3;
    
    JTable cntable;
    
    MyTextField cnsearchtextfield;
    
    MyButton cnsearchbutton;
    MyButton cnuncheckedbutton;
    MyButton uploadbutton;
    
    CnTableModel ctm;
    
    TableRowSorter<CnTableModel> sorter;
    
    JComboBox warehousecombobox;
    
    ArrayList<Cn> cnlist;
    
    public ReportCnTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        cnController = new CnController();
        reportController = new ReportController();
        exportController = new ExportController();
        
        cnscrollpane = new JScrollPane();
        cnscrollpane2 = new JScrollPane();
        cnscrollpane3 = new JScrollPane();
        
        cnlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cnlistrightpanel = new JPanel();
        cnsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cndetailpanel = new JPanel();
        cndetailpanel2 = new JPanel();
        cnactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        cnaction2panel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        cnlistrightpanel.setLayout(new BoxLayout(cnlistrightpanel, BoxLayout.Y_AXIS));
        cndetailpanel.setLayout(new BoxLayout(cndetailpanel, BoxLayout.Y_AXIS));
        cndetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        cndetailpanel.setPreferredSize(new Dimension(300, 60));
        
        cndetailpanel2.setLayout(new BoxLayout(cndetailpanel2, BoxLayout.Y_AXIS));
        cndetailpanel2.setBorder(new EmptyBorder(0, 10, 0, 25));
        cndetailpanel2.setPreferredSize(new Dimension(300, 60));
        
        cnsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari CN "));
        cnactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        cnaction2panel.setBorder(BorderFactory.createTitledBorder(" Unggah Ke Excel "));
        
        datebeginpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dateendpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        cnlist = cnController.getCnList();
        
        ctm = new CnTableModel(cnlist);
        sorter = new TableRowSorter<CnTableModel>(ctm);
        sorter.setSortsOnUpdates(true);
        
        cntable = new JTable(ctm);
        cntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cntable.getTableHeader().setReorderingAllowed(false);
        cntable.setRowHeight(cntable.getRowHeight() + 10);
        
        cntable.setRowSorter(sorter);
        
        cnscrollpane.getViewport().add(cntable, null);
        cnscrollpane.setPreferredSize(new Dimension(600, 420));
        
        cnscrollpane2.getViewport().add(cndetailpanel, null);
        cnscrollpane2.setBorder(BorderFactory.createTitledBorder(" CN yang belum diperiksa "));
        
        cnscrollpane3.getViewport().add(cndetailpanel2, null);
        cnscrollpane3.setBorder(BorderFactory.createTitledBorder(" CN yang sudah diperiksa "));
        
        cnsearchtextfield = new MyTextField("", 250);
        
        cnsearchtextfield.getDocument().addDocumentListener(
        new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });
        
        UtilDateModel model1 = new UtilDateModel();
        model1.setSelected(true);
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        
        UtilDateModel model2 = new UtilDateModel();
        model2.setSelected(true);
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        
        cnsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        cnuncheckedbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Lihat Laporan", 110, 28);
        uploadbutton = new MyButton("Unggah Ke Excel");
        
        datebeginpanel.add(new JLabel("Tanggal Awal : "));
        datebeginpanel.add(datePicker1);
        dateendpanel.add(new JLabel("Tanggal Akhir : "));
        dateendpanel.add(datePicker2);
        
        cnactionpanel.add(cnuncheckedbutton);
        cnaction2panel.add(datebeginpanel);
        cnaction2panel.add(dateendpanel);
        cnaction2panel.add(uploadbutton);
        
        add(cnlistleftpanel);
        add(cnlistrightpanel);
        
        cnlistleftpanel.add(cnscrollpane);
        
        cnlistrightpanel.add(cnsearchpanel);
        cnlistrightpanel.add(cnscrollpane2);
        cnlistrightpanel.add(cnscrollpane3);
        cnlistrightpanel.add(cnactionpanel);
        cnlistrightpanel.add(cnaction2panel);
        
        cnsearchpanel.add(cnsearchtextfield);
        cnsearchpanel.add(cnsearchbutton);
        
        cnuncheckedbutton.addActionListener((ActionEvent e) -> {
            try{
                
                Cn cn = cnController.getCn(1);
                
                new ReportCnListFrame("Laporan", true, true, cn);
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        uploadbutton.addActionListener((ActionEvent e) -> {
            try{
                exportController.cnexportgeneral((Date)datePicker1.getModel().getValue(), (Date)datePicker2.getModel().getValue());
                        
                JOptionPane.showMessageDialog(null, "Data berhasil di unggah ke Excel");
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Gagal mengunggah data");
            }
        });
    }
    
    private void newFilter() {
        RowFilter<CnTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(cnsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
