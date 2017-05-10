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
import controller.Production3Controller;
import controller.WarehouseController;
import entity.Production3;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tablemodel.Production3TableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production3Tab extends JPanel{
    
    Production3Controller production3Controller;
    WarehouseController warehouseController;
    
    JPanel listleftpanel;
    JPanel listrightpanel;
    
    JPanel searchpanel;
    JPanel detailpanel;
    JPanel detailbuttonpanel;
    JPanel actionpanel;
    
    JTable production3table;
    
    JScrollPane jScrollPane;
    JScrollPane jScrollPane2;
    
    MyTextField searchtextfield;
    
    MyButton searchbutton;
    MyButton addbutton;
    MyButton editbutton;
    MyButton deletebutton;
    MyButton detailbutton;
    
    Production3TableModel ptm;
    
    TableRowSorter<Production3TableModel> sorter;
    
    JLabel notelabel;
    MyLabel statuslabel;
    JPanel statuspanel;
    JScrollPane statuspane;
    
    public Production3Tab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        notelabel = new JLabel();
        
        jScrollPane = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        
        production3Controller = new Production3Controller();
        warehouseController = new WarehouseController();
        
        listleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listrightpanel = new JPanel();
        searchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailpanel = new JPanel();
        detailbuttonpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        actionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        listrightpanel.setLayout(new BoxLayout(listrightpanel, BoxLayout.Y_AXIS));
        detailpanel.setLayout(new BoxLayout(detailpanel, BoxLayout.Y_AXIS));
        
        searchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Pindah Barang "));
        actionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        detailpanel.setPreferredSize(new Dimension(350, 180));
        detailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        
        ptm = new Production3TableModel(production3Controller.getProduction3List());
        
        production3table = new JTable(ptm);
        production3table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        production3table.setRowHeight(production3table.getRowHeight() + 10);
        sorter = new TableRowSorter<Production3TableModel>(ptm);
        sorter.setSortsOnUpdates(true);
        
        production3table.getTableHeader().setReorderingAllowed(false);
        
        production3table.setRowSorter(sorter);
        
        jScrollPane.getViewport().add(production3table, null);
        jScrollPane.setPreferredSize(new Dimension(600, 420));
        
        jScrollPane2.getViewport().add(detailpanel, null);
        jScrollPane2.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        searchtextfield = new MyTextField("", 250);
        
        searchtextfield.getDocument().addDocumentListener(
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
        
        searchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        addbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        editbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        deletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        detailbutton = new MyButton(new MyImageIcon().getImage("gui/image/Detail.png"), "Detil", 110, 28);
        
        actionpanel.add(addbutton);
        actionpanel.add(editbutton);
        actionpanel.add(deletebutton);
        
        detailbuttonpanel.add(detailbutton);
        detailbuttonpanel.setBorder(BorderFactory.createTitledBorder(" Detil Pindah Barang "));
        
        add(listleftpanel);
        add(listrightpanel);
        
        listleftpanel.add(jScrollPane);
        
        statuslabel = new MyLabel("");
        statuspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statuspanel.setPreferredSize(new Dimension(350, 30));
        statuspanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        statuspanel.add(statuslabel);
        statuspane = new JScrollPane();
        statuspane.getViewport().add(statuspanel, null);
        statuspane.setBorder(BorderFactory.createTitledBorder("Status"));
        
        listrightpanel.add(searchpanel);
        listrightpanel.add(statuspane);
        listrightpanel.add(jScrollPane2);
        listrightpanel.add(detailbuttonpanel);
        listrightpanel.add(actionpanel);
        
        searchpanel.add(searchtextfield);
        searchpanel.add(searchbutton);
        
        detailpanel.add(notelabel);
        
        addbutton.addActionListener((ActionEvent e) -> {
            UtilDateModel model = new UtilDateModel();
            model.setSelected(true);
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
            
            Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
            JComboBox warehouse = new JComboBox(warehouselist);
            
            JTextArea note = new JTextArea(7, 10);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            
            final JComponent[] inputs = new JComponent[] {
                            new JLabel("Tanggal"),
                            datePicker,
                            new JLabel("Gudang"),
                            warehouse,
                            new JLabel("Catatan"),
                            sp
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Pindah Barang");
            dialog.show();
            
            try{
                if(pane.getValue() == null){

                }
                else if(pane.getValue().toString().equals("0")){
                    String swarehouse = String.valueOf(warehouse.getSelectedItem());
                    
                    Production3 production3 = production3Controller.addProduction3(GlobalFields.USER, 
                            (Date)datePicker.getModel().getValue(), 
                            swarehouse, 
                            note.getText());
                    
                    if(production3 == null){
                        JOptionPane.showMessageDialog(null, "Tidak ada gudang yang dipilih");
                    }
                    else{
                        JPanel panel = (JPanel)this.getParent().getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new Production3DetailTab(production3.getId()));
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan pindah barang");
            }
        });
        
        editbutton.addActionListener((ActionEvent e) ->{
            try{
                int id = Integer.parseInt(production3table.getValueAt(production3table.getSelectedRow(), 0).toString());
                
                Production3 production3 = production3Controller.getProduction3(id);
                
                UtilDateModel model = new UtilDateModel();
                model.setSelected(true);
                JDatePanelImpl datePanel = new JDatePanelImpl(model);
                JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
                datePicker.getModel().setDate(production3.getDate().getYear()+1900, production3.getDate().getMonth(), production3.getDate().getDate());

                Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
                JComboBox warehouse = new JComboBox(warehouselist);
                
                warehouse.setEnabled(false);

                for(int counter = 0; counter < warehouselist.length; counter++){
                    if(production3table.getValueAt(production3table.getSelectedRow(), 3).toString().equals(warehouselist[counter])){
                        warehouse.setSelectedIndex(counter);
                    }
                }

                JTextArea note = new JTextArea(7, 10);

                note.setText(notelabel.getText());

                JScrollPane sp = new JScrollPane();

                sp.setViewportView(note);

                final JComponent[] inputs = new JComponent[] {
                                new JLabel("Tanggal"),
                                datePicker,
                                new JLabel("Gudang"),
                                warehouse,
                                new JLabel("Catatan"),
                                sp
                };
                JOptionPane pane = new JOptionPane();

                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);

                JDialog dialog = pane.createDialog(null, "Edit Pindah Barang");
                dialog.show();

                if(pane.getValue() == null){

                }
                else if(pane.getValue().toString().equals("0")){
                    production3Controller.editProduction3(ptm, id, (Date)datePicker.getModel().getValue(), 
                            String.valueOf(warehouse.getSelectedItem()), 
                            note.getText());
                }
                
                notelabel.setText(note.getText());
            }
            
            catch(Exception exp){
                System.out.println(this.getClass().toString() + " " + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        detailbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(production3table.getValueAt(production3table.getSelectedRow(), 0).toString());
            
                JPanel panel = (JPanel)this.getParent().getParent();
                panel.remove(panel.getComponent(1));
                panel.add(new Production3DetailTab(id));
                panel.revalidate();
                panel.repaint();
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + " " + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        deletebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(production3table.getValueAt(production3table.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus produksi - cn ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Produksi - Cn");
                dialog.show();
                if(jop.getValue() == null){

                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = production3Controller.deleteProduction3(id);

                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus pindah barang ~ jumlah barang tidak mencukupi");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Produksi - Cn dihapus");

                        ptm.deleteRow(id);
                    }
                }
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + " " + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        production3table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                Production3 production3 = production3Controller.getProduction3(Integer.parseInt(production3table.getValueAt(production3table.getSelectedRow(), 0).toString()));
                
                notelabel.setText(production3.getNote());
                
                if(production3.isStatus() == GlobalFields.CHECKED){
                    statuslabel.setText("SUDAH DIPERIKSA");
                    deletebutton.setEnabled(false);
                    editbutton.setEnabled(false);
                }
                else{
                    statuslabel.setText("BELUM DIPERIKSA");
                    deletebutton.setEnabled(true);
                    editbutton.setEnabled(true);
                }
            }
        });
    }
    
    private void newFilter() {
        RowFilter<Production3TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
