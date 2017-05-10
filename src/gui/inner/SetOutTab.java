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
import controller.WarehouseController;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
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
//public class SetOutTab extends JTabbedPane {
  
public class SetOutTab extends JPanel {
//    JPanel setoutlist, setinlist, deletetab;
    
    ItemOutController itemoutController;
    WarehouseController warehouseController;
    
    JPanel setoutlistleftpanel;
    JPanel setoutlistrightpanel;
    
    JScrollPane setoutscrollpane;
    JScrollPane setoutscrollpane2;
    JScrollPane notescrollpane;
    
    JPanel setoutsearchpanel;
    JPanel setoutwarehousepanel;
    JPanel setoutactionpanel;
    JPanel setoutstockpanel;
    JPanel setoutnotepanel;
    JPanel setoutdummypanel;
    
    ItemOutTableModel iitm;
    
    MyTextField setoutsearchtextfield;
    MyTextField setoutsacktextfield;
    MyTextField setoutunittextfield;
    
    MyButton setoutsearchbutton;
    MyButton setoutokbutton;
    
    JComboBox warehousecombobox;
    
    TableRowSorter<ItemOutTableModel> sorter;
    
    ArrayList<Item_Out> itemoutlist;
    
    JTable itemouttable;
    
    JPanel itemoutsackpanel;
    MyLabel itemoutsacklabel;
    JScrollPane itemoutsackpane;
    
    JTextArea note;
    
