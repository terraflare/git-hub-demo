/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inner;

import MyGUI.MyButton;
import MyGUI.MyLabel;
import MyGUI.MyTextField;
import controller.InvoicePurchaseController;
import controller.InvoiceSales2Controller;
import controller.InvoiceSalesController;
import controller.InvoiceWarehouseInController;
import controller.InvoiceWarehouseOutController;
import controller.ItemExchangeController;
import controller.Production2Controller;
import controller.Production3Controller;
import controller.Production4Controller;
import controller.ProductionController;
import controller.ReturnPurchaseController;
import controller.ReturnSalesController;
import controller.WarehouseController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Purnama
 */
public class DeleteTab extends JPanel{
    
    JPanel warningpanel;
    JPanel warningpanel2;
    JPanel datepanel;
    JPanel confirmpanel;
    JPanel buttonpanel;
    
    MyTextField confirmtf;
    
    MyButton deletebutton;
    
    ProductionController productionController;
    Production2Controller production2Controller;
    Production3Controller production3Controller;
    Production4Controller production4Controller;
    InvoicePurchaseController invoicepurchaseController;
    InvoiceSalesController invoicesalesController;
    InvoiceSales2Controller invoicesales2Controller;
    InvoiceWarehouseInController invoicewarehouseinController;
    InvoiceWarehouseOutController invoicewarehouseoutController;
    WarehouseController warehouseController;
    ReturnSalesController returnsalesController;
    ReturnPurchaseController returnpurchaseController;
    ItemExchangeController itemexchangeController;
    
    public DeleteTab(){
        super(new GridLayout(8, 1, 10, 10));
        
        setPreferredSize(new Dimension(995, 440));
        
        productionController = new ProductionController();
        production2Controller = new Production2Controller();
        production3Controller = new Production3Controller();
        production4Controller = new Production4Controller();
        invoicepurchaseController = new InvoicePurchaseController();
        invoicesalesController = new InvoiceSalesController();
        invoicesales2Controller = new InvoiceSales2Controller();
        invoicewarehouseinController = new InvoiceWarehouseInController();
        invoicewarehouseoutController = new InvoiceWarehouseOutController();
        warehouseController = new WarehouseController();
        returnpurchaseController = new ReturnPurchaseController();
        returnsalesController = new ReturnSalesController();
        itemexchangeController = new ItemExchangeController();
        
        warningpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        MyLabel warninglabel  = new MyLabel("<HTML><FONT COLOR=RED>HATI HATI ! "
                + "MODUL INI DIGUNAKAN UNTUK MENGHAPUS DATA KELUAR MASUK BARANG"
                + "<BR/>FAKTUR (KELUAR DAN MASUK), FAKTUR GUDANG, PRODUKSI, DAN PENYESUAIN STOK</FONT></HTML>");
        warningpanel.add(warninglabel);
        
        datepanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        MyLabel datelabel = new MyLabel("Tanggal Akhir : ");
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datepanel.add(datelabel);
        datepanel.add(datePicker);
        
        confirmpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        MyLabel confirmlabel = new MyLabel("Konfirmasi : ");
        confirmtf = new MyTextField("", 200);
        confirmpanel.add(confirmlabel);
        confirmpanel.add(confirmtf);
        
        warningpanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        MyLabel warninglabel2  = new MyLabel("<HTML><FONT COLOR=RED>KETIK 'HAPUS' DI DALAM KONFIRMASI</FONT></HTML>");
        warningpanel2.add(warninglabel2);
        
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deletebutton = new MyButton("Hapus");
        buttonpanel.add(deletebutton);
                
        add(warningpanel);
        add(datepanel);
        add(confirmpanel);
        add(warningpanel2);
        add(buttonpanel);
        
        deletebutton.addActionListener((ActionEvent e) -> {
            if(confirmtf.getText().equals("HAPUS")){
                Date date = (Date)datePicker.getModel().getValue();
                
                try{
                    itemexchangeController.deleteAllItemExchange(date);
                    productionController.deleteAllProduction(date);
                    production2Controller.deleteAllProduction2(date);
                    production3Controller.deleteAllProduction3(date);
                    production4Controller.deleteAllProduction4(date);
                    invoicesalesController.deleteAllInvoiceSales(date);
                    invoicesales2Controller.deleteAllInvoiceSales2(date);
                    invoicepurchaseController.deleteAllInvoicePurchase(date);
                    invoicewarehouseinController.deleteAllInvoiceWarehouseIn(date);
                    invoicewarehouseoutController.deleteAllInvoiceWarehouseOut(date);
                    warehouseController.deleteAllAdjustmentIn(date);
                    warehouseController.deleteAllAdjustmentOut(date);
                    warehouseController.deleteAllAdjustmentCn(date);
                    returnsalesController.deleteAllReturnSales(date);
                    returnpurchaseController.deleteAllReturnPurchase(date);
                    
                    
                    JOptionPane.showMessageDialog(null, "Berhasil menghapus semua data");
                }
                catch(Exception exp){
                    System.out.println(exp);
                    JOptionPane.showMessageDialog(null, "Gagal menghapus data");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Konfirmasi gagal");
            }
        });
    }
}
