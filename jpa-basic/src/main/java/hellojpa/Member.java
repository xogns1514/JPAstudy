package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")//연관관계 주인이다. 외래키를 가지고 있결
    private Team team;


    public Member() {

    }

    public Team getTeam() {
        return team;
    }

//    public void changeTeam(Team team) {
//        //Tip)단순한 getter,setter 관례에 의한것이 아니라 로직이 들어간 것 이름 바꾼다
//        this.team = team;
//        team.getMembers().add(this);
//    }
    public void setTeam(Team team) {
        this.team = team;
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
