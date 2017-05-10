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
import controller.QualityController;
import controller.WarehouseController;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.User;
import entity.Warehouse;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
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
import javax.swing.JTextField;
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
public class ItemInTab extends JPanel{
    
    ItemInController iteminController;
    QualityController qualityController;
    WarehouseController warehouseController;
    
    JPanel iteminlistleftpanel;
    JPanel iteminlistrightpanel;
    
    MyLabel iteminnotelabel;
    
    JPanel iteminsearchpanel;
    JPanel itemindetailpanel;
    JPanel iteminactionpanel;
    
    JScrollPane iteminscrollpane;
    JScrollPane iteminscrollpane2;
    
    JTable itemintable;
    
    MyTextField iteminsearchtextfield;
    
    MyButton iteminsearchbutton;
    MyButton iteminaddbutton;
    MyButton itemineditbutton;
    MyButton itemindisablebutton;
    
    ItemInTableModel iitm;
    
    TableRowSorter<ItemInTableModel> sorter;
    private JPanel iteminstockpanel;
    private JScrollPane iteminstockpane;
    
    public ItemInTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        iteminController = new ItemInController();
        qualityController = new QualityController();    
        warehouseController = new WarehouseController();
        
        iteminnotelabel = new MyLabel("");
        
        iteminscrollpane = new JScrollPane();
        iteminscrollpane2 = new JScrollPane();
        
        iteminlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        iteminlistrightpanel = new JPanel();
        iteminsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemindetailpanel = new JPanel();
        iteminactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        iteminlistrightpanel.setLayout(new BoxLayout(iteminlistrightpanel, BoxLayout.Y_AXIS));
        itemindetailpanel.setLayout(new BoxLayout(itemindetailpanel, BoxLayout.Y_AXIS));
        itemindetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        itemindetailpanel.add(iteminnotelabel);
        
        iteminsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Barang "));
        iteminactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        itemindetailpanel.setPreferredSize(new Dimension(350, 130));
        
        iitm = new ItemInTableModel(iteminController.getItemList());
        sorter = new TableRowSorter<ItemInTableModel>(iitm);
        sorter.setSortsOnUpdates(true);
        
        itemintable = new JTable(iitm);
        itemintable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemintable.getTableHeader().setReorderingAllowed(false);
        itemintable.setRowHeight(itemintable.getRowHeight() + 10);
        
        itemintable.setRowSorter(sorter);
        
        iteminscrollpane.getViewport().add(itemintable, null);
        iteminscrollpane.setPreferredSize(new Dimension(600, 420));
        
        iteminscrollpane2.getViewport().add(itemindetailpanel, null);
        iteminscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        iteminstockpanel = new JPanel();
        iteminstockpanel.setLayout(new BoxLayout(iteminstockpanel, BoxLayout.Y_AXIS));
        iteminstockpanel.setPreferredSize(new Dimension(350, 140));
        iteminstockpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        iteminstockpane = new JScrollPane();
        iteminstockpane.getViewport().add(iteminstockpanel, null);
        iteminstockpane.setBorder(BorderFactory.createTitledBorder("Stok"));
        
        iteminsearchtextfield = new MyTextField("", 250);
        
