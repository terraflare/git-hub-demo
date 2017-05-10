/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Plastic;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class PlasticService extends Service{
    public List getPlasticList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Plastic.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getActivePlasticList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Plastic.class);
        c.add(Restrictions.eq("active", GlobalFields.ACTIVE));
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getActivePlasticNameList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Plastic.class);
        c.add(Restrictions.eq("active", GlobalFields.ACTIVE));
        c.addOrder(Order.asc("name"));
        c.setProjection(Projections.property("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Plastic getPlastic(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Plastic item = (Plastic)session.get(Plastic.class, id);
        session.getTransaction().commit();
        return item;
    }
    
    public Plastic getPlastic(String name){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Plastic.class);
        c.add(Restrictions.eq("name", name));
        session.getTransaction().commit();
        return (Plastic)c.uniqueResult();
    }
}
