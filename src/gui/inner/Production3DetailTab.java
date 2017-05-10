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
import controller.ItemOutController;
import controller.Production3Controller;
import entity.Item_Out;
import entity.Production3;
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
public class Production3DetailTab extends JTabbedPane{
    
    Production3Controller production3Controller;
    ItemOutController itemoutController;
    CnController cnController;
    
    Production3 production3;
    
    JPanel production3detail;
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
    MyTextField insackquantitytf;
    MyTextField inunitquantitytf;
    MyTextField outquantitytf;
    
    MyButton savebutton;
    MyButton addinbutton;
    MyButton productionlistbutton;
    
    MyLabel iteminlabel;
    MyLabel itemoutlabel;
    private final MyTextField exoutquantitytf;
    private final MyLabel exoutquantitylabel;
    private final JPanel exoutquantitypanel;
    private TableRowSorter<ItemOutTableModel> sorter2;
    private MyTextField filtertf2;
    
    public Production3DetailTab(int id){
        production3Controller = new Production3Controller();
        itemoutController = new ItemOutController();
        cnController = new CnController();
        
        production3 = production3Controller.getProduction3(id);
        
        leftbox = Box.createVerticalBox();
        rightbox = Box.createVerticalBox();
        
        production3detail = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        
        insackquantitytf = new MyTextField("0", 100);
        inunitquantitytf = new MyTextField("0.0", 100);
        exoutquantitytf = new MyTextField("0.0", 100);
        outquantitytf = new MyTextField("0.0", 100);
        
        inquantitypanel.setPreferredSize(new Dimension(450, 65));
        inquantitypanel.add(inquantitylabel);
        inquantitypanel.add(insackquantitytf);
        inquantitypanel.add(new MyLabel("Karung "));
        inquantitypanel.add(inunitquantitytf);
        inquantitypanel.add(new MyLabel("Unit "));
        
        exoutquantitypanel.add(exoutquantitylabel);
        exoutquantitypanel.add(exoutquantitytf);
        exoutquantitypanel.add(new MyLabel("KG "));
        
        outquantitypanel.add(outquantitylabel);
        outquantitypanel.add(outquantitytf);
        outquantitypanel.add(new MyLabel("KG "));
        
        production3detail.setPreferredSize(new Dimension(995, 450)); 
        
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        
        notepanel.setBorder(BorderFactory.createTitledBorder(df1.format(production3.getDate())));
        
        String detaillabel = "";
        
        detaillabel += "Tanggal : ";
        detaillabel += df1.format(production3.getDate());
        detaillabel += "    Gudang : ";
        detaillabel += production3.getWarehouse().getName();
        detaillabel += "   Pengguna : ";
        detaillabel += production3.getUser().getUsername();
        detaillabel += "   Status : ";
        if(production3.isStatus() == GlobalFields.CHECKED){
            detaillabel += "SUDAH DIPERIKSA";
        }
        else{
            detaillabel += "BELUM DIPERIKSA";
        }
        
        notelabel = new MyLabel(production3.getNote());
        notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notepanel.setPreferredSize(new Dimension (980, 50));
        notepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        notepanel.add(notelabel);
        notepane = new JScrollPane();
        notepane.getViewport().add(notepanel, null);
        notepane.setBorder(BorderFactory.createTitledBorder("Catatan"));
        
        leftpanel.setPreferredSize(new Dimension (450, 250));
        leftpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("Barang Produksi"),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        rightpanel.setPreferredSize(new Dimension (450, 250));
        rightpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder("CN"),
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
        productionlistbutton = new MyButton("Kembali ke daftar produksi - cn");
        addinbutton = new MyButton("Tambah / ganti barang");
        
        iteminlabel = new MyLabel("");
        
        if(production3.getItemout() != null){
            iteminlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "+ production3.getItemout().getId() +"<BR/>NAMA BARANG : "+ 
                production3.getItemout().getName() +"<BR/>MEREK : "+ production3.getItemout().getMerk().getName() + "<BR/>"
                + "KEMASAN : "+ production3.getItemout().getPackaging().getAmount() + " " + production3.getItemout().getPackaging().getUnit()
                + "<BR/>JUMLAH UNIT PER KARUNG : "+ production3.getItemout().getSacksize() +"</FONT></HMTL>");
            
            int sacksize = production3.getItemout().getSacksize();
            insackquantitytf.setText(String.valueOf((int)production3.getInquantity()/sacksize));
            
            String unit = String.format("%.1f", production3.getInquantity()%sacksize);
            
            inunitquantitytf.setText(unit);
        }
        
        leftpanel.add(iteminlabel);
        JPanel leftdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftdummy.setPreferredSize(new Dimension (350, 30));
        leftdummy.add(addinbutton);
        leftpanel.add(leftdummy);
        
        itemoutlabel = new MyLabel("");
        
        itemoutlabel.setText("<HTML><FONT SIZE=4>ID BARANG : "
                + production3.getCn().getId() +"<BR/>NAMA BARANG : CN</FONT></HMTL>");

        String unit = String.format("%.1f", production3.getOutquantity());
        String exunit = String.format("%.1f", production3.getDifference());
        
        outquantitytf.setText(unit);
        exoutquantitytf.setText(exunit);
        
        rightpanel.add(itemoutlabel);
        JPanel rightdummy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightdummy.setPreferredSize(new Dimension (350, 30));
        rightpanel.add(rightdummy);
        
        savepanel.add(productionlistbutton);
        savepanel.add(savebutton);
        
        production3detail.add(notepane);
        production3detail.add(leftbox);
        production3detail.add(middlepanel);
        production3detail.add(rightbox);
        production3detail.add(savepanel);
        
        addTab(detaillabel, production3detail);
        
        if(production3.isStatus() == GlobalFields.CHECKED){
            addinbutton.setEnabled(false);
            savebutton.setEnabled(false);
            insackquantitytf.setEditable(false);
            inunitquantitytf.setEditable(false);
            exoutquantitytf.setEditable(false);
            outquantitytf.setEditable(false);
        }
        
        productionlistbutton.addActionListener((ActionEvent e) -> {
            JPanel panel = (JPanel)this.getParent();
            panel.remove(panel.getComponent(1));
            panel.add(new GeneralProductionTab(3));
            panel.revalidate();
            panel.repaint();
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
                    
                    production3.setItemout(itemout);
                    
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
                    jop.setMessage("Simpan produksi - cn ini?");
                    JDialog dialog = jop.createDialog(null, "Simpan Produksi");
                    dialog.show();
                    if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    float insackquantity = Float.parseFloat(insackquantitytf.getText());
                    float inunitquantity = Float.parseFloat(inunitquantitytf.getText());
                    float outquantity = Float.parseFloat(outquantitytf.getText());
                    float exoutquantity = Float.parseFloat(exoutquantitytf.getText());
                    
                    float inquantity = insackquantity*production3.getItemout().getSacksize()+inunitquantity;

                    production3.setInquantity(inquantity);
                    production3.setOutquantity(outquantity);
                    production3.setDifference(exoutquantity);
                    
                    boolean result = production3Controller.editProduction3(production3);
                    
                    if(result == GlobalFields.SUCCESS){
                        JPanel panel = (JPanel)this.getParent();
                        panel.remove(panel.getComponent(1));
                        panel.add(new GeneralProductionTab(3));
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
}
