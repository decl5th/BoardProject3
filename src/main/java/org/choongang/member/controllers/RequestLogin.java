package org.choongang.member.controllers;

import lombok.Data;

@Data
// data 클래스; 값을 전달하는 형태의 클래스
public class RequestLogin {
    private String email;
    private String password;
    private boolean saveEmail;
    // redirectUrl 값이 요청 데이터에 있으면 → 로그인 이후에 그 페이지로 이동 없으면? 메인페이지 기능 구현을 위한 추가
    private String redirectUrl;
}
