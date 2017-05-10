/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Merk;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Purnama
 */
public class MerkService extends Service{
    public List getMerkList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Merk.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getMerkNameList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Merk.class);
        c.addOrder(Order.asc("name"));
        c.setProjection(Projections.property("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Merk getMerk(String name){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Merk.class);
        c.add(Restrictions.eq("name", name));
        session.getTransaction().commit();
        return (Merk)c.uniqueResult();
    }
    
    public Merk getMerk(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Merk w = (Merk)session.get(Merk.class, id);
        session.getTransaction().commit();
        return w;
    }
}
