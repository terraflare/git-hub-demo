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
import controller.CnController;
import controller.ItemInController;
import controller.ItemOutController;
import controller.Production4Controller;
import entity.Item_In;
import entity.Production4;
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
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production4DetailTab extends JTabbedPane{
    Production4Controller production4Controller;
    ItemOutController itemoutController;
    CnController cnController;
    
    Production4 production4;
    
    JPanel production4detail;
    JPanel notepanel;
    JPanel savepanel;
    
    JPanel rightpanel;
    JPanel leftpanel;
    JPanel middlepanel;
    
    MyLabel notelabel;
    JScrollPane notepane;
    Box leftbox;
    Box rightbox;
    JPanel outquantitypanel;
    JPanel inquantitypanel;
    MyLabel inquantitylabel;
    MyLabel outquantitylabel;
    MyTextField inquantitytf;
    MyTextField outquantitytf;
    
    MyButton savebutton;
    MyButton addoutbutton;
    MyButton productionlistbutton;
    
    MyLabel iteminlabel;
    MyLabel itemoutlabel;
    MyTextField exinquantitytf;
    MyLabel exinquantitylabel;
    JPanel exinquantitypanel;
    ItemInController iteminController;
    private TableRowSorter<ItemInTableModel> sorter2;
    private MyTextField filtertf2;
    
    public Production4DetailTab(int id){
        production4Controller = new Production4Controller();
        iteminController = new ItemInController();
        cnController = new CnController();
        
        production4 = production4Controller.getProduction4(id);
        
        leftbox = Box.createVerticalBox();
        rightbox = Box.createVerticalBox();
        
        production4detail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        savepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middlepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        exinquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        outquantitypanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        exinquantitylabel = new MyLabel("Jumlah yang diharapkan : ");
        inquantitylabel = new MyLabel("Jumlah sebenarnya : ");
        outquantitylabel = new MyLabel("Jumlah : ");
        
        exinquantitytf = new MyTextField("0.0", 100);
        inquantitytf = new MyTextField("0.0", 100);
        outquantitytf = new MyTextField("0", 100);
        
        exinquantitypanel.add(exinquantitylabel);
        exinquantitypanel.add(exinquantitytf);
        exinquantitypanel.add(new MyLabel("KG "));
        
        inquantitypanel.add(inquantitylabel);
        inquantitypanel.add(inquantitytf);
        inquantitypanel.add(new MyLabel("KG "));
        
        outquantitypanel.setPreferredSize(new Dimension(450, 65));
        outquantitypanel.add(outquantitylabel);
        outquantitypanel.add(outquantitytf);
        outquantitypanel.add(new MyLabel("Karung "));
        
        production4detail.setPreferredSize(new Dimension(995, 450)); 
        
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        
        notepanel.setBorder(BorderFactory.createTitledBorder(df1.format(production4.getDate())));
        
        String detaillabel = "";
        
        detaillabel += "Tanggal : ";
        detaillabel += df1.format(production4.getDate());
        detaillabel += "    Gudang : ";
        detaillabel += production4.getWarehouse().getName();
        detaillabel += "   Pengguna : ";
        detaillabel += production4.getUser().getUsername();
        detaillabel += "   Status : ";
        if(production4.isStatus() == GlobalFields.CHECKED){
            detaillabel += "SUDAH DIPERIKSA";
        }
        else{
            detaillabel += "BELUM DIPERIKSA";
        }
        
        notelabel = new MyLabel(production4.getNote());
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel.setPreferredSize(new Dimension (980, 50));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notepanel.add(notelabel);
        notepane = new JScrollPane();
        notepane.getViewport().add(notepanel, null);
        notepane.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        leftpanel.setPreferredSize(new Dimension (450, 250));
        leftpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("CN"),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        rightpanel.setPreferredSize(new Dimension (450, 250));
        rightpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Barang Curah"),
                BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        
        MyLabel nextlabel = new MyLabel(new MyImageIcon().getImage("gui/image/Next.png"), 64, 64);
        
        middlepanel.add(nextlabel);
        
        leftbox.add(leftpanel);
        leftbox.add(exinquantitypanel);
        leftbox.add(inquantitypanel);
        
        rightbox.add(rightpanel);
        rightbox.add(outquantitypanel);
        
        savepanel.setPreferredSize(new Dimension (980, 50));
        
        savebutton = new MyButton("Simpan");
        productionlistbutton = new MyButton("Kembali ke daftar cn - curah");
        addoutbutton = new MyButton("Tambah / ganti barang");
        
        itemoutlabel = new MyLabel("");
        
        if(production4.getItemin() != null){
            itemoutlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "+ production4.getItemin().getId() +"<BR/>NAMA BARANG : "+ 
                production4.getItemin().getName() +"<BR/>KUALITAS : "+ production4.getItemin().getQuality().getCategory().getName() + " "+
                production4.getItemin().getQuality().getName() + "</FONT></HMTL>");
            
            outquantitytf.setText(String.valueOf(production4.getOutquantity()));
        }
        
        rightpanel.add(itemoutlabel);
        JPanel rightdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightdummy.setPreferredSize(new Dimension (350, 30));
        rightdummy.add(addoutbutton);
        rightpanel.add(rightdummy);
        
        iteminlabel = new MyLabel("");
        
        iteminlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "
                + production4.getCn().getId() +"<BR/>NAMA BARANG : CN</FONT></HMTL>");

        String unit = String.format("%.1f", production4.getInquantity());
        inquantitytf.setText(unit);
        
        String exunit = String.format("%.1f", production4.getDifference());
        exinquantitytf.setText(exunit);
        
        leftpanel.add(iteminlabel);
        JPanel leftdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftdummy.setPreferredSize(new Dimension (350, 30));
        leftpanel.add(leftdummy);
        
        savepanel.add(productionlistbutton);
        savepanel.add(savebutton);
        
        production4detail.add(notepane);
        production4detail.add(leftbox);
        production4detail.add(middlepanel);
        production4detail.add(rightbox);
        production4detail.add(savepanel);
        
        addTab(detaillabel, production4detail);
        
        if(production4.isStatus() == GlobalFields.CHECKED){
            addoutbutton.setEnabled(false);
            savebutton.setEnabled(false);
            inquantitytf.setEditable(false);
            exinquantitytf.setEditable(false);
            outquantitytf.setEditable(false);
        }
        
        productionlistbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralProductionTab(4));
            panel.revalidate();
            panel.repaint();
        });
        
        addoutbutton.addActionListener((ActionEvent e) -> {
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
                    
                    production4.setItemin(itemin);
                    
                    itemoutlabel.setText("<HTML><FONT SIZE=4>ID BARANG: "+ itemin.getId() +"<BR/>NAMA BARANG : "+ 
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
                    jop.setMessage("Simpan cn - curah ini?");
                    JDialog dialog = jop.createDialog(null, "Simpan Produksi");
                    dialog.show();
                    if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    float inquantity = Float.parseFloat(inquantitytf.getText());
                    float exinquantity = Float.parseFloat(exinquantitytf.getText());
                    int outquantity = Integer.parseInt(outquantitytf.getText());
                    
                    production4.setInquantity(inquantity);
                    production4.setOutquantity(outquantity);
                    production4.setDifference(exinquantity);
                    
                    boolean result = production4Controller.editProduction4(production4);
                    
                    if(result == GlobalFields.SUCCESS){
                        JPanel panel = (JPanel)this.getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new GeneralProductionTab(4));
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
        RowFilter<ItemInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filtertf2.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter2.setRowFilter(rf);
    }
}

