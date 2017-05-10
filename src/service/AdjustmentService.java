/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.AdjustmentCn;
import entity.AdjustmentIn;
import entity.AdjustmentOut;
import entity.Item_In;
import entity.Item_Out;
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
public class AdjustmentService extends Service{
    
    public List getAdjustmentCnList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentCn.class);
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentCnList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentCn.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentCnList(Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentCn.class);
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentInList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentIn.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentInList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentIn.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentInList(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentIn.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentOutList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentOut.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentOutList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentOut.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getAdjustmentOutList(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(AdjustmentOut.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public AdjustmentCn getAdjustmentCn(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        AdjustmentCn ac = (AdjustmentCn)session.get(AdjustmentCn.class, id);
        session.getTransaction().commit();
        return ac;
    }
    
    public AdjustmentIn getAdjustmentIn(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        AdjustmentIn ai = (AdjustmentIn)session.get(AdjustmentIn.class, id);
        session.getTransaction().commit();
        return ai;
    }
    
    public AdjustmentOut getAdjustmentOut(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        AdjustmentOut ao = (AdjustmentOut)session.get(AdjustmentOut.class, id);
        session.getTransaction().commit();
        return ao;
    }
}
