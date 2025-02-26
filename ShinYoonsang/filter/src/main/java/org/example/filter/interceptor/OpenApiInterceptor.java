package org.example.filter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class OpenApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre Handle");
        // controller 전달, true : 전달 / false : 전달 X

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // Method Level에 OpenApi Annotation이 달려있는지
        OpenApi methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
        if (methodLevel != null) {
            log.info("Method Level");
            return true;
        }
        // Class Level에 OpenApi Annotation이 달려있는지
        OpenApi classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
        if (classLevel != null) {
            log.info("Class Level");
            return true;
        }
        // Controller로 요청을 보내지 않음
        log.info("Not OpenApi");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // ModelAndView
        log.info("post Handle");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 완료되었을 때 호출
        log.info("after Completion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