    public SetOutTab(){
//        super();
        super(new FlowLayout(FlowLayout.LEFT));
        
//        setoutlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        setoutlist.setPreferredSize(new Dimension(995, 450));
//        setinlist = new SetInTab();
//        deletetab = new DeleteTab();
        
        itemoutController = new ItemOutController();
        warehouseController = new WarehouseController();
        
        setoutscrollpane = new JScrollPane();
        setoutscrollpane2 = new JScrollPane();
        
        setoutlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setoutlistrightpanel = new JPanel();
        setoutlistrightpanel.setLayout(new BoxLayout(setoutlistrightpanel, BoxLayout.Y_AXIS));
        setoutsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setoutactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        setoutsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Barang "));
        setoutactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        setoutwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setoutwarehousepanel.setBorder(BorderFactory.createTitledBorder(" Gudang "));
        Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
        warehousecombobox = new JComboBox(warehouselist);
        warehousecombobox.setSize(300, warehousecombobox.getHeight());
        setoutwarehousepanel.add(warehousecombobox);
        
        setoutstockpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setoutstockpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Stok "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        setoutsacktextfield = new MyTextField("", 50);
        setoutunittextfield = new MyTextField("", 50);
        setoutstockpanel.add(setoutsacktextfield);
        setoutstockpanel.add(new JLabel("Karung"));
        setoutstockpanel.add(setoutunittextfield);
        setoutstockpanel.add(new JLabel("Unit"));
        
        setoutnotepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setoutnotepanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Catatan "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        notescrollpane = new JScrollPane();
        note = new JTextArea(5, 27);
        notescrollpane.setViewportView(note);
        setoutnotepanel.add(notescrollpane);
        
        setoutdummypanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setoutdummypanel.setPreferredSize(new Dimension(350, 30));
        
        itemoutlist = itemoutController.getActiveItemList();
        
        iitm = new ItemOutTableModel(itemoutlist);
        sorter = new TableRowSorter<ItemOutTableModel>(iitm);
        sorter.setSortsOnUpdates(true);
        
        itemouttable = new JTable(iitm);
        itemouttable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemouttable.getTableHeader().setReorderingAllowed(false);
        itemouttable.setRowHeight(itemouttable.getRowHeight() + 10);
        
        itemouttable.setRowSorter(sorter);
        
        setoutscrollpane.getViewport().add(itemouttable, null);
        setoutscrollpane.setPreferredSize(new Dimension(600, 420));
        
        setoutsearchtextfield = new MyTextField("", 250);
        
        setoutsearchtextfield.getDocument().addDocumentListener(
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
        
        setoutsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        setoutokbutton = new MyButton(new MyImageIcon().getImage("gui/image/Ok.png"), "Simpan", 110, 28);
    
        itemoutsacklabel = new MyLabel("");
        itemoutsackpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemoutsackpanel.setPreferredSize(new Dimension(350, 30));
        itemoutsackpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        itemoutsackpanel.add(itemoutsacklabel);
        itemoutsackpane = new JScrollPane();
        itemoutsackpane.getViewport().add(itemoutsackpanel, null);
        itemoutsackpane.setBorder(BorderFactory.createTitledBorder("Jumlah Unit Per Karung"));
        
        //setoutlist.add(setoutlistleftpanel);
        //setoutlist.add(setoutlistrightpanel);
        
        add(setoutlistleftpanel);
        add(setoutlistrightpanel);
        
        setoutactionpanel.add(setoutokbutton);
        
        setoutlistleftpanel.add(setoutscrollpane);
        
        setoutlistrightpanel.add(setoutsearchpanel);
        setoutlistrightpanel.add(itemoutsackpane);
        setoutlistrightpanel.add(setoutwarehousepanel);
        setoutlistrightpanel.add(setoutstockpanel);
        setoutlistrightpanel.add(setoutnotepanel);
        setoutlistrightpanel.add(setoutactionpanel);
        setoutlistrightpanel.add(setoutdummypanel);
        
        setoutsearchpanel.add(setoutsearchtextfield);
        setoutsearchpanel.add(setoutsearchbutton);
        
//        addTab("Set Manual Barang Produksi", setoutlist);
//        addTab("Set Manual Barang Curah", setinlist);
//        addTab("Hapus Data Barang", deletetab);
        
        itemouttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String warname = String.valueOf(warehousecombobox.getSelectedItem());
                int itemid = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());
                
                Warehouse warehouse = warehouseController.getWarehouse(warname);
                Item_Out itemout = itemoutController.getItem(itemid);
                
                Item_Out_Warehouse iow = warehouseController.getItemOutWarehouse(warehouse, itemout);
                
                setoutsacktextfield.setText(String.valueOf((int)iow.getStock()/itemout.getSacksize()));
                setoutunittextfield.setText(String.format("%.1f", iow.getStock()%itemout.getSacksize()));
                
                itemoutsacklabel.setText(String.valueOf(itemout.getSacksize()));
            }
        });
        
        warehousecombobox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String warname = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());

                    Warehouse warehouse = warehouseController.getWarehouse(warname);
                    Item_Out itemout = itemoutController.getItem(itemid);

                    Item_Out_Warehouse iow = warehouseController.getItemOutWarehouse(warehouse, itemout);

                    setoutsacktextfield.setText(String.valueOf((int)iow.getStock()/itemout.getSacksize()));
                    setoutunittextfield.setText(String.format("%.1f", iow.getStock()%itemout.getSacksize()));
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() +"~" + exc);
                    JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
                }
            }
        });
        
        setoutokbutton.addActionListener((ActionEvent e) -> {
            try{
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Ubah stok barang ini?");
                JDialog dialog = jop.createDialog(null, "Ubah Stok");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    String warname = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());

                    Warehouse warehouse = warehouseController.getWarehouse(warname);
                    Item_Out itemout = itemoutController.getItem(itemid);

                    float quantity = (Integer.parseInt(setoutsacktextfield.getText()) * itemout.getSacksize()) + Float.parseFloat(setoutunittextfield.getText());

                    if(quantity < 0){
                        JOptionPane.showMessageDialog(null, "Jumlah barang tidak bisa kurang dari 0");
                    }
                    else{
                        warehouseController.setItemOutWarehouseStock(warehouse, itemout, quantity, note.getText());
                        note.setText("");
                        JOptionPane.showMessageDialog(null, "Stok barang berhasil dirubah");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris atau gudang yang dipilih");
            }
        });
    }
    
    private void newFilter() {
        RowFilter<ItemOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(setoutsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
