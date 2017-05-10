/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.UserController;
import controller.WarehouseController;
import entity.User;
import entity.Warehouse;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.UserTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class UserTab extends JTabbedPane{
    
    UserController userController;
    WarehouseController warehouseController;
    
    JPanel userlist;
    
    JPanel listleftpanel;
    JPanel listrightpanel;
    
    JPanel searchpanel;
    JPanel detailpanel;
    JPanel changewarehousepanel;
    JPanel actionpanel;
    
    JTable usertable;
    
    JScrollPane jScrollPane;
    JScrollPane jScrollPane2;
    
    MyTextField searchtextfield;
    
    MyButton searchbutton;
    MyButton addbutton;
    MyButton editbutton;
    MyButton disablebutton;
    MyButton changebutton;
    
    UserTableModel utm;
    
    TableRowSorter<UserTableModel> sorter;
    
    public UserTab(){
        
        super();
        
        jScrollPane = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        
        userController = new UserController();
        warehouseController = new WarehouseController();
        
        userlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listrightpanel = new JPanel();
        searchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailpanel = new JPanel();
        actionpanel = new JPanel(new GridLayout(0, 3, 5, 5));
        changewarehousepanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        listrightpanel.setLayout(new BoxLayout(listrightpanel, BoxLayout.Y_AXIS));
        detailpanel.setLayout(new BoxLayout(detailpanel, BoxLayout.Y_AXIS));
        
        changewarehousepanel.setBorder(BorderFactory.createTitledBorder(" Ganti Wewenang Gudang "));
        searchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Pengguna "));
        actionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        userlist.setPreferredSize(new Dimension(995, 450));
        detailpanel.setPreferredSize(new Dimension(350, 250));
        detailpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        
        utm = new UserTableModel(userController.getUserList());
        sorter = new TableRowSorter<UserTableModel>(utm);
        sorter.setSortsOnUpdates(true);
        
        usertable = new JTable(utm);
        usertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usertable.getTableHeader().setReorderingAllowed(false);
        usertable.setRowHeight(usertable.getRowHeight() + 10);
        
        usertable.setRowSorter(sorter);
        
        jScrollPane.getViewport().add(usertable, null);
        jScrollPane.setPreferredSize(new Dimension(600, 435));
        
        jScrollPane2.getViewport().add(detailpanel, null);
        jScrollPane2.setBorder(BorderFactory.createTitledBorder(" Detil Pengguna "));
        
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
        disablebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Nonaktif", 110, 28);
        changebutton = new MyButton("Ganti");
        
        changewarehousepanel.add(changebutton);
        
        actionpanel.add(addbutton);
        actionpanel.add(editbutton);
        actionpanel.add(disablebutton);
        
        userlist.add(listleftpanel);
        userlist.add(listrightpanel);
        
        listleftpanel.add(jScrollPane);
        
        listrightpanel.add(searchpanel);
        listrightpanel.add(jScrollPane2);
        listrightpanel.add(changewarehousepanel);
        listrightpanel.add(actionpanel);
        
        searchpanel.add(searchtextfield);
        searchpanel.add(searchbutton);
        
        addTab("Daftar Pengguna", userlist);
        
        addbutton.addActionListener((ActionEvent e) -> {
            JTextField username = new JTextField();
            JTextField password = new JPasswordField();
            
            String[] roles = GlobalFields.USER_ROLE;
            
            JComboBox rolelist = new JComboBox(roles);
            
            final JComponent[] inputs = new JComponent[] {
                            new JLabel("Nama"),
                            username,
                            new JLabel("Kata Sandi"),
                            password,
                            new JLabel("Wewenang"),
                            rolelist
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Pengguna");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    
                    if(username.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    else if(password.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Password tidak boleh kosong");
                    }
                    else{
                        userController.addUser(utm, username.getText(), 
                                password.getText(), 
                                String.valueOf(rolelist.getSelectedItem()));
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Pengguna sudah ada");
            }
        });
        
        editbutton.addActionListener((ActionEvent e) -> {
            
            try{
                int id = Integer.parseInt(usertable.getValueAt(usertable.getSelectedRow(), 0).toString());
                
                JTextField username = new JTextField(usertable.getValueAt(usertable.getSelectedRow(), 1).toString());
                JTextField password = new JPasswordField(usertable.getValueAt(usertable.getSelectedRow(), 2).toString());

                String[] roles = GlobalFields.USER_ROLE;

                JComboBox rolelist = new JComboBox(roles);
                
                for(int counter = 0; counter < roles.length; counter++){
                    if(usertable.getValueAt(usertable.getSelectedRow(), 3).toString().equals(roles[counter])){
                        rolelist.setSelectedIndex(counter);
                    }
                }
                
                final JComponent[] inputs = new JComponent[] {
                                new JLabel("Nama"),
                                username,
                                new JLabel("Kata Sandi"),
                                password,
                                new JLabel("Wewenang"),
                                rolelist
                };
                
                JOptionPane pane = new JOptionPane();

                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);

                JDialog dialog = pane.createDialog(null, "Edit Pengguna");
                dialog.show();

                try{
                    if(pane.getValue() == null){
                    
                    }
                    else if(pane.getValue().toString().equals("0")){
                        
                        if(username.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                        else if(password.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong");
                        }
                        else{
                            userController.editUser(utm, id, username.getText(), 
                                    password.getText(), 
                                    String.valueOf(rolelist.getSelectedItem()));
                        }
                    }
                }
                catch(Exception exc){
                    System.out.println(this.getClass().toString() + "~" + exc);
                    JOptionPane.showMessageDialog(null, "Pengguna sudah ada");
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        disablebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(usertable.getValueAt(usertable.getSelectedRow(), 0).toString());
                
                User user = userController.getUser(id);
                
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                if(user.isActive() == GlobalFields.ACTIVE){
                    jop.setMessage("Nonaktifkan pengguna ini?");
                }
                else{
                    jop.setMessage("Aktifkan pengguna ini?");
                }
                JDialog dialog = jop.createDialog(null, "Ganti Status Pengguna");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    userController.changeStatus(utm, user);
                    
                    if(user.isActive() == GlobalFields.ACTIVE){
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
        
        changebutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(usertable.getValueAt(usertable.getSelectedRow(), 0).toString());
            
                User user = userController.getUser(id);

                List<Warehouse> warehouselist = warehouseController.getWarehouseList();

                int size = warehouselist.size();

                JPanel upperpanel = new JPanel(new GridLayout(size/2,2));

                ArrayList arr = new ArrayList();

                for(Warehouse warehouse : warehouselist){
                    JCheckBox jcb = new JCheckBox(warehouse.getName());

                    for(Warehouse tempwar : user.getWarehouses()){
                        if(tempwar.getName().equals(warehouse.getName())){
                            jcb.setSelected(true);
                        }
                    }
                    arr.add(jcb);
                    upperpanel.add(jcb);
                }

                final JComponent[] inputs = new JComponent[] {
                            new JLabel("Gudang :"),
                    upperpanel
                    };

                JOptionPane pane = new JOptionPane();

                pane.setMessage(inputs);
                pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);

                JDialog dialog = pane.createDialog(null, "Akses Gudang");
                dialog.show();

                if(pane.getValue() == null){

                }
                else if(pane.getValue().toString().equals("0")){

                    ArrayList ls = new ArrayList();

                    for(Object o : arr){
                        JCheckBox jcb = (JCheckBox)o;
                        if(jcb.getSelectedObjects() != null){
                            ls.add(jcb.getSelectedObjects()[0]);
                        }
                    }

                    User u = userController.editUser(ls, user);
                    
                    if(u.getId() == GlobalFields.USER.getId()){
                        GlobalFields.USER = u;
                    }

                    detailpanel.removeAll();

                    detailpanel.add(new JLabel("Pengguna ini diijinkan untuk mengakses gudang :"));

                    Set<Warehouse> set = user.getWarehouses();

                    for(Warehouse w : set){
                        detailpanel.add(new JLabel("- " + w.getName()));
                    }

                    detailpanel.revalidate();
                    detailpanel.repaint();
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + "~" + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        usertable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                detailpanel.removeAll();
                
                detailpanel.add(new JLabel("Pengguna ini diijinkan untuk mengakses gudang :"));
                
                User user = userController.getUser(Integer.parseInt(usertable.getValueAt(usertable.getSelectedRow(), 0).toString()));
                
                Set<Warehouse> set = user.getWarehouses();
                
                for(Warehouse w : set){
                    detailpanel.add(new JLabel("- " + w.getName()));
                }
                
                detailpanel.revalidate();
                detailpanel.repaint();
                
                if(user.getRole().equals("SUPERADMIN")){
                    disablebutton.setEnabled(false);
                    disablebutton.setText("Nonaktif");
                    disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                }
                else{
                    disablebutton.setEnabled(true);
                    if(user.isActive() == GlobalFields.ACTIVE){
                        disablebutton.setText("Nonaktif");
                        disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Delete.png"));
                    }
                    else{
                        disablebutton.setText("Aktif");
                        disablebutton.setIcon(new MyImageIcon().getImage("gui/image/Ok.png"));
                    }
                }
                
            }
        });
    }
    
    private void newFilter() {
        RowFilter<UserTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
