package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* 직접적으로 빈을 설정해주는 방식.
* 이렇게하면 나중에 로직변경할 경우 수정없이 구현체만 바꿀 수 있다.
*
* */

@Configuration
public class SpringConfig {

    /*    private final DataSource dataSource;
            public SpringConfig(DataSource dataSource){
                this.dataSource = dataSource;
            }
     */
/*    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired  // 생성자가 하나인 경우 생략가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService (){

        return new MemberService(memberRepository);
    }

    /*@Bean
    public MemberRepository memberRepository (){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em); // 실행해야하는데 엔티티매니저가 필요하기때문에 만들어놓고 작성

    }*/
}
