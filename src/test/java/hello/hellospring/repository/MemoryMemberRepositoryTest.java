package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 test를 진행 한 후 남아있는 데이터를 지워준다. MemoryMemberRepository에 cleanStore()를 만들어놓았음.
    @AfterEach
    public void afterEach(){

        repository.cleanStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // 반환타입이 optinal에서 값을 꺼낼때는 get으로 꺼낼수 있음
        // 내가 계속 System.out.println()로 찍어서 글자로 보기엔 불편하지 않을까?
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, null );
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 =  new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =  new Member();
        member2.setName("spring2");
        repository.save(member2);

        // get()을 하면 꺼낼수 있음 .?
        Member result = repository.findByName("spring1").get();

        // Member result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(member1);
        // member1으로 셋팅되어있는데  spring2로 설정해두면..
        // spring2로 Test를 하면 다른객체다 라고 error남
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        // findAll만 Test진행했을 때는 문제가 없었는데..클래스 전체 Test진행하니까
        // 문제가 있다고 에러남. 뭐가 문제일까?
        // test할때 순서에 상관없이 진행되는데 findAll이 돌아가고
        // 그때의 데이터가 남아있는 상태에서 findByName이 진행되서 에러났던거였음.
        // 그래서 데이터를 지워줘야한다. 그래서 추가한다. test가 끝날때마다 데이터 지워주는 걸.!!!!!!
        // 위에 afterEach()만들어줌.. 순서 체크
    }
}
