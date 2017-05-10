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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import service.AdjustmentService;
import service.CnService;
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
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ExportController {
    
    AdjustmentService adjustmentService;
    InvoicePurchaseService invoicepurchaseService;
    InvoiceSalesService invoicesalesService;
    InvoiceSales2Service invoicesales2Service;
    InvoiceWarehouseService invoicewarehouseService;
    ProductionService productionService;
    Production2Service production2Service;
    WarehouseService warehouseService;
    ItemService itemService;
    ReturnSalesService returnsalesService;
    ReturnPurchaseService returnpurchaseService;
    Production3Service production3Service;
    Production4Service production4Service;
    CnService cnService;
    
    public ExportController(){
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
        returnsalesService = new ReturnSalesService();
        returnpurchaseService = new ReturnPurchaseService();
        cnService = new CnService();
    }
    
    public void outexportstock(List itemid, String war){
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        HSSFSheet sheet = workbook.createSheet("SHEET1");
        
        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        
        data.put(1, new Object[]{"Laporan Stok di : " + war + " tanggal " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())});
        data.put(2, new Object[]{"NAMA BARANG", "MEREK", "KEMASAN", "STATUS", "STOK(KARUNG)", "STOK(UNIT)"});
        
        int counter = 3;
        
        for(Object ob : itemid){
            int id = (Integer)ob;
            
            Item_Out item = itemService.getItemOut(id);
            Warehouse warehouse = warehouseService.getWarehouse(war);
            
            String status;
            
            if(item.isActive() == GlobalFields.ACTIVE){
                status = "AKTIF";
            }
            else{
                status = "NONAKTIF";
            }
            
            float currentstock = warehouseService.getItem_Out_Warehouse(warehouse, item).getStock();
            int sacksize = item.getSacksize();
            
            data.put(counter, new Object[]{item.getName(), item.getMerk().getName(),
            item.getPackaging().getAmount() + " " + item.getPackaging().getUnit(), status,
            (int)currentstock/sacksize, Float.parseFloat(String.format("%.1f", currentstock%sacksize))});
            
            Set<Integer> keyset = data.keySet();
            
            int rownum = 0;
            for (Integer key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if(obj instanceof Date)
                        cell.setCellValue((Date)obj);
                    else if(obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                    else if(obj instanceof Float)
                        cell.setCellValue((Float)obj);
                }
            }
            counter++;
        }
        
        try {
            FileOutputStream out =
                    new FileOutputStream(new File("stokproduksi" + war + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
            
        } catch (IOException eee) {
            eee.printStackTrace();
            
        }
    }
    
    public void inexportstock(List itemid, String war){
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        HSSFSheet sheet = workbook.createSheet("SHEET1");
        
        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        
        data.put(1, new Object[]{"Laporan Stok di : " + war + " tanggal " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())});
        data.put(2, new Object[]{"NAMA BARANG", "KUALITAS", "STATUS", "STOK"});
        
        int counter = 3;
        
        for(Object ob : itemid){
            int id = (Integer)ob;
            
            Item_In item = itemService.getItemIn(id);
            Warehouse warehouse = warehouseService.getWarehouse(war);
            
            String status;
            
            if(item.isActive() == GlobalFields.ACTIVE){
                status = "AKTIF";
            }
            else{
                status = "NONAKTIF";
            }
            
            int currentstock = warehouseService.getItem_In_Warehouse(warehouse, item).getStock();
            
            data.put(counter, new Object[]{item.getName(), item.getQuality().getCategory().getName() + " " + item.getQuality().getName(),
                status,
            currentstock});
            
            Set<Integer> keyset = data.keySet();
            
            int rownum = 0;
            for (Integer key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if(obj instanceof Date)
                        cell.setCellValue((Date)obj);
                    else if(obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                    else if(obj instanceof Float)
                        cell.setCellValue((Float)obj);
                }
            }
            counter++;
        }
        
        try {
            FileOutputStream out =
                    new FileOutputStream(new File("stokcurah" + war + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
            
        } catch (IOException eee) {
            eee.printStackTrace();
        }
    }
    
    public void cnexportgeneral(Date start, Date end){
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        Cn cn = cnService.getCn(1);
        
        Date sdate = DateUtil.toStartOfDay(start);
        Date edate = DateUtil.toEndofDay(end);
        
        List<Report> ls = new ArrayList<Report>();
        
        List pro3list = production3Service.getProduction3List(sdate, edate);
        List pro4list = production4Service.getProduction4List(sdate, edate);
        List adjcnlist = adjustmentService.getAdjustmentCnList(sdate, edate);
        
        for(Object o : pro3list){
            Production3 production = (Production3)o;

            Report report = new Report();
            report.setNote("diproduksi dari " + production.getItemout().getName());
            report.setDate(production.getDate());
            report.setQuantity(production.getDifference());
            report.setStatus(GlobalFields.IN);
            report.setType("Produksi Ke CN");
            report.setTypeid(production.getId());
            report.setUser(production.getUser());
            report.setInputquantity(production.getDifference());
            report.setChecked(production.isStatus());

            ls.add(report);

            Report nreport = new Report();

            float diff = production.getDifference() - production.getOutquantity();

            Calendar cal = Calendar.getInstance();
            cal.setTime(production.getDate());
            cal.add(Calendar.SECOND, 1);
            Date ndate = cal.getTime();

            //impas
            if(diff == 0){

            }
            //lebih
            else if(diff < 0){
                nreport.setNote("Lebih");
                nreport.setDate(ndate);
                nreport.setQuantity(-diff);
                nreport.setStatus(GlobalFields.IN);
                nreport.setUser(production.getUser());
                ls.add(nreport);
            }
            //susut
            else{
                nreport.setNote("Susut");
                nreport.setDate(ndate);
                nreport.setQuantity(diff);
                nreport.setStatus(GlobalFields.OUT);
                nreport.setUser(production.getUser());
                ls.add(nreport);
            }   
        }
        
        for(Object o : pro4list){
            Production4 production = (Production4)o;

            Report report = new Report();
            report.setNote("diubah ke " + production.getItemin().getName());
            report.setDate(production.getDate());
            report.setQuantity(production.getDifference());
            report.setStatus(GlobalFields.OUT);
            report.setType("CN Ke Curah");
            report.setTypeid(production.getId());
            report.setUser(production.getUser());
            report.setInputquantity(production.getDifference());
            report.setChecked(production.isStatus());

            ls.add(report);

            Report nreport = new Report();

            float diff = production.getDifference() - production.getInquantity();

            Calendar cal = Calendar.getInstance();
            cal.setTime(production.getDate());
            cal.add(Calendar.SECOND, 1);
            Date ndate = cal.getTime();

            //impas
            if(diff == 0){

            }
            //lebih
            else if(diff < 0){
                nreport.setNote("Lebih");
                nreport.setDate(ndate);
                nreport.setQuantity(-diff);
                nreport.setStatus(GlobalFields.OUT);
                nreport.setUser(production.getUser());
                ls.add(nreport);
            }
            //susut
            else{
                nreport.setNote("Susut");
                nreport.setDate(ndate);
                nreport.setQuantity(diff);
                nreport.setStatus(GlobalFields.IN);
                nreport.setUser(production.getUser());
                ls.add(nreport);
            }   
        }
        
        for(Object o : adjcnlist){
            AdjustmentCn ac = (AdjustmentCn)o;
            Report report = new Report();
            report.setDate(ac.getDate());
            report.setNote(ac.getNote());
            report.setType("Penyesuaian Stok");
            report.setTypeid(ac.getId());
            report.setUser(ac.getUser());
            report.setChecked(ac.isStatus());

            if(ac.getQuantity() < 0){
                report.setQuantity(-ac.getQuantity());
                report.setStatus(GlobalFields.OUT);
            }
            else{
                report.setQuantity(ac.getQuantity());
                report.setStatus(GlobalFields.IN);
            }

            ls.add(report);
        }
        
        Comparator reportcomp = new ReportComparator();
        Collections.sort(ls, reportcomp);
            
        float currentstock = cn.getStock();
            
        HSSFSheet sheet = workbook.createSheet("CN");
            
        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        int counter = ls.size()+3;
            
        data.put(1, new Object[]{"CN"});
        data.put(2, new Object[]{"Jangka waktu : " + new SimpleDateFormat("yyyy-MM-dd").format(sdate) + " sd " + new SimpleDateFormat("yyyy-MM-dd").format(edate)});
        data.put(3, new Object[]{"TANGGAL", "KETERANGAN", "PENGGUNA", "CATATAN", "MASUK(KG)",
            "KELUAR(KG)", "STOK(KG)"});
            
        for(Report report : ls){
            String newstring = new SimpleDateFormat("yyyy-MM-dd").format(report.getDate());

            if(report.isStatus() == GlobalFields.IN){
                data.put(counter, new Object[]{newstring, report.getType(), 
                report.getUser().getUsername(), report.getNote(), 
                Float.parseFloat(String.format("%.1f", report.getQuantity())),
                0, Float.parseFloat(String.format("%.1f", currentstock))});
            }
            else{
                data.put(counter, new Object[]{newstring, report.getType(), 
                report.getUser().getUsername(), report.getNote(), 
                0,
                Float.parseFloat(String.format("%.1f", report.getQuantity())),
                Float.parseFloat(String.format("%.1f", currentstock))});
            }

            if(report.isStatus() == GlobalFields.IN){
                currentstock -= report.getQuantity();
            }
            else{
                currentstock += report.getQuantity();
            }
            counter--;
        }

        Set<Integer> keyset = data.keySet();

        int rownum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof Float)
                    cell.setCellValue((Float)obj);
            }
        }
        
        
        try {
            FileOutputStream out =
                    new FileOutputStream(new File("akumulasicn" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
        } catch (IOException eee) {
            eee.printStackTrace();
        }
    }
    
    public void outexportgeneral(List itemid, String war, Date start, Date end){
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        for(Object ob : itemid){
            int id = (Integer)ob;
            
            Item_Out item = itemService.getItemOut(id);
            Warehouse warehouse = warehouseService.getWarehouse(war);
            Date sdate = DateUtil.toStartOfDay(start);
            Date edate = DateUtil.toEndofDay(end);
            
            List<Report> ls = new ArrayList<Report>();
            
            List isls = invoicesalesService.getInvoiceSalesList(warehouse, item, sdate, edate);
            List prols =  productionService.getProductionList(warehouse, item, sdate, edate);
            List inpro2ls =  production2Service.getInProduction2List(warehouse, item);
            List outpro2ls =  production2Service.getOutProduction2List(warehouse, item);
            List inwarls = invoicewarehouseService.getInInvoiceWarehouseOutList(warehouse, item, sdate, edate);
            List outwarls = invoicewarehouseService.getOutInvoiceWarehouseOutList(warehouse, item, sdate, edate);
            List adjls = adjustmentService.getAdjustmentOutList(warehouse, item, sdate, edate);
            List retls = returnsalesService.getReturnSalesList(warehouse, item, sdate, edate);
            List pro3ls = production3Service.getProduction3List(warehouse, item, sdate, edate);
            
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

            for(Object o : prols){
                Production production = (Production)o;

                Report report = new Report();
                report.setNote("diproduksi dari " + production.getItemin().getName());
                report.setDate(production.getDate());
                report.setQuantity(production.getInputquantity());
                report.setStatus(GlobalFields.IN);
                report.setType("Produksi");
                report.setTypeid(production.getId());
                report.setUser(production.getUser());
                report.setInputquantity(production.getInputquantity());
                report.setChecked(production.isStatus());

                ls.add(report);
                
                Report nreport = new Report();
                
                float diff = production.getInputquantity() - production.getOutquantity();
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(production.getDate());
                cal.add(Calendar.SECOND, 1);
                Date ndate = cal.getTime();
                
                //impas
                if(diff == 0){
                    
                }
                //lebih
                else if(diff < 0){
                    nreport.setNote("Lebih produksi");
                    nreport.setDate(ndate);
                    nreport.setQuantity(-diff);
                    nreport.setStatus(GlobalFields.IN);
                    nreport.setUser(production.getUser());
                    ls.add(nreport);
                }
                //susut
                else{
                    nreport.setNote("Susut produksi");
                    nreport.setDate(ndate);
                    nreport.setQuantity(diff);
                    nreport.setStatus(GlobalFields.OUT);
                    nreport.setUser(production.getUser());
                    ls.add(nreport);
                }   
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
            
            float currentstock = warehouseService.getItem_Out_Warehouse(warehouse, item).getStock();
            int sacksize = item.getSacksize();
            
            HSSFSheet sheet = workbook.createSheet(item.getName()+ "~" + item.getId());
            
            Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
            int counter = ls.size()+3;
            
            String detail = item.getName() + " ~ " + item.getMerk().getName() + " ~ " + item.getPackaging().getAmount() + " " + item.getPackaging().getUnit();
            
            data.put(1, new Object[]{detail});
            data.put(2, new Object[]{"Jangka waktu : " + new SimpleDateFormat("yyyy-MM-dd").format(sdate) + " sd " + new SimpleDateFormat("yyyy-MM-dd").format(edate)});
            data.put(3, new Object[]{"TANGGAL", "KETERANGAN", "PENGGUNA", "CATATAN", "MASUK(KARUNG)",
                "MASUK(UNIT)", "KELUAR(KARUNG)", "KELUAR(UNIT)", "STOK(KARUNG)", "STOK(UNIT)"});
            
            for(Report report : ls){
                String newstring = new SimpleDateFormat("yyyy-MM-dd").format(report.getDate());
                
                if(report.isStatus() == GlobalFields.IN){
                    data.put(counter, new Object[]{newstring, report.getType(), 
                    report.getUser().getUsername(), report.getNote(), 
                    (int)report.getQuantity()/sacksize, Float.parseFloat(String.format("%.1f", report.getQuantity()%sacksize)),
                    0, 0, (int)currentstock/sacksize,
                    Float.parseFloat(String.format("%.1f", currentstock%sacksize))});
                }
                else{
                    data.put(counter, new Object[]{newstring, report.getType(), 
                    report.getUser().getUsername(), report.getNote(), 
                    0, 0,
                    (int)report.getQuantity()/sacksize, Float.parseFloat(String.format("%.1f", report.getQuantity()%sacksize)),
                    (int)currentstock/sacksize,
                    Float.parseFloat(String.format("%.1f", currentstock%sacksize))});
                }
                
                if(report.isStatus() == GlobalFields.IN){
                    currentstock -= report.getQuantity();
                }
                else{
                    currentstock += report.getQuantity();
                }
                counter--;
            }
            
            Set<Integer> keyset = data.keySet();
            
            int rownum = 0;
            for (Integer key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if(obj instanceof Date)
                        cell.setCellValue((Date)obj);
                    else if(obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                    else if(obj instanceof Float)
                        cell.setCellValue((Float)obj);
                }
            }
        }
        
        try {
            FileOutputStream out =
                    new FileOutputStream(new File("akumulasiproduksi" + war + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
        } catch (IOException eee) {
            eee.printStackTrace();
        }
    }
    
    public void inexportgeneral(List itemid, String war, Date start, Date end){
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        for(Object ob : itemid){
            int id = (Integer)ob;
            
            Item_In item = itemService.getItemIn(id);
            Warehouse warehouse = warehouseService.getWarehouse(war);
            Date sdate = DateUtil.toStartOfDay(start);
            Date edate = DateUtil.toEndofDay(end);
            
            List<Report> ls = new ArrayList<Report>();
        
            List ipls = invoicepurchaseService.getInvoicePurchaseList(warehouse, item, sdate, edate);
            List prols =  productionService.getProductionList(warehouse, item, sdate, edate);
            List inwarls = invoicewarehouseService.getInInvoiceWarehouseInList(warehouse, item, sdate, edate);
            List outwarls = invoicewarehouseService.getOutInvoiceWarehouseInList(warehouse, item, sdate, edate);
            List adjls = adjustmentService.getAdjustmentInList(warehouse, item, sdate, edate);
            List isls = invoicesales2Service.getInvoiceSales2List(warehouse, item, sdate, edate);
            List rpls = returnpurchaseService.getReturnPurchaseList(warehouse, item, sdate, edate);
            List pro4ls = production4Service.getProduction3List(warehouse, item, sdate, edate);

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
            
            int currentstock = warehouseService.getItem_In_Warehouse(warehouse, item).getStock();
            
            HSSFSheet sheet = workbook.createSheet(item.getName() + "~" + item.getId());
            
            Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
            int counter = ls.size()+3;
            
            String detail = item.getName() + " ~ " + item.getQuality().getCategory().getName() + " " + item.getQuality().getName();
            
            data.put(1, new Object[]{detail});
            data.put(2, new Object[]{"Jangka waktu : " + new SimpleDateFormat("yyyy-MM-dd").format(sdate) + " sd " + new SimpleDateFormat("yyyy-MM-dd").format(edate)});
            data.put(3, new Object[]{"TANGGAL", "KETERANGAN", "PENGGUNA", "CATATAN", "MASUK", "KELUAR", "STOK"});
            
            for(Report report : ls){
                
                String newstring = new SimpleDateFormat("yyyy-MM-dd").format(report.getDate());
                
                if(report.isStatus() == GlobalFields.IN){
                    data.put(counter, new Object[]{newstring, report.getType(), 
                    report.getUser().getUsername(), report.getNote(), 
                    (int)report.getQuantity(), 0 ,currentstock});
                }
                else{
                    data.put(counter, new Object[]{newstring.toString(), report.getType(), 
                    report.getUser().getUsername(), report.getNote(), 
                    0, (int)report.getQuantity(), currentstock});
                }
                
                if(report.isStatus() == GlobalFields.IN){
                    currentstock -= report.getQuantity();
                }
                else{
                    currentstock += report.getQuantity();
                }
                
                counter--;
            }
            
            Set<Integer> keyset = data.keySet();
            int rownum = 0;
            for (Integer key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if(obj instanceof Date)
                        cell.setCellValue((Date)obj);
                    else if(obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                }
            }   
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File("akumulasicurah" + war + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
        } catch (IOException eee) {
            eee.printStackTrace();
        }
    }
}
