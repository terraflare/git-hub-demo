/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Item_In;
import entity.Item_Out;
import entity.Production;
import entity.User;
import entity.Warehouse;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ProductionService extends Service{
    public List getProductionList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProductionList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.lt("date", date) ); 
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Production getProduction(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Production production = (Production)session.get(Production.class, id);
        session.getTransaction().commit();
        return production;
    }
    
//    public List getCheckedProductionList(Warehouse warehouse, Item_In item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(Production.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("itemin", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getCheckedProductionList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(Production.class);
//        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("itemout", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getUncheckedProductionList(Warehouse warehouse, Item_In item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(Production.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("itemin", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
//    
//    public List getUncheckedProductionList(Warehouse warehouse, Item_Out item){
//        Session session = currentSession();
//        session.getTransaction().begin();
//        Criteria c = session.createCriteria(Production.class);
//        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
//        c.add(Restrictions.eq("warehouse", warehouse));
//        c.add(Restrictions.eq("itemout", item));
//        c.addOrder(Order.desc("date"));
//        session.getTransaction().commit();
//        return c.list();
//    }
    
    public List getProductionList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProductionList(Warehouse warehouse, Item_Out item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProductionList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getProductionList(Warehouse warehouse, Item_In item, Date begin, Date end){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
        c.add(Expression.between("date", begin, end));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public int countCheckedProductionList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
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
    
    public int countCheckedProductionList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("status", GlobalFields.CHECKED));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
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
    
    public int countUncheckedProductionList(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemin", item));
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
    
    public int countUncheckedProductionList(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Production.class);
        c.add(Restrictions.eq("status", GlobalFields.UNCHECKED));
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("itemout", item));
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
