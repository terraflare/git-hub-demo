/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import MyGUI.MyButton;
import MyGUI.MyFrame;
import MyGUI.MyImageIcon;
import MyGUI.MyLabel;
import MyGUI.MyPasswordField;
import MyGUI.MyTextField;
import controller.CnController;
import controller.ConnectionController;
import controller.LoginController;
import controller.UserController;
import entity.Connection;
import entity.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import util.GlobalFields;
import util.HibernateUtil;

/**
 *
 * @author Purnama
 */
public class Login implements Printable{
    
    MyFrame loginframe;
    
    Box mainbox;
        
    JPanel upperpanel;
    JPanel usernamepanel;
    JPanel passwordpanel;
    JPanel buttonpanel;
    JPanel lowerpanel;
    JPanel loginpanel;
    JPanel settingpanel;
    JPanel copyrightpanel;
    JPanel connectionpanel;
    JPanel passwordpanel2;
    JPanel buttonpanel2;
    
    MyButton loginbutton;
    MyButton settingbutton;
    MyButton connectbutton;
    
    MyLabel usernamelabel;
    MyLabel passwordlabel;
    MyLabel connectionlabel;
    MyLabel passwordlabel2;
    MyLabel copyrightlabel;
    MyLabel picturelabel;
    
    JComboBox connectioncombobox;
    JComboBox choicecombobox;
    
    MyTextField usernametextfield;
    
    MyPasswordField passwordfield;
    MyPasswordField passwordfield2;
    
    ConnectionController connectionController;
    UserController userController;
    LoginController loginController;
    CnController cnController;
    
