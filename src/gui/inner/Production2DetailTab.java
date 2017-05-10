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
import controller.Production2Controller;
import entity.Item_Out;
import entity.Production2;
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
import tablemodel.ItemOutTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production2DetailTab extends JTabbedPane{
    
    Production2Controller production2Controller;
    ItemOutController itemoutController;
    ItemInController iteminController;
    
    Production2 production2;
    
    JPanel production2detail;
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
    JPanel outquantitypanel;
    JPanel inquantitypanel;
    MyLabel inquantitylabel;
    MyLabel outquantitylabel;
    MyTextField insackquantitytf;
    MyTextField inunitquantitytf;
    MyTextField outsackquantitytf;
    MyTextField outunitquantitytf;
    
    MyButton savebutton;
    MyButton addinbutton;
    MyButton addoutbutton;
    MyButton productionlistbutton;
    
    TableRowSorter<ItemOutTableModel> sorter;
    MyTextField filtertf;
    
    TableRowSorter<ItemOutTableModel> sorter2;
    MyTextField filtertf2;
    MyLabel iteminlabel;
    MyLabel itemoutlabel;
    
    public Production2DetailTab(int id){
        production2Controller = new Production2Controller();
        itemoutController = new ItemOutController();
        iteminController = new ItemInController();
        
        production2 = production2Controller.getProduction2(id);
        
        leftbox = Box.createVerticalBox();
        rightbox = Box.createVerticalBox();
        
        production2detail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        savepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middlepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        inquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        outquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        inquantitylabel = new MyLabel("Jumlah : ");
        outquantitylabel = new MyLabel("Jumlah : ");
        
        insackquantitytf = new MyTextField("0", 100);
        inunitquantitytf = new MyTextField("0.0", 100);
        outsackquantitytf = new MyTextField("0", 100);
        outunitquantitytf = new MyTextField("0.0", 100);
         
        inquantitypanel.add(inquantitylabel);
        inquantitypanel.add(insackquantitytf);
        inquantitypanel.add(new MyLabel("Karung "));
        inquantitypanel.add(inunitquantitytf);
        inquantitypanel.add(new MyLabel("Unit "));
        
        outquantitypanel.add(outquantitylabel);
        outquantitypanel.add(outsackquantitytf);
        outquantitypanel.add(new MyLabel("Karung "));
        outquantitypanel.add(outunitquantitytf);
        outquantitypanel.add(new MyLabel("Unit "));
        
        production2detail.setPreferredSize(new Dimension(995, 450)); 
        
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        
        notepanel.setBorder(BorderFactory.createTitledBorder(df1.format(production2.getDate())));
        
        String detaillabel = "";
        
        detaillabel += "Tanggal : ";
        detaillabel += df1.format(production2.getDate());
        detaillabel += "    Gudang : ";
        detaillabel += production2.getWarehouse().getName();
        detaillabel += "   Pengguna : ";
        detaillabel += production2.getUser().getUsername();
        detaillabel += "   Status : ";
        if(production2.isStatus() == GlobalFields.CHECKED){
            detaillabel += "SUDAH DIPERIKSA";
        }
        else{
            detaillabel += "BELUM DIPERIKSA";
        }
        
        notelabel = new MyLabel(production2.getNote());
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel.setPreferredSize(new Dimension (980, 50));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notepanel.add(notelabel);
        notepane = new JScrollPane();
        notepane.getViewport().add(notepanel, null);
        notepane.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        leftpanel.setPreferredSize(new Dimension (450, 250));
        leftpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Barang Pindah Masuk"),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        rightpanel.setPreferredSize(new Dimension (450, 250));
        rightpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Barang Pindah Hasil"),
                BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        
        MyLabel nextlabel = new MyLabel(new MyImageIcon().getImage("gui/image/Next.png"), 64, 64);
        
        middlepanel.add(nextlabel);
        
        leftbox.add(leftpanel);
        leftbox.add(inquantitypanel);
        
        rightbox.add(rightpanel);
        rightbox.add(outquantitypanel);
        
        savepanel.setPreferredSize(new Dimension (980, 50));
        
        savebutton = new MyButton("Simpan");
        productionlistbutton = new MyButton("Kembali ke daftar pindah barang");
        addinbutton = new MyButton("Tambah / ganti barang");
        addoutbutton = new MyButton("Tambah / ganti barang");
        
        iteminlabel = new MyLabel("");
        
        if(production2.getItemin() != null){
            iteminlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "+ production2.getItemin().getId() +"<BR/>NAMA BARANG : "+ 
                production2.getItemin().getName() +"<BR/>MEREK : "+ production2.getItemin().getMerk().getName() + "<BR/>"
                + "KEMASAN : "+ production2.getItemin().getPackaging().getAmount() + " " + production2.getItemin().getPackaging().getUnit()
                + "<BR/>JUMLAH UNIT PER KARUNG : "+ production2.getItemin().getSacksize() +"</FONT></HMTL>");
            
            int sacksize = production2.getItemin().getSacksize();
            insackquantitytf.setText(String.valueOf((int)production2.getInquantity()/sacksize));
            
            String unit = String.format("%.1f", production2.getInquantity()%sacksize);
            
            inunitquantitytf.setText(unit);
        }
        
        leftpanel.add(iteminlabel);
        JPanel leftdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftdummy.setPreferredSize(new Dimension (350, 30));
        leftdummy.add(addinbutton);
        leftpanel.add(leftdummy);
        
        itemoutlabel = new MyLabel("");
        
        if(production2.getItemout() != null){
            itemoutlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "+ production2.getItemout().getId() +"<BR/>NAMA BARANG : "+ 
                production2.getItemout().getName() +"<BR/>MEREK : "+ production2.getItemout().getMerk().getName() + "<BR/>"
                + "KEMASAN : "+ production2.getItemout().getPackaging().getAmount() + " " + production2.getItemout().getPackaging().getUnit()
                + "<BR/>JUMLAH UNIT PER KARUNG : "+ production2.getItemout().getSacksize() +"</FONT></HMTL>");
            
            int sacksize = production2.getItemout().getSacksize();
            outsackquantitytf.setText(String.valueOf((int)production2.getOutquantity()/sacksize));
            
            String unit = String.format("%.1f", production2.getOutquantity()%sacksize);
            
            outunitquantitytf.setText(unit);
        }
        
        rightpanel.add(itemoutlabel);
        JPanel rightdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightdummy.setPreferredSize(new Dimension (350, 30));
        rightdummy.add(addoutbutton);
        rightpanel.add(rightdummy);
        
        savepanel.add(productionlistbutton);
        savepanel.add(savebutton);
        
        production2detail.add(notepane);
        production2detail.add(leftbox);
        production2detail.add(middlepanel);
        production2detail.add(rightbox);
        production2detail.add(savepanel);
        
        addTab(detaillabel, production2detail);
        
        if(production2.isStatus() == GlobalFields.CHECKED){
            addinbutton.setEnabled(false);
            addoutbutton.setEnabled(false);
            savebutton.setEnabled(false);
            insackquantitytf.setEditable(false);
            inunitquantitytf.setEditable(false);
            outsackquantitytf.setEditable(false);
            outunitquantitytf.setEditable(false);
        }
        
        productionlistbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralProductionTab(2));
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
            
            JDialog dialog = pane.createDialog(null, "Tambah Barang ke Pindahan");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    
                    Item_Out itemout = itemoutController.getItem(itemid);
                    
                    production2.setItemout(itemout);
                    
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

            ItemOutTableModel iotm = new ItemOutTableModel(itemoutController.getActiveItemList());
            
            JTable itemtable = new JTable(iotm);
            itemtable.getTableHeader().setReorderingAllowed(false);
            itemtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sorter2 = new TableRowSorter<ItemOutTableModel>(iotm);
            
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
            
            JDialog dialog = pane.createDialog(null, "Tambah Barang ke Pindahan");
            dialog.show();
            
            try{
                if(pane.getValue() == null){
                    
                }
                else if(pane.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(itemtable.getValueAt(itemtable.getSelectedRow(), 0).toString());
                    
                    Item_Out itemout = itemoutController.getItem(itemid);
                    
                    production2.setItemin(itemout);
                    
                    iteminlabel.setText("<HTML><FONT SIZE=4>ID BARANG: "+ itemout.getId() +"<BR/>NAMA BARANG : "+ 
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
        
        savebutton.addActionListener((ActionEvent e) -> {
            try{
                
                JOptionPane jop = new JOptionPane();
                    jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                    jop.setMessage("Simpan pindah barang ini?");
                    JDialog dialog = jop.createDialog(null, "Simpan Pindahan");
                    dialog.show();
                    if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    float insackquantity = Float.parseFloat(insackquantitytf.getText());
                    float inunitquantity = Float.parseFloat(inunitquantitytf.getText());
                    float outsackquantity = Float.parseFloat(outsackquantitytf.getText());
                    float outunitquantity = Float.parseFloat(outunitquantitytf.getText());

                    float inquantity = insackquantity*production2.getItemin().getSacksize()+inunitquantity;
                    float outquantity = outsackquantity*production2.getItemout().getSacksize()+outunitquantity;

                    production2.setInquantity(inquantity);
                    production2.setOutquantity(outquantity);
                    
                    boolean result = production2Controller.editProduction2(production2);
                    
                    if(result == GlobalFields.SUCCESS){
                        JPanel panel = (JPanel)this.getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new GeneralProductionTab(2));
                        panel.revalidate();
                        panel.repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan pindah barang ~ jumlah barang masuk atau keluar tidak mencukupi");
                    }
                }
            }
            catch(Exception exc){
                System.out.println(this.getClass().toString() + " " + exc);
                JOptionPane.showMessageDialog(null, "Gagal menyimpan pindah barang");
            }
        });
    }
    
    private void newFilter2() {
        RowFilter<ItemOutTableModel, Object> rf = null;
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
