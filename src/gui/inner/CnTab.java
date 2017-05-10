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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tablemodel.CnTableModel;

/**
 *
 * @author Purnama
 */
public class CnTab extends JPanel{
    
    private CnController cnController;
    
    JPanel cnleftpanel, cnrightpanel;
    
    JPanel cnsearchpanel, cndetailpanel, cnactionpanel;
    
    JScrollPane cnscrollpane;
    
    JTable cntable;
    
    CnTableModel ctm;
    TableRowSorter<CnTableModel> sorter;
    
    MyTextField cnsearchtextfield;
    MyButton cnsearchbutton;
    private final MyButton detailbutton;
    
    public CnTab(){
        super(new FlowLayout(FlowLayout.LEFT));
     
        cnController = new CnController();
        
        cnscrollpane = new JScrollPane();
        
        cnleftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cnrightpanel = new JPanel();
        cnrightpanel.setLayout(new BoxLayout(cnrightpanel, BoxLayout.Y_AXIS));
        
        ctm = new CnTableModel(cnController.getCnList());
        sorter = new TableRowSorter<CnTableModel>(ctm);
        sorter.setSortsOnUpdates(true);
        
        cntable = new JTable(ctm);
        cntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cntable.getTableHeader().setReorderingAllowed(false);
        cntable.setRowHeight(cntable.getRowHeight() + 10);
        
        cntable.setRowSorter(sorter);
        
        cnscrollpane.getViewport().add(cntable, null);
        cnscrollpane.setPreferredSize(new Dimension(600, 420));
        
        add(cnleftpanel);
        cnleftpanel.add(cnscrollpane);
        
        cnsearchpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cnsearchpanel.setBorder(BorderFactory.createTitledBorder(" Cari Cn "));
        
        cnsearchtextfield = new MyTextField("", 250);
        
        cnsearchtextfield.getDocument().addDocumentListener(
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
        
        cnsearchbutton = new MyButton(new MyImageIcon().getImage("gui/image/Search.png"), "", 75, 28);
        
        cnsearchpanel.add(cnsearchtextfield);
        cnsearchpanel.add(cnsearchbutton);
        
        cndetailpanel = new JPanel();
        cnactionpanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        cnactionpanel.setBorder(BorderFactory.createTitledBorder(" Pilihan "));
        
        cndetailpanel.setPreferredSize(new Dimension(350, 300));
        cndetailpanel.setBorder(BorderFactory.createTitledBorder(" Catatan "));
        
        detailbutton = new MyButton(new MyImageIcon().getImage("gui/image/Detail.png"), "Detil", 110, 28);
        
        cnactionpanel.add(detailbutton);
        
        add(cnrightpanel);
        cnrightpanel.add(cnsearchpanel);
        cnrightpanel.add(cndetailpanel);
        cnrightpanel.add(cnactionpanel);
    }
    
    private void newFilter() {
        RowFilter<CnTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(cnsearchtextfield.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}

