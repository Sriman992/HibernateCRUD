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
        Product p1 = new Product("Laptop","Dell i5",55000,10);
        Product p2 = new Product("Mouse","Wireless Mouse",500,50);

        session.save(p1);
        session.save(p2);

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