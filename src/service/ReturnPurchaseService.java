/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ReturnPurchase;
import entity.Item_In;
import entity.ReturnPurchase;
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
public class ReturnPurchaseService extends Service{
    public List getReturnPurchaseList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ReturnPurchase.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getReturnPurchaseList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ReturnPurchase.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getUncheckedReturnPurchaseList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ReturnPurchase.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public ReturnPurchase getReturnPurchase(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        ReturnPurchase returnpurchase = (ReturnPurchase)session.get(ReturnPurchase.class, id);
        session.getTransaction().commit();
        return returnpurchase;
    }
    
    public List getReturnPurchaseList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ReturnPurchase.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getReturnPurchaseList(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ReturnPurchase.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
}
