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
import controller.PackagingController;
import entity.Packaging;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import tablemodel.PackagingTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class PackagingTab extends JPanel{
    
    PackagingController packagingController;
    
    JPanel packaginglistleftpanel;
    JPanel packaginglistrightpanel;
    
    MyLabel packagingnotelabel;
    
    JPanel packagingsearchpanel;
    JPanel packagingdetailpanel;
    JPanel packagingactionpanel;
    
    JScrollPane packagingscrollpane;
    JScrollPane packagingscrollpane2;
    
    JTable packagingtable;
    
    MyTextField packagingsearchtextfield;
    
    MyButton packagingsearchbutton;
    MyButton packagingaddbutton;
    MyButton packagingeditbutton;
    MyButton packagingdeletebutton;
    
    PackagingTableModel ptm;
    
    TableRowSorter<PackagingTableModel> sorter;
    
    public PackagingTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        packagingController = new PackagingController();
        
        packagingnotelabel = new MyLabel("");
        
        packagingscrollpane = new JScrollPane();
        packagingscrollpane2 = new JScrollPane();
        
        packaginglistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        packaginglistrightpanel = new JPanel();
        packagingsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        packagingdetailpanel = new JPanel();
        packagingactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        packaginglistrightpanel.setLayout(new BoxLayout(packaginglistrightpanel, BoxLayout.Y_AXIS));
        packagingdetailpanel.setLayout(new BoxLayout(packagingdetailpanel, BoxLayout.Y_AXIS));
        packagingdetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        packagingdetailpanel.add(packagingnotelabel);
        
        packagingsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Kemasan "));
        packagingactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        packagingdetailpanel.setPreferredSize(new Dimension(350, 290));
        
        ptm = new PackagingTableModel(packagingController.getPackagingList());
        sorter = new TableRowSorter<PackagingTableModel>(ptm);
        sorter.setSortsOnUpdates(true);
        
        packagingtable = new JTable(ptm);
        packagingtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        packagingtable.getTableHeader().setReorderingAllowed(false);
        packagingtable.setRowHeight(packagingtable.getRowHeight() + 10);
        
        packagingtable.setRowSorter(sorter);
        
        packagingscrollpane.getViewport().add(packagingtable, null);
        packagingscrollpane.setPreferredSize(new Dimension(600, 420));
        
        packagingscrollpane2.getViewport().add(packagingdetailpanel, null);
        packagingscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        packagingsearchtextfield = new MyTextField("", 250);
        
        packagingsearchtextfield.getDocument().addDocumentListener(
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
        
        packagingsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        packagingaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        packagingeditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        packagingdeletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        
        packagingactionpanel.add(packagingaddbutton);
        packagingactionpanel.add(packagingeditbutton);
        packagingactionpanel.add(packagingdeletebutton);
        
        add(packaginglistleftpanel);
        add(packaginglistrightpanel);
        
        packaginglistleftpanel.add(packagingscrollpane);
        
        packaginglistrightpanel.add(packagingsearchpanel);
        packaginglistrightpanel.add(packagingscrollpane2);
        packaginglistrightpanel.add(packagingactionpanel);
        
        packagingsearchpanel.add(packagingsearchtextfield);
        packagingsearchpanel.add(packagingsearchbutton);
        
        packagingaddbutton.addActionListener((ActionEvent e) -> {
            JTextField amount = new JTextField();
            String[] unit = GlobalFields.PACKAGING_UNIT;
            
            JComboBox unitlist = new JComboBox(unit);
            JTextArea note = new JTextArea(7, 10);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            final JComponent[] inputs = new JComponent[] {
                            new JLabel("Jumlah"),
                            amount,
                            new JLabel("Satuan Berat"),
                            unitlist,
                            new JLabel("Catatan"),
                            sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Kemasan");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    
                    float i = Float.parseFloat(amount.getText());

                    if(i <= 0){
                        JOptionPane.showMessageDialog(null, "Jumlah tidak dapat sama atau kurang dari 0");
                    }
                    else if(amount.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
                    }
                    else{
                        try{
                            packagingController.addPackaging(ptm, amount.getText(), 
                                    String.valueOf(unitlist.getSelectedItem()), 
                                    note.getText());
                        }
                        catch(Exception exception){
                            System.out.println(this.getClass().toString() + "~" + exception);
                            JOptionPane.showMessageDialog(null, "Kemasan sudah ada");
                            
                        }
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Jumlah harus angka");
            }
        });
        
        packagingeditbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(packagingtable.getValueAt(packagingtable.getSelectedRow(), 0).toString());
                
                Object[] unitlist = GlobalFields.PACKAGING_UNIT;
                
                JTextField amount = new JTextField(packagingtable.getValueAt(packagingtable.getSelectedRow(), 1).toString());
                JTextArea note = new JTextArea(packagingnotelabel.getText(), 7, 10);
                
                JComboBox unit = new JComboBox(unitlist);
                
                for(int counter = 0; counter < unitlist.length; counter++){
                    if(packagingtable.getValueAt(packagingtable.getSelectedRow(), 2).toString().equals(unitlist[counter])){
                        unit.setSelectedIndex(counter);
                    }
                }
                
                JScrollPane sp = new JScrollPane();
            
                sp.setViewportView(note);

                final JComponent[] inputs = new JComponent[] {
                                new JLabel("Jumlah"),
                                amount,
                                new JLabel("Satuan Berat"),
                                unit,
                                new JLabel("Catatan"),
                                sp
                };
                JOptionPane pane = new JOptionPane();

                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);

                JDialog dialog = pane.createDialog(null, "Edit Kemasan");
                dialog.show();

                
                if(pane.getValue() == null){

                }

                else if(pane.getValue().toString().equals("0")){

                    try{
                        float i = Float.parseFloat(amount.getText());

                        if(i <= 0){
                            JOptionPane.showMessageDialog(null, "Jumlah tidak dapat sama atau kurang dari 0");
                        }
                        else if(amount.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
                        }
                        else{
                            try{
                                String unitname = String.valueOf(unit.getSelectedItem());
                                packagingController.editPackaging(ptm, id, amount.getText(), unitname, note.getText());
                                packagingnotelabel.setText(note.getText());
                            }
                            catch(Exception exc){
                                System.out.println(this.getClass().toString() + "~" + exc);
                                JOptionPane.showMessageDialog(null, "Kemasan sudah ada");
                            }
                        }
                    }
                    catch(Exception exception){
                        System.out.println(this.getClass().toString() + "~" + exception);
                        JOptionPane.showMessageDialog(null, "Jumlah harus angka");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        packagingdeletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(packagingtable.getValueAt(packagingtable.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus kemasan ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Kemasan");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = packagingController.deletePackaging(id);
                    
                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus kemasan ~ kemasan sudah digunakan di tabel lain");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Kemasan dihapus");
                        
                        ptm.deleteRow(id);
                        
                        packagingnotelabel.setText("");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris dipilih");
            }
        });
        
        packagingtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Packaging packaging = packagingController.getPackaging(Integer.parseInt(packagingtable.getValueAt(packagingtable.getSelectedRow(), 0).toString()));
                packagingnotelabel.setText(packaging.getNote());
            }
            
        });
    }
    
    private void newFilter() {
        RowFilter<PackagingTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(packagingsearchtextfield.getText(), 2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
