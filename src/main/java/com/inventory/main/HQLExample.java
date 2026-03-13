package com.inventory.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class HQLExample {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // -----------------------------
        // 1. Sort products by price ASC
        // -----------------------------
        System.out.println("Price Ascending:");
        Query<Product> q1 = session.createQuery(
                "FROM Product ORDER BY price ASC", Product.class);

        q1.list().forEach(p -> 
            System.out.println(p.getName()+" "+p.getPrice())
        );


        // -----------------------------
        // 2. Sort products by price DESC
        // -----------------------------
        System.out.println("\nPrice Descending:");
        Query<Product> q2 = session.createQuery(
                "FROM Product ORDER BY price DESC", Product.class);

        q2.list().forEach(p -> 
            System.out.println(p.getName()+" "+p.getPrice())
        );


        // -----------------------------
        // 3. Sort by quantity highest first
        // -----------------------------
        System.out.println("\nQuantity Highest First:");
        Query<Product> q3 = session.createQuery(
                "FROM Product ORDER BY quantity DESC", Product.class);

        q3.list().forEach(p -> 
            System.out.println(p.getName()+" "+p.getQuantity())
        );


        // -----------------------------
        // 4. Pagination (First 3 products)
        // -----------------------------
        System.out.println("\nFirst 3 Products:");

        Query<Product> q4 = session.createQuery(
                "FROM Product", Product.class);

        q4.setFirstResult(0);
        q4.setMaxResults(3);

        q4.list().forEach(p ->
            System.out.println(p.getName())
        );


        // -----------------------------
        // 5. Pagination (Next 3 products)
        // -----------------------------
        System.out.println("\nNext 3 Products:");

        q4.setFirstResult(3);
        q4.setMaxResults(3);

        q4.list().forEach(p ->
            System.out.println(p.getName())
        );


        // -----------------------------
        // 6. Count total products
        // -----------------------------
        Query<Long> q5 = session.createQuery(
                "SELECT COUNT(*) FROM Product", Long.class);

        System.out.println("\nTotal Products: "+ q5.uniqueResult());


        // -----------------------------
        // 7. Count products where quantity > 0
        // -----------------------------
        Query<Long> q6 = session.createQuery(
                "SELECT COUNT(*) FROM Product WHERE quantity > 0",
                Long.class);

        System.out.println("Products with quantity > 0: "+ q6.uniqueResult());


        // -----------------------------
        // 8. Group by description
        // -----------------------------
        System.out.println("\nGroup by Description:");

        Query<Object[]> q7 = session.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description");

        for(Object[] row : q7.list()) {
            System.out.println(row[0]+" : "+row[1]);
        }


        // -----------------------------
        // 9. Minimum and Maximum price
        // -----------------------------
        Query<Object[]> q8 = session.createQuery(
                "SELECT MIN(price), MAX(price) FROM Product");

        Object[] result = q8.uniqueResult();

        System.out.println("\nMin Price: "+result[0]);
        System.out.println("Max Price: "+result[1]);


        // -----------------------------
        // 10. WHERE clause (price range)
        // -----------------------------
        System.out.println("\nProducts between 1000 and 20000:");

        Query<Product> q9 = session.createQuery(
                "FROM Product WHERE price BETWEEN 1000 AND 20000",
                Product.class);

        q9.list().forEach(p ->
            System.out.println(p.getName()+" "+p.getPrice())
        );


        // -----------------------------
        // 11. LIKE queries
        // -----------------------------

        // Names starting with M
        System.out.println("\nNames starting with M:");

        Query<Product> q10 = session.createQuery(
                "FROM Product WHERE name LIKE 'M%'", Product.class);

        q10.list().forEach(p -> System.out.println(p.getName()));


        // Names ending with r
        System.out.println("\nNames ending with r:");

        Query<Product> q11 = session.createQuery(
                "FROM Product WHERE name LIKE '%r'", Product.class);

        q11.list().forEach(p -> System.out.println(p.getName()));


        // Names containing substring
        System.out.println("\nNames containing 'top':");

        Query<Product> q12 = session.createQuery(
                "FROM Product WHERE name LIKE '%top%'", Product.class);

        q12.list().forEach(p -> System.out.println(p.getName()));


        // Exact character length
        System.out.println("\nNames with length 5:");

        Query<Product> q13 = session.createQuery(
                "FROM Product WHERE LENGTH(name)=5", Product.class);

        q13.list().forEach(p -> System.out.println(p.getName()));


        session.close();
    }
}