///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package gui.inner;
//
//import MyGUI.MyLabel;
//import controller.ReportController;
//import controller.WarehouseController;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import javax.swing.BorderFactory;
//import javax.swing.JComboBox;
//import javax.swing.JPanel;
//import javax.swing.JTabbedPane;
//import javax.swing.border.CompoundBorder;
//import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
//import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
//import net.sourceforge.jdatepicker.impl.UtilDateModel;
//import util.GlobalFields;
//
///**
// *
// * @author Purnama
// */
//public class ReportTab extends JTabbedPane {
//    
//    JPanel reportlist;
//    
//    JPanel leftpanel;
//    JPanel rightpanel;
//    JPanel lowerpanel;
//    
////    JPanel leftperiodpanel;
////    MyLabel leftperiodlabel;
////    JPanel leftwarehousepanel;
////    MyLabel leftwarehouselabel;
////    JComboBox leftwarehousecombobox;
////    
////    JPanel rightperiodpanel;
////    MyLabel rightperiodlabel;
//    
//    
//    
//    WarehouseController warehouseController;
//    ReportController reportController;
//    
//    JPanel reportin;
//    JPanel reportout;
//    
//    public ReportTab(){
//        super();
//        
//        warehouseController = new WarehouseController();
//        reportController = new ReportController();
//        
//        reportin = new ReportInTab();
//        reportout = new ReportOutTab();
//        
//        reportlist = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        reportlist.setPreferredSize(new Dimension(1000, 450));
//        
//        leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        rightpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        lowerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        
//        leftpanel.setPreferredSize(new Dimension (600, 350));
//        leftpanel.setBorder(new CompoundBorder(
//            BorderFactory.createTitledBorder("Invoice & Production Report"),
//            BorderFactory.createEmptyBorder(0, 10, 0, 25)
//            ));
//        
////        leftperiodpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        leftperiodlabel = new MyLabel("Period : ");
////        leftperiodpanel.add(leftperiodlabel);
////        UtilDateModel model1 = new UtilDateModel();
////        model1.setSelected(true);
////        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
////        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
////        leftperiodpanel.add(datePicker1);
////        leftperiodpanel.add(new MyLabel(" - "));
////        UtilDateModel model2 = new UtilDateModel();
////        model2.setSelected(true);
////        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
////        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
////        leftperiodpanel.add(datePicker2);
////        leftpanel.add(leftperiodpanel);
////        
////        leftwarehousepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        leftwarehouselabel = new MyLabel("Warehouse : ");
////        leftwarehousepanel.add(leftwarehouselabel);
////        Object[] warehouselist = warehouseController.getWarehouseNameList(GlobalFields.USER);
////        leftwarehousecombobox = new JComboBox(warehouselist);
////        leftwarehousepanel.add(leftwarehousecombobox);
////        leftpanel.add(leftwarehousepanel);
//        
//        rightpanel.setPreferredSize(new Dimension (380, 350));
//        rightpanel.setBorder(new CompoundBorder(
//            BorderFactory.createTitledBorder("Unchecked Item"),
//                BorderFactory.createEmptyBorder(0, 10, 0, 25)));
//        
//        
//        
////        rightperiodpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        rightperiodlabel = new MyLabel("Period : ");
////        rightperiodpanel.add(rightperiodlabel);
////        
////        rightpanel.add(rightperiodpanel);
//        
//        lowerpanel.setPreferredSize(new Dimension (985, 80));
//        lowerpanel.setBorder(new CompoundBorder(
//            BorderFactory.createTitledBorder("Export Report"),
//            BorderFactory.createEmptyBorder(0, 10, 0, 25)
//            ));
//        
//        reportlist.add(leftpanel);
//        reportlist.add(rightpanel);
//        reportlist.add(lowerpanel);
//        
//        addTab("Report", reportlist);
//        addTab("Item Out Report", reportout);
//        addTab("Item In Report", reportin);
//    }
//}
