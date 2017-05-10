/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyFrame;
import MyGUI.MyImageIcon;
import MyGUI.MyLabel;
import controller.ReportController;
import controller.WarehouseController;
import entity.Item_In;
import entity.Report;
import entity.Warehouse;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
//public class ReportInListFrame extends JTabbedPane{
public class ReportInListFrame extends MyFrame{

    JPanel reportlist;
    JPanel header;
    
    JPanel previouspanel, nextpanel, detailpanel, buttonpanel, statuspanel;
    
    MyButton savebutton;
    MyButton printbutton;
    
    JScrollPane reportscrollpane;
    
    Box box;
    
    ReportController reportController;
    WarehouseController warehouseController;
    
    List<JCheckBox> checkboxls ;
    
    ArrayList<Item_In> iteminlist;
    
    int index;
    
    Warehouse warehouse;
    
    String title;
    
    boolean c, uc;
    
    JCheckBox checkbox, uncheckbox;
    
    public ReportInListFrame(String title, Warehouse warehouse, Item_In item, 
            ArrayList<Item_In> iteminlist, int index, boolean c, boolean uc){
        super(title);
        
        this.iteminlist = iteminlist;
        this.index = index;
        this.warehouse = warehouse;
        this.title = title;
        this.c = c;
        this.uc = uc;
        
        checkboxls = new ArrayList<JCheckBox>();
        
        reportController = new ReportController();
        warehouseController = new WarehouseController();
        
        box = Box.createVerticalBox();
        
        reportscrollpane = new JScrollPane();
        
        header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setPreferredSize(new Dimension(1000, 100));
        header.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 25));
        
        nextpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        MyLabel nextlabel = new MyLabel(new MyImageIcon().getImage("gui/image/Next.png"), 64, 64);
        nextpanel.add(nextlabel);
        
        previouspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        MyLabel previouslabel = new MyLabel(new MyImageIcon().getImage("gui/image/Previous.png"), 64, 64);
        previouspanel.add(previouslabel);
        
        previouslabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    previousitem(index-1);
                }
            });
        
        nextlabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nextitem(index+1);
                }
            });
        
        detailpanel = new JPanel();
        detailpanel.setLayout(new BoxLayout(detailpanel, BoxLayout.Y_AXIS));
        detailpanel.setPreferredSize(new Dimension(400, 75));
        detailpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Detil "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        statuspanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statuspanel.setPreferredSize(new Dimension(200, 75));
        statuspanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Status "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        checkbox = new JCheckBox("Sudah Diperiksa");
        uncheckbox = new JCheckBox("Belum Diperiksa");
        
        checkbox.setSelected(c);
        uncheckbox.setSelected(uc);
        
        statuspanel.add(checkbox);
        statuspanel.add(uncheckbox);
        
        checkbox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    checkoruncheck(checkbox.isSelected(), uncheckbox.isSelected());
                }
            });
        
        uncheckbox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    checkoruncheck(checkbox.isSelected(), uncheckbox.isSelected());
                }
            });
        
        buttonpanel = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonpanel.setPreferredSize(new Dimension(200, 75));
        buttonpanel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(" Pilihan "),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
        
        printbutton = new MyButton("Cetak");
        savebutton = new MyButton("Simpan");
        
