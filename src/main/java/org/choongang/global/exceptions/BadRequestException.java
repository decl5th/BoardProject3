package org.choongang.global.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class BadRequestException  extends CommonException {

    public BadRequestException() {
        this("잘못된 요청입니다."); // propertis에서 키값으로 바꿔서 넣어줄 수 있도록
    }

    public BadRequestException(String message) {
        super(message, HttpServletResponse.SC_BAD_REQUEST); // 기본 생성자, 응답 코드 400으로 제한
    }
}