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
import controller.MerkController;
import entity.Merk;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import tablemodel.MerkTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class MerkTab extends JPanel{
    
    MerkController merkController;
    
    JPanel merklistleftpanel;
    JPanel merklistrightpanel;
    
    MyLabel merknotelabel;
    
    JPanel merksearchpanel;
    JPanel merkdetailpanel;
    JPanel merkactionpanel;
    
    JScrollPane merkscrollpane;
    JScrollPane merkscrollpane2;
    
    JTable merktable;
    
    MyTextField merksearchtextfield;
    
    MyButton merksearchbutton;
    MyButton merkaddbutton;
    MyButton merkeditbutton;
    MyButton merkdeletebutton;
    
    MerkTableModel mtm;
    
    TableRowSorter<MerkTableModel> sorter;
    
    public MerkTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        merkController = new MerkController();
        
        merknotelabel = new MyLabel("");
        
        merkscrollpane = new JScrollPane();
        merkscrollpane2 = new JScrollPane();
        
        merklistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        merklistrightpanel = new JPanel();
        merksearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        merkdetailpanel = new JPanel();
        merkactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        merklistrightpanel.setLayout(new BoxLayout(merklistrightpanel, BoxLayout.Y_AXIS));
        merkdetailpanel.setLayout(new BoxLayout(merkdetailpanel, BoxLayout.Y_AXIS));
        merkdetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        merkdetailpanel.add(merknotelabel);
        
        merksearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Merek "));
        merkactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        merkdetailpanel.setPreferredSize(new Dimension(350, 290));
        
        mtm = new MerkTableModel(merkController.getMerkList());
        sorter = new TableRowSorter<MerkTableModel>(mtm);
        sorter.setSortsOnUpdates(true);
        
        merktable = new JTable(mtm);
        
        merktable.getTableHeader().setReorderingAllowed(false);
        merktable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        merktable.setRowSorter(sorter);
        merktable.setRowHeight(merktable.getRowHeight() + 10);
        
        merkscrollpane.getViewport().add(merktable, null);
        merkscrollpane.setPreferredSize(new Dimension(600, 420));
        
        merkscrollpane2.getViewport().add(merkdetailpanel, null);
        merkscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        merksearchtextfield = new MyTextField("", 250);
        
        merksearchtextfield.getDocument().addDocumentListener(
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
        
        merksearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        merkaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        merkeditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        merkdeletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        
        merkactionpanel.add(merkaddbutton);
        merkactionpanel.add(merkeditbutton);
        merkactionpanel.add(merkdeletebutton);
        
        add(merklistleftpanel);
        add(merklistrightpanel);
        
        merklistleftpanel.add(merkscrollpane);
        
        merklistrightpanel.add(merksearchpanel);
        merklistrightpanel.add(merkscrollpane2);
        merklistrightpanel.add(merkactionpanel);
        
        merksearchpanel.add(merksearchtextfield);
        merksearchpanel.add(merksearchbutton);
        
        merkaddbutton.addActionListener((ActionEvent e) -> {
            JTextField name = new JTextField();
            JTextArea note = new JTextArea(7, 10);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            final JComponent[] inputs = new JComponent[] {
                            new JLabel("Nama"),
                            name,
                            new JLabel("Catatan"),
                            sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Merek");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    if(name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else{
                        merkController.addMerk(mtm, name.getText(), note.getText());
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
            } 
        });
        
        merkeditbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(merktable.getValueAt(merktable.getSelectedRow(), 0).toString());
                
                JTextField name = new JTextField(merktable.getValueAt(merktable.getSelectedRow(), 1).toString());
                JTextArea note = new JTextArea(merknotelabel.getText(), 7, 10);
                
                JScrollPane sp = new JScrollPane();
            
                sp.setViewportView(note);

                final JComponent[] inputs = new JComponent[] {
                                new JLabel("Nama"),
                                name,
                                new JLabel("Catatan"),
                                sp
                };
                JOptionPane pane = new JOptionPane();

                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                
                JDialog dialog = pane.createDialog(null, "Edit Merek");
                dialog.show();
                
                try{
                    if(pane.getValue() == null){
                    
                    }
                    else if(pane.getValue().toString().equals("0")){
                        if(name.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                        else{
                            merkController.editMerk(mtm, id, name.getText(), note.getText());

                            merknotelabel.setText(note.getText());
                        }
                    }
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() + "~" + exc);
                    JOptionPane.showMessageDialog(null, "Merek sudah ada");
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        merkdeletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(merktable.getValueAt(merktable.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus merek ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Merek");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = merkController.deleteMerk(id);
                    
                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus merek - merek sudah digunakan di tabel lain");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Merek dihapus");
                        
                        mtm.deleteRow(id);
                        
                        merknotelabel.setText("");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        merktable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Merk merk = merkController.getMerk(Integer.parseInt(merktable.getValueAt(merktable.getSelectedRow(), 0).toString()));
                
                merknotelabel.setText(merk.getNote());
            }
        });
    }
    
    private void newFilter() {
        RowFilter<MerkTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(merksearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
