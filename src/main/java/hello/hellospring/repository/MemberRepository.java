package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { //회원을 저장하는 저장소
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환됨.
    Optional<Member> findById(Long id); //findById = id로 회원을 찾음.
    Optional<Member> findByName(String name); //Optional = 자바 8에 들어간 기능.
    List<Member> findAll(); //findAll은 지금까지 저장된 모든 회원 리스트를 반환해줌.
}
