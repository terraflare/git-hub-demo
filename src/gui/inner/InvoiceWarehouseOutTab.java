/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.InvoiceWarehouseOutController;
import controller.ItemOutController;
import controller.WarehouseController;
import entity.InvoiceWarehouseOut;
import entity.Item_Out;
import gui.frame.BasicInvoicePanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tablemodel.InvoiceWarehouseOutTableModel;
import tablemodel.ItemOutTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseOutTab extends BasicInvoicePanel{
    InvoiceWarehouseOutController invoicewarehouseoutController;
    ItemOutController itemController;
    WarehouseController warehouseController;

    InvoiceWarehouseOutTableModel iwotm;
    
    TableRowSorter<InvoiceWarehouseOutTableModel> sorter;
    TableRowSorter<ItemOutTableModel> sorter2;
    
    MyTextField filtertf;
    
    public InvoiceWarehouseOutTab(){
        super();
        
        invoicewarehouseoutController = new InvoiceWarehouseOutController();
        itemController = new ItemOutController();
        warehouseController = new WarehouseController();
        
        iwotm = new InvoiceWarehouseOutTableModel(invoicewarehouseoutController.getInvoiceWarehouseOutList());
        
        maintable.setModel(iwotm);
        
        sorter = new TableRowSorter<InvoiceWarehouseOutTableModel>(iwotm);
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

            ItemOutTableModel iitm = new ItemOutTableModel(itemController.getActiveItemList());
            
            JTable itemtable = new JTable(iitm);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            itemtable.getTableHeader().setReorderingAllowed(false);
            sorter2 = new TableRowSorter<ItemOutTableModel>(iitm);
            
            sorter2.setSortsOnUpdates(true);
            
            itemtable.setRowSorter(sorter2);
            itemscrollpane.getViewport().add(itemtable, null);
            itemscrollpane.setPreferredSize(new Dimension(600, 350));
            
            JPanel lowerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel upperpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            MyTextField quantitysacktf = new MyTextField("0", 50);
            MyTextField quantityunittf = new MyTextField("0", 50);
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
            lowerpanel.add(quantitysacktf);
            lowerpanel.add(new JLabel("karung"));
            lowerpanel.add(quantityunittf);
            lowerpanel.add(new JLabel("unit"));
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
            
            JDialog dialog = pane.createDialog(null, "Faktur Gudang (Produksi)");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(quantitysacktf.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Jumlah karung tidak boleh kosong");
                }
                else if(quantityunittf.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Jumlah unit tidak boleh kosong");
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    String sorigin = String.valueOf(origin.getSelectedItem());
                    String sdestination = String.valueOf(destination.getSelectedItem());
                    float quantityunit = Float.parseFloat(quantityunittf.getText());
                    int quantitysack = Integer.parseInt(quantitysacktf.getText());
                    
                    if(quantityunit < 0 || quantitysack < 0){
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak dapat kurang dari 0");
                    }
                    else if(sorigin.equals(sdestination)){
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~  gudang asal dan tujuan sama");
                    }
                    else{
                        boolean result = invoicewarehouseoutController.addInvoiceWarehouseOut(iwotm, (Date)datePicker.getModel().getValue(),
                                GlobalFields.USER, quantitysack, quantityunit, 
                                itemid, sorigin, sdestination, 
                                note.getText());

                        if(result == GlobalFields.FAIL){
                            JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak mencukupi atau gudang asal dan tujuan sama");
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
                        boolean result = invoicewarehouseoutController.deleteInvoiceWarehouseOut(id);
                        
                        if(result == GlobalFields.FAIL){
                            JOptionPane.showMessageDialog(null, "Gagal menghapus faktur ~ faktur digunakan di tabel lain");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Faktur dihapus");

                            iwotm.deleteRow(id);
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
                
                InvoiceWarehouseOut invoicewarehouseout = invoicewarehouseoutController.getInvoiceWarehouseOut(Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString()));
                
                Item_Out item = invoicewarehouseout.getItem();
                
                 if(invoicewarehouseout.isStatus() == GlobalFields.CHECKED){
                    statuslabel.setText("SUDAH DIPERIKSA");
                    deletebutton.setEnabled(false);
                }
                else{
                    statuslabel.setText("BELUM DIPERIKSA");
                    deletebutton.setEnabled(true);
                }
                
                notepanel.add(new JLabel(invoicewarehouseout.getNote()));
                
                notepanel.revalidate();
                notepanel.repaint();
                
                itempanel.removeAll();
                
                itempanel.add(new JLabel("ID : " + item.getId()));
                itempanel.add(new JLabel("Nama : " + item.getName()));
                itempanel.add(new JLabel("Merek : " + item.getMerk().getName()));
                itempanel.add(new JLabel("Kemasan : " + item.getPackaging().getAmount() + " " + item.getPackaging().getUnit()));
                itempanel.add(new JLabel("Jumlah unit per karung : " + item.getSacksize()));
                
                String unit = String.format("%.1f", invoicewarehouseout.getQuantity()%item.getSacksize());
                
                itempanel.add(new JLabel("Jumlah : " + (int)invoicewarehouseout.getQuantity()/item.getSacksize() + " sacks " + unit + " units"));
                
                itempanel.revalidate();
                itempanel.repaint();
            }
        });
    }
    
    private void newFilter() {
        RowFilter<InvoiceWarehouseOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    
    private void newFilter2() {
        RowFilter<ItemOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter2.setRowFilter(rf);
    }
}
