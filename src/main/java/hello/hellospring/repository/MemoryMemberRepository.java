package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 동시성문제가 있을 수 있어서 공유되는 변수일경우 ConcurrentHashMap을 사용해야됨
    private static long sequence = 0L; //sequence는 0 1 2 키값을 생성해줌


    //save를 할때 메모리니까 어디에다가 저장을 해줘야됨 = Map을 이용해서 저장.
    //Map에 key는 회원의 id니까 Long으로 하고 값은 Member
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //member를 세이브할때 시퀀스값을 하나 올려줌.
        store.put(member.getId(), member); //스토어에 getId로 멤버 넣으면 맵에 저장이됨.
        return member; //이렇게 스토어에 넣기전에 member에 id값을 세팅해주고 이름은 save하기전에 넘어온 상태임
    }

    @Override
    public Optional<Member> findById(Long id) { //findById는 스토어에서 꺼내면됨.
        return Optional.ofNullable(store.get(id)); //이결과가 없으면 null인데 과거엔 그렇게 했지만 요즘은
        //null을 optional로 감싸서 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return  store.values().stream() //자바8 람다 store를 루프로 돌린다는 의미.
                .filter(member -> member.getName().equals(name))
                //람다가 사용되는데 member.getName()이 여기 파라미터로 넘어온 name이랑 같은지 확인
                //같은 경우에만 필터링이된다. 찾으면 반환.
                .findAny(); //.findAny()는 하나라도 찾는것.
        //=>그 결과가 Optional로 반환을 함. 맵에서 돌면서 하나 찾아지면 걔를 반환을 하는데 끝까지 없으면
    }   //Optional에 null이 포함되서 반환한다.

    @Override
    public List<Member> findAll() { //자바에서 실무할땐 루프돌리기 편해서 list를 많이 씀.
        return new ArrayList<>(store.values());
    }
}
