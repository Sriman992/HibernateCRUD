package com.inventory.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductMain {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // INSERT
        Product p1 = new Product("Laptop","Electronics",55000,10);
        Product p2 = new Product("Mouse","Electronics",500,50);
        Product p3 = new Product("Keyboard","Electronics",1500,25);
        Product p4 = new Product("Monitor","Electronics",12000,15);
        Product p5 = new Product("Phone","Electronics",30000,8);
        Product p6 = new Product("Tablet","Electronics",25000,6);

        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.save(p4);
        session.save(p5);
        session.save(p6);
        tx.commit();

        // RETRIEVE
        session = HibernateUtil.getSessionFactory().openSession();
        Product p = session.get(Product.class,1);
        System.out.println("Product Name: "+p.getName());

        // UPDATE
        Transaction tx2 = session.beginTransaction();
        p.setPrice(60000);
        session.update(p);
        tx2.commit();

        // DELETE
        Transaction tx3 = session.beginTransaction();
        session.delete(p);
        tx3.commit();

        session.close();
    }
}