//        buttonpanel.add(printbutton);
        buttonpanel.add(savebutton);
        
        header.add(previouslabel);
        header.add(detailpanel);
        header.add(statuspanel);
        header.add(buttonpanel);
        header.add(nextpanel);
        
        String detail = item.getName() + " ~ " + item.getQuality().getCategory().getName() + " " + item.getQuality().getName();
        
        detailpanel.add(new MyLabel(detail));
        detailpanel.add(new MyLabel("Gudang : " + warehouse.getName()));
        
        reportlist = new JPanel();
        reportlist.setLayout(new BoxLayout(reportlist, BoxLayout.Y_AXIS));
        
        reportscrollpane.setPreferredSize(new Dimension(1000, 500));
        reportscrollpane.getViewport().add(reportlist, null);
        
        reportlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reportlist.setPreferredSize(new Dimension(1000, 450));
        reportlist.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 25));
        
        reportscrollpane.setPreferredSize(new Dimension(1000, 500));
        reportscrollpane.getViewport().add(reportlist, null);
        
        List reportls = reportController.getReport(warehouse, item);
        
        JPanel headerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerpanel.setBackground(Color.gray);
        headerpanel.setPreferredSize(new Dimension(950, 50));
        
        JPanel descriptionpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        descriptionpanel.setOpaque(false);
        descriptionpanel.add(new MyLabel("Deskripsi"));
        descriptionpanel.setPreferredSize(new Dimension(540, 25));
        headerpanel.add(descriptionpanel);
        
        JPanel inpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inpanel.setOpaque(false);
        inpanel.add(new MyLabel("Masuk"));
        inpanel.setPreferredSize(new Dimension(105, 25));
        inpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        headerpanel.add(inpanel);
        
        JPanel outpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outpanel.setOpaque(false);
        outpanel.add(new MyLabel("Keluar"));
        outpanel.setPreferredSize(new Dimension(105, 25));
        outpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        headerpanel.add(outpanel);
        
        JPanel stockpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        stockpanel.setOpaque(false);
        stockpanel.add(new MyLabel("Stok"));
        stockpanel.setPreferredSize(new Dimension(105, 25));
        stockpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        headerpanel.add(stockpanel);
        
        JPanel checkpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        checkpanel.setOpaque(false);
        checkpanel.setPreferredSize(new Dimension(40, 25));
        checkpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        JCheckBox check = new JCheckBox("");
        checkpanel.add(check);
        headerpanel.add(checkpanel);
        
        reportlist.add(headerpanel);
        
        check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    for(JCheckBox jcb : checkboxls){
                        jcb.setSelected(true);
                    }
                } else {
                    for(JCheckBox jcb : checkboxls){
                        jcb.setSelected(false);
                    }
                }
            }
        });
        
        Stack stack = new Stack();
        
        int currentstock = warehouseController.getItemInWarehouse(warehouse, item).getStock();
        
        for(Object o : reportls){
            Report report = (Report)o;
            
            JPanel toppanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            toppanel.setBackground(Color.lightGray);
            toppanel.setOpaque(false);
            toppanel.setPreferredSize(new Dimension(950, 50));
            
            toppanel.setBorder(new CompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
            BorderFactory.createEmptyBorder(0, 10, 0, 25)
            ));
            
            JPanel typepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            typepanel.setOpaque(false);
            typepanel.setPreferredSize(new Dimension(125, 25));
            
            MyLabel typelabel = new MyLabel(report.getType());
            typelabel.setToolTipText("ID : " + report.getTypeid());
            
            JPanel datepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            datepanel.setOpaque(false);
            datepanel.setPreferredSize(new Dimension(100, 25));
            
            SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
            
            MyLabel datelabel = new MyLabel(df1.format(report.getDate()));
            
            JPanel iteminsack = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JPanel iteminunit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            JPanel itemoutsack = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JPanel itemoutunit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            JPanel stocksack = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JPanel stockunit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            MyLabel iteminsacklabel = new MyLabel("0");
            MyLabel iteminunitlabel = new MyLabel("0");
            
            MyLabel itemoutsacklabel = new MyLabel("0");
            MyLabel itemoutunitlabel = new MyLabel("0");
            
            MyLabel stocksacklabel = new MyLabel("0");
            MyLabel stockunitlabel = new MyLabel("0");
            
            iteminsack.setOpaque(false);
            iteminunit.setOpaque(false);
            itemoutsack.setOpaque(false);
            itemoutunit.setOpaque(false);
            stocksack.setOpaque(false);
            stockunit.setOpaque(false);
            
            iteminsack.setPreferredSize(new Dimension(50, 25));
            iteminunit.setPreferredSize(new Dimension(50, 25));
            itemoutsack.setPreferredSize(new Dimension(50, 25));
            itemoutunit.setPreferredSize(new Dimension(50, 25));
            stocksack.setPreferredSize(new Dimension(50, 25));
            stockunit.setPreferredSize(new Dimension(50, 25));
            
            iteminsack.add(iteminsacklabel);
            iteminunit.add(iteminunitlabel);
            itemoutsack.add(itemoutsacklabel);
            itemoutunit.add(itemoutunitlabel);
            stocksack.add(stocksacklabel);
            stockunit.add(stockunitlabel);
            
            iteminsack.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            iteminunit.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            itemoutsack.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            itemoutunit.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            stocksack.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            stockunit.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            
            typepanel.add(typelabel);
            datepanel.add(datelabel);
            
            JPanel userpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            userpanel.setOpaque(false);
            userpanel.setPreferredSize(new Dimension(75, 25));
            
            MyLabel userlabel = new MyLabel(report.getUser().getUsername());
            userpanel.add(userlabel);
            
            JPanel notepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            notepanel.setOpaque(false);
            notepanel.setPreferredSize(new Dimension(215, 25));
            
            MyLabel notelabel = new MyLabel(report.getNote());
            notepanel.add(notelabel);
            
            JPanel jcbpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            jcbpanel.setOpaque(false);
            jcbpanel.setPreferredSize(new Dimension(40, 25));
            jcbpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
            JCheckBox jcb = new JCheckBox("");
            jcb.setToolTipText(report.getType() + "-" + report.getTypeid());
            jcb.setSelected(report.isChecked());
            jcbpanel.add(jcb);
            
            checkboxls.add(jcb);
            
            jcb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    autochecker(checkboxls.indexOf(jcb));
                }
            });
            
            toppanel.add(datepanel);
            toppanel.add(typepanel);
            toppanel.add(userpanel);
            toppanel.add(notepanel);
            toppanel.add(iteminsack);
            toppanel.add(iteminunit);
            toppanel.add(itemoutsack);
            toppanel.add(itemoutunit);
            toppanel.add(stocksack);
            toppanel.add(stockunit);
            toppanel.add(jcbpanel);
            
            stocksacklabel.setText(String.valueOf(currentstock));
            
            if(report.isStatus() == GlobalFields.IN){
                iteminsacklabel.setText(String.valueOf((int)report.getQuantity()));
                currentstock -= report.getQuantity();
            }
            else{
                itemoutsacklabel.setText(String.valueOf((int)report.getQuantity()));
                currentstock += report.getQuantity();
            }
            
            toppanel.addMouseListener(new MouseListener(){

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    toppanel.setOpaque(true);
                    toppanel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    toppanel.setOpaque(false);
                    toppanel.repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                }
            
            });
            
            if(c == true && uc == true){
                stack.push(toppanel);
            }
            else if(c == true){
                if(report.isChecked()){
                    stack.push(toppanel);
                }
            }
            else if(uc == true){
                if(!report.isChecked()){
                    stack.push(toppanel);
                }
            }
        }
        
        while(!stack.empty()){
            JPanel panel = (JPanel)stack.pop();
            
            reportlist.add(panel);
        }
        
        add(box);
        
        box.add(header);
        box.add(reportscrollpane);
        
        setResizable(false);
        display(this);
        pack();
        setLocationToCenter(this);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        
        savebutton.addActionListener((ActionEvent e) -> {
            
            JOptionPane jop = new JOptionPane();
            jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            jop.setMessage("Simpan laporan ini?");
            JDialog dialog = jop.createDialog(null, "Simpan Laporan");
            dialog.show();
            if(jop.getValue() == null){

            }
            else if(jop.getValue().toString().equals("0")){
            
                ArrayList<String> temp = new ArrayList<String>();
                ArrayList<String> temp2 = new ArrayList<String>();
                
                    try{
                    for(JCheckBox checkbox : checkboxls){
                        if(checkbox.isSelected()){
                            temp.add(checkbox.getToolTipText());
                        }
                        else{
                            temp2.add(checkbox.getToolTipText());
                        }
                    }
                }
                catch(Exception exp){
                    System.out.println(this.getClass().toString() + "~" + exp);
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan laporan");
                }
            
            reportController.savereportin(temp, temp2);
            
            this.dispose();
            new ReportInListFrame(title, warehouse, iteminlist.get(index), iteminlist, index, c, uc);
            }
        });
    }
    
    public void autochecker(int in){
//        for(int i = 0; i < in; i++){
//            JCheckBox jcb = checkboxls.get(i);
//            jcb.setSelected(false);
//        }
//        
//        for(int i = checkboxls.size()-1; i > in; i--){
//            JCheckBox jcb = checkboxls.get(i);
//            jcb.setSelected(true);
//        }
    }
    
    public void nextitem(int in){
        try{
            this.dispose();
            new ReportInListFrame(title, warehouse, iteminlist.get(in), iteminlist, in, c, uc);
        }
        catch(Exception e){
            this.dispose();
            new ReportInListFrame(title, warehouse, iteminlist.get(0), iteminlist, 0, c, uc);
        }
    }
    
    public void previousitem(int in){
        try{
            this.dispose();
            new ReportInListFrame(title, warehouse, iteminlist.get(in), iteminlist, in, c, uc);
        }
        catch(Exception e){
            this.dispose();
            new ReportInListFrame(title, warehouse, iteminlist.get(iteminlist.size()-1), iteminlist, iteminlist.size()-1, c, uc);
        }
    }
    
    public void checkoruncheck(boolean c, boolean uc){
        this.dispose();
            new ReportInListFrame(title, warehouse, iteminlist.get(index), iteminlist, index, c, uc);
    }
}
