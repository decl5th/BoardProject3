package org.choongang.member.controllers;

import lombok.Data;

@Data
// data 클래스; 값을 전달하는 형태의 클래스
public class RequestLogin {
    private String email;
    private String password;
    private boolean saveEmail;
}
