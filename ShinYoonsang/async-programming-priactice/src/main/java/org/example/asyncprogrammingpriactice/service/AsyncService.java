package org.example.asyncprogrammingpriactice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AsyncService {

    private final EmailService emailService;

    // Bean 주입
    public void aSyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // Instance로 선언 후 메서드 호출
    public void aSyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService2 = new EmailService();
        emailService2.sendMail();
        emailService2.sendMailWithCustomThreadPool();
    }

    // 내부 메서드 호출
    public void aSyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
