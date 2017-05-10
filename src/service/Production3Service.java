/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Item_Out;
import entity.Production3;
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
public class Production3Service extends Service{
    public List getProduction3List(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction3List(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.add(Restrictions.lt("date", date) ); 
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Production3 getProduction3(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Production3 production3 = (Production3)session.get(Production3.class, id);
        session.getTransaction().commit();
        return production3;
    }
    
    public List getProduction3List(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction3List(Item_Out itemout){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.add(Restrictions.eq("itemout", itemout));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction3List(Warehouse warehouse, Item_Out itemout){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.add(Restrictions.eq("itemout", itemout));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction3List(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction3List(Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production3.class);
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
}
