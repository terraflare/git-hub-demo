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
import controller.ItemExchangeController;
import controller.ItemOutController;
import controller.ReturnSalesController;
import controller.WarehouseController;
import entity.ItemExchange;
import entity.Item_Out;
import entity.ReturnSales;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tablemodel.ItemOutTableModel;
import tablemodel.ReturnSalesTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ReturnSalesTab extends JPanel{
    
    ReturnSalesController returnsalesController;
    ItemOutController itemController;
    WarehouseController warehouseController;
    ItemExchangeController itemexchangeController;
    
    JPanel returnsaleslistleftpanel;
    JPanel returnsaleslistrightpanel;
    
    JPanel returnsalessearchpanel;
    JPanel returnsalesactionpanel;
    
    JPanel returnsalesexchangepanel;
    
    JScrollPane returnsalesscrollpane;
    
    JTable returnsalestable;
    
    MyTextField returnsalessearchtextfield;
    
    MyButton returnsalessearchbutton;
    MyButton returnsalesaddbutton;
    MyButton returnsalesdeletebutton;
    MyButton returnsalesexchangebutton;

    ReturnSalesTableModel istm;
    
    TableRowSorter<ReturnSalesTableModel> sorter;
    TableRowSorter<ItemOutTableModel> sorter2;
    
    JPanel returnsalesnotepanel;
    JScrollPane returnsalesnotepane;
    JPanel returnsalesitempanel;
    JScrollPane returnsalesitempane;
    
    MyTextField filtertf;
    JScrollPane returnsalesstatuspane;
    MyLabel returnsalesstatuslabel;
    JPanel returnsalesstatuspanel;
    
    public ReturnSalesTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        returnsalesController = new ReturnSalesController();
        itemController = new ItemOutController();
        warehouseController = new WarehouseController();
        itemexchangeController = new ItemExchangeController();
        
        returnsalesscrollpane = new JScrollPane();
        
        returnsaleslistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnsaleslistrightpanel = new JPanel();
        returnsalessearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnsalesactionpanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        returnsalesexchangepanel = new JPanel(new GridLayout(0, 1, 5, 5));
        returnsalesexchangepanel.setBorder(BorderFactory.createTitledBorder(" Tambah Tukar Guling "));
        
        returnsaleslistrightpanel.setLayout(new BoxLayout(returnsaleslistrightpanel, BoxLayout.Y_AXIS));
        
        returnsalessearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Retur "));
        returnsalesactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        istm = new ReturnSalesTableModel(returnsalesController.getReturnSalesList());
        
        returnsalestable = new JTable(istm);
        returnsalestable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        returnsalestable.setRowHeight(returnsalestable.getRowHeight() + 10);
        sorter = new TableRowSorter<ReturnSalesTableModel>(istm);
        sorter.setSortsOnUpdates(true);
        
        returnsalestable.getTableHeader().setReorderingAllowed(false);
        
        returnsalestable.setRowSorter(sorter);
        
        returnsalesscrollpane.getViewport().add(returnsalestable, null);
        returnsalesscrollpane.setPreferredSize(new Dimension(600, 420));
        
        returnsalessearchtextfield = new MyTextField("", 250);
        
        returnsalessearchtextfield.getDocument().addDocumentListener(
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
        
        returnsalessearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        returnsalesaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        returnsalesdeletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        returnsalesexchangebutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        
        returnsalesactionpanel.add(returnsalesaddbutton);
        returnsalesactionpanel.add(returnsalesdeletebutton);
        
        returnsalesexchangepanel.add(returnsalesexchangebutton);
        
        add(returnsaleslistleftpanel);
        add(returnsaleslistrightpanel);
        
        returnsalesnotepanel = new JPanel();
        returnsalesnotepanel.setLayout(new BoxLayout(returnsalesnotepanel, BoxLayout.Y_AXIS));
        returnsalesnotepanel.setPreferredSize(new Dimension(350, 50));
        returnsalesnotepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        returnsalesnotepane = new JScrollPane();
        returnsalesnotepane.getViewport().add(returnsalesnotepanel, null);
        returnsalesnotepane.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        returnsalesitempanel = new JPanel();
        returnsalesitempanel.setLayout(new BoxLayout(returnsalesitempanel, BoxLayout.Y_AXIS));
        returnsalesitempanel.setPreferredSize(new Dimension(350, 100));
        returnsalesitempanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        returnsalesitempane = new JScrollPane();
        returnsalesitempane.getViewport().add(returnsalesitempanel, null);
        returnsalesitempane.setBorder(BorderFactory.createTitledBorder("Barang"));
        
        returnsalesstatuslabel = new MyLabel("");
        returnsalesstatuspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnsalesstatuspanel.setPreferredSize(new Dimension(350, 30));
        returnsalesstatuspanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        returnsalesstatuspanel.add(returnsalesstatuslabel);
        returnsalesstatuspane = new JScrollPane();
        returnsalesstatuspane.getViewport().add(returnsalesstatuspanel, null);
        returnsalesstatuspane.setBorder(BorderFactory.createTitledBorder("Status"));
        
        returnsaleslistleftpanel.add(returnsalesscrollpane);
        
        returnsaleslistrightpanel.add(returnsalessearchpanel);
        returnsaleslistrightpanel.add(returnsalesstatuspane);
        returnsaleslistrightpanel.add(returnsalesitempane);
        returnsaleslistrightpanel.add(returnsalesnotepane);
        returnsaleslistrightpanel.add(returnsalesexchangepanel);
        returnsaleslistrightpanel.add(returnsalesactionpanel);
        
        returnsalessearchpanel.add(returnsalessearchtextfield);
        returnsalessearchpanel.add(returnsalessearchbutton);
        
        returnsalesexchangebutton.addActionListener((ActionEvent e) -> {
            UtilDateModel model = new UtilDateModel();
            model.setSelected(true);
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
            
            final JComponent[] inputs = new JComponent[] {
                new JLabel("Tanggal"),
                datePicker,
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Tukar Guling");
            dialog.show();
            
            try{
                if(pane.getValue() == null){

                }
                else if(pane.getValue().toString().equals("0")){
                    
                    int id = Integer.parseInt(returnsalestable.getValueAt(returnsalestable.getSelectedRow(), 0).toString());
                    
                    ItemExchange ie = itemexchangeController.addItemExchange(id, GlobalFields.USER, 
                            (Date)datePicker.getModel().getValue());
                    
                    JPanel panel = (JPanel)this.getParent().getParent();
                    panel.remove(panel.getComponent(1));
                    panel.add(new ItemExchangeDetailTab(ie.getId()));
                    panel.revalidate();
                    panel.repaint();
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan tukar guling ~ Tidak ada retur produksi yang dipilih");
            }
        });
        
        returnsalesaddbutton.addActionListener((ActionEvent e) -> {
            
            JScrollPane itemscrollpane = new JScrollPane();

            ItemOutTableModel iotm = new ItemOutTableModel(itemController.getActiveItemList());
            
            JTable itemtable = new JTable(iotm);
            itemtable.getTableHeader().setReorderingAllowed(false);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sorter2 = new TableRowSorter<ItemOutTableModel>(iotm);
            
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
            JComboBox warehouse = new JComboBox(warehouselist);
            
            JLabel warehouselabel = new JLabel("Gudang");
            
            JTextArea note = new JTextArea(5, 15);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            JLabel notelabel = new JLabel("Catatan");
            
            JCheckBox cncb = new JCheckBox();
            JCheckBox tgcb = new JCheckBox();
            
            lowerpanel.add(quantitylabel);
            lowerpanel.add(quantitysacktf);
            lowerpanel.add(new JLabel("karung"));
            lowerpanel.add(quantityunittf);
            lowerpanel.add(new JLabel("unit"));
            lowerpanel.add(datePicker);
            lowerpanel.add(warehouselabel);
            lowerpanel.add(warehouse);
            lowerpanel.add(cncb);
            lowerpanel.add(new JLabel("Masuk Ke CN"));
            lowerpanel.add(tgcb);
            lowerpanel.add(new JLabel("Tukar Guling"));
            upperpanel.add(filterlabel);
            upperpanel.add(filtertf);
            upperpanel.add(itemsearchbutton);
            
            final JComponent[] inputs = new JComponent[] {
                            upperpanel, itemscrollpane, lowerpanel, notelabel, sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Retur Penjualan");
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
                    
                    if(cncb.isSelected() == true){
                        JTextField cntf1 = new JTextField();
                        JTextField cntf2 = new JTextField();

                        final JComponent[] inputs2 = new JComponent[] {
                            new JLabel("Jumlah Yang Diharapkan"),
                            cntf1,
                            new JLabel("Jumlah Sebenarnya"),
                            cntf2,
                        };
                        JOptionPane pane2 = new JOptionPane();

                        pane2.setMessage(inputs2);
                        pane2.setOptionType(JOptionPane.OK_CANCEL_OPTION);

                        JDialog dialog2 = pane2.createDialog(null, "Masukan Jumlah CN");
                        dialog2.show();
                        
                        if(pane2.getValue() == null){
                    
                        }
                        else if(pane2.getValue().toString().equals("0")){
                            int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                            int quantitysack = Integer.parseInt(quantitysacktf.getText());
                            float quantityunit = Float.parseFloat(quantityunittf.getText());

                            if(quantitysack < 0 || quantityunit < 0){
                                JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak dapat kurang dari 0");
                            }
                            else{
                                returnsalesController.addReturnSales(istm, (Date)datePicker.getModel().getValue(),
                                        GlobalFields.USER, quantitysack, quantityunit,
                                        itemid, String.valueOf(warehouse.getSelectedItem()), note.getText(), tgcb.isSelected(), 
                                        Integer.parseInt(cntf1.getText()), Integer.parseInt(cntf2.getText()));
                            }
                        }
                    }
                    else{
                        int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                        int quantitysack = Integer.parseInt(quantitysacktf.getText());
                        float quantityunit = Float.parseFloat(quantityunittf.getText());

                        if(quantitysack < 0 || quantityunit < 0){
                            JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ jumlah barang tidak dapat kurang dari 0");
                        }
                        else{
                            returnsalesController.addReturnSales(istm, (Date)datePicker.getModel().getValue(),
                                    GlobalFields.USER, quantitysack, quantityunit,
                                    itemid, String.valueOf(warehouse.getSelectedItem()), note.getText(), tgcb.isSelected() );
                        }
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan faktur ~ tidak ada barang atau gudang yang dipilih");
            }
        });
        
        returnsalesdeletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(returnsalestable.getValueAt(returnsalestable.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                    jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                    jop.setMessage("Hapus retur ini?");
                    JDialog dialog = jop.createDialog(null, "Hapus retur");
                    dialog.show();
                    if(jop.getValue() == null){
                        
                    }
                    else if(jop.getValue().toString().equals("0")){
                        boolean result = returnsalesController.deleteReturnSales(id);
                        
                        if(result == GlobalFields.FAIL){
                            JOptionPane.showMessageDialog(null, "Gagal menghapus retur ~ jumlah barang tidak mencukupi");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Retur dihapus");

                            istm.deleteRow(id);
                            
                            returnsalesstatuslabel.setText("");
                            
                            returnsalesnotepanel.removeAll();
                            returnsalesnotepanel.revalidate();
                            returnsalesnotepanel.repaint();
                            
                            returnsalesitempanel.removeAll();
                            returnsalesnotepanel.revalidate();
                            returnsalesnotepanel.repaint();
                        }
                    }
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        returnsalestable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                returnsalesnotepanel.removeAll();
                
                ReturnSales returnsales = returnsalesController.getReturnSales(Integer.parseInt(returnsalestable.getValueAt(returnsalestable.getSelectedRow(), 0).toString()));
                
                Item_Out item = returnsales.getItem();
                
                if(returnsales.isStatus() == GlobalFields.CHECKED){
                    returnsalesstatuslabel.setText("SUDAH DIPERIKSA");
                    returnsalesdeletebutton.setEnabled(false);
                }
                else{
                    returnsalesstatuslabel.setText("BELUM DIPERIKSA");
                    returnsalesdeletebutton.setEnabled(true);
                }
                
                returnsalesnotepanel.add(new JLabel(returnsales.getNote()));
                
                returnsalesnotepanel.revalidate();
                returnsalesnotepanel.repaint();
                
                returnsalesitempanel.removeAll();
                
                returnsalesitempanel.add(new JLabel("ID : " + item.getId()));
                returnsalesitempanel.add(new JLabel("Nama : " + item.getName()));
                returnsalesitempanel.add(new JLabel("Merek : " + item.getMerk().getName()));
                returnsalesitempanel.add(new JLabel("Kemasan : " + item.getPackaging().getAmount() + " " + item.getPackaging().getUnit()));
                returnsalesitempanel.add(new JLabel("Jumlah unit per karung : " + item.getSacksize()));
                
                String unit = String.format("%.1f", returnsales.getQuantity()%item.getSacksize());
                
                returnsalesitempanel.add(new JLabel("Jumlah : " + (int)returnsales.getQuantity()/item.getSacksize() + " karung " + unit + " unit"));
                
                returnsalesitempanel.revalidate();
                returnsalesitempanel.repaint();
            }
        });
    }
    
    private void newFilter() {
        RowFilter<ReturnSalesTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(returnsalessearchtextfield.getText(), 1);
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
