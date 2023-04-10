package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //애플리케이션 로딩 시점에 딱 하나만 있어야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //jpa의 모든 변경은 트랜잭션 안에서 이루어져야 한다.
        tx.begin();

        try {
            Order order = new Order();
            order.addOrderITem(new OrderItem());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();//자원을 다 쓰면 반환 해주어야 함
        }

        emf.close();//웹 내려갈때 엔티티 매니저 팩터리 닫아줘야 함.
    }
}
