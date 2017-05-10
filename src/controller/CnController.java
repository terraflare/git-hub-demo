/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AdjustmentCn;
import entity.Cn;
import java.util.ArrayList;
import java.util.Date;
import service.CnService;
import service.GeneralService;
import tablemodel.CnTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class CnController {
    
    private CnService cnService;
    private GeneralService generalService;
    
    public CnController(){
        cnService = new CnService();
        generalService = new GeneralService();
    }
    
    public ArrayList<Cn> getCnList(){
        return(ArrayList<Cn>)cnService.getCnList();
    }
    
    public Cn getCn(int id){
        return cnService.getCn(id);
    }
    
    public void init(){
        Cn cn = new Cn();
        cn.setStock(0);
        
        generalService.Persist(cn);
    }

    public void setCnWarehouseStock(CnTableModel ctm, int itemid, float quantity, String text) {
        Cn cn = getCn(itemid);
        
        float oldstock = cn.getStock();
        
        cn.setStock(quantity);
        generalService.Merge(cn);
        
        AdjustmentCn ac = new AdjustmentCn();
        ac.setCn(cn);
        ac.setDate(new Date());
        ac.setNote(text);
        ac.setQuantity(quantity-oldstock);
        ac.setStatus(GlobalFields.UNCHECKED);
        ac.setUser(GlobalFields.USER);
        generalService.Persist(ac);
        
        ctm.editRow(cn);
    }
}
