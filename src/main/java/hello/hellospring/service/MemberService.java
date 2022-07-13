package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/* 회원가입에만 필요하기때문에 그 메소드에다가만 트랜잭션 걸어두 되고
   클래스 전체에 걸어두어도 된다.
*/
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    *   // 같은 이름이 있는 중복 회원X
        // 옵셔널안에 member가 있는거라고 생각하면 됨.
        // 감싸고 있어서 null일 경우를 보완해줌 (옵셔널이랑 변수 생성 단축키 참고: c + a + v)*****
        // result에 만약 값이 있으면 exception을 던진다.
        // result.orElseGet();은  값이 있으면 꺼내고 아니면 어떤 메소드를 실행해 or 기본값을 꺼내 이런 식으로 쓴다.
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(m -> {
        //   throw new IllegalStateException("이미 존재하는 회원입니다.");
        // });
        // 위와 같이 코드를 짜면 안예쁘기때문에 최대한 명료하며 보기편하게 아래와 같이 사용
        // c + a + s + t (Refactor this 단축키: method 생성)
        // 벨리데이션 체크
    * */
    public Long join(Member member){
            validateDuplicateMember(member);

            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다.");
                });
    }

    /*
    * 전체 회원 조회
    * 서비스클래스는 비즈니스 의존적으로 사용.
    * */
    public List<Member> findMembers() {

            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }

}