        iteminsearchtextfield.getDocument().addDocumentListener(
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
        
        iteminsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        iteminaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        itemineditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        itemindisablebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Nonaktif", 110, 28);
        
        iteminactionpanel.add(iteminaddbutton);
        iteminactionpanel.add(itemineditbutton);
        iteminactionpanel.add(itemindisablebutton);
        
        add(iteminlistleftpanel);
        add(iteminlistrightpanel);
        
        iteminlistleftpanel.add(iteminscrollpane);
        
        iteminlistrightpanel.add(iteminsearchpanel);
        iteminlistrightpanel.add(iteminscrollpane2);
        iteminlistrightpanel.add(iteminstockpane);
        iteminlistrightpanel.add(iteminactionpanel);
        
        iteminsearchpanel.add(iteminsearchtextfield);
        iteminsearchpanel.add(iteminsearchbutton);
        
        iteminaddbutton.addActionListener((ActionEvent e) -> {
            JTextField name = new JTextField();
            Object[] qualitylist1 = qualityController.getQualityNameList();
            JComboBox quality = new JComboBox(qualitylist1);
            
            JTextArea note = new JTextArea(7, 10);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            final JComponent[] inputs = new JComponent[]{new JLabel("Nama"), name, 
                new JLabel("Kualitas"), quality, 
                new JLabel("Catatan"), sp};
            JOptionPane pane = new JOptionPane();
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = pane.createDialog(null, "Tambah Barang");
            dialog.show();
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    
                    if(quality.getSelectedItem() == null){
                        JOptionPane.showMessageDialog(null, "Kualitas tidak boleh kosong");
                    }
                    else if(name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else{
                        iteminController.addItem(iitm, name.getText(),
                                String.valueOf(quality.getSelectedItem()),
                                note.getText());
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Barang sudah ada");
            }
        });
        
        itemineditbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(itemintable.getValueAt(itemintable.getSelectedRow(), 0).toString());
                
                JTextField name = new JTextField(itemintable.getValueAt(itemintable.getSelectedRow(), 1).toString());
                Object[] qualitylist1 = qualityController.getQualityNameList();
                
                JComboBox quality = new JComboBox(qualitylist1);
                
                for(int counter = 0; counter < qualitylist1.length; counter++){
                    if(itemintable.getValueAt(itemintable.getSelectedRow(), 2).toString().contains((CharSequence) qualitylist1[counter])){
                        quality.setSelectedIndex(counter);
                    }
                }
                
                JTextArea note = new JTextArea(iteminnotelabel.getText(), 7, 10);
            
                JScrollPane sp = new JScrollPane();

                sp.setViewportView(note);
                
                final JComponent[] inputs = new JComponent[]{new JLabel("Nama"), name, 
                new JLabel("Kualitas"), quality, 
                new JLabel("Catatan"), sp};
                JOptionPane pane = new JOptionPane();
                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                JDialog dialog = pane.createDialog(null, "Edit Barang");
                dialog.show();
                
                try{
                    if(pane.getValue() == null){

                    }
                    else if(pane.getValue().toString().equals("0")){
                        
                        if(name.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                        else{
                            iteminController.editItem(iitm, id, name.getText(), 
                                    String.valueOf(quality.getSelectedItem()),
                                    note.getText());

                            iteminnotelabel.setText(note.getText());
                        }
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
        
        itemintable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Item_In itemin = iteminController.getItem(Integer.parseInt(itemintable.getValueAt(itemintable.getSelectedRow(), 0).toString()));
                
                iteminnotelabel.setText(itemin.getNote());
                
                if(itemin.isActive() == GlobalFields.ACTIVE){
                    itemindisablebutton.setText("Nonaktif");
                    itemindisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                }
                else{
                    itemindisablebutton.setText("Aktif");
                    itemindisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                }
                
                User user = GlobalFields.USER;
                
                Set<Warehouse> warehouselist = user.getWarehouses();
                    iteminstockpanel.removeAll();
                
                
                for(Warehouse war : warehouselist){
                    Item_In_Warehouse iiw = warehouseController.getItemInWarehouse(war, itemin);
                    iteminstockpanel.add(new JLabel(war.getName() + " : " + iiw.getStock() + " karung"));
                }
                
                iteminstockpanel.revalidate();
                iteminstockpanel.repaint();
            }
        });
        
        itemindisablebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(itemintable.getValueAt(itemintable.getSelectedRow(), 0).toString());
                
                Item_In itemin = iteminController.getItem(id);
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                if(itemin.isActive() == GlobalFields.ACTIVE){
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
                    iteminController.changeStatus(iitm, itemin);
                    
                    if(itemin.isActive() == GlobalFields.ACTIVE){
                        itemindisablebutton.setText("Nonaktif");
                        itemindisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                    }
                    else{
                        itemindisablebutton.setText("Aktif");
                        itemindisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
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
        RowFilter<ItemInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(iteminsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
