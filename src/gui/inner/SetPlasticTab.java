/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.PlasticController;
import controller.WarehouseController;
import entity.Plastic;
import entity.Plastic_Warehouse;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.PlasticTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class SetPlasticTab extends JTabbedPane{
    
    PlasticController plasticController;
    WarehouseController warehouseController;
    
    JPanel setplasticlist;
    JPanel setinlistleftpanel;
    JPanel setinlistrightpanel;
    
    JScrollPane setinscrollpane;
    JScrollPane setinscrollpane2;
    
    JPanel setinsearchpanel;
    JPanel setinwarehousepanel;
    JPanel setinactionpanel;
    JPanel setinstockpanel;
    JPanel setindummypanel;
    
    PlasticTableModel ptm;
    
    MyTextField setinsearchtextfield;
    MyTextField setinsacktextfield;
    
    MyButton setinsearchbutton;
    MyButton setinokbutton;
    
    JComboBox warehousecombobox;
    
    TableRowSorter<PlasticTableModel> sorter;
    
    ArrayList<Plastic> plasticlist;
    
    JTable plastictable;
    JPanel setinnotepanel;
    JScrollPane notescrollpane;
    JTextArea note;
    
    public SetPlasticTab(){
        super();
        
        setplasticlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setplasticlist.setPreferredSize(new Dimension(1000, 450));
        
        plasticController = new PlasticController();
        warehouseController = new WarehouseController();
        
        setinscrollpane = new JScrollPane();
        setinscrollpane2 = new JScrollPane();
        
        setinlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setinlistrightpanel = new JPanel();
        setinlistrightpanel.setLayout(new BoxLayout(setinlistrightpanel, BoxLayout.Y_AXIS));
        setinsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setinactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        setinsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Barang "));
        setinactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        setinwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setinwarehousepanel.setBorder(BorderFactory.createTitledBorder(" Gudang "));
        Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
        warehousecombobox = new JComboBox(warehouselist);
        warehousecombobox.setSize(300, warehousecombobox.getHeight());
        setinwarehousepanel.add(warehousecombobox);
        
        setinstockpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setinstockpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Stok "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        setinsacktextfield = new MyTextField("", 50);
        setinstockpanel.add(setinsacktextfield);
        setinstockpanel.add(new JLabel("Lembar"));
        
        setinnotepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setinnotepanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Catatan "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        notescrollpane = new JScrollPane();
        note = new JTextArea(5, 27);
        notescrollpane.setViewportView(note);
        setinnotepanel.add(notescrollpane);
        
        setindummypanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setindummypanel.setPreferredSize(new Dimension(350, 80));
        
        plasticlist = plasticController.getActivePlasticList();
        
        ptm = new PlasticTableModel(plasticlist);
        sorter = new TableRowSorter<PlasticTableModel>(ptm);
        sorter.setSortsOnUpdates(true);
        
        plastictable = new JTable(ptm);
        plastictable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plastictable.getTableHeader().setReorderingAllowed(false);
        
        plastictable.setRowSorter(sorter);
        
        setinscrollpane.getViewport().add(plastictable, null);
        setinscrollpane.setPreferredSize(new Dimension(600, 430));
        
        setinsearchtextfield = new MyTextField("", 250);
        
        setinsearchtextfield.getDocument().addDocumentListener(
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
        
        setinsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        setinokbutton = new MyButton(new MyImageIcon().getImage("gui/image/Ok.png"), "Simpan", 110, 28);
        
        setplasticlist.add(setinlistleftpanel);
        setplasticlist.add(setinlistrightpanel);
        
        setinactionpanel.add(setinokbutton);
        
        setinlistleftpanel.add(setinscrollpane);
        
        setinlistrightpanel.add(setinsearchpanel);
        setinlistrightpanel.add(setinwarehousepanel);
        setinlistrightpanel.add(setinstockpanel);
        setinlistrightpanel.add(setinnotepanel);
        setinlistrightpanel.add(setinactionpanel);
        setinlistrightpanel.add(setindummypanel);
        
        setinsearchpanel.add(setinsearchtextfield);
        setinsearchpanel.add(setinsearchbutton);
        
        addTab("Set Manual Plastik", setplasticlist);
        
        plastictable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String warname = String.valueOf(warehousecombobox.getSelectedItem());
                int itemid = Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString());
                
                Warehouse warehouse = warehouseController.getWarehouse(warname);
                Plastic plastic = plasticController.getPlastic(itemid);
                
                Plastic_Warehouse iiw = warehouseController.getPlasticWarehouse(warehouse, plastic);
                
                setinsacktextfield.setText(String.valueOf((int)iiw.getStock()));
            }
        });
        
        warehousecombobox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String warname = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString());

                    Warehouse warehouse = warehouseController.getWarehouse(warname);
                    Plastic plastic = plasticController.getPlastic(itemid);

                    Plastic_Warehouse iiw = warehouseController.getPlasticWarehouse(warehouse, plastic);

                    setinsacktextfield.setText(String.valueOf((int)iiw.getStock()));
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() +"~" + exc);
                    JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
                }
            }
        });
        
        setinokbutton.addActionListener((ActionEvent e) -> {
            try{
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Ubah stok plastik ini?");
                JDialog dialog = jop.createDialog(null, "Ubah Stok");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    String warname = String.valueOf(warehousecombobox.getSelectedItem());
                    int itemid = Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString());

                    Warehouse warehouse = warehouseController.getWarehouse(warname);
                    Plastic plastic = plasticController.getPlastic(itemid);

                    int quantity = Integer.parseInt(setinsacktextfield.getText());

                    if(quantity < 0){
                        JOptionPane.showMessageDialog(null, "Jumlah plastik tidak bisa kurang dari 0");
                    }
                    else{
                        warehouseController.setPlasticWarehouseStock(warehouse, plastic, quantity, note.getText());
                        note.setText("");
                        JOptionPane.showMessageDialog(null, "Stok plastik berhasil dirubah");
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
        RowFilter<PlasticTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(setinsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}