    public Login(){
        
        loginframe = new MyFrame("Login");
        
        connectionController = new ConnectionController();
        userController = new UserController();
        loginController = new LoginController();
        cnController = new CnController();
        
        File file = new File("connection.xml");
        
        if(!file.exists()){
            try {
                connectionController.init();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        connectionController.getConnections();
        
        mainbox = Box.createVerticalBox();
        
        upperpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamepanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        passwordpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lowerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loginpanel = new JPanel(new GridLayout(3, 0));
        settingpanel = new JPanel(new GridLayout(3, 0));
        copyrightpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        connectionpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        passwordpanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonpanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        loginpanel.setBorder(BorderFactory.createTitledBorder(" Login "));
        settingpanel.setBorder(BorderFactory.createTitledBorder(" Pengaturan "));
        usernamepanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        passwordpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        connectionpanel.setBorder(new EmptyBorder(0, 10, 0, 25));
        passwordpanel2.setBorder(new EmptyBorder(0, 10, 0, 25));
        lowerpanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        
        picturelabel = new MyLabel(new MyImageIcon().getImage("gui/image/Java_1.jpg"), 756, 450);
        
        usernamelabel = new MyLabel("Nama : ");
        passwordlabel = new MyLabel("Kata Sandi : ");
        connectionlabel = new MyLabel("Koneksi : ");
        passwordlabel2 = new MyLabel("Password : ");
        copyrightlabel = new MyLabel("Created by Purnama & Alexander Copyright \u00a9 2014. All right reserved.");
        
        connectioncombobox = new JComboBox(GlobalFields.CONNECTIONLIST.toArray());
        connectioncombobox.setRenderer(new ConnectionCellRenderer());
        connectioncombobox.setPreferredSize(new Dimension(225, 22));
        
        choicecombobox = new JComboBox(GlobalFields.LOGIN_CHOICE);
        
        usernametextfield = new MyTextField("", 250);
        passwordfield = new MyPasswordField("", 250);
        passwordfield2 = new MyPasswordField("", 225);
        
        loginbutton = new MyButton("Login");
        settingbutton = new MyButton("Atur Koneksi");
        connectbutton = new MyButton("Sambung");
        
//        buttonpanel.add(choicecombobox);
        buttonpanel.add(loginbutton);
        
        upperpanel.add(picturelabel);
        
        usernamepanel.add(usernamelabel);
        usernamepanel.add(usernametextfield);
        
        passwordpanel.add(passwordlabel);
        passwordpanel.add(passwordfield);
        
        connectionpanel.add(connectionlabel);
        connectionpanel.add(connectioncombobox);
        
        passwordpanel2.add(passwordlabel2);
        passwordpanel2.add(passwordfield2);
        
        buttonpanel2.add(connectbutton);
        buttonpanel2.add(settingbutton);
        
        lowerpanel.add(loginpanel);
        lowerpanel.add(settingpanel);
        
        loginpanel.add(usernamepanel);
        loginpanel.add(passwordpanel);
        loginpanel.add(buttonpanel);
        
        settingpanel.add(connectionpanel);
        settingpanel.add(buttonpanel2);
        
        copyrightpanel.add(copyrightlabel);
        
        mainbox.add(upperpanel);
        mainbox.add(lowerpanel);
        mainbox.add(copyrightpanel);
        
        loginframe.add(mainbox);
        
        loginframe.setResizable(false);
        loginframe.display(loginframe);
        loginframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginframe.pack();
        loginframe.setLocationToCenter(loginframe);
        
        loginbutton.addActionListener((ActionEvent e) -> {
            login();
            
//            PrinterJob job = PrinterJob.getPrinterJob();
//            job.setPrintable(this);
//            boolean ok = job.printDialog();
//            if (ok) {
//                try {
//                     job.print();
//                } catch (PrinterException ex) {
//                 /* The job did not successfully complete */
//                }
//            }
        });
        
        connectbutton.addActionListener((ActionEvent e) -> {
            Connection connection = (Connection)connectioncombobox.getSelectedItem();
            
            HibernateUtil.changeDataSource(connection.getConnection()+""+connection.getSchema(), connection.getUsername(), connection.getPassword());
            
            JOptionPane.showMessageDialog(loginframe, "Database berhasil diganti");
        });
        
        settingbutton.addActionListener((ActionEvent e) -> {
            loginframe.dispose();
            new ConnectionManagement();
        });
        
        usernametextfield.addActionListener((ActionEvent e) ->{
            login();
        });
        
        passwordfield.addActionListener((ActionEvent e) ->{
            login();
        });
    }
    
    public void login(){
        User user = loginController.Login(usernametextfield.getText(), String.copyValueOf(passwordfield.getPassword()));
        passwordfield.setText("");
        
        if(userController.getUserList().isEmpty()){
            try{
                user = loginController.init();
                JOptionPane.showMessageDialog(loginframe, "Pengguna 'superadmin' dibuat");
            }
            catch(Exception e){
                
            }
        }
        
        if(cnController.getCnList().isEmpty()){
            cnController.init();
        }
        
        if(user == null){
            JOptionPane.showMessageDialog(loginframe, "Nama atau kata sandi salah");
        }
        else if(user.isActive() == GlobalFields.INACTIVE){
            JOptionPane.showMessageDialog(loginframe, "Pengguna ini tidak lagi aktif");
        }
        else{
            String type = (String)choicecombobox.getSelectedItem();
            loginframe.dispose();
            new Mainmenu(user, type);
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        Dimension dim = loginframe.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = pf.getImageableHeight();
        double pWidth = pf.getImageableWidth();

        double pXStart = pf.getImageableX();
        double pYStart = pf.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;


        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        loginframe.paint(g2);
        
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
//        Graphics2D g2d = (Graphics2D)g;
//        g2d.translate(pf.getImageableX(), pf.getImageableY());
//
//        /* Now print the window and its visible contents */
//        loginframe.printAll(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
}

class ConnectionCellRenderer extends JLabel implements ListCellRenderer {

  public ConnectionCellRenderer() {
    setOpaque(true);
    setIconTextGap(12);
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    Connection connection = (Connection) value;
    setText(connection.getName());
    if (isSelected) {
      setBackground(Color.lightGray);
      setForeground(Color.black);
    } else {
      setBackground(Color.white);
      setForeground(Color.black);
    }
    return this;
  }
}