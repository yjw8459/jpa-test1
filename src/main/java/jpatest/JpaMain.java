package jpatest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml의 persistence-unit name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        //EntityManager를 만들면 DB연결이 됨.
        EntityManager entityManager = emf.createEntityManager();

        entityManager.close();
        emf.close();
    }
}
