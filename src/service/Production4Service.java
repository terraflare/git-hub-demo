/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Item_In;
import entity.Production4;
import entity.User;
import entity.Warehouse;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Purnama
 */
public class Production4Service extends Service{
    public List getProduction4List(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction4List(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.add(Restrictions.lt("date", date) ); 
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Production4 getProduction4(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Production4 production4 = (Production4)session.get(Production4.class, id);
        session.getTransaction().commit();
        return production4;
    }
    
    public List getProduction4List(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction4List(Item_In itemin){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.add(Restrictions.eq("itemin", itemin));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction4List(Warehouse warehouse, Item_In itemin){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.add(Restrictions.eq("itemin", itemin));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction3List(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction4List(Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production4.class);
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
}
