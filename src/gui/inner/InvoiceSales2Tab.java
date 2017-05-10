/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.InvoiceSales2Controller;
import controller.ItemInController;
import controller.WarehouseController;
import entity.InvoiceSales2;
import entity.Item_In;
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
import tablemodel.InvoiceSales2TableModel;
import tablemodel.ItemInTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceSales2Tab extends BasicInvoicePanel{
    
    InvoiceSales2Controller invoicesales2Controller;
    ItemInController itemController;
    WarehouseController warehouseController;
    
    InvoiceSales2TableModel is2tm;
    
    TableRowSorter<InvoiceSales2TableModel> sorter;
    TableRowSorter<ItemInTableModel> sorter2;
    
    MyTextField filtertf;
    
    public InvoiceSales2Tab(){
        super();
        
        invoicesales2Controller = new InvoiceSales2Controller();
        itemController = new ItemInController();
        warehouseController = new WarehouseController();
        
        is2tm = new InvoiceSales2TableModel(invoicesales2Controller.getInvoiceSales2List());
        
        maintable.setModel(is2tm);
        
        sorter = new TableRowSorter<InvoiceSales2TableModel>(is2tm);
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
            
            itemtable.getTableHeader().setReorderingAllowed(false);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
            JComboBox warehouse = new JComboBox(warehouselist);
            
            JLabel warehouselabel = new JLabel("Gudang");
            
            JTextArea note = new JTextArea(5, 15);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            JLabel notelabel = new JLabel("Catatan");
            
            lowerpanel.add(quantitylabel);
            lowerpanel.add(quantitytf);
            lowerpanel.add(datePicker);
            lowerpanel.add(warehouselabel);
            lowerpanel.add(warehouse);
            upperpanel.add(filterlabel);
            upperpanel.add(filtertf);
            upperpanel.add(itemsearchbutton);
            
            final JComponent[] inputs = new JComponent[] {
                            upperpanel, itemscrollpane, lowerpanel, notelabel, sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Faktur Penjualan");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(quantitytf.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    int quantity = Integer.parseInt(quantitytf.getText());
                    String swarehouse = String.valueOf(warehouse.getSelectedItem());
                    
                    if(quantity < 0){
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak dapat kurang dari 0");
                    }
                    else{
                        boolean result = invoicesales2Controller.addInvoiceSales2(is2tm, (Date)datePicker.getModel().getValue(),
                                GlobalFields.USER, quantity,
                                itemid, swarehouse, note.getText());
                        
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
                    boolean result = invoicesales2Controller.deleteInvoiceSales2(id);

                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus faktur ~ jumlah barang tidak mencukupi");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Faktur dihapus");

                        is2tm.deleteRow(id);

                        statuslabel.setText("");

                        notepanel.removeAll();
                        notepanel.revalidate();
                        notepanel.repaint();

                        itempanel.removeAll();
                        itempanel.revalidate();
                        itempanel.repaint();
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
                InvoiceSales2 invoicesales2 = invoicesales2Controller.getInvoiceSales2(Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString()));
                
                Item_In item = invoicesales2.getItem();
                
                 if(invoicesales2.isStatus() == GlobalFields.CHECKED){
                    statuslabel.setText("SUDAH DIPERIKSA");
                    deletebutton.setEnabled(false);
                }
                else{
                    statuslabel.setText("BELUM DIPERIKSA");
                    deletebutton.setEnabled(true);
                }
                
                notepanel.removeAll();
                 
                notepanel.add(new JLabel(invoicesales2.getNote()));
                
                notepanel.revalidate();
                notepanel.repaint();
                
                itempanel.removeAll();
                
                itempanel.add(new JLabel("ID : " + item.getId()));
                itempanel.add(new JLabel("Nama : " + item.getName()));
                itempanel.add(new JLabel("Kualitas : " + item.getQuality().getCategory().getName() + " " + item.getQuality().getName()));
                itempanel.add(new JLabel("Jumlah : " + (int)invoicesales2.getQuantity() + " karung"));
                
                itempanel.revalidate();
                itempanel.repaint();
            }
        });
    }
    
    private void newFilter() {
        RowFilter<InvoiceSales2TableModel, Object> rf = null;
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