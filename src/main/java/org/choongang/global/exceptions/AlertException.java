package org.choongang.global.exceptions;

// 예외에 대한 부분을 알림창 형태로 메세지 띄워서 보여주는 클래스
public class AlertException extends CommonException{
    public AlertException(String message, int status) {
        super(message, status);
    }
}
