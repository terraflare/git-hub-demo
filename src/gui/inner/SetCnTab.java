/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyImageIcon;
import MyGUI.MyTextField;
import controller.CnController;
import entity.Cn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.CnTableModel;

/**
 *
 * @author Purnama
 */
public class SetCnTab extends JPanel{
    private final JScrollPane setcnscrollpane;
    private final JPanel setcnlistleftpanel;
    private final JPanel setcnlistrightpanel;
    private final JPanel setcnsearchpanel;
    private final JPanel setcnactionpanel;
    private final JPanel setcnnotepanel;
    private final JScrollPane notescrollpane;
    private final JTextArea note;
    private final JPanel setcndummypanel;
    private CnController cnController;
    private final ArrayList<Cn> cnlist;
    private final CnTableModel ctm;
    private final JTable cntable;
    
    TableRowSorter<CnTableModel> sorter;
    private final MyTextField setcnsearchtextfield;
    private final MyButton setcnokbutton;
    private final MyButton setcnsearchbutton;
    private final JPanel setcnstockpanel;
    private final MyTextField setcnsacktextfield;
    
    public SetCnTab(){
        super(new FlowLayout(FlowLayout.LEFT));
        
        cnController = new CnController();
        
        setcnscrollpane = new JScrollPane();
        
        setcnlistleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setcnlistrightpanel = new JPanel();
        setcnlistrightpanel.setLayout(new BoxLayout(setcnlistrightpanel, BoxLayout.Y_AXIS));
        setcnsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setcnactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        setcnsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari CN "));
        setcnactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        setcnstockpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setcnstockpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Stok "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        setcnsacktextfield = new MyTextField("", 50);
        setcnstockpanel.add(setcnsacktextfield);
        setcnstockpanel.add(new JLabel("KG"));
        
        setcnnotepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setcnnotepanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Catatan "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)));
        notescrollpane = new JScrollPane();
        note = new JTextArea(5, 27);
        notescrollpane.setViewportView(note);
        setcnnotepanel.add(notescrollpane);
        
        setcndummypanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setcndummypanel.setPreferredSize(new Dimension(350, 140));
        
        cnlist = cnController.getCnList();
        
        ctm = new CnTableModel(cnlist);
        sorter = new TableRowSorter<CnTableModel>(ctm);
        sorter.setSortsOnUpdates(true);
        
        cntable = new JTable(ctm);
        cntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cntable.getTableHeader().setReorderingAllowed(false);
        cntable.setRowHeight(cntable.getRowHeight() + 10);
        
        cntable.setRowSorter(sorter);
        
        setcnscrollpane.getViewport().add(cntable, null);
        setcnscrollpane.setPreferredSize(new Dimension(600, 420));
        
        setcnsearchtextfield = new MyTextField("", 250);
        
        setcnsearchtextfield.getDocument().addDocumentListener(
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
        
        setcnsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        setcnokbutton = new MyButton(new MyImageIcon().getImage("gui/image/Ok.png"), "Simpan", 110, 28);
        
        add(setcnlistleftpanel);
        add(setcnlistrightpanel);
        
        setcnactionpanel.add(setcnokbutton);
        
        setcnlistleftpanel.add(setcnscrollpane);
        
        setcnlistrightpanel.add(setcnsearchpanel);
        setcnlistrightpanel.add(setcnstockpanel);
        setcnlistrightpanel.add(setcnnotepanel);
        setcnlistrightpanel.add(setcnactionpanel);
        setcnlistrightpanel.add(setcndummypanel);
        
        setcnsearchpanel.add(setcnsearchtextfield);
        setcnsearchpanel.add(setcnsearchbutton);
        
        cntable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int itemid = Integer.parseInt(cntable.getValueAt(cntable.getSelectedRow(), 0).toString());
                
                Cn cn = cnController.getCn(itemid);
                
                setcnsacktextfield.setText(String.format("%.1f", cn.getStock()));
            }
        });
        
        setcnokbutton.addActionListener((ActionEvent e) -> {
            try{
                JOptionPane jop = new JOptionPane();
                jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                jop.setMessage("Ubah stok cn ini?");
                JDialog dialog = jop.createDialog(null, "Ubah Stok");
                dialog.show();
                if(jop.getValue() == null){
                }
                else if(jop.getValue().toString().equals("0")){
                    int itemid = Integer.parseInt(cntable.getValueAt(cntable.getSelectedRow(), 0).toString());

                    float quantity = Float.parseFloat(setcnsacktextfield.getText());

                    if(quantity < 0){
                        JOptionPane.showMessageDialog(null, "Jumlah barang tidak bisa kurang dari 0");
                    }
                    else{
                        cnController.setCnWarehouseStock(ctm, itemid, quantity, note.getText());
                        note.setText("");
                        JOptionPane.showMessageDialog(null, "Stok barang berhasil dirubah");
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
        RowFilter<CnTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(setcnsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
