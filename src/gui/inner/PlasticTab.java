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
import controller.PlasticController;
import controller.WarehouseController;
import entity.Item_Out;
import entity.Plastic;
import entity.Plastic_Warehouse;
import entity.User;
import entity.Warehouse;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import tablemodel.PlasticTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class PlasticTab extends JTabbedPane{
    
    PlasticController plasticController;
    WarehouseController warehouseController;
    ItemOutController itemoutController;
    
    JPanel plasticlistleftpanel;
    JPanel plasticlistrightpanel;
    
    MyLabel plasticnotelabel;
    
    JPanel plasticsearchpanel;
    JPanel plasticdetailpanel;
    JPanel plasticactionpanel;
    
    JScrollPane plasticscrollpane;
    JScrollPane plasticscrollpane2;
    
    JTable plastictable;
    
    MyTextField plasticsearchtextfield;
    
    MyButton plasticsearchbutton;
    MyButton plasticaddbutton;
    MyButton plasticeditbutton;
    MyButton plasticdisablebutton;
    
    PlasticTableModel ptm;
    
    TableRowSorter<PlasticTableModel> sorter;
    
    JPanel plasticstockpanel;
    JScrollPane plasticstockpane;
    
    JPanel plasticsackpanel;
    MyButton plasticsackbutton;
    JScrollPane plasticsackpane;
    
    JPanel plasticlist;
    
    public PlasticTab(){
        super();
        
        plasticlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plasticlist.setPreferredSize(new Dimension(1000, 450));
        
        plasticController = new PlasticController();
        warehouseController = new WarehouseController();
        itemoutController = new ItemOutController();
        
        plasticnotelabel = new MyLabel("");
        
        plasticscrollpane = new JScrollPane();
        plasticscrollpane2 = new JScrollPane();
        
        plasticlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plasticlistrightpanel = new JPanel();
        plasticsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plasticdetailpanel = new JPanel();
        plasticactionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        plasticlistrightpanel.setLayout(new BoxLayout(plasticlistrightpanel, BoxLayout.Y_AXIS));
        plasticdetailpanel.setLayout(new BoxLayout(plasticdetailpanel, BoxLayout.Y_AXIS));
        plasticdetailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        plasticdetailpanel.add(plasticnotelabel);
        
        plasticsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Plastik "));
        plasticactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        plasticdetailpanel.setPreferredSize(new Dimension(350, 115));
        
        ptm = new PlasticTableModel(plasticController.getPlasticList());
        
        sorter = new TableRowSorter<PlasticTableModel>(ptm);
        sorter.setSortsOnUpdates(true);
        
        plastictable = new JTable(ptm);
        plastictable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plastictable.getTableHeader().setReorderingAllowed(false);
        
        plastictable.setRowSorter(sorter);
        
        plasticscrollpane.getViewport().add(plastictable, null);
        plasticscrollpane.setPreferredSize(new Dimension(600, 430));
        
        plasticscrollpane2.getViewport().add(plasticdetailpanel, null);
        plasticscrollpane2.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        plasticstockpanel = new JPanel();
        plasticstockpanel.setLayout(new BoxLayout(plasticstockpanel, BoxLayout.Y_AXIS));
        plasticstockpanel.setPreferredSize(new Dimension(350, 115));
        plasticstockpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        plasticstockpane = new JScrollPane();
        plasticstockpane.getViewport().add(plasticstockpanel, null);
        plasticstockpane.setBorder(BorderFactory.createTitledBorder("Stok"));
        
        plasticsackbutton = new MyButton("Lihat Barang Yang Menggunakan Plastik Ini");
        plasticsackpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        plasticsackpanel.setPreferredSize(new Dimension(350, 30));
        plasticsackpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        plasticsackpanel.add(plasticsackbutton);
        plasticsackpane = new JScrollPane();
        plasticsackpane.getViewport().add(plasticsackpanel, null);
        plasticsackpane.setBorder(BorderFactory.createTitledBorder("Detil"));
        
        plasticsearchtextfield = new MyTextField("", 250);
        
        plasticsearchtextfield.getDocument().addDocumentListener(
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
        
        plasticsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        plasticaddbutton = new MyButton(new MyImageIcon().getImage("gui/image/Add.png"), "Tambah", 110, 28);
        plasticeditbutton = new MyButton(new MyImageIcon().getImage("gui/image/Edit.png"), "Edit", 110, 28);
        plasticdisablebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Nonaktif", 110, 28);
        
        plasticactionpanel.add(plasticaddbutton);
        plasticactionpanel.add(plasticeditbutton);
        plasticactionpanel.add(plasticdisablebutton);
        
        plasticlist.add(plasticlistleftpanel);
        plasticlist.add(plasticlistrightpanel);
        
        plasticlistleftpanel.add(plasticscrollpane);
        
        plasticlistrightpanel.add(plasticsearchpanel);
        plasticlistrightpanel.add(plasticscrollpane2);
        plasticlistrightpanel.add(plasticstockpane);
        plasticlistrightpanel.add(plasticsackpane);
        plasticlistrightpanel.add(plasticactionpanel);
        
        plasticsearchpanel.add(plasticsearchtextfield);
        plasticsearchpanel.add(plasticsearchbutton);
    
        addTab("Plastik", plasticlist);
        
        plasticsackbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString());

                ArrayList<Item_Out> aio = itemoutController.getItemList(id);

                final JComponent[] inputs = new JComponent[aio.size()];

                for(int i = 0; i < aio.size(); i++){
                    Item_Out io = aio.get(i);

                    inputs[i] = new JLabel(i+1 + ". " + io.getName());
                }

                JOptionPane pane = new JOptionPane();
                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.PLAIN_MESSAGE);
                JDialog dialog = pane.createDialog(null, "Barang yang menggunakan plastik ini");
                dialog.show();
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        plasticaddbutton.addActionListener((ActionEvent e) -> {
            JTextField name = new JTextField();
            
            JTextArea note = new JTextArea(7, 10);
            
            JScrollPane sp = new JScrollPane();
            
            sp.setViewportView(note);
            final JComponent[] inputs = new JComponent[]{new JLabel("Nama"), name, 
                new JLabel("Catatan"), sp,};
            JOptionPane pane = new JOptionPane();
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = pane.createDialog(null, "Tambah Plastik");
            dialog.show();
            try{
                if(pane.getValue() == null){
                 
                }
                else if(name.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                }
                else if(pane.getValue().toString().equals("0")){
                    
                    plasticController.addPlastic(ptm, name.getText(),
                    note.getText());
                    
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Plastik sudah ada");
            }
        });
        
        plasticeditbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString());
                
                JTextField name = new JTextField(plastictable.getValueAt(plastictable.getSelectedRow(), 1).toString());
                
                JTextArea note = new JTextArea(plasticnotelabel.getText(), 7, 10);
            
                JScrollPane sp = new JScrollPane();

                sp.setViewportView(note);
                
                final JComponent[] inputs = new JComponent[]{new JLabel("Nama"), name, 
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

                        plasticController.editPlastic(ptm, id, name.getText(), 
                                note.getText());
                        
                        plasticnotelabel.setText(note.getText());
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
        
        plastictable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Plastic plastic = plasticController.getPlastic(Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString()));
                
                plasticnotelabel.setText(plastic.getNote());
                
                if(plastic.isActive() == GlobalFields.ACTIVE){
                    plasticdisablebutton.setText("Nonaktif");
                    plasticdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                }
                else{
                    plasticdisablebutton.setText("Aktif");
                    plasticdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                }
                
                User user = GlobalFields.USER;
                
                Set<Warehouse> warehouselist = user.getWarehouses();
                
                plasticstockpanel.removeAll();
                
                for(Warehouse war : warehouselist){
                    Plastic_Warehouse iow = warehouseController.getPlasticWarehouse(war, plastic);
                    
                    plasticstockpanel.add(new JLabel(war.getName() + " : " + 
                            iow.getStock()+ " lembar"));
                }
                
                plasticstockpanel.revalidate();
                plasticstockpanel.repaint();
            }
        });
        
        plasticdisablebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(plastictable.getValueAt(plastictable.getSelectedRow(), 0).toString());
                
                Plastic plastic = plasticController.getPlastic(id);
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                if(plastic.isActive() == GlobalFields.ACTIVE){
                    jop.setMessage("Nonaktifkan plastik ini?");
                }
                else{
                    jop.setMessage("Aktifkan plastik ini?");
                }
                JDialog dialog = jop.createDialog(null, "Ganti Status Plastik");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    plasticController.changeStatus(ptm, plastic);
                    
                    if(plastic.isActive() == GlobalFields.ACTIVE){
                        plasticdisablebutton.setText("Nonaktif");
                        plasticdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                    }
                    else{
                        plasticdisablebutton.setText("Aktif");
                        plasticdisablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
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
        RowFilter<PlasticTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(plasticsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
