package org.choongang.member.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Service;
import org.choongang.global.exceptions.AlertException;
import org.choongang.member.constants.UserType;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.validators.JoinValidators;
import org.mindrot.jbcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinValidators validators;
    private final MemberMapper mapper;

    public void process(RequestJoin form) {
        validators.check(form);

        // 비번 해시화
        String hash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));
        form.setPassword(hash);

        // DB에 영구 저장 처리
        Member member = Member.builder()
                .email(form.getEmail())
                .password(hash)
                .userName(form.getUserName())
                .userType(UserType.USER)
                .build();

        int result = mapper.register(member);
        if (result < 1) {
            throw new AlertException("회원가입에 실패하였습니다", HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
