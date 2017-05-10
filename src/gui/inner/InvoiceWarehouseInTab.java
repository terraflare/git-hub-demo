/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyLabel;
import MyGUI.MyTextField;
import controller.InvoiceWarehouseInController;
import controller.ItemInController;
import controller.WarehouseController;
import entity.InvoiceWarehouseIn;
import entity.Item_In;
import gui.frame.BasicInvoicePanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tablemodel.InvoiceWarehouseInTableModel;
import tablemodel.ItemInTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseInTab extends BasicInvoicePanel{
    
    InvoiceWarehouseInController invoicewarehouseinController;
    ItemInController itemController;
    WarehouseController warehouseController;
    
    InvoiceWarehouseInTableModel iwitm;
    
    TableRowSorter<InvoiceWarehouseInTableModel> sorter;
    TableRowSorter<ItemInTableModel> sorter2;
    
    MyTextField filtertf;
    
    public InvoiceWarehouseInTab(){
        super();
        
        invoicewarehouseinController = new InvoiceWarehouseInController();
        itemController = new ItemInController();
        warehouseController = new WarehouseController();
        
        iwitm = new InvoiceWarehouseInTableModel(invoicewarehouseinController.getInvoiceWarehouseInList());
        
        maintable.setModel(iwitm);
        
        sorter = new TableRowSorter<InvoiceWarehouseInTableModel>(iwitm);
        sorter.setSortsOnUpdates(true);
        
        maintable.setRowSorter(sorter);
        
        searchtextfield.getDocument().addDocumentListener(
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
        
        addbutton.addActionListener((ActionEvent e) -> {
            
            JScrollPane itemscrollpane = new JScrollPane();

            ItemInTableModel iitm = new ItemInTableModel(itemController.getActiveItemList());
            
            JTable itemtable = new JTable(iitm);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            itemtable.getTableHeader().setReorderingAllowed(false);
            sorter2 = new TableRowSorter<ItemInTableModel>(iitm);
            
            sorter2.setSortsOnUpdates(true);
            
            itemtable.setRowSorter(sorter2);
            itemscrollpane.getViewport().add(itemtable, null);
            itemscrollpane.setPreferredSize(new Dimension(600, 350));
            
            JPanel lowerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel upperpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            MyTextField quantitytf = new MyTextField("0", 150);
            JLabel quantitylabel = new JLabel("Jumlah");
            
            filtertf = new MyTextField("", 250);
            JLabel filterlabel = new JLabel("Cari");
            
            filtertf.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    newFilter2();
                }
                public void insertUpdate(DocumentEvent e) {
                    newFilter2();
                }
                public void removeUpdate(DocumentEvent e) {
                    newFilter2();
                }
            });
            
            MyButton itemsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
            
            UtilDateModel model = new UtilDateModel();
            model.setSelected(true);
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
            
            Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
            JComboBox origin = new JComboBox(warehouselist);
            JComboBox destination = new JComboBox(warehouselist);
            
            JLabel originlabel = new JLabel("Asal");
            JLabel destinationlabel = new JLabel("Tujuan");
            
            JTextArea note = new JTextArea(5, 15);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            JLabel notelabel = new JLabel("Catatan");
            
            lowerpanel.add(quantitylabel);
            lowerpanel.add(quantitytf);
            lowerpanel.add(datePicker);
            lowerpanel.add(originlabel);
            lowerpanel.add(origin);
            lowerpanel.add(destinationlabel);
            lowerpanel.add(destination);
            upperpanel.add(filterlabel);
            upperpanel.add(filtertf);
            upperpanel.add(itemsearchbutton);
            
            final JComponent[] inputs = new JComponent[] {
                            upperpanel, itemscrollpane, lowerpanel, notelabel, sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Faktur Gudang (Curah)");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(quantitytf.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    String sorigin = String.valueOf(origin.getSelectedItem());
                    String sdestination = String.valueOf(destination.getSelectedItem());
                    int quantity = Integer.parseInt(quantitytf.getText());
                    
                    if(quantity < 0){
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak dapat kurang dari 0");
                    }
                    else if(sorigin.equals(sdestination)){
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~  gudang asal dan tujuan sama");
                    }
                    else{
                        boolean result = invoicewarehouseinController.addInvoiceWarehouseIn(iwitm, (Date)datePicker.getModel().getValue(),
                                GlobalFields.USER, quantity,
                                itemid, sorigin, sdestination, 
                                note.getText());

                        if(result == GlobalFields.FAIL){
                            JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak mencukupi");
                        }
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ tidak ada barang atau gudang yang dipilih");
            }
        });
        
        deletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus faktur ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Faktur");
                dialog.show();
                if(jop.getValue() == null){

                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = invoicewarehouseinController.deleteInvoiceWarehouseIn(id);

                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus faktur ~ jumlah barang tidak mencukupi");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Faktur dihapus");

                        iwitm.deleteRow(id);
                    }
                }
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        maintable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notepanel.removeAll();
                
                InvoiceWarehouseIn invoicewarehousein = invoicewarehouseinController.getInvoiceWarehouseIn(Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString()));
                
                Item_In item = invoicewarehousein.getItem();
                
                 if(invoicewarehousein.isStatus() == GlobalFields.CHECKED){
                    statuslabel.setText("SUDAH DIPERIKSA");
                    deletebutton.setEnabled(false);
                }
                else{
                    statuslabel.setText("BELUM DIPERIKSA");
                    deletebutton.setEnabled(true);
                }
                
                notepanel.add(new JLabel(invoicewarehousein.getNote()));
                
                notepanel.revalidate();
                notepanel.repaint();
                
                itempanel.removeAll();
                
                itempanel.add(new JLabel("ID : " + item.getId()));
                itempanel.add(new JLabel("Nama : " + item.getName()));
                itempanel.add(new JLabel("Kualitas : " + item.getQuality().getCategory().getName() + " " + item.getQuality().getName()));
                itempanel.add(new JLabel("Jumlah : " + (int)invoicewarehousein.getQuantity()));
                
                itempanel.revalidate();
                itempanel.repaint();
            }
        });
    }
    
    private void newFilter() {
        RowFilter<InvoiceWarehouseInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    
    private void newFilter2() {
        RowFilter<ItemInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter2.setRowFilter(rf);
    }
}