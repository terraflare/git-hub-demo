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
import controller.ItemOutController;
import controller.ReportController;
import controller.WarehouseController;
import entity.Item_Out;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.ItemOutTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
//public class ReportOutTab extends JTabbedPane{
public class ReportOutTab extends JPanel{

    ItemOutController itemoutController;
    WarehouseController warehouseController;
    ReportController reportController;
    
//    JPanel reportout, reportin, export;
    
    JPanel reportoutlistleftpanel;
    JPanel reportoutlistrightpanel;
    
    MyLabel reportoutinvoicesaleslabel;
    MyLabel reportoutinvoicewarehouselabel;
    MyLabel reportoutproductionlabel;
    MyLabel reportoutreturnsaleslabel;
    
    MyLabel creportoutinvoicesaleslabel;
    MyLabel creportoutinvoicewarehouselabel;
    MyLabel creportoutproductionlabel;
    MyLabel creportoutreturnsaleslabel;
    
    JPanel reportoutsearchpanel;
    JPanel reportoutdetailpanel;
    JPanel reportoutdetailpanel2;
    JPanel reportoutwarehousepanel;
    JPanel reportoutactionpanel;
    
    JScrollPane reportoutscrollpane;
    JScrollPane reportoutscrollpane2;
    JScrollPane reportoutscrollpane3;
    
    JTable itemouttable;
    
    MyTextField reportoutsearchtextfield;
    
    MyButton reportoutsearchbutton;
    MyButton reportoutuncheckedbutton;
    
    ItemOutTableModel iitm;
    
    TableRowSorter<ItemOutTableModel> sorter;
    
    JComboBox warehousecombobox;
    
    ArrayList<Item_Out> itemoutlist;
    
    public ReportOutTab(){
        super(new FlowLayout(FlowLayout.LEFT));
//        super();
        
//        reportout = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        reportin = new ReportInTab();
//        export = new ExportTab();
        
        itemoutController = new ItemOutController();
        warehouseController = new WarehouseController();
        reportController = new ReportController();
        
        reportoutinvoicesaleslabel = new MyLabel("");
        reportoutproductionlabel = new MyLabel("");
        reportoutinvoicewarehouselabel = new MyLabel("");
        reportoutreturnsaleslabel = new MyLabel("");
        
        creportoutinvoicesaleslabel = new MyLabel("");
        creportoutproductionlabel = new MyLabel("");
        creportoutinvoicewarehouselabel = new MyLabel("");
        creportoutreturnsaleslabel = new MyLabel("");
        
        reportoutscrollpane = new JScrollPane();
        reportoutscrollpane2 = new JScrollPane();
        reportoutscrollpane3 = new JScrollPane();
        
        reportoutlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportoutlistrightpanel = new JPanel();
        reportoutsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportoutdetailpanel = new JPanel();
        reportoutdetailpanel2 = new JPanel();
        reportoutactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        reportoutlistrightpanel.setLayout(new BoxLayout(reportoutlistrightpanel, BoxLayout.Y_AXIS));
        reportoutdetailpanel.setLayout(new BoxLayout(reportoutdetailpanel, BoxLayout.Y_AXIS));
        reportoutdetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        reportoutdetailpanel.setPreferredSize(new Dimension(350, 100));
        reportoutdetailpanel.add(reportoutinvoicesaleslabel);
        reportoutdetailpanel.add(reportoutinvoicewarehouselabel);
        reportoutdetailpanel.add(reportoutproductionlabel);
        reportoutdetailpanel.add(reportoutreturnsaleslabel);
        
        reportoutdetailpanel2.setLayout(new BoxLayout(reportoutdetailpanel2, BoxLayout.Y_AXIS));
        reportoutdetailpanel2.setBorder(new EmptyBorder(0, 10, 0, 25));
        reportoutdetailpanel2.setPreferredSize(new Dimension(350, 100));
        reportoutdetailpanel2.add(creportoutinvoicesaleslabel);
        reportoutdetailpanel2.add(creportoutinvoicewarehouselabel);
        reportoutdetailpanel2.add(creportoutproductionlabel);
        reportoutdetailpanel2.add(creportoutreturnsaleslabel);
        
        reportoutsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Barang "));
        reportoutactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        reportoutwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportoutwarehousepanel.setBorder(BorderFactory.createTitledBorder(" Gudang "));
        Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
        warehousecombobox = new JComboBox(warehouselist);
        warehousecombobox.setSize(300, warehousecombobox.getHeight());
        reportoutwarehousepanel.add(warehousecombobox);
        
        warehousecombobox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String warehouse = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());

