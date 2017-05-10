/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.InvoicePurchase;
import entity.Item_In;
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
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoicePurchaseService extends Service{
    public List getInvoicePurchaseList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoicePurchaseList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.lt("date", date)); 
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getUncheckedInvoicePurchaseList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoicePurchaseList(Warehouse warehouse, Item_In item){
     Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoicePurchaseList(Warehouse warehouse, Item_In item, Date begin, Date end){
     Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public InvoicePurchase getInvoicePurchase(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        InvoicePurchase invoicepurchase = (InvoicePurchase)session.get(InvoicePurchase.class, id);
        session.getTransaction().commit();
        return invoicepurchase;
    }
    
    public int countUncheckedInvoicePurchase(Item_In itemin, Warehouse warehouse){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.add(Restrictions.eq("item", itemin));
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
    
    public int countCheckedInvoicePurchase(Item_In itemin, Warehouse warehouse){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoicePurchase.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
        c.add(Restrictions.eq("item", itemin));
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
