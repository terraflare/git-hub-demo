/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.ItemExchangeController;
import entity.ItemExchange;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.ItemExchangeTableModel;

/**
 *
 * @author Purnama
 */
public class ItemExchangeTab extends JPanel{
    
    private ItemExchangeController itemexchangeController;
    
    JPanel leftpanel;
    JPanel rightpanel;
    
    JPanel searchpanel;
    JPanel actionpanel;
    JPanel detailpanel;
    
    JScrollPane scrollpane;
    
    JTable table;
    
    MyTextField searchtextfield;
    
    MyButton searchbutton;
    MyButton deletebutton;
    MyButton detailbutton;
    
    ItemExchangeTableModel ietm;
    
    TableRowSorter<ItemExchangeTableModel> sorter;
    private final JPanel invoicesalespanel;
    private final JScrollPane invoicesalespane;
    private final JScrollPane returnsalespane;
    private final JPanel returnsalespanel;
    
    
    public ItemExchangeTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        
        itemexchangeController = new ItemExchangeController();
        
        scrollpane = new JScrollPane();
        
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel();
        searchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        detailpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        detailpanel.setBorder(BorderFactory.createTitledBorder(" Detil Tukar Guling "));
        
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
        
        searchpanel.setBorder(BorderFactory.createTitledBorder(" Cari "));
        actionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        ietm = new ItemExchangeTableModel(itemexchangeController.getItemExchangeList());
        table = new JTable(ietm);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(table.getRowHeight() + 10);
        sorter = new TableRowSorter<ItemExchangeTableModel>(ietm);
        sorter.setSortsOnUpdates(true);
        
        table.getTableHeader().setReorderingAllowed(false);
        
        table.setRowSorter(sorter);
        
        scrollpane.getViewport().add(table, null);
        scrollpane.setPreferredSize(new Dimension(600, 420));
        
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
        
        returnsalespanel = new JPanel();
        returnsalespanel.setLayout(new BoxLayout(returnsalespanel, BoxLayout.Y_AXIS));
        returnsalespanel.setPreferredSize(new Dimension(350, 100));
        returnsalespanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        returnsalespane = new JScrollPane();
        returnsalespane.getViewport().add(returnsalespanel, null);
        returnsalespane.setBorder(BorderFactory.createTitledBorder("Retur Penjualan"));
        
        invoicesalespanel = new JPanel();
        invoicesalespanel.setLayout(new BoxLayout(invoicesalespanel, BoxLayout.Y_AXIS));
        invoicesalespanel.setPreferredSize(new Dimension(350, 100));
        invoicesalespanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        invoicesalespane = new JScrollPane();
        invoicesalespane.getViewport().add(invoicesalespanel, null);
        invoicesalespane.setBorder(BorderFactory.createTitledBorder("Faktur Penjualan"));
        
        searchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        deletebutton = new MyButton(new MyImageIcon().getImage("gui/image/Delete.png"), "Hapus", 110, 28);
        detailbutton = new MyButton(new MyImageIcon().getImage("gui/image/Detail.png"), "Detil", 110, 28);
        
        leftpanel.add(scrollpane);
        
        rightpanel.add(searchpanel);
        rightpanel.add(returnsalespane);
        rightpanel.add(invoicesalespane);
        rightpanel.add(detailpanel);
        rightpanel.add(actionpanel);
        
        searchpanel.add(searchtextfield);
        searchpanel.add(searchbutton);
        
        detailpanel.add(detailbutton);
        
        actionpanel.add(deletebutton);
        
        add(leftpanel);
        add(rightpanel);
        
        detailbutton.addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            
                JPanel panel = (JPanel)this.getParent().getParent();
                panel.remove(panel.getComponent(1));
                panel.add(new ItemExchangeDetailTab(id));
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
                int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                
                JOptionPane jop = new JOptionPane();
                    jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                    jop.setMessage("Hapus tukar guling ini?");
                    JDialog dialog = jop.createDialog(null, "Hapus Tukar Guling");
                    dialog.show();
                    if(jop.getValue() == null){
                        
                    }
                    else if(jop.getValue().toString().equals("0")){
                        itemexchangeController.deleteItemExchange(id);
                        ietm.deleteRow(id);
                        JOptionPane.showMessageDialog(null, "Tukar Guling Dihapus");
                        
                        returnsalespanel.removeAll();
                        
                        invoicesalespanel.removeAll();
                        
                        returnsalespanel.revalidate();
                        returnsalespanel.repaint();

                        invoicesalespanel.revalidate();
                        invoicesalespanel.repaint();
                    }
            }
            catch(Exception exp){
                System.out.println(this.getClass().toString() + "~" + exp);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                
                ItemExchange itemexchange = itemexchangeController.getItemExchange(id);
                
                returnsalespanel.removeAll();
                
                if(itemexchange.getReturnsales() != null){
                    returnsalespanel.add(new JLabel("ID RETUR : " + itemexchange.getReturnsales().getId()));
                    returnsalespanel.add(new JLabel("TANGGAL : " + df1.format(itemexchange.getDate())));
                    returnsalespanel.add(new JLabel("GUDANG : " + itemexchange.getReturnsales().getWarehouse().getName()));
                    returnsalespanel.add(new JLabel("PENGGUNA : "+ itemexchange.getReturnsales().getUser().getUsername()));
                }
                
                invoicesalespanel.removeAll();
                
                if(itemexchange.getInvoicesales() != null){
                    invoicesalespanel.add(new JLabel("ID RETUR : " + itemexchange.getInvoicesales().getId()));
                    invoicesalespanel.add(new JLabel("TANGGAL : " + df1.format(itemexchange.getDate())));
                    invoicesalespanel.add(new JLabel("GUDANG : " + itemexchange.getInvoicesales().getWarehouse().getName()));
                    invoicesalespanel.add(new JLabel("PENGGUNA : "+ itemexchange.getInvoicesales().getUser().getUsername()));
                }
                
                returnsalespanel.revalidate();
                returnsalespanel.repaint();
                
                invoicesalespanel.revalidate();
                invoicesalespanel.repaint();
            }
        });
    }
    
    private void newFilter() {
        RowFilter<ItemExchangeTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(searchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}


