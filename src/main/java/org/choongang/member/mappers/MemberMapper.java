package org.choongang.member.mappers;

import org.choongang.member.entities.Member;

public interface MemberMapper {
    // id값의 기준이 메서드명 
    Member get(String email);
    int exists(String email); // 회원이 존재하는지에 대한 쿼리
    int register(Member member);
}
