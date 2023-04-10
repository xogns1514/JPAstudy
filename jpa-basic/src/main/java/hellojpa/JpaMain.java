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
                    .setMaxResults(10) //1번부터 10개 가져와
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
            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            team.addMember(member);

//            team.getMembers().add(member); //**->위에꺼랑 로직을 같이 넣어야 -> 연관관계 편의 메소드로 해결

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            //em.flush(), em.clear() 없으면 아직 1차캐시에 있음
            //따라서 컬레션에 저장되어 있지 않음
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
            //

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
