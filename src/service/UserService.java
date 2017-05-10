/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Purnama
 */
public class UserService extends Service{
    
    public User Login(String username, String password){
        Session session = currentSession();
        session.getTransaction().begin();
//        
//        Query query = session.createSQLQuery("ALTER TABLE invoicepurchase MODIFY quantity FLOAT");
//        System.out.println(query.executeUpdate());
//        
//        Query query2 = session.createSQLQuery("ALTER TABLE invoicesales MODIFY quantity FLOAT");
//        System.out.println(query2.executeUpdate());
//        
//        Query query3 = session.createSQLQuery("ALTER TABLE itemoutwarehouse MODIFY stock FLOAT");
//        System.out.println(query3.executeUpdate());
//        
//        Query query4 = session.createSQLQuery("ALTER TABLE production MODIFY inquantity FLOAT");
//        System.out.println(query4.executeUpdate());
//        
//        Query query5 = session.createSQLQuery("ALTER TABLE production MODIFY inputquantity FLOAT");
//        System.out.println(query5.executeUpdate());
//        
//        Query query6 = session.createSQLQuery("ALTER TABLE invoicewarehousein MODIFY quantity FLOAT");
//        System.out.println(query6.executeUpdate());
//        
//        Query query7 = session.createSQLQuery("ALTER TABLE invoicewarehouseout MODIFY quantity FLOAT");
//        System.out.println(query7.executeUpdate());
        
        Criteria c = session.createCriteria(User.class);
        c.add(Restrictions.eq("username", username));
        c.add(Restrictions.eq("password", password));
        User u = (User)c.uniqueResult();
        session.getTransaction().commit();
        return u;
    }
    
    public List getUserList(){
        Session session = currentSession();
        session.getTransaction().begin();
        Criteria c = session.createCriteria(User.class);
        c.addOrder(Order.asc("username"));
        session.getTransaction().commit();
        return c.list();
    }
    
    public User getUser(int id){
        Session session = currentSession();
        session.getTransaction().begin();
        User u = (User)session.get(User.class, id);
        session.getTransaction().commit();
        return u;
    }
}
