/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Plastic;
import entity.Warehouse;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemService extends Service{
    public List getItemInList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_In.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getActiveItemInList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_In.class);
        c.add(Restrictions.eq("active", GlobalFields.ACTIVE));
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getActiveItemOutList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_Out.class);
        c.add(Restrictions.eq("active", GlobalFields.ACTIVE));
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Item_In getItemIn(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Item_In item = (Item_In)session.get(Item_In.class, id);
        session.getTransaction().commit();
        return item;
    }
    
    public List getItemOutList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_Out.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getItemOutList(Plastic plastic){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_Out.class);
        c.addOrder(Order.asc("name"));
        c.add(Restrictions.eq("plastic", plastic));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Item_Out getItemOut(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Item_Out item = (Item_Out)session.get(Item_Out.class, id);
        session.getTransaction().commit();
        return item;
    }
    
    public Item_In_Warehouse getItem_In_Warehouse(Item_In item, Warehouse warehouse){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_In_Warehouse.class);
        c.add(Restrictions.eq("item", item));
        c.add(Restrictions.eq("warehouse", warehouse));
        Item_In_Warehouse iiw = (Item_In_Warehouse)c.uniqueResult();
        session.getTransaction().commit();
        return iiw;
    }
    
    public Item_Out_Warehouse getItem_Out_Warehouse(Item_Out item, Warehouse warehouse){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_Out_Warehouse.class);
        c.add(Restrictions.eq("item", item));
        c.add(Restrictions.eq("warehouse", warehouse));
        Item_Out_Warehouse iow = (Item_Out_Warehouse)c.uniqueResult();
        session.getTransaction().commit();
        return iow;
    }
}
