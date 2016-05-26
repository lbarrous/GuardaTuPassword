/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guardatupasss;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author JOSE RAMON
 */
public class ManejaClave {

    Session session;
    Transaction tx;

    public void iniciaOperacion() throws HibernateException {
        System.out.println("Comenzando con Hibernate");
        session = HibernateUtil.getSessionFactory().openSession(); //iniciamos una session hibernate
        tx = session.beginTransaction(); // comienza la transaccion
    }

    public void finalizaOperacion() throws HibernateException {
        System.out.println("Finalizando con Hibernate");
        tx.commit();
        session.close();
    }

    public void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        System.out.println("Ocurrió un error en la capa de acceso a datos " + he.getMessage());
        System.exit(0);
    }

    public void insertarclave(Clave c) {
        try {
            iniciaOperacion();
            session.save(c);
            System.out.println("clave insertada correctamente");
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }
    

    }
    public void ActualizarUsuario(Usuario u){
        try {
            iniciaOperacion();
            session.update(u);
            System.out.println("usuario actualizado correctamente");
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }
    }

    public boolean estaUsuario(String nombre, String clave) {
          boolean encontrado=false;
        try {
            iniciaOperacion();
            String st=" from usuario u where u.nombre= :n and u.clave= :c";
            Query q =session.createQuery(st);
            q.setParameter("n", nombre);
            q.setParameter("c", clave);
            
            List<Usuario> litaUsers=q.list();
            
            encontrado=litaUsers.isEmpty();
            
            
            System.out.println("usuario actualiza correctamente");
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }
        return encontrado;
    }
    
    public Usuario obtenerUsuario(String nombre, String clave) {
          Usuario u=null;
        try {
            iniciaOperacion();
            String st=" from usuario u where u.nombre= :n and u.clave= :c";
            Query q =session.createQuery(st);
            q.setParameter("n", nombre);
            q.setParameter("c", clave);
            
            List<Usuario> litaUsers=q.list();
            
            if(!litaUsers.isEmpty()){
                u=litaUsers.get(0);
            }
            
           
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }
        return u;
    }
    public void verClaves(int id_user) {
         
        try {
            iniciaOperacion();
            String st=" from clave c where  c.usuario= :id_user";
            Query q =session.createQuery(st);
            q.setParameter("id", id_user);
            
            List<Clave> litaClaves=q.list();
            
            for (Clave clave : litaClaves) {
                System.out.println("Id clave: "+clave.getId());
                System.out.println("Id usuario: "+clave.getUsuario());
                System.out.println("Nombre: "+clave.getNombreUsuario());
                System.out.println("Contraseña: "+clave.getClave());
                System.out.println("Descripción: "+clave.getDescripcion());
                
                System.out.println("\n____________________\n");
            }
            
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }
        
    }

}
