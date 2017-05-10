/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import org.hibernate.Session;

/**
 *
 * @author Purnama
 */
public class GeneralService extends Service{
    public void Persist(Object o){
        Session session = currentSession();
        session.getTransaction().begin();
        session.persist(o);
        session.getTransaction().commit();
    }
    
    public void Merge(Object o){
        Session session = currentSession();
        session.getTransaction().begin();
        session.merge(o);
        session.getTransaction().commit();
    }
    
    public void Update(Object o){
        Session session = currentSession();
        session.getTransaction().begin();
        session.update(o);
        session.getTransaction().commit();
    }
    
    public void Delete(Object o){
        Session session = currentSession();
        session.getTransaction().begin();
        session.delete(o);
        session.getTransaction().commit();
    }
    
    public void SaveorUpdate(Object o){
        Session session = currentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
    }
}
