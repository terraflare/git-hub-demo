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
import controller.CategoryController;
import controller.QualityController;
import entity.Quality;
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
import tablemodel.QualityTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class QualityTab extends JPanel{
    
    QualityController qualityController;
    CategoryController categoryController;
    
    JPanel qualitylistleftpanel;
    JPanel qualitylistrightpanel;
    
    MyLabel qualitynotelabel;
    
    JPanel qualitysearchpanel;
    JPanel qualitydetailpanel;
    JPanel qualityactionpanel;
    
    JScrollPane qualityscrollpane;
    JScrollPane qualityscrollpane2;
    
    JTable qualitytable;
    
    MyTextField qualitysearchtextfield;
    
    MyButton qualitysearchbutton;
    MyButton qualityaddbutton;
    MyButton qualityeditbutton;
    MyButton qualitydeletebutton;
    
    QualityTableModel qtm;
    
    TableRowSorter<QualityTableModel> sorter;
    
    public QualityTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        qualityController = new QualityController();
        categoryController = new CategoryController();
        
        qualitynotelabel = new MyLabel("");
        
        qualityscrollpane = new JScrollPane();
        qualityscrollpane2 = new JScrollPane();
        
        qualitylistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qualitylistrightpanel = new JPanel();
        qualitysearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qualitydetailpanel = new JPanel();
        qualityactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        qualitylistrightpanel.setLayout(new BoxLayout(qualitylistrightpanel, BoxLayout.Y_AXIS));
        qualitydetailpanel.setLayout(new BoxLayout(qualitydetailpanel, BoxLayout.Y_AXIS));
        qualitydetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        qualitydetailpanel.add(qualitynotelabel);
        
        qualitysearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Kualitas "));
        qualityactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        qualitydetailpanel.setPreferredSize(new Dimension(350, 290));
        
        qtm = new QualityTableModel(qualityController.getQualityList());
        sorter = new TableRowSorter<QualityTableModel>(qtm);
        sorter.setSortsOnUpdates(true);
        
        qualitytable = new JTable(qtm);
        qualitytable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        qualitytable.getTableHeader().setReorderingAllowed(false);
        qualitytable.setRowHeight(qualitytable.getRowHeight() + 10);
        
        qualitytable.setRowSorter(sorter);
        
        qualityscrollpane.getViewport().add(qualitytable, null);
        qualityscrollpane.setPreferredSize(new Dimension(600, 420));
        
        qualityscrollpane2.getViewport().add(qualitydetailpanel, null);
        qualityscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        qualitysearchtextfield = new MyTextField("", 250);
        
        qualitysearchtextfield.getDocument().addDocumentListener(
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
        
        qualitysearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        qualityaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        qualityeditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        qualitydeletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        
        qualityactionpanel.add(qualityaddbutton);
        qualityactionpanel.add(qualityeditbutton);
        qualityactionpanel.add(qualitydeletebutton);
        
        add(qualitylistleftpanel);
        add(qualitylistrightpanel);
        
        qualitylistleftpanel.add(qualityscrollpane);
        
        qualitylistrightpanel.add(qualitysearchpanel);
        qualitylistrightpanel.add(qualityscrollpane2);
        qualitylistrightpanel.add(qualityactionpanel);
        
        qualitysearchpanel.add(qualitysearchtextfield);
        qualitysearchpanel.add(qualitysearchbutton);
        
        qualityaddbutton.addActionListener((ActionEvent e) -> {
            Object[] categorylist = categoryController.getCategoryNameList();
            
            JTextField name = new JTextField();
            JTextArea note = new JTextArea(7, 10);
            
            JComboBox category = new JComboBox(categorylist);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            final JComponent[] inputs = new JComponent[] {
                            new JLabel("Nama"),
                            name,
                            new JLabel("Kategori"),
                            category,
                            new JLabel("Catatan"),
                            sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tmabah Kualitas");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                
                else if(pane.getValue().toString().equals("0")){
                    if(category.getSelectedItem() == null){
                        JOptionPane.showMessageDialog(null, "Kategori tidak boleh kosong");   
                    }
                    else if(name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else{
                    qualityController.addQuality(qtm, name.getText(), 
                            String.valueOf(category.getSelectedItem()), 
                            note.getText());
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Kualitas sudah ada");
            }
        });
        
        qualityeditbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(qualitytable.getValueAt(qualitytable.getSelectedRow(), 0).toString());
                
                Object[] categorylist = categoryController.getCategoryNameList();
                
                JTextField name = new JTextField(qualitytable.getValueAt(qualitytable.getSelectedRow(), 1).toString());
                JTextArea note = new JTextArea(qualitynotelabel.getText(), 7, 10);
                
                JComboBox category = new JComboBox(categorylist);
                
                for(int counter = 0; counter < categorylist.length; counter++){
                    if(qualitytable.getValueAt(qualitytable.getSelectedRow(), 2).toString().equals(categorylist[counter])){
                        category.setSelectedIndex(counter);
                    }
                }
                
                JScrollPane sp = new JScrollPane();
            
                sp.setViewportView(note);

                final JComponent[] inputs = new JComponent[] {
                                new JLabel("Nama"),
                                name,
                                new JLabel("Kategori"),
                                category,
                                new JLabel("Catatan"),
                                sp
                };
                JOptionPane pane = new JOptionPane();

                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);

                JDialog dialog = pane.createDialog(null, "Edit Kualitas");
                dialog.show();

                try{
                    if(pane.getValue() == null){
                    
                    }
                    else if(pane.getValue().toString().equals("0")){
                        
                        if(name.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                        else{
                            String catname = String.valueOf(category.getSelectedItem());

                            qualityController.editQuality(qtm, id, name.getText(), catname, note.getText());

                            qualitynotelabel.setText(note.getText());
                        }
                    }
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() + "~" + exc);
                    JOptionPane.showMessageDialog(null, "Kualitas sudah ada");
                }
            
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        qualitydeletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(qualitytable.getValueAt(qualitytable.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus kualitas ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Kualitas");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = qualityController.deleteQuality(id);
                    
                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus kualitas ~ kualitas sudah digunakan di tabel lain");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Kualitas dihapus");
                        
                        qtm.deleteRow(id);
                        
                        qualitynotelabel.setText("");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        qualitytable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Quality quality = qualityController.getQuality(Integer.parseInt(qualitytable.getValueAt(qualitytable.getSelectedRow(), 0).toString()));
                
                qualitynotelabel.setText(quality.getNote());
            }
            
        });
    }
    
    private void newFilter() {
        RowFilter<QualityTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(qualitysearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
