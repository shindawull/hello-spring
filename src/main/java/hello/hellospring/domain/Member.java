package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa가 관리하는 엔티티라고 보면된다.
public class Member {

    // IDENTITY는 자동으로 id를 생성해주는 것을 말함
    // 오라클은 시퀀스.. 내가 직접 넣어줄수 있고. 근데 디비가 직접 생성해주는 것을 아이덴티티
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //임의의 값 고객X 시스템이 저장

    //@Column(name="username") 디비컬럼명이 username이면 이렇게 설정하면 매핑됨.
    private String name;

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
