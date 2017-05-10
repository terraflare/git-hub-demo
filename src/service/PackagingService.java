/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Packaging;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Purnama
 */
public class PackagingService extends Service{
    public List getPackagingList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Packaging.class);
        c.addOrder(Order.asc("unit"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Packaging getPackaging(Float amount, String unit){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Packaging.class);
        c.add(Restrictions.eq("amount", amount));
        c.add(Restrictions.eq("unit", unit));
        session.getTransaction().commit();
        return (Packaging)c.uniqueResult();
    }
    
    public Packaging getPackaging(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Packaging q = (Packaging)session.get(Packaging.class, id);
        session.getTransaction().commit();
        return q;
    }
}
