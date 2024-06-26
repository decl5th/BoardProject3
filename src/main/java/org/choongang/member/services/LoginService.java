package org.choongang.member.services;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Service;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.validators.LoginValidators;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginValidators loginValidators;
    private final MemberMapper mapper;
    private final HttpSession session;

    public void process(RequestLogin form) {
        loginValidators.check(form);

        // 회원 정보 조회
        Member member = mapper.get(form.getEmail());

        // 세션에 회원 정보 유지
        session.setAttribute("member", member);
    }

}
