package org.choongang.member.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.PostMapping;
import org.choongang.global.config.annotations.RequestMapping;
import org.choongang.member.services.JoinService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinService joinService; // 생성자 DI

    // 회원 가입 양식
    @GetMapping("/join")
    public String join( ) {
        return "member/join";
    }

    // 회원 가입 처리
    @PostMapping("/join")
    public String joinPs(RequestJoin form, HttpServletRequest request) {

        joinService.process(form);

        String url = request.getContextPath() + "/member/login";
        String script = String.format("parent.location.replace('%s');", url); // 부모쪽 창 이동하기 위함

        request.setAttribute("script", script);

        return "commons/execute_script"; // 스크립트 형태로 로그인 페이지 이동
    }

    //로그인 양식
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String loginPs(RequestLogin form) {

        return null;
    }
}
