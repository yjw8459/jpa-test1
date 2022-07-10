package jpatest;

import javax.persistence.*;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
//@TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class User {

    //@Id : 직접 할당
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) 자동 생성, GenerationType.AUTO DB 방언에 따른 자동생성

    //데이터베이스에 기본키 생성 위임 autoIncrement
    //auto_increment는 데이터베이스에 INSERT SQL을 실행한 이후에 ID 값을 알 수 있다.
    //IDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행하고 DB에서 식별자를 조회
    //IDENTITY는 persist 시점에 INSERT 쿼리가 날아간다. COMMIT 시점 X(IDENTITY는 DB에 저장된 후에 값을 알 수 있기 때문이다.)
    //Long형 + 대체키(SEQUENCE 등) 같이 사용하는 것을 권장
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // DB의 SEQUENCE 오브젝트를 통해서 값을 가져온 후 세팅한다. 자료형은 Integer, Long을 사용해야 한다.
    // SEQUENCE Generator를 사용할 경우
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    //SEQUENCE 전략은 persist 시점에 DB에 SEQUNECE값을 조회해서 값을 세팅한 후에 영속성 컨텍스트에 저장한다.(버퍼링 가능)
    //allocationSize : 미리 시퀀스를 가져옴, SEQUENCE를 여러번 호출할 경우 네트워크를 계속 타기 때문에 미리 조회하여 메모리에서 사용한다.
    //여러 WAS가 있어도 동시성 문제 없이 사용할 수 있다.

    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    //allocationSize : 미리 시퀀스를 가져옴, SEQUENCE를 여러번 호출할 경우 네트워크를 계속 타기 때문에 미리 조회하여 메모리에서 사용한다.
    //여러 WAS가 있어도 동시성 문제 없이 사용할 수 있다.
    private Long id;

    @Column(nullable = false)
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
