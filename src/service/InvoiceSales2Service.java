/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.InvoiceSales2;
import entity.Item_In;
import entity.User;
import entity.Warehouse;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceSales2Service extends Service{
    public List getInvoiceSales2List(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales2.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceSales2List(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales2.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getUncheckedInvoiceSales2List(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales2.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public InvoiceSales2 getInvoiceSales2(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        InvoiceSales2 invoicesales = (InvoiceSales2)session.get(InvoiceSales2.class, id);
        session.getTransaction().commit();
        return invoicesales;
    }
    
    public List getInvoiceSales2List(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales2.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInvoiceSales2List(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(InvoiceSales2.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
}
