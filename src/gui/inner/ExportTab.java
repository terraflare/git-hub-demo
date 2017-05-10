/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.ExportController;
import controller.ItemInController;
import controller.ItemOutController;
import controller.WarehouseController;
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
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tablemodel.ItemInTableModel;
import tablemodel.ItemOutTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ExportTab extends JPanel{
    
    JPanel iteminpanel, itemoutpanel;
    JPanel inwarehousepanel, outwarehousepanel;
    JPanel indatepanel, outdatepanel;
    JPanel indatepanel2, outdatepanel2;
    JPanel inbuttonpanel, outbuttonpanel;
    JPanel infilterpanel, outfilterpanel;
    
    MyButton inbutton, outbutton;
    MyButton insearchbutton, outsearchbutton;
    
    WarehouseController warehouseController;
    ItemInController iteminController;
    ItemOutController itemoutController;
    ExportController exportController;
    
    JComboBox inwarehousecombobox;
    JComboBox outwarehousecombobox;
    
    JComboBox intypecombobox;
    JComboBox outtypecombobox;
    
    JScrollPane iteminscrollpane;
    JScrollPane itemoutscrollpane;
    
    JTable itemintable;
    JTable itemouttable;
    
    ItemInTableModel iitm;
    ItemOutTableModel iotm;
    
    TableRowSorter<ItemInTableModel> insorter;
    TableRowSorter<ItemOutTableModel> outsorter;
    
    MyTextField intextfield, outtextfield;
    
    String[] type = {"Laporan Stok", "Laporan Keluar Masuk Barang"};
    
    public ExportTab(){
        super(new GridLayout(1, 2, 10, 10));
        
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        warehouseController = new WarehouseController();
        iteminController = new ItemInController();
        itemoutController = new ItemOutController();
        exportController = new ExportController();
    
        Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
        
        iteminpanel = new JPanel();
        iteminpanel.setLayout(new BoxLayout(iteminpanel, BoxLayout.Y_AXIS));
        iteminpanel.setBorder(
            BorderFactory.createTitledBorder(" Barang Curah "));
        
        intextfield = new MyTextField("", 200);
        insearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        
        intextfield.getDocument().addDocumentListener(
        new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                innewFilter();
            }
            public void insertUpdate(DocumentEvent e) {
                innewFilter();
            }
            public void removeUpdate(DocumentEvent e) {
                innewFilter();
            }
        });
        
        infilterpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        infilterpanel.add(intextfield);
        infilterpanel.add(insearchbutton);
        
        iitm = new ItemInTableModel(iteminController.getItemList());
        insorter = new TableRowSorter<ItemInTableModel>(iitm);
        insorter.setSortsOnUpdates(true);
        
        itemintable = new JTable(iitm);
        itemintable.getTableHeader().setReorderingAllowed(false);
        
        itemintable.setRowSorter(insorter);
        
        iteminscrollpane = new JScrollPane();
        iteminscrollpane.getViewport().add(itemintable, null);
        iteminscrollpane.setPreferredSize(new Dimension(450, 200));
        
        inwarehousecombobox = new JComboBox(warehouselist);
        
        intypecombobox = new JComboBox(type);
        
        inwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inwarehousepanel.add(new JLabel(" Gudang : "));
        inwarehousepanel.add(inwarehousecombobox);
        inwarehousepanel.add(new JLabel(" ~ "));
        inwarehousepanel.add(intypecombobox);
        
        UtilDateModel model1 = new UtilDateModel();
        model1.setSelected(true);
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        
        indatepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        indatepanel.add(new JLabel(" Tanggal Mulai : "));
        indatepanel.add(datePicker1);
        
        UtilDateModel model2 = new UtilDateModel();
        model2.setSelected(true);
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        
        indatepanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        indatepanel2.add(new JLabel(" Tanggal Akhir : "));
        indatepanel2.add(datePicker2);
        
        inbutton = new MyButton("Unggah ke Excel");
        inbuttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inbuttonpanel.add(inbutton);
        
        iteminpanel.add(infilterpanel);
        iteminpanel.add(iteminscrollpane);
        iteminpanel.add(inwarehousepanel);
        iteminpanel.add(indatepanel);
        iteminpanel.add(indatepanel2);
        iteminpanel.add(inbuttonpanel);
        
        itemoutpanel = new JPanel();
        itemoutpanel.setLayout(new BoxLayout(itemoutpanel, BoxLayout.Y_AXIS));
        itemoutpanel.setBorder(
            BorderFactory.createTitledBorder(" Barang Produksi "));
        
        outtextfield = new MyTextField("", 200);
        outsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        
        outtextfield.getDocument().addDocumentListener(
        new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                outnewFilter();
            }
            public void insertUpdate(DocumentEvent e) {
                outnewFilter();
            }
            public void removeUpdate(DocumentEvent e) {
                outnewFilter();
            }
        });
        
        outfilterpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        outfilterpanel.add(outtextfield);
        outfilterpanel.add(outsearchbutton);
        
        iotm = new ItemOutTableModel(itemoutController.getItemList());
        outsorter = new TableRowSorter<ItemOutTableModel>(iotm);
        outsorter.setSortsOnUpdates(true);
        
        itemouttable = new JTable(iotm);
        itemouttable.getTableHeader().setReorderingAllowed(false);
        
        itemouttable.setRowSorter(outsorter);
        
        itemoutscrollpane = new JScrollPane();
        itemoutscrollpane.getViewport().add(itemouttable, null);
        itemoutscrollpane.setPreferredSize(new Dimension(450, 200));
        
        outwarehousecombobox = new JComboBox(warehouselist);
        
        outtypecombobox = new JComboBox(type);
        
        outwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outwarehousepanel.add(new JLabel(" Gudang : "));
        outwarehousepanel.add(outwarehousecombobox);
        outwarehousepanel.add(new JLabel(" ~ "));
        outwarehousepanel.add(outtypecombobox);
        
        UtilDateModel model3 = new UtilDateModel();
        model3.setSelected(true);
        JDatePanelImpl datePanel3 = new JDatePanelImpl(model3);
        JDatePickerImpl datePicker3 = new JDatePickerImpl(datePanel3);
        
        outdatepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outdatepanel.add(new JLabel(" Tanggal Mulai : "));
        outdatepanel.add(datePicker3);
        
        UtilDateModel model4 = new UtilDateModel();
        model4.setSelected(true);
        JDatePanelImpl datePanel4 = new JDatePanelImpl(model4);
        JDatePickerImpl datePicker4 = new JDatePickerImpl(datePanel4);
        
        outdatepanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outdatepanel2.add(new JLabel(" Tanggal Akhir : "));
        outdatepanel2.add(datePicker4);
        
        outbutton = new MyButton("Unggah ke Excel");
        outbuttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outbuttonpanel.add(outbutton);
        
        itemoutpanel.add(outfilterpanel);
        itemoutpanel.add(itemoutscrollpane);
        itemoutpanel.add(outwarehousepanel);
        itemoutpanel.add(outdatepanel);
        itemoutpanel.add(outdatepanel2);
        itemoutpanel.add(outbuttonpanel);
        
        add(iteminpanel);
        add(itemoutpanel);
        
        inbutton.addActionListener((ActionEvent e) -> {
            
            int [] selected = itemintable.getSelectedRows();
            
            if(selected.length == 0){
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
            else{
                ArrayList<Integer> selectedid = new ArrayList<Integer>();
                
                for(int s : selected){
                    selectedid.add(Integer.parseInt(itemintable.getValueAt(s, 0).toString()));
                }
                
                try{
                    if(intypecombobox.getSelectedItem().equals("Laporan Stok")){
                        exportController.inexportstock(selectedid, inwarehousecombobox.getSelectedItem().toString());
                    }
                    else{
                        exportController.inexportgeneral(selectedid, 
                                inwarehousecombobox.getSelectedItem().toString(),
                                (Date)datePicker1.getModel().getValue(), (Date)datePicker2.getModel().getValue());
                    }
                    JOptionPane.showMessageDialog(null, "Data berhasil di unggah ke Excel");
                }
                catch(Exception exp){
                    JOptionPane.showMessageDialog(null, "Gagal mengunggah data");
                }
            }
        });
        
        outbutton.addActionListener((ActionEvent e) -> {
            int [] selected = itemouttable.getSelectedRows();
            
            if(selected.length == 0){
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
            else{
                ArrayList<Integer> selectedid = new ArrayList<Integer>();
                
                for(int s : selected){
                    selectedid.add(Integer.parseInt(itemouttable.getValueAt(s, 0).toString()));
                }
                
                try{
                    if(outtypecombobox.getSelectedItem().equals("Laporan Stok")){
                        exportController.outexportstock(selectedid, outwarehousecombobox.getSelectedItem().toString());

                    }
                    else{
                        exportController.outexportgeneral(selectedid, 
                                outwarehousecombobox.getSelectedItem().toString(),
                                (Date)datePicker3.getModel().getValue(), (Date)datePicker4.getModel().getValue());
                    }
                    JOptionPane.showMessageDialog(null, "Data berhasil di unggah ke Excel");
                }
                catch(Exception exp){
                    JOptionPane.showMessageDialog(null, "Gagal mengunggah data");
                }
            }
        });
    }
    
    private void innewFilter() {
        RowFilter<ItemInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(intextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        insorter.setRowFilter(rf);
    }
    
    private void outnewFilter() {
        RowFilter<ItemOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(outtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        outsorter.setRowFilter(rf);
    }
}
