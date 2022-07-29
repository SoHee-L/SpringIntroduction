package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest { //다른애들 갖다쓸게 아니니 굳이 public으로 할 필요 없음.

    MemberRepository repository= new MemoryMemberRepository();

    @Test
    public void save(){ //아까 만든 세이브기능을 이 메서드가 실행할 수 있음.
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장소에 member를 저장
        //저장하고나면 id가 세팅됨.

        repository.findById(member.getId()); //findById로 내가 넣은게 제대로 들어갔는지 확인
    }
}
