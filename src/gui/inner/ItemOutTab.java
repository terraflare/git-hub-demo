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
import controller.MerkController;
import controller.PackagingController;
import controller.PlasticController;
import controller.WarehouseController;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.User;
import entity.Warehouse;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
//public class ItemOutTab extends JTabbedPane{
public class ItemOutTab extends JPanel{
    
//    JPanel itemoutlist;
//    JPanel iteminlist;
//    JPanel categorylist;
//    JPanel qualitylist;
//    JPanel merklist;
//    JPanel packaginglist;
    
    ItemOutController itemoutController;
    MerkController merkController;
    PackagingController packagingController;
    WarehouseController warehouseController;
    PlasticController plasticController;
    
    JPanel itemoutlistleftpanel;
    JPanel itemoutlistrightpanel;
    
    MyLabel itemoutnotelabel;
    
    JPanel itemoutsearchpanel;
    JPanel itemoutdetailpanel;
    JPanel itemoutactionpanel;
    
    JScrollPane itemoutscrollpane;
    JScrollPane itemoutscrollpane2;
    
    JTable itemouttable;
    
    MyTextField itemoutsearchtextfield;
    
    MyButton itemoutsearchbutton;
    MyButton itemoutaddbutton;
    MyButton itemouteditbutton;
    MyButton itemoutdisablebutton;
    
    ItemOutTableModel iotm;
    
    TableRowSorter<ItemOutTableModel> sorter;
    
    JPanel itemoutstockpanel;
    JScrollPane itemoutstockpane;
    
    JPanel itemoutsackpanel;
    MyLabel itemoutsacklabel;
    JScrollPane itemoutsackpane;
    
