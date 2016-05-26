/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guardatupasss;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alvaro
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Usuario user = new Usuario();
        user.setId(1);
        user.setNombre("User");
        user.setClave("123456");
        session.save(user);
        tx.commit();
        
        //tx = session.beginTransaction();
        Clave clave = new Clave();
        clave.setUsuario(1);
        clave.setDescripcion(null);
        clave.setClave("123456");
        clave.setNombreUsuario("prueba");
        session.save(clave);
        tx.commit();
    }
    
}
