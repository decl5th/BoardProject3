package org.choongang.member.validators;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Component;
import org.choongang.global.exceptions.AlertException;
import org.choongang.global.validators.RequiredValidator;
import org.choongang.global.validators.Validator;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;

@Component
@RequiredArgsConstructor
public class LoginValidators implements Validator<RequestLogin>, RequiredValidator {

    private final MemberMapper mapper; // 생성자 의존성 주입

    @Override
    public void check(RequestLogin form) {
        String email = form.getEmail();
        String password = form.getPassword();

        int status = HttpServletResponse.SC_BAD_REQUEST; // 400

        // 필수 항목 검증 - 이메일, 비번
        checkRequired(email, new AlertException("이메일을 입력하세요.", status));
        checkRequired(password, new AlertException("비밀번호를 입력하세요.", status));

        // 이메일로 회원이 조회되는지 검증(가입된 회원인지 체크)
        String message = "이메일 또는 비밀번호가 일치하지 않습니다.";
        Member member = mapper.get(email);
        checkTrue(member != null, new AlertException(message, status));

        // 비번 일치 여부 체크
        boolean isMatched = BCrypt.checkpw(password, member.getPassword()); // db에서 해시화된 비번
        checkTrue(isMatched, new AlertException(message, status));
    }
}