/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.InvoiceSales;
import entity.Item_Out;
import entity.User;
import entity.Warehouse;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceSalesService extends Service{
    
    public List getInvoiceSalesList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceSalesList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getUncheckedInvoiceSalesList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
//    public List getUncheckedInvoiceSalesList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceSales.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getCheckedInvoiceSalesList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceSales.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
    
    public List getInvoiceSalesList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceSalesList(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public InvoiceSales getInvoiceSales(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        InvoiceSales invoicesales = (InvoiceSales)session.get(InvoiceSales.class, id);
        session.getTransaction().commit();
        return invoicesales;
    }
    
    public int countCheckedInvoiceSales(Item_Out itemout, Warehouse warehouse){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
        c.add(Restrictions.eq("item", itemout));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.setProjection(Projections.rowCount());
        List result = c.list();
        session.getTransaction().commit();
        try{
            return Integer.parseInt(result.get(0).toString());
            
        }
        catch(Exception e){
            return 0;
        }
    }
    
    public int countUncheckedInvoiceSales(Item_Out itemout, Warehouse warehouse){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.add(Restrictions.eq("item", itemout));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.setProjection(Projections.rowCount());
        List result = c.list();
        session.getTransaction().commit();
        try{
            return Integer.parseInt(result.get(0).toString());
            
        }
        catch(Exception e){
            return 0;
        }
    }
}
