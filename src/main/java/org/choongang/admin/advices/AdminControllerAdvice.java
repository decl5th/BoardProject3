package org.choongang.admin.advices;

import lombok.RequiredArgsConstructor;
import org.choongang.global.Interceptor;
import org.choongang.global.config.annotations.ControllerAdvice;
import org.choongang.global.exceptions.UnAuthorizedException;
import org.choongang.member.MemberUtil;

@RequiredArgsConstructor
@ControllerAdvice("org.choongang.admin")
public class AdminControllerAdvice implements Interceptor {

    private final MemberUtil memberUtil;

    @Override
    public boolean preHandle() {

        if (!memberUtil.isAdmin()) {
            throw new UnAuthorizedException();
        }

        return true; // true/false 값에 따라서 관리자페이지를 보이고 안보이고를 설정
    }
}