                    if(!warehouse.equals("null")){
                        int isnum = reportController.getUncheckedInvoiceSales(warehouse, itemid);
                        reportoutinvoicesaleslabel.setText("Faktur penjualan yang belum diperiksa : " + isnum);

                        int pronum = reportController.getUncheckedProductionOut(warehouse, itemid);
                        reportoutproductionlabel.setText("Produksi yang belum diperiksa : " + pronum);

                        int warnum = reportController.getUncheckedInvoiceWarehouseOut(warehouse, itemid);
                        reportoutinvoicewarehouselabel.setText("Faktur gudang yang belum diperiksa : " + warnum);

                        int retnum = reportController.getUncheckedReturnSales(warehouse, itemid);
                        reportoutreturnsaleslabel.setText("Retur penjualan yang belum diperiksa : " + retnum);
                        
                        int isnum2 = reportController.getCheckedInvoiceSales(warehouse, itemid);
                        creportoutinvoicesaleslabel.setText("Faktur penjualan yang sudah diperiksa : " + isnum2);

                        int pronum2 = reportController.getCheckedProductionOut(warehouse, itemid);
                        creportoutproductionlabel.setText("Produksi yang sudah diperiksa : " + pronum2);

                        int warnum2 = reportController.getCheckedInvoiceWarehouseOut(warehouse, itemid);
                        creportoutinvoicewarehouselabel.setText("Faktur gudang yang sudah diperiksa : " + warnum2);
                        
                        int retnum2 = reportController.getCheckedReturnSales(warehouse, itemid);
                        creportoutreturnsaleslabel.setText("Retur penjualan yang sudah diperiksa : " + retnum2);
                    }
                    else{
                        reportoutinvoicesaleslabel.setText("");
                        reportoutproductionlabel.setText("");
                        reportoutinvoicewarehouselabel.setText("");
                        reportoutreturnsaleslabel.setText("");

                        creportoutinvoicesaleslabel.setText("");
                        creportoutproductionlabel.setText("");
                        creportoutinvoicewarehouselabel.setText("");
                        creportoutreturnsaleslabel.setText("");
                    }
                }
                catch(Exception exp){
                            
                }
            }
        });
        
        itemoutlist = itemoutController.getItemList();
        
        iitm = new ItemOutTableModel(itemoutlist);
        sorter = new TableRowSorter<ItemOutTableModel>(iitm);
        sorter.setSortsOnUpdates(true);
        
        itemouttable = new JTable(iitm);
        itemouttable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemouttable.getTableHeader().setReorderingAllowed(false);
        itemouttable.setRowHeight(itemouttable.getRowHeight() + 10);
        
        itemouttable.setRowSorter(sorter);
        
        reportoutscrollpane.getViewport().add(itemouttable, null);
        reportoutscrollpane.setPreferredSize(new Dimension(600, 420));
        
        reportoutscrollpane2.getViewport().add(reportoutdetailpanel, null);
        reportoutscrollpane2.setBorder(BorderFactory.createTitledBorder(" Faktur yang belum diperiksa "));
        
        reportoutscrollpane3.getViewport().add(reportoutdetailpanel2, null);
        reportoutscrollpane3.setBorder(BorderFactory.createTitledBorder(" Faktur yang sudah diperiksa "));
        
        reportoutsearchtextfield = new MyTextField("", 250);
        
        reportoutsearchtextfield.getDocument().addDocumentListener(
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
        
        reportoutsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        reportoutuncheckedbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Lihat Laporan", 110, 28);
        
        reportoutactionpanel.add(reportoutuncheckedbutton);
        
//        reportout.add(reportoutlistleftpanel);
//        reportout.add(reportoutlistrightpanel);
        
        add(reportoutlistleftpanel);
        add(reportoutlistrightpanel);
        
        reportoutlistleftpanel.add(reportoutscrollpane);
        
        reportoutlistrightpanel.add(reportoutsearchpanel);
        reportoutlistrightpanel.add(reportoutwarehousepanel);
        reportoutlistrightpanel.add(reportoutscrollpane2);
        reportoutlistrightpanel.add(reportoutscrollpane3);
        reportoutlistrightpanel.add(reportoutactionpanel);
        
        reportoutsearchpanel.add(reportoutsearchtextfield);
        reportoutsearchpanel.add(reportoutsearchbutton);
        
//        addTab("Laporan Barang Produksi", reportout);
//        addTab("Laporan Barang Curah", reportin);
//        addTab("Unggah ke Excel", export);
        
        itemouttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    String warehouse = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());

                    if(!warehouse.equals("null")){
                        int isnum = reportController.getUncheckedInvoiceSales(warehouse, itemid);
                        reportoutinvoicesaleslabel.setText("Faktur penjualan yang belum diperiksa : " + isnum);

                        int pronum = reportController.getUncheckedProductionOut(warehouse, itemid);
                        reportoutproductionlabel.setText("Produksi yang belum diperiksa : " + pronum);

                        int warnum = reportController.getUncheckedInvoiceWarehouseOut(warehouse, itemid);
                        reportoutinvoicewarehouselabel.setText("Faktur gudang yang belum diperiksa : " + warnum);

                        int retnum = reportController.getUncheckedReturnSales(warehouse, itemid);
                        reportoutreturnsaleslabel.setText("Retur penjualan yang belum diperiksa : " + retnum);
                        
                        int isnum2 = reportController.getCheckedInvoiceSales(warehouse, itemid);
                        creportoutinvoicesaleslabel.setText("Faktur penjualan yang sudah diperiksa : " + isnum2);

                        int pronum2 = reportController.getCheckedProductionOut(warehouse, itemid);
                        creportoutproductionlabel.setText("Produksi yang sudah diperiksa : " + pronum2);

                        int warnum2 = reportController.getCheckedInvoiceWarehouseOut(warehouse, itemid);
                        creportoutinvoicewarehouselabel.setText("Faktur gudang yang sudah diperiksa : " + warnum2);
                        
                        int retnum2 = reportController.getCheckedReturnSales(warehouse, itemid);
                        creportoutreturnsaleslabel.setText("Retur penjualan yang sudah diperiksa : " + retnum2);
                    }
                    else{
                        reportoutinvoicesaleslabel.setText("");
                        reportoutproductionlabel.setText("");
                        reportoutinvoicewarehouselabel.setText("");
                        reportoutreturnsaleslabel.setText("");

                        creportoutinvoicesaleslabel.setText("");
                        creportoutproductionlabel.setText("");
                        creportoutinvoicewarehouselabel.setText("");
                        creportoutreturnsaleslabel.setText("");
                    }
                }
                catch(Exception exp){
                    
                }
            }
        });
        
        reportoutuncheckedbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());
                String warehouse = warehousecombobox.getSelectedItem().toString();
                
                Item_Out io = itemoutController.getItem(id);
                Warehouse w = warehouseController.getWarehouse(warehouse);
                
                new ReportOutListFrame("Laporan", w, io, itemoutlist, itemouttable.getSelectedRow(), true, true);
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
    }
    
    private void newFilter() {
        RowFilter<ItemOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(reportoutsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
