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
import controller.ItemInController;
import controller.ReportController;
import controller.WarehouseController;
import entity.Item_In;
import entity.Warehouse;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
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
import tablemodel.ItemInTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ReportInTab extends JPanel{
    
    ItemInController iteminController;
    WarehouseController warehouseController;
    ReportController reportController;
    
    JPanel reportinlistleftpanel;
    JPanel reportinlistrightpanel;
    
    MyLabel reportininvoicepurchaselabel;
    MyLabel reportininvoicewarehouselabel;
    MyLabel reportinproductionlabel;
    
    MyLabel creportininvoicepurchaselabel;
    MyLabel creportininvoicewarehouselabel;
    MyLabel creportinproductionlabel;
    
    JPanel reportinsearchpanel;
    JPanel reportindetailpanel;
    JPanel reportindetailpanel2;
    JPanel reportinwarehousepanel;
    JPanel reportinactionpanel;
    
    JScrollPane reportinscrollpane;
    JScrollPane reportinscrollpane2;
    JScrollPane reportinscrollpane3;
    
    JTable itemintable;
    
    MyTextField reportinsearchtextfield;
    
    MyButton reportinsearchbutton;
    MyButton reportinuncheckedbutton;
    
    ItemInTableModel iitm;
    
    TableRowSorter<ItemInTableModel> sorter;
    
    JComboBox warehousecombobox;
    
    ArrayList<Item_In> iteminlist;
    
    public ReportInTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        iteminController = new ItemInController();
        warehouseController = new WarehouseController();
        reportController = new ReportController();
        
        reportininvoicepurchaselabel = new MyLabel("");
        reportinproductionlabel = new MyLabel("");
        reportininvoicewarehouselabel = new MyLabel("");
        
        creportininvoicepurchaselabel = new MyLabel("");
        creportinproductionlabel = new MyLabel("");
        creportininvoicewarehouselabel = new MyLabel("");
        
        reportinscrollpane = new JScrollPane();
        reportinscrollpane2 = new JScrollPane();
        reportinscrollpane3 = new JScrollPane();
        
        reportinlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportinlistrightpanel = new JPanel();
        reportinsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportindetailpanel = new JPanel();
        reportindetailpanel2 = new JPanel();
        reportinactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        reportinlistrightpanel.setLayout(new BoxLayout(reportinlistrightpanel, BoxLayout.Y_AXIS));
        
        reportindetailpanel.setLayout(new BoxLayout(reportindetailpanel, BoxLayout.Y_AXIS));
        reportindetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        reportindetailpanel.setPreferredSize(new Dimension(350, 100));
        reportindetailpanel.add(reportininvoicepurchaselabel);
        reportindetailpanel.add(reportininvoicewarehouselabel);
        reportindetailpanel.add(reportinproductionlabel);
        
        reportindetailpanel2.setLayout(new BoxLayout(reportindetailpanel2, BoxLayout.Y_AXIS));
        reportindetailpanel2.setBorder(new EmptyBorder(0, 10, 0, 25));
        reportindetailpanel2.setPreferredSize(new Dimension(350, 100));
        reportindetailpanel2.add(creportininvoicepurchaselabel);
        reportindetailpanel2.add(creportininvoicewarehouselabel);
        reportindetailpanel2.add(creportinproductionlabel);
        
        reportinsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari barang "));
        reportinactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        reportinwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportinwarehousepanel.setBorder(BorderFactory.createTitledBorder(" Gudang "));
        Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
        warehousecombobox = new JComboBox(warehouselist);
        warehousecombobox.setSize(300, warehousecombobox.getHeight());
        reportinwarehousepanel.add(warehousecombobox);
        
        warehousecombobox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String warehouse = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(itemintable.getValueAt(itemintable.getSelectedRow(), 0).toString());

                    if(!warehouse.equals("null")){
                        int ipnum = reportController.getUncheckedInvoicePurchase(warehouse, itemid);
                        reportininvoicepurchaselabel.setText("Faktur pembelian yang belum diperiksa : " + ipnum);

                        int pronum = reportController.getUncheckedProductionIn(warehouse, itemid);
                        reportinproductionlabel.setText("Produksi yang belum diperiksa : " + pronum);

                        int warnum = reportController.getUncheckedInvoiceWarehouseIn(warehouse, itemid);
                        reportininvoicewarehouselabel.setText("Faktur gudang yang belum diperiksa : " + warnum);

                        int ipnum2 = reportController.getCheckedInvoicePurchase(warehouse, itemid);
                        creportininvoicepurchaselabel.setText("Faktur pembelian yang sudah diperiksa : " + ipnum2);

                        int pronum2 = reportController.getCheckedProductionIn(warehouse, itemid);
                        creportinproductionlabel.setText("Produksi yang sudah diperiksa : " + pronum2);

                        int warnum2 = reportController.getCheckedInvoiceWarehouseIn(warehouse, itemid);
                        creportininvoicewarehouselabel.setText("Faktur gudang yang sudah diperiksa : " + warnum2);
                    }
                    else{
                        reportininvoicepurchaselabel.setText("");
                        reportinproductionlabel.setText("");
                        reportininvoicewarehouselabel.setText("");

                        creportininvoicepurchaselabel.setText("");
                        creportinproductionlabel.setText("");
                        creportininvoicewarehouselabel.setText("");
                    }
                }
                catch(Exception exp){
                    
                }
            }
        });
        
        iteminlist = iteminController.getItemList();
        
        iitm = new ItemInTableModel(iteminlist);
        sorter = new TableRowSorter<ItemInTableModel>(iitm);
        sorter.setSortsOnUpdates(true);
        
        itemintable = new JTable(iitm);
        itemintable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemintable.getTableHeader().setReorderingAllowed(false);
        itemintable.setRowHeight(itemintable.getRowHeight() + 10);
        
        itemintable.setRowSorter(sorter);
        
        reportinscrollpane.getViewport().add(itemintable, null);
        reportinscrollpane.setPreferredSize(new Dimension(600, 420));
        
        reportinscrollpane2.getViewport().add(reportindetailpanel, null);
        reportinscrollpane2.setBorder(BorderFactory.createTitledBorder(" Faktur yang belum diperiksa "));
        
        reportinscrollpane3.getViewport().add(reportindetailpanel2, null);
        reportinscrollpane3.setBorder(BorderFactory.createTitledBorder(" Faktur yang sudah diperiksa "));
        
        reportinsearchtextfield = new MyTextField("", 250);
        
        reportinsearchtextfield.getDocument().addDocumentListener(
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
        
        reportinsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        reportinuncheckedbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Lihat Laporan", 110, 28);
        
        reportinactionpanel.add(reportinuncheckedbutton);
        
        add(reportinlistleftpanel);
        add(reportinlistrightpanel);
        
        reportinlistleftpanel.add(reportinscrollpane);
        
        reportinlistrightpanel.add(reportinsearchpanel);
        reportinlistrightpanel.add(reportinwarehousepanel);
        reportinlistrightpanel.add(reportinscrollpane2);
        reportinlistrightpanel.add(reportinscrollpane3);
        reportinlistrightpanel.add(reportinactionpanel);
        
        reportinsearchpanel.add(reportinsearchtextfield);
        reportinsearchpanel.add(reportinsearchbutton);
        
        itemintable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    String warehouse = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(itemintable.getValueAt(itemintable.getSelectedRow(), 0).toString());

                    if(!warehouse.equals("null")){
                        int ipnum = reportController.getUncheckedInvoicePurchase(warehouse, itemid);
                        reportininvoicepurchaselabel.setText("Faktur pembelian yang belum diperiksa : " + ipnum);

                        int pronum = reportController.getUncheckedProductionIn(warehouse, itemid);
                        reportinproductionlabel.setText("Produksi yang belum diperiksa : " + pronum);

                        int warnum = reportController.getUncheckedInvoiceWarehouseIn(warehouse, itemid);
                        reportininvoicewarehouselabel.setText("Faktur gudang yang belum diperiksa : " + warnum);

                        int ipnum2 = reportController.getCheckedInvoicePurchase(warehouse, itemid);
                        creportininvoicepurchaselabel.setText("Faktur pembelian yang sudah diperiksa : " + ipnum2);

                        int pronum2 = reportController.getCheckedProductionIn(warehouse, itemid);
                        creportinproductionlabel.setText("Produksi yang sudah diperiksa : " + pronum2);

                        int warnum2 = reportController.getCheckedInvoiceWarehouseIn(warehouse, itemid);
                        creportininvoicewarehouselabel.setText("Faktur gudang yang sudah diperiksa : " + warnum2);
                    }
                    else{
                        reportininvoicepurchaselabel.setText("");
                        reportinproductionlabel.setText("");
                        reportininvoicewarehouselabel.setText("");

                        creportininvoicepurchaselabel.setText("");
                        creportinproductionlabel.setText("");
                        creportininvoicewarehouselabel.setText("");
                    }
                }
                catch(Exception exp){
                    
                }
            }
        });
        
        reportinuncheckedbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(itemintable.getValueAt(itemintable.getSelectedRow(), 0).toString());
                String warehouse = warehousecombobox.getSelectedItem().toString();
                
                Item_In ii = iteminController.getItem(id);
                Warehouse w = warehouseController.getWarehouse(warehouse);
                
                new ReportInListFrame("Laporan", w, ii, iteminlist, itemintable.getSelectedRow(), true, true);
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
    }
    
    private void newFilter() {
        RowFilter<ItemInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(reportinsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
