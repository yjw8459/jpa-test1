package jpatest;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;
    private String name;

    //다대일 매핑처럼 외래키가 있는 곳이 연관관계의 주인.
    @OneToOne(mappedBy = "locker")
    private Member member;
}
