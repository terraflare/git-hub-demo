/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import entity.Connection;
import entity.User;
import java.util.List;

/**
 *
 * @author Purnama
 */
public class GlobalFields {
    
    public static User USER;
    
    public static List<Connection> CONNECTIONLIST;
    
    public static String [] LOGIN_CHOICE = {"GULA", "PLASTIK"};
    
    public static String [] USER_ROLE = {"SUPERADMIN", "ADMIN", "USER"};
    
    public static String [] PACKAGING_UNIT = {"G", "KG"};
    
    public static boolean INACTIVE = false;
    public static boolean ACTIVE = true;
    
    public static boolean SUCCESS = true;
    public static boolean FAIL = false;
    
    public static boolean CHECKED = true;
    public static boolean UNCHECKED = false;
    
    public static boolean IN = true;
    public static boolean OUT = false;
}
