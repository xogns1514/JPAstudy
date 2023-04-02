package hellojpa;

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
            /*Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA"); 수정 -> em.persist로 저장하지 않아도됨
//            em.remove(findMember); 삭제
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    //멤버 객체를 대상으로 쿼리
                    .setFirstResult(1)
                    .setMaxResults(10) //1번부터 10개 가져옴
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());*/
            //------------------------------------------------------//
/*
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            //영속
            System.out.println("=== BEFORE ===");
            em.persist(member);//영속화한다고 DBd에 바로 쿼리가 날아가지 않음, 이때 1차 캐시에 저장을 함
            System.out.println("=== AFTER ===");

            Member findMember = em.find(Member.class, 101L);
*/
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAA"); //persist를 호출하지 않아도 된다
//
//            em.detach(member);
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("=======================");
            // 처음 호출시
            //DB SEQ = 1 | 1
            //두번 호출, SEQ에 50개씩 써야하는데 1이라 한번 더 호출
            //DB SEQ = 51 | 2 memory에서 호출
            //DB SEQ = 51 | 3 memory에서 호출

//            em.persist(member1);
//            em.persist(member2);
//            em.persist(member3);

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            System.out.println("=======================");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();//자원을 다 쓰면 반환 해주어야 함
        }

        emf.close();//웹 내려갈때 엔티티 매니저 팩터리 닫아줘야 함.
    }
}
