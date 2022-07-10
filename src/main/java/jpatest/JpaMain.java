package jpatest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml의 persistence-unit name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        //EntityManager를 만들면 DB연결이 됨.
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction(); // Transaction이 있어야 Insert쿼리가 날아감
        tx.begin();
        try {
            Member findMember = entityManager.find(Member.class, 1L);

            // JPQL은 RDB 테이블을 대상으로 하는게 아닌, 추상화된 객체를 대상으로 조회한다.
            List findMembers = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(4)
                    .setMaxResults(5)
                    .getResultList();// Paging, DB에 맞게 방언 적용, Oracle : Rownum, MySQL : ...
            //entityManager.persist(member);
            System.out.println(findMember.getId());
            System.out.println(findMember.getName());
            //영속화하면 1차 캐시에 저장되고, 쓰기지연 SQL 저장소에 저장된다.
            //commit 시점에 한꺼번에 저장된다.(flush)
            entityManager.persist(new Member(150L, "A"));
            entityManager.persist(new Member(151L, "B"));
            System.out.println("==============");

            findMember.setName("ttt");  // 영속성 컨텍스트에 등록된 엔티티는 persist 없이 수정가능.
            //준영속상태
            entityManager.detach(findMember);   //영속성 컨텍스트에서 제외. UPDATE 쿼리가 날아가지 않는다.
            entityManager.clear(); //영속성 컨텍스트 초기화, 영속 관리대상이 비워짐.
            entityManager.close(); //영속성 컨텍스트 종료.

            entityManager.persist(new Member(200L, "member200"));

            tx.commit();
        }
        catch (Exception e){
            tx.rollback();
        }
        finally {
            entityManager.close();
        }
        emf.close();
    }
}
