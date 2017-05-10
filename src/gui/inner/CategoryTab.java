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
import entity.Category;
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
import tablemodel.CategoryTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class CategoryTab extends JPanel{

    CategoryController categoryController;
    
    JPanel categorylistleftpanel;
    JPanel categorylistrightpanel;
    
    MyLabel categorynotelabel;
    
    JPanel categorysearchpanel;
    JPanel categorydetailpanel;
    JPanel categoryactionpanel;
    
    JScrollPane categoryscrollpane;
    JScrollPane categoryscrollpane2;
    
    JTable categorytable;
    
    MyTextField categorysearchtextfield;
    
    MyButton categorysearchbutton;
    MyButton categoryaddbutton;
    MyButton categoryeditbutton;
    MyButton categorydeletebutton;
    
    CategoryTableModel ctm;
    
    TableRowSorter<CategoryTableModel> sorter;
    
    public CategoryTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        categoryController = new CategoryController();
        
        categorynotelabel = new MyLabel("");
        
        categoryscrollpane = new JScrollPane();
        categoryscrollpane2 = new JScrollPane();
        
        categorylistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categorylistrightpanel = new JPanel();
        categorysearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categorydetailpanel = new JPanel();
        categoryactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        categorylistrightpanel.setLayout(new BoxLayout(categorylistrightpanel, BoxLayout.Y_AXIS));
        categorydetailpanel.setLayout(new BoxLayout(categorydetailpanel, BoxLayout.Y_AXIS));
        categorydetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        categorydetailpanel.add(categorynotelabel);
        
        categorysearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Kategori "));
        categoryactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        categorydetailpanel.setPreferredSize(new Dimension(350, 290));
        
        ctm = new CategoryTableModel(categoryController.getCategoryList());
        sorter = new TableRowSorter<CategoryTableModel>(ctm);
        sorter.setSortsOnUpdates(true);
        
        categorytable = new JTable(ctm);
        categorytable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categorytable.getTableHeader().setReorderingAllowed(false);
        categorytable.setRowHeight(categorytable.getRowHeight() + 10);
        
        categorytable.setRowSorter(sorter);
        
        categoryscrollpane.getViewport().add(categorytable, null);
        categoryscrollpane.setPreferredSize(new Dimension(600, 420));
        
        categoryscrollpane2.getViewport().add(categorydetailpanel, null);
        categoryscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        categorysearchtextfield = new MyTextField("", 250);
        
        categorysearchtextfield.getDocument().addDocumentListener(
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
        
        categorysearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        categoryaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        categoryeditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        categorydeletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        
        categoryactionpanel.add(categoryaddbutton);
        categoryactionpanel.add(categoryeditbutton);
        categoryactionpanel.add(categorydeletebutton);
        
        add(categorylistleftpanel);
        add(categorylistrightpanel);
        
        categorylistleftpanel.add(categoryscrollpane);
        
        categorylistrightpanel.add(categorysearchpanel);
        categorylistrightpanel.add(categoryscrollpane2);
        categorylistrightpanel.add(categoryactionpanel);
        
        categorysearchpanel.add(categorysearchtextfield);
        categorysearchpanel.add(categorysearchbutton);
        
        categoryaddbutton.addActionListener((ActionEvent e) -> {
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
            
            JDialog dialog = pane.createDialog(null, "Tambah Kategori");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    if(name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else{
                        categoryController.addCategory(ctm, name.getText(), note.getText());
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Kategori sudah ada");
            }
        });
        
        categoryeditbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(categorytable.getValueAt(categorytable.getSelectedRow(), 0).toString());
                
                JTextField name = new JTextField(categorytable.getValueAt(categorytable.getSelectedRow(), 1).toString());
                JTextArea note = new JTextArea(categorynotelabel.getText(), 7, 10);
                
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

                JDialog dialog = pane.createDialog(null, "Edit Kategori");
                dialog.show();

                try{
                    if(pane.getValue() == null){
                    
                    }
                    else if(pane.getValue().toString().equals("0")){
                        if(name.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                        else{
                            categoryController.editCategory(ctm, id, name.getText(), note.getText());

                            categorynotelabel.setText(note.getText());
                        }
                    }
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() + "~" + exc);
                    JOptionPane.showMessageDialog(null, "Kategori sudah ada");
                }
            
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        categorydeletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(categorytable.getValueAt(categorytable.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus kategori ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Kategori");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = categoryController.deleteCategory(id);
                    
                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus kategori ~ kategori sudah digunakan di tabel lain");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Kategori dihapus");
                        
                        ctm.deleteRow(id);
                        
                        categorynotelabel.setText("");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        categorytable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Category category = categoryController.getCategory(Integer.parseInt(categorytable.getValueAt(categorytable.getSelectedRow(), 0).toString()));
                
                categorynotelabel.setText(category.getNote());
            }
            
        });
    }
    
    private void newFilter() {
        RowFilter<CategoryTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(categorysearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
