/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import controller.WarehouseController;
import entity.Warehouse;
import gui.frame.BasicItemPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.WarehouseTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class WarehouseTab extends BasicItemPanel{
    
    JPanel actionpanel;
    
    MyButton addbutton;
    MyButton editbutton;
    MyButton disablebutton;
    
    WarehouseTableModel wtm;
    
    WarehouseController warehouseController;
    
    TableRowSorter<WarehouseTableModel> sorter;
    
    
    public WarehouseTab(){
        
        super();
        
        warehouseController = new WarehouseController();
        
        actionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        actionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        wtm = new WarehouseTableModel(warehouseController.getWarehouseList());
        sorter = new TableRowSorter<WarehouseTableModel>(wtm);
        sorter.setSortsOnUpdates(true);
        
        maintable.setModel(wtm);
        maintable.setRowSorter(sorter);
        
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
        
        addbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        editbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        disablebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Nonaktif", 110, 28);
        
        actionpanel.add(addbutton);
        actionpanel.add(editbutton);
        actionpanel.add(disablebutton);
        
        rightpanel.add(actionpanel);
        
        addbutton.addActionListener((ActionEvent e) -> {
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
            
            JDialog dialog = pane.createDialog(null, "Tambah Gudang");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    if(name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else{
                        warehouseController.addWarehouse(wtm, name.getText(), note.getText());
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Gudang sudah ada");
            } 
        });
        
        editbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString());
                
                JTextField name = new JTextField(maintable.getValueAt(maintable.getSelectedRow(), 1).toString());
                JTextArea note = new JTextArea(7, 10);
                note.setText(notelabel.getText());
                
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

                JDialog dialog = pane.createDialog(null, "Edit Gudang");
                dialog.show();

                try{
                    if(pane.getValue() == null){
                    
                    }
                    else if(pane.getValue().toString().equals("0")){
                        
                        if(name.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                        else{
                            warehouseController.editWarehouse(wtm, id, name.getText(), note.getText());

                            notelabel.setText(note.getText());
                        }
                    }
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() + "~" + exc);
                    JOptionPane.showMessageDialog(null, "Gudang sudah ada");
                    
                }
            
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        disablebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString());
                
                Warehouse warehouse = warehouseController.getWarehouse(id);
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                if(warehouse.isActive() == GlobalFields.ACTIVE){
                    jop.setMessage("Nonaktifkan gudang ini?");
                }
                else{
                    jop.setMessage("Aktifkan gudang ini?");
                }
                JDialog dialog = jop.createDialog(null, "Ganti Status Gudang");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    warehouseController.changeStatus(wtm, warehouse);
                    
                    if(warehouse.isActive() == GlobalFields.ACTIVE){
                        disablebutton.setText("Nonaktif");
                        disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                    }
                    else{
                        disablebutton.setText("Aktif");
                        disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        maintable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Warehouse warehouse = warehouseController.getWarehouse(Integer.parseInt(maintable.getValueAt(maintable.getSelectedRow(), 0).toString()));
                
                notelabel.setText(warehouse.getNote());
                
                if(warehouse.isActive() == GlobalFields.ACTIVE){
                    disablebutton.setText("Nonaktif");
                    disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                }
                else{
                    disablebutton.setText("Aktif");
                    disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                }
            }
            
        });
    }
    
    private void newFilter() {
        RowFilter<WarehouseTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
