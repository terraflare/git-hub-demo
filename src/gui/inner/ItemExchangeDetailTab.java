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
import controller.InvoiceSalesController;
import controller.ItemExchangeController;
import controller.ReturnSalesController;
import entity.InvoiceSales;
import entity.ItemExchange;
import entity.Item_Out;
import entity.ReturnSales;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.InvoiceSalesTableModel;
import tablemodel.ReturnSalesTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemExchangeDetailTab extends JTabbedPane{
    
    ItemExchangeController itemexchangeController;
    InvoiceSalesController invoicesalesController;
    ReturnSalesController returnsalesController;
    
    ItemExchange itemexchange;
    
    JPanel itemexchangedetail;
    JPanel notepanel;
    JPanel savepanel;
    
    JPanel rightpanel;
    JPanel leftpanel;
    JPanel middlepanel;
    
    MyLabel itemexchangelabel;
    JScrollPane notepane;
    Box leftbox;
    Box rightbox;
    
    MyButton savebutton;
    MyButton returnbutton;
    MyButton invoicebutton;
    MyButton productionlistbutton;
    
    MyLabel returnlabel;
    MyLabel invoicelabel;
    
    TableRowSorter<InvoiceSalesTableModel> sorter1;
    TableRowSorter<ReturnSalesTableModel> sorter2;
    MyTextField filtertf1;
    MyTextField filtertf2;
    
    public ItemExchangeDetailTab(int id){
        itemexchangeController = new ItemExchangeController();
        invoicesalesController = new InvoiceSalesController();
        returnsalesController = new ReturnSalesController();
        
        itemexchange = itemexchangeController.getItemExchange(id);
        
        leftbox = Box.createVerticalBox();
        rightbox = Box.createVerticalBox();
        
        itemexchangedetail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        savepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middlepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        itemexchangedetail.setPreferredSize(new Dimension(995, 450));
        
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        
        String detaillabel = "";
        
        detaillabel += "Tanggal : ";
        detaillabel += df1.format(itemexchange.getDate());
        detaillabel += "   Pengguna : ";
        detaillabel += itemexchange.getUser().getUsername();
        
        notepanel = new JPanel();
        notepanel.setPreferredSize(new Dimension (980, 100));
        notepanel.setLayout(new BoxLayout(notepanel, BoxLayout.Y_AXIS));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notepane = new JScrollPane();
        notepane.getViewport().add(notepanel, null);
        notepane.setBorder(BorderFactory.createTitledBorder("Barang"));
        
        Item_Out item = itemexchange.getReturnsales().getItem();
        
        notepanel.add(new JLabel("ID : " + item.getId()));
        notepanel.add(new JLabel("Nama : " + item.getName()));
        notepanel.add(new JLabel("Merek : " + item.getMerk().getName()));
        notepanel.add(new JLabel("Kemasan : " + item.getPackaging().getAmount() + " " + item.getPackaging().getUnit()));
        notepanel.add(new JLabel("Jumlah unit per karung : " + item.getSacksize()));
        
        String unit = String.format("%.1f", itemexchange.getReturnsales().getQuantity()%item.getSacksize());
        notepanel.add(new JLabel("Jumlah : " + (int)itemexchange.getReturnsales().getQuantity()/item.getSacksize() + " karung " + unit + " unit"));
        
        leftpanel.setPreferredSize(new Dimension (450, 250));
        leftpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Retur Produksi"),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        rightpanel.setPreferredSize(new Dimension (450, 250));
        rightpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Invoice Produksi"),
                BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        
        MyLabel nextlabel = new MyLabel(new MyImageIcon().getImage("gui/image/Next.png"), 64, 64);
        
        middlepanel.add(nextlabel);
        
        leftbox.add(leftpanel);
        
        rightbox.add(rightpanel);
        
        savepanel.setPreferredSize(new Dimension (980, 50));
        
        savebutton = new MyButton("Simpan");
        productionlistbutton = new MyButton("Kembali ke daftar tukar guling");
        returnbutton = new MyButton("Tambah / ganti retur");
        invoicebutton = new MyButton("Tambah / ganti faktur");
        
        returnlabel = new MyLabel("");
        
        if(itemexchange.getReturnsales() != null){
            returnlabel.setText("<HTML><FONT SIZE=4>ID RETUR : " + itemexchange.getReturnsales().getId() +
                    "<BR/>TANGGAL : " + df1.format(itemexchange.getDate()) +
                    "<BR/>GUDANG : " + itemexchange.getReturnsales().getWarehouse().getName() +
                    "<BR/>PENGGUNA : "+ itemexchange.getReturnsales().getUser().getUsername() + 
                    "</FONT></HMTL>");
        }
        
        leftpanel.add(returnlabel);
        JPanel leftdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftdummy.setPreferredSize(new Dimension (350, 30));
        leftdummy.add(returnbutton);
        leftpanel.add(leftdummy);
        
        invoicelabel = new MyLabel("");
        
        if(itemexchange.getInvoicesales() != null){
            invoicelabel.setText("<HTML><FONT SIZE=4>ID RETUR : " + itemexchange.getInvoicesales().getId() +
                    "<BR/>TANGGAL : " + df1.format(itemexchange.getDate()) +
                    "<BR/>GUDANG : " + itemexchange.getInvoicesales().getWarehouse().getName() +
                    "<BR/>PENGGUNA : "+ itemexchange.getInvoicesales().getUser().getUsername() + 
                    "</FONT></HMTL>");
        }
        
        rightpanel.add(invoicelabel);
        JPanel rightdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightdummy.setPreferredSize(new Dimension (350, 30));
        rightdummy.add(invoicebutton);
        rightpanel.add(rightdummy);
        
        savepanel.add(productionlistbutton);
        savepanel.add(savebutton);
        
        itemexchangedetail.add(notepane);
        itemexchangedetail.add(leftbox);
        itemexchangedetail.add(middlepanel);
        itemexchangedetail.add(rightbox);
        itemexchangedetail.add(savepanel);
        
        addTab(detaillabel, itemexchangedetail);
        
        productionlistbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralInvoiceTab(6));
            panel.revalidate();
            panel.repaint();
        });
        
        invoicebutton.addActionListener((ActionEvent e) -> {
            JScrollPane itemscrollpane = new JScrollPane();

            InvoiceSalesTableModel istm = new InvoiceSalesTableModel(invoicesalesController.getInvoiceSalesList());
            
            JTable invoicetable = new JTable(istm);
            invoicetable.getTableHeader().setReorderingAllowed(false);
            invoicetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sorter1 = new TableRowSorter<InvoiceSalesTableModel>(istm);
            
            sorter1.setSortsOnUpdates(true);
            
            invoicetable.setRowSorter(sorter1);
            
            itemscrollpane.getViewport().add(invoicetable, null);
            itemscrollpane.setPreferredSize(new Dimension(600, 350));
            
            JPanel upperpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            filtertf1 = new MyTextField("", 250);
            JLabel filterlabel = new JLabel("Cari");
            
            filtertf1.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    newFilter1();
                }
                public void insertUpdate(DocumentEvent e) {
                    newFilter1();
                }
                public void removeUpdate(DocumentEvent e) {
                    newFilter1();
                }
            });
            
            MyButton itemsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
            
            upperpanel.add(filterlabel);
            upperpanel.add(filtertf1);
            upperpanel.add(itemsearchbutton);
            
            final JComponent[] inputs = new JComponent[] {
                            upperpanel, itemscrollpane
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Faktur ke Tukar Guling");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    int invoiceid = Integer.parseInt(invoicetable.getValueAt(invoicetable.getSelectedRow(), 0).toString());
                    
                    InvoiceSales invoicesales = invoicesalesController.getInvoiceSales(invoiceid);
                    
                    itemexchange.setInvoicesales(invoicesales);
                    
                    invoicelabel.setText("<HTML><FONT SIZE=4>ID RETUR : " + itemexchange.getInvoicesales().getId() +
                    "<BR/>TANGGAL : " + df1.format(itemexchange.getDate()) +
                    "<BR/>GUDANG : " + itemexchange.getInvoicesales().getWarehouse().getName() +
                    "<BR/>PENGGUNA : "+ itemexchange.getInvoicesales().getUser().getUsername() + 
                    "</FONT></HMTL>");
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        returnbutton.addActionListener((ActionEvent e) -> {
            JScrollPane itemscrollpane = new JScrollPane();

            ReturnSalesTableModel istm = new ReturnSalesTableModel(returnsalesController.getReturnSalesList());
            
            JTable returntable = new JTable(istm);
            returntable.getTableHeader().setReorderingAllowed(false);
            returntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sorter2 = new TableRowSorter<ReturnSalesTableModel>(istm);
            
            sorter2.setSortsOnUpdates(true);
            
            returntable.setRowSorter(sorter2);
            
            itemscrollpane.getViewport().add(returntable, null);
            itemscrollpane.setPreferredSize(new Dimension(600, 350));
            
            JPanel upperpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            filtertf2 = new MyTextField("", 250);
            JLabel filterlabel = new JLabel("Cari");
            
            filtertf2.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    newFilter2();
                }
                public void insertUpdate(DocumentEvent e) {
                    newFilter2();
                }
                public void removeUpdate(DocumentEvent e) {
                    newFilter2();
                }
            });
            
            MyButton itemsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
            
            upperpanel.add(filterlabel);
            upperpanel.add(filtertf2);
            upperpanel.add(itemsearchbutton);
            
            final JComponent[] inputs = new JComponent[] {
                            upperpanel, itemscrollpane
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Faktur ke Tukar Guling");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    int returnid = Integer.parseInt(returntable.getValueAt(returntable.getSelectedRow(), 0).toString());
                    
                    ReturnSales returnsales = returnsalesController.getReturnSales(returnid);
                    
                    itemexchange.setReturnsales(returnsales);
                    
                    returnlabel.setText("<HTML><FONT SIZE=4>ID RETUR : " + itemexchange.getReturnsales().getId() +
                    "<BR/>TANGGAL : " + df1.format(itemexchange.getDate()) +
                    "<BR/>GUDANG : " + itemexchange.getReturnsales().getWarehouse().getName() +
                    "<BR/>PENGGUNA : "+ itemexchange.getReturnsales().getUser().getUsername() + 
                    "</FONT></HMTL>");
                    
                    Item_Out itemout = returnsales.getItem();
                    
                    notepanel.removeAll();
                    
                    notepanel.add(new JLabel("ID : " + itemout.getId()));
                    notepanel.add(new JLabel("Nama : " + itemout.getName()));
                    notepanel.add(new JLabel("Merek : " + itemout.getMerk().getName()));
                    notepanel.add(new JLabel("Kemasan : " + itemout.getPackaging().getAmount() + " " + itemout.getPackaging().getUnit()));
                    notepanel.add(new JLabel("Jumlah unit per karung : " + itemout.getSacksize()));
                    
                    notepanel.revalidate();
                    notepanel.repaint();
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        savebutton.addActionListener((ActionEvent e) -> {
            try{
                
                JOptionPane jop = new JOptionPane();
                    jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                    jop.setMessage("Simpan tukar guling ini?");
                    JDialog dialog = jop.createDialog(null, "Tukar Guling");
                    dialog.show();
                    if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    
                    boolean result = itemexchangeController.editItemExchange(itemexchange);
                    
                    if(result == GlobalFields.SUCCESS){
                        JPanel panel = (JPanel)this.getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new GeneralInvoiceTab(6));
                        panel.revalidate();
                        panel.repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan tukar guling ~ barang atau jumlah barang ke dua faktur tidak cocok");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan tukar guling");
            }
        });
    }
    
    private void newFilter1() {
        RowFilter<InvoiceSalesTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf1.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter1.setRowFilter(rf);
    }
    
    private void newFilter2() {
        RowFilter<ReturnSalesTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf2.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter2.setRowFilter(rf);
    }
}
