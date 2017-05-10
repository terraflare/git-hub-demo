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
import entity.Plastic_Warehouse;
import entity.Warehouse;
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
public class WarehouseService extends Service{
    public List getWarehouseList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Warehouse.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public List getWarehouseNameList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Warehouse.class);
        c.addOrder(Order.asc("name"));
        c.setProjection(Projections.property("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Warehouse getWarehouse(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Warehouse w = (Warehouse)session.get(Warehouse.class, id);
        session.getTransaction().commit();
        return w;
    }
    
    public Warehouse getWarehouse(String name){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Warehouse.class);
        c.add(Restrictions.eq("name", name));
        session.getTransaction().commit();
        return (Warehouse)c.uniqueResult();
    }
    
    public Item_In_Warehouse getItem_In_Warehouse(Warehouse warehouse, Item_In item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_In_Warehouse.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        session.getTransaction().commit();
        return (Item_In_Warehouse)c.uniqueResult();
    }
    
    public Item_Out_Warehouse getItem_Out_Warehouse(Warehouse warehouse, Item_Out item){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Item_Out_Warehouse.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("item", item));
        session.getTransaction().commit();
        return (Item_Out_Warehouse)c.uniqueResult();
    }
    
    public Plastic_Warehouse getPlastic_Warehouse(Warehouse warehouse, Plastic plastic){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Plastic_Warehouse.class);
        c.add(Restrictions.eq("warehouse", warehouse));
        c.add(Restrictions.eq("plastic", plastic));
        session.getTransaction().commit();
        return (Plastic_Warehouse)c.uniqueResult();
    }
}
