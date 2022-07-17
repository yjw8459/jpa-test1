package jpatest;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    @OneToMany(mappedBy = "member")
    private List<Member> member = new ArrayList<>();
    private String name;
}
