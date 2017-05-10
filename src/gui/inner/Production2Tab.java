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
import controller.Production2Controller;
import controller.WarehouseController;
import entity.Production2;
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
import tablemodel.Production2TableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production2Tab extends JPanel{
    
    Production2Controller production2Controller;
    WarehouseController warehouseController;
    
    JPanel listleftpanel;
    JPanel listrightpanel;
    
    JPanel searchpanel;
    JPanel detailpanel;
    JPanel detailbuttonpanel;
    JPanel actionpanel;
    
    JTable production2table;
    
    JScrollPane jScrollPane;
    JScrollPane jScrollPane2;
    
    MyTextField searchtextfield;
    
    MyButton searchbutton;
    MyButton addbutton;
    MyButton editbutton;
    MyButton deletebutton;
    MyButton detailbutton;
    
    Production2TableModel ptm;
    
    TableRowSorter<Production2TableModel> sorter;
    
    JLabel notelabel;
    MyLabel statuslabel;
    JPanel statuspanel;
    JScrollPane statuspane;
    
    public Production2Tab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        notelabel = new JLabel();
        
        jScrollPane = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        
        production2Controller = new Production2Controller();
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
        
        ptm = new Production2TableModel(production2Controller.getProduction2List());
        
        production2table = new JTable(ptm);
        production2table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        production2table.setRowHeight(production2table.getRowHeight() + 10);
        sorter = new TableRowSorter<Production2TableModel>(ptm);
        sorter.setSortsOnUpdates(true);
        
        production2table.getTableHeader().setReorderingAllowed(false);
        
        production2table.setRowSorter(sorter);
        
        jScrollPane.getViewport().add(production2table, null);
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
                    
                    Production2 production2 = production2Controller.addProduction2(GlobalFields.USER, 
                            (Date)datePicker.getModel().getValue(), 
                            swarehouse, 
                            note.getText());
                    
                    if(production2 == null){
                        JOptionPane.showMessageDialog(null, "Tidak ada gudang yang dipilih");
                    }
                    else{
                        JPanel panel = (JPanel)this.getParent().getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new Production2DetailTab(production2.getId()));
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
                int id = Integer.parseInt(production2table.getValueAt(production2table.getSelectedRow(), 0).toString());
                
                Production2 production2 = production2Controller.getProduction2(id);
                
                UtilDateModel model = new UtilDateModel();
                model.setSelected(true);
                JDatePanelImpl datePanel = new JDatePanelImpl(model);
                JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
                datePicker.getModel().setDate(production2.getDate().getYear()+1900, production2.getDate().getMonth(), production2.getDate().getDate());

                Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
                JComboBox warehouse = new JComboBox(warehouselist);
                
                warehouse.setEnabled(false);

                for(int counter = 0; counter < warehouselist.length; counter++){
                    if(production2table.getValueAt(production2table.getSelectedRow(), 3).toString().equals(warehouselist[counter])){
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
                    production2Controller.editProduction2(ptm, id, (Date)datePicker.getModel().getValue(), 
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
                int id = Integer.parseInt(production2table.getValueAt(production2table.getSelectedRow(), 0).toString());
            
                JPanel panel = (JPanel)this.getParent().getParent();
                panel.remove(panel.getComponent(1));
                panel.add(new Production2DetailTab(id));
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
                int id = Integer.parseInt(production2table.getValueAt(production2table.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Hapus pindah barang ini?");
                JDialog dialog = jop.createDialog(null, "Hapus Pindah Barang");
                dialog.show();
                if(jop.getValue() == null){

                }
                else if(jop.getValue().toString().equals("0")){
                    boolean result = production2Controller.deleteProduction2(id);

                    if(result == GlobalFields.FAIL){
                        JOptionPane.showMessageDialog(null, "Gagal menghapus pindah barang ~ jumlah barang tidak mencukupi");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Pindah Barang dihapus");

                        ptm.deleteRow(id);
                    }
                }
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + " " + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        production2table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                Production2 production2 = production2Controller.getProduction2(Integer.parseInt(production2table.getValueAt(production2table.getSelectedRow(), 0).toString()));
                
                notelabel.setText(production2.getNote());
                
                if(production2.isStatus() == GlobalFields.CHECKED){
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
        RowFilter<Production2TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
