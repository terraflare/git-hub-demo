/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Purnama
 */
public class Service {
    
    Session currentSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }
}
