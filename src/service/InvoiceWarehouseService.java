/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.InvoiceWarehouseIn;
import entity.InvoiceWarehouseOut;
import entity.Item_In;
import entity.Item_Out;
import entity.User;
import entity.Warehouse;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseService extends Service{
    public List getInvoiceWarehouseInList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceWarehouseInList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceWarehouseOutList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceWarehouseOutList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public InvoiceWarehouseIn getInvoiceWarehouseIn(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        InvoiceWarehouseIn invoicewarehouse = (InvoiceWarehouseIn)session.get(InvoiceWarehouseIn.class, id);
        session.getTransaction().commit();
        return invoicewarehouse;
    }
    
    public InvoiceWarehouseOut getInvoiceWarehouseOut(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        InvoiceWarehouseOut invoicewarehouse = (InvoiceWarehouseOut)session.get(InvoiceWarehouseOut.class, id);
        session.getTransaction().commit();
        return invoicewarehouse;
    }
    
    public List getInInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("destination", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInInvoiceWarehouseInList(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("destination", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getOutInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getOutInvoiceWarehouseInList(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getOutInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getOutInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("destination", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("destination", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
//    public List getCheckedInInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("destination", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getCheckedOutInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getCheckedInInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("destination", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getCheckedOutInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getUncheckedInInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("destination", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getUncheckedOutInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getUncheckedInInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("destination", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getUncheckedOutInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("item", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
    
    public int countCheckedInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
        Criterion origin = Restrictions.eq("warehouse", warehouse);
        Criterion destination = Restrictions.eq("destination", warehouse);
        LogicalExpression orExp = Restrictions.or(origin,destination);
        c.add(orExp);
        c.add(Restrictions.eq("item", item));
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
    
    public int countCheckedInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
        Criterion origin = Restrictions.eq("warehouse", warehouse);
        Criterion destination = Restrictions.eq("destination", warehouse);
        LogicalExpression orExp = Restrictions.or(origin,destination);
        c.add(orExp);
        c.add(Restrictions.eq("item", item));
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
    
    public int countUncheckedInvoiceWarehouseInList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseIn.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        Criterion origin = Restrictions.eq("warehouse", warehouse);
        Criterion destination = Restrictions.eq("destination", warehouse);
        LogicalExpression orExp = Restrictions.or(origin,destination);
        c.add(orExp);
        c.add(Restrictions.eq("item", item));
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
    
    public int countUncheckedInvoiceWarehouseOutList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceWarehouseOut.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        Criterion origin = Restrictions.eq("warehouse", warehouse);
        Criterion destination = Restrictions.eq("destination", warehouse);
        LogicalExpression orExp = Restrictions.or(origin,destination);
        c.add(orExp);
        c.add(Restrictions.eq("item", item));
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
