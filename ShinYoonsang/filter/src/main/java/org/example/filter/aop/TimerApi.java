package org.example.filter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.filter.model.UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class TimerApi {

    @Pointcut(value = "within(org.example.filter.controller.UserApiController)")
    public void timerPointCut() {
    }

    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before");
    }

    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("After");
    }

    @AfterReturning(value = "timerPointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        log.info("AfterReturning");
    }

    @AfterThrowing(value = "timerPointCut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        log.info("AfterThrowing");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("메소드 실행 이전");

        // 메서드 Arguments
//        Object[] args = joinPoint.getArgs();
//        Arrays.stream(args).forEach(
//                it -> {
//                    if(it instanceof UserRequest) {
//                        UserRequest tempUser = (UserRequest) it;
//                        String phoneNumber = tempUser.getPhoneNumber().replace("-", "");
//                        tempUser.setPhoneNumber(phoneNumber);
//                    }
//                }
//        );

        // 암/복호화, 로깅
//        List<UserRequest> list = Arrays.asList(
//                new UserRequest()
//        );
//        joinPoint.proceed(list.toArray());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed(); // 메서드 실행
        stopWatch.stop();

        log.info("총 소요된 시간 MS = {}", stopWatch.getTotalTimeMillis());

        log.info("메소드 실행 이후");
    }
}
