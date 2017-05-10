/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Item_Out;
import entity.Production2;
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
public class Production2Service extends Service {
    public List getProduction2List(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production2.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProduction2List(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production2.class);
        c.add(Restrictions.lt("date", date) ); 
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Production2 getProduction2(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Production2 production2 = (Production2)session.get(Production2.class, id);
        session.getTransaction().commit();
        return production2;
    }
    
    public List getOutProduction2List(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production2.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getOutProduction2List(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production2.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInProduction2List(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production2.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getInProduction2List(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production2.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
}
