package org.choongang.global;

import jakarta.servlet.http.HttpServletRequest;

public interface Interceptor {
    boolean preHandle();
}
