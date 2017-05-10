/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Quality;
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
public class QualityService extends Service {
    public List getQualityList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Quality.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getQualityNameList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Quality.class);
        c.addOrder(Order.asc("name"));
        c.setProjection(Projections.property("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Quality getQuality(String name){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Quality.class);
        c.add(Restrictions.eq("name", name));
        session.getTransaction().commit();
        return (Quality)c.uniqueResult();
    }
    
   public Quality getQuality(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Quality q = (Quality)session.get(Quality.class, id);
        session.getTransaction().commit();
        return q;
    }
}
