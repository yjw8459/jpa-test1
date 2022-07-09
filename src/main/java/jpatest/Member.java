package jpatest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Member {

    //JPA는 리플렉션을 사용하기 때문에 기본 생성자가 있어야한다.
    public Member(){}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
