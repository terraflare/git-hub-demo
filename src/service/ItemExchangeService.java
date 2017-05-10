/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.ItemExchange;
import entity.User;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Purnama
 */
public class ItemExchangeService extends Service{
    
    public List getItemExchangeList(User user){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ItemExchange.class);
        c.add(Restrictions.eq("user", user));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public ItemExchange getItemExchange(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        ItemExchange itemexchange = (ItemExchange)session.get(ItemExchange.class, id);
        session.getTransaction().commit();
        return itemexchange;
    }
    
    public List getItemExchangeList(Date date){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(ItemExchange.class);
        c.add(Restrictions.lt("date", date));
        c.addOrder(Order.desc("date"));
        session.getTransaction().commit();
        return c.list();
    }
}
