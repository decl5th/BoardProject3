package org.choongang.global.exceptions;

// 알림을 보내고 히스토리 백하는 형태 히든 프레임에 대한 처리
public class AlertBackException extends AlertException {
    private String target;

    public AlertBackException(String message, int status, String target) {
        super(message, status);
        this.target = target;
    }

    public AlertBackException(String message, int status) {
        this(message, status, "self");
    }

    public String getTarget() {
        return target;
    }
}