    public ItemOutTab(){
//        super();
        
//        itemoutlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        iteminlist = new ItemInTab();
//        categorylist = new CategoryTab();
//        qualitylist = new QualityTab();
//        merklist = new MerkTab();
//        packaginglist = new PackagingTab();
        
        super(new FlowLayout(FlowLayout.LEFT));
        
//        itemoutlist.setPreferredSize(new Dimension(1000, 450));
        
        itemoutController = new ItemOutController();
        merkController = new MerkController();
        packagingController = new PackagingController();
        warehouseController = new WarehouseController();
        plasticController = new PlasticController();
        
        itemoutnotelabel = new MyLabel("");
        
        itemoutscrollpane = new JScrollPane();
        itemoutscrollpane2 = new JScrollPane();
        
        itemoutlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemoutlistrightpanel = new JPanel();
        itemoutsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemoutdetailpanel = new JPanel();
        itemoutactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        itemoutlistrightpanel.setLayout(new BoxLayout(itemoutlistrightpanel, BoxLayout.Y_AXIS));
        itemoutdetailpanel.setLayout(new BoxLayout(itemoutdetailpanel, BoxLayout.Y_AXIS));
        itemoutdetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        itemoutdetailpanel.add(itemoutnotelabel);
        
        itemoutsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Barang "));
        itemoutactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        itemoutdetailpanel.setPreferredSize(new Dimension(350, 105));
        
        iotm = new ItemOutTableModel(itemoutController.getItemList());
        
        sorter = new TableRowSorter<ItemOutTableModel>(iotm);
        sorter.setSortsOnUpdates(true);
        
        itemouttable = new JTable(iotm);
        itemouttable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemouttable.getTableHeader().setReorderingAllowed(false);
        itemouttable.setRowHeight(itemouttable.getRowHeight() + 10);
        
        itemouttable.setRowSorter(sorter);
        
        itemoutscrollpane.getViewport().add(itemouttable, null);
        itemoutscrollpane.setPreferredSize(new Dimension(600, 420));
        
        itemoutscrollpane2.getViewport().add(itemoutdetailpanel, null);
        itemoutscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        itemoutstockpanel = new JPanel();
        itemoutstockpanel.setLayout(new BoxLayout(itemoutstockpanel, BoxLayout.Y_AXIS));
        itemoutstockpanel.setPreferredSize(new Dimension(350, 115));
        itemoutstockpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        itemoutstockpane = new JScrollPane();
        itemoutstockpane.getViewport().add(itemoutstockpanel, null);
        itemoutstockpane.setBorder(BorderFactory.createTitledBorder("Stok"));
        
        itemoutsacklabel = new MyLabel("");
        itemoutsackpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemoutsackpanel.setPreferredSize(new Dimension(350, 30));
        itemoutsackpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        itemoutsackpanel.add(itemoutsacklabel);
        itemoutsackpane = new JScrollPane();
        itemoutsackpane.getViewport().add(itemoutsackpanel, null);
        itemoutsackpane.setBorder(BorderFactory.createTitledBorder("Jumlah Unit Per Karung"));
        
        itemoutsearchtextfield = new MyTextField("", 250);
        
        itemoutsearchtextfield.getDocument().addDocumentListener(
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
        
        itemoutsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        itemoutaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        itemouteditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        itemoutdisablebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Nonaktif", 110, 28);
        
        itemoutactionpanel.add(itemoutaddbutton);
        itemoutactionpanel.add(itemouteditbutton);
        itemoutactionpanel.add(itemoutdisablebutton);
        
//        itemoutlist.add(itemoutlistleftpanel);
//        itemoutlist.add(itemoutlistrightpanel);
        
        add(itemoutlistleftpanel);
        add(itemoutlistrightpanel);
        
        itemoutlistleftpanel.add(itemoutscrollpane);
        
        itemoutlistrightpanel.add(itemoutsearchpanel);
        itemoutlistrightpanel.add(itemoutsackpane);
        itemoutlistrightpanel.add(itemoutscrollpane2);
        itemoutlistrightpanel.add(itemoutstockpane);
        itemoutlistrightpanel.add(itemoutactionpanel);
        
        itemoutsearchpanel.add(itemoutsearchtextfield);
        itemoutsearchpanel.add(itemoutsearchbutton);
        
//        addTab("Barang Produksi", itemoutlist);
//        addTab("Barang Masuk (Curah)", iteminlist);
//        addTab("Merek", merklist);
//        addTab("Kemasan", packaginglist);
//        addTab("Kualitas", qualitylist);
//        addTab("Kategori", categorylist);
        
        itemoutaddbutton.addActionListener((ActionEvent e) -> {
            JTextField name = new JTextField();
            JTextField sacksize = new JTextField("0");
            Object[] merklist1 = merkController.getMerkNameList();
            Vector packaginglist1 = packagingController.getPackagingNameList();
            Object[] plasticlist1 = plasticController.getActivePlasticNameList();
            JComboBox merk = new JComboBox(merklist1);
            JComboBox packaging = new JComboBox(packaginglist1);
            JComboBox plastic = new JComboBox(plasticlist1);
            plastic.addItem("Tidak ada plastik");
            
            JTextArea note = new JTextArea(7, 10);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            final JComponent[] inputs = new JComponent[]{new JLabel("Nama"), name, 
                new JLabel("Merek"), merk, 
                new JLabel("Kemasan"), packaging, 
                new JLabel("Plastik"), plastic,
                new JLabel("Catatan"), sp,
                new JLabel("Jumlah Unit Per Karung"), sacksize
                };
            JOptionPane pane = new JOptionPane();
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = pane.createDialog(null, "Tambah Barang");
            dialog.show();
            
            if(pane.getValue() == null){

            }
            else if(name.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
            }
            else if(pane.getValue().toString().equals("0")){

                try{
                    int i = Integer.parseInt(sacksize.getText());

                    if(i <= 0){
                        JOptionPane.showMessageDialog(null, "Jumlah unit per karung tidak dapat sama atau kurang dari 0");
                    }
                    else if(merk.getSelectedItem() == null){
                        JOptionPane.showMessageDialog(null, "Merek tidak boleh kosong");
                    }
                    else if(packaging.getSelectedItem() == null){
                        JOptionPane.showMessageDialog(null, "Kemasan tidak boleh kosong");
                    }
                    else{
                        try{
                            itemoutController.addItem(iotm, name.getText(), String.valueOf(merk.getSelectedItem()), 
                            String.valueOf(packaging.getSelectedItem()), 
                            String.valueOf(plastic.getSelectedItem()), Integer.parseInt(sacksize.getText()),
                            note.getText());
                        }
                        catch(Exception exc){
                            System.out.println(this.getClass().toString() + "~" + exc);
                            JOptionPane.showMessageDialog(null, "Barang sudah ada");
                        }
                    }
                }
                catch(Exception exception){
                    System.out.println(this.getClass().toString() + "~" + exception);
                    JOptionPane.showMessageDialog(null, "Jumlah unit per karung harus angka");
                }
            }
        });
        
        itemouteditbutton.addActionListener((ActionEvent e) -> {
            try{
                Item_Out itemout = itemoutController.getItem(Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString()));
                
                int id = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());
                
                JTextField name = new JTextField(itemouttable.getValueAt(itemouttable.getSelectedRow(), 1).toString());
                Object[] merklist1 = merkController.getMerkNameList();
                Vector packaginglist1 = packagingController.getPackagingNameList();
                Object[] plasticlist1 = plasticController.getActivePlasticNameList();
                
                JComboBox merk = new JComboBox(merklist1);
                
                for(int counter = 0; counter < merklist1.length; counter++){
                    if(itemouttable.getValueAt(itemouttable.getSelectedRow(), 2).toString().equals(merklist1[counter])){
                        merk.setSelectedIndex(counter);
                    }
                }
                
                JComboBox packaging = new JComboBox(packaginglist1);
                
                for(int counter = 0; counter < packaginglist1.size(); counter++){
                    if(itemouttable.getValueAt(itemouttable.getSelectedRow(), 3).toString().equals(packaginglist1.get(counter))){
                        packaging.setSelectedIndex(counter);
                    }
                }
                
                JComboBox plastic = new JComboBox(plasticlist1);
                plastic.addItem("Tidak ada plastik");
                
                if(itemout.getPlastic() == null){
                    plastic.setSelectedIndex(plasticlist1.length);
                }
                else{
                    for(int counter = 0; counter < plasticlist1.length; counter++){
                        if(itemout.getPlastic().getName().equals(plasticlist1[counter])){
                            plastic.setSelectedIndex(counter);
                        }
                    }
                }
                
                JTextArea note = new JTextArea(itemoutnotelabel.getText(), 7, 10);
            
                JScrollPane sp = new JScrollPane();

                sp.setViewportView(note);
                
                final JComponent[] inputs = new JComponent[]{new JLabel("Nama"), name, 
                new JLabel("Merek"), merk, 
                new JLabel("Kemasan"), packaging, 
                new JLabel("Plastik"), plastic,
                new JLabel("Catatan"), sp};
                JOptionPane pane = new JOptionPane();
                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                JDialog dialog = pane.createDialog(null, "Edit Barang");
                dialog.show();
                
                try{
                    if(pane.getValue() == null){

                    }
                    else if(name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else if(pane.getValue().toString().equals("0")){

                        itemoutController.editItem(iotm, id, name.getText(), 
                                String.valueOf(merk.getSelectedItem()), 
                                String.valueOf(packaging.getSelectedItem()),
                                String.valueOf(plastic.getSelectedItem()),
                                note.getText());
                        
                        itemoutnotelabel.setText(note.getText());
                    }
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() + "~" + exc);
                    JOptionPane.showMessageDialog(null, "Barang sudah ada");
                }
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        itemouttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Item_Out itemout = itemoutController.getItem(Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString()));
                
                itemoutnotelabel.setText(itemout.getNote());
                
                if(itemout.isActive() == GlobalFields.ACTIVE){
                    itemoutdisablebutton.setText("Nonaktif");
                    itemoutdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                }
                else{
                    itemoutdisablebutton.setText("Aktif");
                    itemoutdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                }
                
                User user = GlobalFields.USER;
                
                Set<Warehouse> warehouselist = user.getWarehouses();
                
                itemoutstockpanel.removeAll();
                
                for(Warehouse war : warehouselist){
                    Item_Out_Warehouse iow = warehouseController.getItemOutWarehouse(war, itemout);
                    
                    String unit =  String.format("%.1f", iow.getStock()%itemout.getSacksize());
                    
                    itemoutstockpanel.add(new JLabel(war.getName() + " : " + (int)iow.getStock()/itemout.getSacksize() + " karung " + unit + " unit"));
                }
                
                itemoutstockpanel.revalidate();
                itemoutstockpanel.repaint();
                
                itemoutsacklabel.setText(String.valueOf(itemout.getSacksize()));
            }
        });
        
        itemoutdisablebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(itemouttable.getValueAt(itemouttable.getSelectedRow(), 0).toString());
                
                Item_Out itemout = itemoutController.getItem(id);
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                if(itemout.isActive() == GlobalFields.ACTIVE){
                    jop.setMessage("Nonaktifkan barang ini?");
                }
                else{
                    jop.setMessage("Aktifkan barang ini?");
                }
                JDialog dialog = jop.createDialog(null, "Ganti Status Barang");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    itemoutController.changeStatus(iotm, itemout);
                    
                    if(itemout.isActive() == GlobalFields.ACTIVE){
                        itemoutdisablebutton.setText("Nonaktif");
                        itemoutdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                    }
                    else{
                        itemoutdisablebutton.setText("Aktif");
                        itemoutdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
    }
    
    private void newFilter() {
        RowFilter<ItemOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(itemoutsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
