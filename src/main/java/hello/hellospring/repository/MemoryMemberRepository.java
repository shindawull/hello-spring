package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
     //구현하기 , 저장
     // 실무에서는 동시성문제가있을수있어서 공유되는 변수일 때는 컨커렁맵?
     private static Map<Long, Member> store = new HashMap<>();
     private static long sequence = 0L; //시퀀스 0 1 2 ...

     @Override
     public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
     }

     @Override
     public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성때문에 Optional을 사용함.
     }

     @Override
     public Optional<Member> findByName(String name) {
         return store.values().stream()
                 .filter(member -> member.getName().equals(name))
                 .findAny();
     }

     @Override
     public List<Member> findAll() {
         // 자바 실무에서는 List를 많이 사용함
         return new ArrayList<>(store.values());
     }

    // run 후 남아있는 데이터를 싹 지워준다.
     public void cleanStore() {
         store.clear();
     }
}
