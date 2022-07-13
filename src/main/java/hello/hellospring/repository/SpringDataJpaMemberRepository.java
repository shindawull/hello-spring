package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {

    // 구현해야죠???
    // 구현할게 없습니다~!
    // SpringDataJpaMemberRepository 인터페이스만 있잖아요
    // 이렇게하면 스프링데이터jpa가 구현체가 자동으로 만들어줘서 스프링데이터제피에이가 구현해서 자동등록해줌.
    // 그걸 그냥 가져다 사용하면 된다. ---> SpringConfig로 가서 추가..

    @Override
    Optional<Member> findByName(String name);
}
