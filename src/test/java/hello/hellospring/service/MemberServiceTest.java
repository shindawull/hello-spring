package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // @BeforeEach가 있는 메서드는 테스트메서드 실행 이전에 수행된다.
    // 계속해서 객체생성해서 사용하니 그렇게 하지않게 쓰는 방법
    // test를 실행할때마다 각각 생성해준다. test는 독립적으로 실행되야하기 때문에.
    // MemberService()에서 constructor 생성 후 @BeforeEach로 생성한다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //@AfterEach은 테스터메소드가 실행된 이후에 수행된다.
    @AfterEach
    public void afterEach(){
        //돌때마다 메모리가 클린됨.
        memberRepository.cleanStore();
    }

    @Test
    void join() {
        //회원가입 : test는
        //given : 뭐가 주어졌는데
        Member member = new Member();
        member.setName("Hello");

        //when : 이걸실행했을때
        Long saveId = memberService.join(member);

        //then(검증) : 결과가 이게 나와야한다
        //assertThat은 처음에 없을거다 왜냐면 import static org.junit.jupiter.api.Assertions.*;가 jUnit꺼기 때문.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //assertThrows(NullPointerException.class, () ->  memberService.join(member2)); test 실패뜸 !!
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->  memberService.join(member2));
        // message는 어떻게 반환해요?  assertThrows 이친구는 반환이 됩니다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

/* 1번째로 했던 익셉션, 좋은 문법이 있어 주석철.
        try{
            memberService.join(member2);
            fail(); // memberService.join(member2); 실행되고나서 에러가 나야하는데 그냥 넘어갈수 있기때문에 fail을 해준다.
        }catch (IllegalStateException e){
            // join()에서의 excetion문구와 같은지 비교한다 제대로 던졌는지 체크.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.090909");

        }
*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}