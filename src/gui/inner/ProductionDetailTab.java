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
import controller.ItemInController;
import controller.ItemOutController;
import controller.ProductionController;
import entity.Item_In;
import entity.Item_Out;
import entity.Production;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
import tablemodel.ItemInTableModel;
import tablemodel.ItemOutTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ProductionDetailTab extends JTabbedPane{
    
    ProductionController productionController;
    ItemOutController itemoutController;
    ItemInController iteminController;
    
    Production production;
    
    JPanel productiondetail;
    JPanel notepanel;
    JPanel savepanel;
    
    JPanel rightpanel;
    JPanel leftpanel;
    JPanel middlepanel;
    
    MyLabel productionlabel;
    MyLabel notelabel;
    JScrollPane notepane;
    Box leftbox;
    Box rightbox;
    JPanel exoutquantitypanel;
    JPanel outquantitypanel;
    JPanel inquantitypanel;
    MyLabel inquantitylabel;
    MyLabel exoutquantitylabel;
    MyLabel outquantitylabel;
    MyTextField inquantitytf;
    MyTextField exoutsackquantitytf;
    MyTextField outsackquantitytf;
    MyTextField exoutunitquantitytf;
    MyTextField outunitquantitytf;
    
    MyButton savebutton;
    MyButton addinbutton;
    MyButton addoutbutton;
    MyButton productionlistbutton;
    
    TableRowSorter<ItemOutTableModel> sorter;
    MyTextField filtertf;
    
    TableRowSorter<ItemInTableModel> sorter2;
    MyTextField filtertf2;
    MyLabel iteminlabel;
    MyLabel itemoutlabel;
    
    public ProductionDetailTab(int id){
        productionController = new ProductionController();
        itemoutController = new ItemOutController();
        iteminController = new ItemInController();
        
        production = productionController.getProduction(id);
        
        leftbox = Box.createVerticalBox();
        rightbox = Box.createVerticalBox();
        
        productiondetail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        savepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middlepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        inquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exoutquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        outquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        inquantitylabel = new MyLabel("Jumlah : ");
        exoutquantitylabel = new MyLabel("Jumlah yang diharapkan : ");
        outquantitylabel = new MyLabel("Jumlah sebenarnya : ");
        
        inquantitytf = new MyTextField("0", 100);
        exoutsackquantitytf = new MyTextField("0", 100);
        outsackquantitytf = new MyTextField("0", 100);
        exoutunitquantitytf = new MyTextField("0.0", 100);
        outunitquantitytf = new MyTextField("0.0", 100);
        
        inquantitypanel.setPreferredSize(new Dimension(450, 65));
        inquantitypanel.add(inquantitylabel);
        inquantitypanel.add(inquantitytf);
        inquantitypanel.add(new MyLabel("Karung "));
        
        exoutquantitypanel.add(exoutquantitylabel);
        exoutquantitypanel.add(exoutsackquantitytf);
        exoutquantitypanel.add(new MyLabel("Karung "));
        exoutquantitypanel.add(exoutunitquantitytf);
        exoutquantitypanel.add(new MyLabel("Unit "));
        
        outquantitypanel.add(outquantitylabel);
        outquantitypanel.add(outsackquantitytf);
        outquantitypanel.add(new MyLabel("Karung "));
        outquantitypanel.add(outunitquantitytf);
        outquantitypanel.add(new MyLabel("Unit "));
        
        productiondetail.setPreferredSize(new Dimension(995, 450)); 
        
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        
        notepanel.setBorder(BorderFactory.createTitledBorder(df1.format(production.getDate())));
        
        String detaillabel = "";
        
        detaillabel += "Tanggal : ";
        detaillabel += df1.format(production.getDate());
        detaillabel += "    Gudang : ";
        detaillabel += production.getWarehouse().getName();
        detaillabel += "   Pengguna : ";
        detaillabel += production.getUser().getUsername();
        detaillabel += "   Status : ";
        if(production.isStatus() == GlobalFields.CHECKED){
            detaillabel += "SUDAH DIPERIKSA";
        }
        else{
            detaillabel += "BELUM DIPERIKSA";
        }
        
        notelabel = new MyLabel(production.getNote());
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel.setPreferredSize(new Dimension (980, 50));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notepanel.add(notelabel);
        notepane = new JScrollPane();
        notepane.getViewport().add(notepanel, null);
        notepane.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        leftpanel.setPreferredSize(new Dimension (450, 250));
        leftpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Barang Masuk (Curah)"),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        rightpanel.setPreferredSize(new Dimension (450, 250));
        rightpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Barang Produksi"),
                BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        
        MyLabel nextlabel = new MyLabel(new MyImageIcon().getImage("gui/image/Next.png"), 64, 64);
        
        middlepanel.add(nextlabel);
        
        leftbox.add(leftpanel);
        leftbox.add(inquantitypanel);
        
        rightbox.add(rightpanel);
        rightbox.add(exoutquantitypanel);
        rightbox.add(outquantitypanel);
        
        savepanel.setPreferredSize(new Dimension (980, 50));
        
        savebutton = new MyButton("Simpan");
        productionlistbutton = new MyButton("Kembali ke daftar produksi");
        addinbutton = new MyButton("Tambah / ganti barang");
        addoutbutton = new MyButton("Tambah / ganti barang");
        
        iteminlabel = new MyLabel("");
        
        if(production.getItemin() != null){
            iteminlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "+ production.getItemin().getId() +"<BR/>NAMA BARANG : "+ 
                production.getItemin().getName() +"<BR/>KUALITAS : "+ production.getItemin().getQuality().getCategory().getName() + " "+
                production.getItemin().getQuality().getName() + "</FONT></HMTL>");
            
            inquantitytf.setText(String.valueOf(production.getInquantity()));
        }
        
        leftpanel.add(iteminlabel);
        JPanel leftdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftdummy.setPreferredSize(new Dimension (350, 30));
        leftdummy.add(addinbutton);
        leftpanel.add(leftdummy);
        
        itemoutlabel = new MyLabel("");
        
        if(production.getItemout() != null){
            itemoutlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "+ production.getItemout().getId() +"<BR/>NAMA BARANG : "+ 
                production.getItemout().getName() +"<BR/>MEREK : "+ production.getItemout().getMerk().getName() + "<BR/>"
                + "KEMASAN : "+ production.getItemout().getPackaging().getAmount() + " " + production.getItemout().getPackaging().getUnit()
                + "<BR/>JUMLAH UNIT PER KARUNG : "+ production.getItemout().getSacksize() +"</FONT></HMTL>");
            
            int sacksize = production.getItemout().getSacksize();
            exoutsackquantitytf.setText(String.valueOf((int)production.getInputquantity()/sacksize));
            outsackquantitytf.setText(String.valueOf((int)production.getOutquantity()/sacksize));
            
            String unit1 = String.format("%.1f", production.getInputquantity()%sacksize);
            String unit2 = String.format("%.1f", production.getOutquantity()%sacksize);
            
            exoutunitquantitytf.setText(unit1);
            outunitquantitytf.setText(unit2);
        }
        
        rightpanel.add(itemoutlabel);
        JPanel rightdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightdummy.setPreferredSize(new Dimension (350, 30));
        rightdummy.add(addoutbutton);
        rightpanel.add(rightdummy);
        
        savepanel.add(productionlistbutton);
        savepanel.add(savebutton);
        
        productiondetail.add(notepane);
        productiondetail.add(leftbox);
        productiondetail.add(middlepanel);
        productiondetail.add(rightbox);
        productiondetail.add(savepanel);
        
        addTab(detaillabel, productiondetail);
        
        if(production.isStatus() == GlobalFields.CHECKED){
            addinbutton.setEnabled(false);
            addoutbutton.setEnabled(false);
            savebutton.setEnabled(false);
            inquantitytf.setEditable(false);
            exoutsackquantitytf.setEditable(false);
            outsackquantitytf.setEditable(false);
            exoutunitquantitytf.setEditable(false);
            outunitquantitytf.setEditable(false);
        }
        
        productionlistbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralProductionTab(1));
            panel.revalidate();
            panel.repaint();
        });
        
        addoutbutton.addActionListener((ActionEvent e) -> {
            JScrollPane itemscrollpane = new JScrollPane();

            ItemOutTableModel iotm = new ItemOutTableModel(itemoutController.getActiveItemList());
            
            JTable itemtable = new JTable(iotm);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            itemtable.getTableHeader().setReorderingAllowed(false);
            
            sorter = new TableRowSorter<ItemOutTableModel>(iotm);
            
            sorter.setSortsOnUpdates(true);
            
            itemtable.setRowSorter(sorter);
            
            itemscrollpane.getViewport().add(itemtable, null);
            itemscrollpane.setPreferredSize(new Dimension(600, 350));
            
            JPanel upperpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            filtertf = new MyTextField("", 250);
            JLabel filterlabel = new JLabel("Cari");
            
            filtertf.getDocument().addDocumentListener(
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
            
            MyButton itemsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
            
            upperpanel.add(filterlabel);
            upperpanel.add(filtertf);
            upperpanel.add(itemsearchbutton);
            
            final JComponent[] inputs = new JComponent[] {
                            upperpanel, itemscrollpane
            };
            JOptionPane pane = new JOptionPane();
            
            pane.setMessage(inputs);
            pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            
            JDialog dialog = pane.createDialog(null, "Tambah Barang ke Produksi");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    
                    Item_Out itemout = itemoutController.getItem(itemid);
                    
                    production.setItemout(itemout);
                    
                    itemoutlabel.setText("<HTML><FONT SIZE=4>ID BARANG: "+ itemout.getId() +"<BR/>NAMA BARANG : "+ 
                            itemout.getName() +"<BR/>MEREK : "+ itemout.getMerk().getName() + "<BR/>"
                            + "KEMASAN : "+ itemout.getPackaging().getAmount() + " " + itemout.getPackaging().getUnit()
                            + "<BR/>JUMLAH UNIT PER KARUNG : "+ itemout.getSacksize() +"</FONT></HMTL>");
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih");
            }
        });
        
        addinbutton.addActionListener((ActionEvent e) -> {
            JScrollPane itemscrollpane = new JScrollPane();

            ItemInTableModel iitm = new ItemInTableModel(iteminController.getActiveItemList());
            
            JTable itemtable = new JTable(iitm);
            itemtable.getTableHeader().setReorderingAllowed(false);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sorter2 = new TableRowSorter<ItemInTableModel>(iitm);
            
            sorter2.setSortsOnUpdates(true);
            
            itemtable.setRowSorter(sorter2);
            
            itemscrollpane.getViewport().add(itemtable, null);
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
            
            JDialog dialog = pane.createDialog(null, "Tambah Barang ke Produksi");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    
                    Item_In itemin = iteminController.getItem(itemid);
                    
                    production.setItemin(itemin);
                    
                    iteminlabel.setText("<HTML><FONT SIZE=4>ID BARANG: "+ itemin.getId() +"<BR/>NAMA BARANG : "+ 
                            itemin.getName() +"<BR/>KUALITAS : "+ itemin.getQuality().getCategory().getName() + " "+
                            itemin.getQuality().getName() + "</FONT></HMTL>");
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
                    jop.setMessage("Simpan produksi ini?");
                    JDialog dialog = jop.createDialog(null, "Simpan Produksi");
                    dialog.show();
                    if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    int inquantity = Integer.parseInt(inquantitytf.getText());
                    float exoutsackquantity = Float.parseFloat(exoutsackquantitytf.getText());
                    float outsackquantity = Float.parseFloat(outsackquantitytf.getText());
                    float exoutunitquantity = Float.parseFloat(exoutunitquantitytf.getText());
                    float outunitquantity = Float.parseFloat(outunitquantitytf.getText());

                    float exoutquantity = exoutsackquantity*production.getItemout().getSacksize()+exoutunitquantity;
                    float outquantity = outsackquantity*production.getItemout().getSacksize()+outunitquantity;

                    production.setInquantity(inquantity);
                    production.setInputquantity(exoutquantity);
                    production.setOutquantity(outquantity);
                    
                    boolean result = productionController.editProduction(production);
                    
                    if(result == GlobalFields.SUCCESS){
                        JPanel panel = (JPanel)this.getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new GeneralProductionTab(1));
                        panel.revalidate();
                        panel.repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan produksi ~ jumlah barang masuk atau keluar tidak mencukupi");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan produksi");
            }
        });
    }
    
    private void newFilter2() {
        RowFilter<ItemInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf2.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter2.setRowFilter(rf);
    }
    
    private void newFilter() {
        RowFilter<ItemOutTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
