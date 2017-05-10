/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import comparator.ReportComparator;
import entity.AdjustmentCn;
import entity.AdjustmentIn;
import entity.AdjustmentOut;
import entity.Cn;
import entity.InvoicePurchase;
import entity.InvoiceSales;
import entity.InvoiceSales2;
import entity.InvoiceWarehouse;
import entity.InvoiceWarehouseIn;
import entity.InvoiceWarehouseOut;
import entity.Item_In;
import entity.Item_Out;
import entity.Production;
import entity.Production2;
import entity.Production3;
import entity.Production4;
import entity.Report;
import entity.ReturnPurchase;
import entity.ReturnSales;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import service.AdjustmentService;
import service.CnService;
import service.GeneralService;
import service.InvoicePurchaseService;
import service.InvoiceSales2Service;
import service.InvoiceSalesService;
import service.InvoiceWarehouseService;
import service.ItemService;
import service.Production2Service;
import service.Production3Service;
import service.Production4Service;
import service.ProductionService;
import service.ReturnPurchaseService;
import service.ReturnSalesService;
import service.WarehouseService;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ReportController {
    
    AdjustmentService adjustmentService;
    InvoicePurchaseService invoicepurchaseService;
    InvoiceSalesService invoicesalesService;
    InvoiceSales2Service invoicesales2Service;
    InvoiceWarehouseService invoicewarehouseService;
    ProductionService productionService;
    Production2Service production2Service;
    Production3Service production3Service;
    Production4Service production4Service;
    WarehouseService warehouseService;
    ItemService itemService;
    ReturnSalesService returnsalesService;
    ReturnPurchaseService returnpurchaseService;
    CnService cnService;
    
    GeneralService generalService;
    
    public ReportController(){
        adjustmentService = new AdjustmentService();
        invoicepurchaseService = new InvoicePurchaseService();
        invoicesalesService = new InvoiceSalesService();
        invoicesales2Service = new InvoiceSales2Service();
        invoicewarehouseService = new InvoiceWarehouseService();
        productionService = new ProductionService();
        production2Service = new Production2Service();
        production3Service = new Production3Service();
        production4Service = new Production4Service();
        warehouseService = new WarehouseService();
        itemService = new ItemService();
        generalService = new GeneralService();
        returnsalesService = new ReturnSalesService();
        returnpurchaseService = new ReturnPurchaseService();
        cnService = new CnService();
    }
    
    public int getCheckedInvoiceSales(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return invoicesalesService.countCheckedInvoiceSales(io, w);
    }
    
    public int getCheckedReturnSales(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return returnsalesService.countCheckedReturnSales(io, w);
    }
    
    public int getCheckedInvoicePurchase(String warehouse, int iteminid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_In ii = itemService.getItemIn(iteminid);
        
        return invoicepurchaseService.countCheckedInvoicePurchase(ii, w);
    }
    
    public int getCheckedProductionIn(String warehouse, int iteminid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_In ii = itemService.getItemIn(iteminid);
        
        return productionService.countCheckedProductionList(w, ii);
    }
    
    public int getCheckedProductionOut(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return productionService.countCheckedProductionList(w, io);
    }
    
    public int getCheckedInvoiceWarehouseIn(String warehouse, int iteminid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_In ii = itemService.getItemIn(iteminid);
        
        return invoicewarehouseService.countCheckedInvoiceWarehouseInList(w, ii);
    }
    
    public int getCheckedInvoiceWarehouseOut(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return invoicewarehouseService.countCheckedInvoiceWarehouseOutList(w, io);
    }
    
    public int getUncheckedInvoiceSales(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return invoicesalesService.countUncheckedInvoiceSales(io, w);
    }
    
    public int getUncheckedReturnSales(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return returnsalesService.countUncheckedReturnSales(io, w);
    }
    
    public int getUncheckedInvoicePurchase(String warehouse, int iteminid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_In ii = itemService.getItemIn(iteminid);
        
        return invoicepurchaseService.countUncheckedInvoicePurchase(ii, w);
    }
    
    public int getUncheckedProductionIn(String warehouse, int iteminid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_In ii = itemService.getItemIn(iteminid);
        
        return productionService.countUncheckedProductionList(w, ii);
    }
    
    public int getUncheckedProductionOut(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return productionService.countUncheckedProductionList(w, io);
    }
    
    public int getUncheckedInvoiceWarehouseIn(String warehouse, int iteminid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_In ii = itemService.getItemIn(iteminid);
        
        return invoicewarehouseService.countUncheckedInvoiceWarehouseInList(w, ii);
    }
    
    public int getUncheckedInvoiceWarehouseOut(String warehouse, int itemoutid){
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Item_Out io = itemService.getItemOut(itemoutid);
        
        return invoicewarehouseService.countUncheckedInvoiceWarehouseOutList(w, io);
    }
    
    public int getCheckedCn(){
        return cnService.countCheckedCnList();
    }
    
    public int getUncheckedCn(){
        return cnService.countUncheckedCnList();
    }
    
    public List getReport(Warehouse warehouse, Item_Out item){
        List ls = new ArrayList();
        
        List isls = invoicesalesService.getInvoiceSalesList(warehouse, item);
        List prols =  productionService.getProductionList(warehouse, item);
        List inpro2ls =  production2Service.getInProduction2List(warehouse, item);
        List outpro2ls =  production2Service.getOutProduction2List(warehouse, item);
        List inwarls = invoicewarehouseService.getInInvoiceWarehouseOutList(warehouse, item);
        List outwarls = invoicewarehouseService.getOutInvoiceWarehouseOutList(warehouse, item);
        List adjls = adjustmentService.getAdjustmentOutList(warehouse, item);
        List retls = returnsalesService.getReturnSalesList(warehouse, item);
        List pro3ls = production3Service.getProduction3List(warehouse, item);
        
        for(Object o : retls){
            ReturnSales rs = (ReturnSales)o;
            
            Report report = new Report();
            report.setDate(rs.getDate());
            report.setQuantity(rs.getQuantity());
            report.setType("Retur Penjualan");
            report.setTypeid(rs.getId());
            report.setUser(rs.getUser());
            report.setStatus(GlobalFields.IN);
            report.setChecked(rs.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : isls){
            InvoiceSales is = (InvoiceSales)o;
            
            Report report = new Report();
            report.setDate(is.getDate());
            report.setQuantity(is.getQuantity());
            report.setType("Faktur Penjualan");
            report.setTypeid(is.getId());
            report.setUser(is.getUser());
            report.setStatus(GlobalFields.OUT);
            report.setChecked(is.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : prols){
            Production production = (Production)o;
            
            Report report = new Report();
            report.setNote("diproduksi dari " + production.getItemin().getName());
            report.setDate(production.getDate());
            report.setQuantity(production.getOutquantity());
            report.setStatus(GlobalFields.IN);
            report.setType("Produksi");
            report.setTypeid(production.getId());
            report.setUser(production.getUser());
            report.setInputquantity(production.getInputquantity());
            report.setChecked(production.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : inpro2ls){
            Production2 pr = (Production2)o;
            
            Report report = new Report();
            report.setNote("dipindahkan ke " + pr.getItemout().getName());
            report.setDate(pr.getDate());
            report.setQuantity(pr.getInquantity());
            report.setType("Pindah Barang");
            report.setTypeid(pr.getId());
            report.setUser(pr.getUser());
            report.setStatus(GlobalFields.OUT);
            report.setChecked(pr.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : outpro2ls){
            Production2 pr = (Production2)o;
            
            Report report = new Report();
            report.setNote("dipindahkan dari " + pr.getItemin().getName());
            report.setDate(pr.getDate());
            report.setQuantity(pr.getOutquantity());
            report.setType("Pindah Barang");
            report.setTypeid(pr.getId());
            report.setUser(pr.getUser());
            report.setStatus(GlobalFields.IN);
            report.setChecked(pr.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : pro3ls){
            Production3 pr = (Production3)o;
            
            Report report = new Report();
            report.setNote("diubah ke CN");
            report.setDate(pr.getDate());
            report.setQuantity(pr.getInquantity());
            report.setType("Produksi Ke CN");
            report.setTypeid(pr.getId());
            report.setUser(pr.getUser());
            report.setStatus(GlobalFields.OUT);
            report.setChecked(pr.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : inwarls){
            InvoiceWarehouse iw = (InvoiceWarehouse)o;
            
            Report report = new Report();
            report.setDate(iw.getDate());
            report.setNote("dipindahkan dari " + iw.getWarehouse().getName());
            report.setQuantity(iw.getQuantity());
            report.setStatus(GlobalFields.IN);
            report.setType("Faktur Gudang");
            report.setTypeid(iw.getId());
            report.setUser(iw.getUser());
            report.setChecked(iw.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : outwarls){
            InvoiceWarehouse iw = (InvoiceWarehouse)o;
            
            Report report = new Report();
            report.setDate(iw.getDate());
            report.setNote("dipindahkan ke " + iw.getDestination().getName());
            report.setQuantity(iw.getQuantity());
            report.setStatus(GlobalFields.OUT);
            report.setType("Faktur Gudang");
            report.setTypeid(iw.getId());
            report.setUser(iw.getUser());
            report.setChecked(iw.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : adjls){
            AdjustmentOut ao = (AdjustmentOut)o;
            Report report = new Report();
            report.setDate(ao.getDate());
            report.setNote(ao.getNote());
            report.setType("Penyesuaian Stok");
            report.setTypeid(ao.getId());
            report.setUser(ao.getUser());
            report.setChecked(ao.isStatus());
            
            if(ao.getQuantity() < 0){
                report.setQuantity(-ao.getQuantity());
                report.setStatus(GlobalFields.OUT);
            }
            else{
                report.setQuantity(ao.getQuantity());
                report.setStatus(GlobalFields.IN);
            }
            
            ls.add(report);
        }
        
        Comparator reportcomp = new ReportComparator();
        Collections.sort(ls, reportcomp);
        
        return ls;
    }
    
    public List getReport(Warehouse warehouse, Item_In item){
        List ls = new ArrayList();
        
        List ipls = invoicepurchaseService.getInvoicePurchaseList(warehouse, item);
        List prols =  productionService.getProductionList(warehouse, item);
        List inwarls = invoicewarehouseService.getInInvoiceWarehouseInList(warehouse, item);
        List outwarls = invoicewarehouseService.getOutInvoiceWarehouseInList(warehouse, item);
        List adjls = adjustmentService.getAdjustmentInList(warehouse, item);
        List isls = invoicesales2Service.getInvoiceSales2List(warehouse, item);
        List rpls = returnpurchaseService.getReturnPurchaseList(warehouse, item);
        List pro4ls = production4Service.getProduction4List(warehouse, item);
        
        for(Object o : rpls){
            ReturnPurchase rp = (ReturnPurchase)o;
            
            Report report = new Report();
            report.setDate(rp.getDate());
            report.setQuantity(rp.getQuantity());
            report.setType("Retur Pembelian");
            report.setTypeid(rp.getId());
            report.setUser(rp.getUser());
            report.setStatus(GlobalFields.OUT);
            report.setChecked(rp.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : isls){
            InvoiceSales2 is2 = (InvoiceSales2)o;
            
            Report report = new Report();
            report.setDate(is2.getDate());
            report.setQuantity(is2.getQuantity());
            report.setType("Faktur Penjualan");
            report.setTypeid(is2.getId());
            report.setUser(is2.getUser());
            report.setStatus(GlobalFields.OUT);
            report.setChecked(is2.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : ipls){
            InvoicePurchase ip = (InvoicePurchase)o;
            
            Report report = new Report();
            report.setDate(ip.getDate());
            report.setQuantity(ip.getQuantity());
            report.setType("Faktur Pembelian");
            report.setTypeid(ip.getId());
            report.setUser(ip.getUser());
            report.setStatus(GlobalFields.IN);
            report.setChecked(ip.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : prols){
            Production production = (Production)o;
            
            Report report = new Report();
            report.setNote("diproduksi ke " + production.getItemout().getName());
            report.setDate(production.getDate());
            report.setQuantity(production.getInquantity());
            report.setStatus(GlobalFields.OUT);
            report.setType("Produksi");
            report.setTypeid(production.getId());
            report.setUser(production.getUser());
            report.setChecked(production.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : pro4ls){
            Production4 production = (Production4)o;
            
            Report report = new Report();
            report.setNote("diproduksi dari CN");
            report.setDate(production.getDate());
            report.setQuantity(production.getOutquantity());
            report.setStatus(GlobalFields.IN);
            report.setType("CN Ke Curah");
            report.setTypeid(production.getId());
            report.setUser(production.getUser());
            report.setChecked(production.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : inwarls){
            InvoiceWarehouse iw = (InvoiceWarehouse)o;
            
            Report report = new Report();
            report.setDate(iw.getDate());
            report.setNote("dipindahkan dari " + iw.getWarehouse().getName());
            report.setQuantity(iw.getQuantity());
            report.setStatus(GlobalFields.IN);
            report.setType("Faktur Gudang");
            report.setTypeid(iw.getId());
            report.setUser(iw.getUser());
            report.setChecked(iw.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : outwarls){
            InvoiceWarehouse iw = (InvoiceWarehouse)o;
            
            Report report = new Report();
            report.setDate(iw.getDate());
            report.setNote("Dipindahkan ke " + iw.getDestination().getName());
            report.setQuantity(iw.getQuantity());
            report.setStatus(GlobalFields.OUT);
            report.setType("Faktur Gudang");
            report.setTypeid(iw.getId());
            report.setUser(iw.getUser());
            report.setChecked(iw.isStatus());
            
            ls.add(report);
        }
        
        for(Object o : adjls){
            AdjustmentIn ai = (AdjustmentIn)o;
            Report report = new Report();
            report.setDate(ai.getDate());
            report.setNote(ai.getNote());
            report.setType("Penyesuaian Stok");
            report.setTypeid(ai.getId());
            report.setUser(ai.getUser());
            report.setChecked(ai.isStatus());
            
            if(ai.getQuantity() < 0){
                report.setQuantity(-ai.getQuantity());
                report.setStatus(GlobalFields.OUT);
            }
            else{
                report.setQuantity(ai.getQuantity());
                report.setStatus(GlobalFields.IN);
            }
            
            ls.add(report);
        }
        
        Comparator reportcomp = new ReportComparator();
        Collections.sort(ls, reportcomp);
        
        return ls;
    }
    
    public List getReport(Cn cn){
        List ls = new ArrayList();
        
        List pro3list = production3Service.getProduction3List();
        List pro4list = production4Service.getProduction4List();
        List adjcnlist = adjustmentService.getAdjustmentCnList();
        
        for(Object o : pro3list){
            Production3 pro3 = (Production3)o;
            
            Report report = new Report();
            report.setNote(pro3.getNote());
            report.setDate(pro3.getDate());
            report.setQuantity(pro3.getOutquantity());
            report.setStatus(GlobalFields.IN);
            report.setType("Produksi - CN");
            report.setTypeid(pro3.getId());
            report.setUser(pro3.getUser());
            report.setChecked(pro3.isStatus());
            report.setInputquantity(pro3.getDifference());
            
            ls.add(report);
            
        }
        
        for(Object o : pro4list){
            Production4 pro4 = (Production4)o;
            
            Report report = new Report();
            report.setNote(pro4.getNote());
            report.setDate(pro4.getDate());
            report.setQuantity(pro4.getInquantity());
            report.setStatus(GlobalFields.OUT);
            report.setType("CN - Curah");
            report.setTypeid(pro4.getId());
            report.setUser(pro4.getUser());
            report.setChecked(pro4.isStatus());
            report.setInputquantity(pro4.getDifference());
            
            ls.add(report);
        }
        
        for(Object o : adjcnlist){
            AdjustmentCn adjcn = (AdjustmentCn)o;
            
            Report report = new Report();
            report.setDate(adjcn.getDate());
            report.setNote(adjcn.getNote());
            report.setType("Penyesuaian Stok");
            report.setTypeid(adjcn.getId());
            report.setUser(adjcn.getUser());
            report.setChecked(adjcn.isStatus());
            
            if(adjcn.getQuantity() < 0){
                report.setQuantity(-adjcn.getQuantity());
                report.setStatus(GlobalFields.OUT);
            }
            else{
                report.setQuantity(adjcn.getQuantity());
                report.setStatus(GlobalFields.IN);
            }
            
            ls.add(report);
        }
        
        Comparator reportcomp = new ReportComparator();
        Collections.sort(ls, reportcomp);
        
        return ls;
    }
    
    public void savereportcn(ArrayList<String> selectedvalues, ArrayList<String> nselectedvalues){
        for(String value : selectedvalues){
            String [] temp = value.split("_");
            String type = temp[0];
            int id = Integer.parseInt(temp[1]);
            
            try{
                if(type.equals("CN - Curah")){
                    Production4 production4 = production4Service.getProduction4(id);
                    production4.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(production4);
                }
                else if(type.equals("Produksi - CN")){
                    Production3 production3 = production3Service.getProduction3(id);
                    production3.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(production3);
                }
                else if(type.equals("Penyesuaian Stok")){
                    AdjustmentCn ac = adjustmentService.getAdjustmentCn(id);
                    ac.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(ac);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        
        for(String value : nselectedvalues){
            String [] temp = value.split("_");
            String type = temp[0];
            int id = Integer.parseInt(temp[1]);
            
            try{
                if(type.equals("CN - Curah")){
                    Production4 production4 = production4Service.getProduction4(id);
                    production4.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(production4);
                }
                else if(type.equals("Produksi - CN")){
                    Production3 production3 = production3Service.getProduction3(id);
                    production3.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(production3);
                }
                else if(type.equals("Penyesuaian Stok")){
                    AdjustmentCn ac = adjustmentService.getAdjustmentCn(id);
                    ac.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(ac);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void savereportin(ArrayList<String> selectedvalues, ArrayList<String> nselectedvalues){
        for(String value : selectedvalues){
            String [] temp = value.split("-");
            String type = temp[0];
            int id = Integer.parseInt(temp[1]);
            
            try{
                
                
                if(type.equals("Faktur Pembelian")){
                    InvoicePurchase ip = invoicepurchaseService.getInvoicePurchase(id);
                    ip.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(ip);
                }
                else if(type.equals("CN Ke Curah")){
                    Production4 pro4 = production4Service.getProduction4(id);
                    pro4.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(pro4);
                }
                else if(type.equals("Faktur Gudang")){
                    InvoiceWarehouseIn iwi = invoicewarehouseService.getInvoiceWarehouseIn(id);
                    iwi.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(iwi);
                }
                else if(type.equals("Produksi")){
                    Production p = productionService.getProduction(id);
                    p.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(p);
                }
                else if(type.equals("Penyesuaian Stok")){
                    AdjustmentIn ai = adjustmentService.getAdjustmentIn(id);
                    ai.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(ai);
                }
                else if(type.equals("Faktur Penjualan")){
                    InvoiceSales2 is2 = invoicesales2Service.getInvoiceSales2(id);
                    is2.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(is2);
                }
                else if(type.equals("Retur Pembelian")){
                    ReturnPurchase rp = returnpurchaseService.getReturnPurchase(id);
                    rp.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(rp);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        
        for(String value : nselectedvalues){
            String [] temp = value.split("-");
            String type = temp[0];
            int id = Integer.parseInt(temp[1]);
            
            try{
            
                if(type.equals("Faktur Pembelian")){
                    InvoicePurchase ip = invoicepurchaseService.getInvoicePurchase(id);
                    ip.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(ip);
                }
                else if(type.equals("CN Ke Curah")){
                    Production4 pro4 = production4Service.getProduction4(id);
                    pro4.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(pro4);
                }
                else if(type.equals("Faktur Gudang")){
                    InvoiceWarehouseIn iwi = invoicewarehouseService.getInvoiceWarehouseIn(id);
                    iwi.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(iwi);
                }
                else if(type.equals("Produksi")){
                    Production p = productionService.getProduction(id);
                    p.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(p);
                }
                else if(type.equals("Penyesuaian Stok")){
                    AdjustmentIn ai = adjustmentService.getAdjustmentIn(id);
                    ai.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(ai);
                }
                else if(type.equals("Faktur Penjualan")){
                    InvoiceSales2 is2 = invoicesales2Service.getInvoiceSales2(id);
                    is2.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(is2);
                }
                else if(type.equals("Retur Pembelian")){
                    ReturnPurchase rp = returnpurchaseService.getReturnPurchase(id);
                    rp.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(rp);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void savereportout(ArrayList<String> selectedvalues, ArrayList<String> nselectedvalues){
        for(String value : selectedvalues){
            String [] temp = value.split("-");
            String type = temp[0];
            int id = Integer.parseInt(temp[1]);
            
            try{
            
                if(type.equals("Faktur Penjualan")){
                    InvoiceSales is = invoicesalesService.getInvoiceSales(id);
                    is.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(is);
                }
                else if(type.equals("Produksi Ke CN")){
                    Production3 pro3 = production3Service.getProduction3(id);
                    pro3.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(pro3);
                }
                else if(type.equals("Retur Penjualan")){
                    ReturnSales rs = returnsalesService.getReturnSales(id);
                    rs.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(rs);
                }
                else if(type.equals("Faktur Gudang")){
                    InvoiceWarehouseOut iwo = invoicewarehouseService.getInvoiceWarehouseOut(id);
                    iwo.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(iwo);
                }
                else if(type.equals("Produksi")){
                    Production p = productionService.getProduction(id);
                    p.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(p);
                }
                else if(type.equals("Penyesuaian Stok")){
                    AdjustmentOut ao = adjustmentService.getAdjustmentOut(id);
                    ao.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(ao);
                }
                else if(type.equals("Pindah Barang")){
                    Production2 pro = production2Service.getProduction2(id);
                    pro.setStatus(GlobalFields.CHECKED);
                    generalService.Merge(pro);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        
        for(String value : nselectedvalues){
            String [] temp = value.split("-");
            String type = temp[0];
            int id = Integer.parseInt(temp[1]);
            
            try{
            
                if(type.equals("Faktur Penjualan")){
                    InvoiceSales is = invoicesalesService.getInvoiceSales(id);
                    is.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(is);
                }
                else if(type.equals("Produksi Ke CN")){
                    Production3 pro3 = production3Service.getProduction3(id);
                    pro3.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(pro3);
                }
                else if(type.equals("Retur Penjualan")){
                    ReturnSales rs = returnsalesService.getReturnSales(id);
                    rs.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(rs);
                }
                else if(type.equals("Faktur Gudang")){
                    InvoiceWarehouseOut iwo = invoicewarehouseService.getInvoiceWarehouseOut(id);
                    iwo.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(iwo);
                }
                else if(type.equals("Produksi")){
                    Production p = productionService.getProduction(id);
                    p.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(p);
                }
                else if(type.equals("Penyesuaian Stok")){
                    AdjustmentOut ao = adjustmentService.getAdjustmentOut(id);
                    ao.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(ao);
                }
                else if(type.equals("Pindah Barang")){
                    Production2 pro = production2Service.getProduction2(id);
                    pro.setStatus(GlobalFields.UNCHECKED);
                    generalService.Merge(pro);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
