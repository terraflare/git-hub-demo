/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Category;
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
public class CategoryService extends Service{
    public List getCategoryList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Category.class);
        c.addOrder(Order.asc("name"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public Category getCategory(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        Category q = (Category)session.get(Category.class, id);
        session.getTransaction().commit();
        return q;
    }
    
    public Category getCategory(String name){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Category.class);
        c.add(Restrictions.eq("name", name));
        session.getTransaction().commit();
        return (Category)c.uniqueResult();
    }
    
    public List getCategoryNameList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(Category.class);
        c.addOrder(Order.asc("name"));
        c.setProjection(Projections.property("name"));
        session.getTransaction().commit();
        return c.list();
    }
}
