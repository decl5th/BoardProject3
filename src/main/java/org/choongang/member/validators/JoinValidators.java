package org.choongang.member.validators;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Component;
import org.choongang.global.exceptions.AlertException;
import org.choongang.global.validators.EmailValidator;
import org.choongang.global.validators.RequiredValidator;
import org.choongang.global.validators.Validator;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.mappers.MemberMapper;

@Component
@RequiredArgsConstructor
public class JoinValidators implements Validator<RequestJoin>, RequiredValidator, EmailValidator {

    //@NonNull // 상수를 쓰지 않는 경우
    private final MemberMapper mapper;

    @Override
    public void check(RequestJoin form) {
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String userName = form.getUserName();
        boolean termsAgree = form.isTermsAgree();
        int status = HttpServletResponse.SC_BAD_REQUEST;

        /* 필수 항목 유효성 검사 B */
        checkRequired(email, new AlertException("이메일을 입력하세요", status));
        checkRequired(password, new AlertException("비밀번호을 입력하세요", status));
        checkRequired(confirmPassword, new AlertException("비밀번호를 확인하세요", status));
        checkRequired(userName, new AlertException("회원명을 입력하세요", status));
        checkTrue(termsAgree, new AlertException("약관에 동의해 주세요", status));
        /* 필수 항목 유효성 검사 D */

        // 이메일 형식 체크
        checkTrue(checkEmail(email), new AlertException("이메일 형식이 아닙니다.", status));

        // 이메일 중복 여부 체크 - 이미 가입된 회원인지에 대한 여부
        checkTrue(mapper.exists(email) == 0L, new AlertException("이미 가입된 이메일입니다", status));

        // 비번 자리수는 8자리 이상
        checkTrue(password.length() >= 8, new AlertException("비밀번호는 8자리 이상 입력하세요", status));

        // 비번과 비번 확인의 일치 여부
        checkTrue(password.equals(confirmPassword), new AlertException("비밀번호가 일치하지 않습니다.", status));
    }
}
