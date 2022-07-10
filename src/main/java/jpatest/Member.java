package jpatest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// JPA 관리 객체 선언, JPA에서 사용할 엔티티 이름을 지정한다. name을 따로 선언하지 않을 경우 클래스 이름을 따름
//같은 클래스 이름이 없으면 가급적 기본 값을 사용한다.
@Entity

// 엔티티와 매핑할 테이블 지정, name을 따로 선언하지 않을 경우 클래스 이름을 따름
@Table
public class Member {

    //JPA는 리플렉션을 사용하기 때문에 기본 생성자가 있어야한다.
    public Member(){}

    public Member(Long id, String name) {
        this.id = id;
        this.username = name;
    }
    @Id
    private Long id;
    @Column(name = "name",length = 10, nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username; //@Column을 적지 않으면 변수명으로 매핑
    private Integer age;
    @Enumerated(EnumType.STRING)    //Enum 타입, Ordinal 사용 X
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)   //날짜, 시간, 날짜시간 옵션 있음
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //LocaDate, LocalDateTime을 사용하면 @Temporal 사용 필요X
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob    //CLOB, BLOB 큰 컨텐츠
    private String description;

    @Transient//매핑제외
    private int temp;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.username = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return username;
    }
}
