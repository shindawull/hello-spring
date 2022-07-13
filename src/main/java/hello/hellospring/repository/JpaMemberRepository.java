package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // jpa는 EntityManager를 통해서 모든게 동작한다.
    // 라이브러리를 통해서
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist의 뜻은 영속하다,영구저장하다의 뜻,
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회용
        return Optional.ofNullable(member); // 리턴을 옵셔널로 하기때문에..
    }

    // 약간 특별한 JPQL언어 사용, sql이랑 비슷함
    // 차이?
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 인라인 단축키 커멘드 옵션 n
        // 대부분 테이블 대상으로 쿼리를 날리지만
        // JPQL은 객체를 대상으로 쿼리를 날리는 것임
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 조회, 저장, 업뎃,딜트 는 쿼리를 작성 할 필요가 없음. 자동.
    // PK 기반이 아닌 나머지는 sql문을 작성해줘야한다.


}
