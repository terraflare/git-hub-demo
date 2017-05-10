/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cn;
import entity.Warehouse;
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
public class CnService extends Service{
    
    public List getCnList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Cn.class);
        c.addOrder(Order.asc("id"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Cn getCn(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Cn q = (Cn)session.get(Cn.class, id);
        session.getTransaction().commit();
        return q;
    }
    
    public int countCheckedCnList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Cn.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
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
    
    public int countUncheckedCnList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Cn.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
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
