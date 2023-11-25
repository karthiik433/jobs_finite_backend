package com.jobsPortal.jobs_finite.notifySubscribers;


import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncNotify {

    @Autowired
    private EmailServiceImpl emailService;

    @Async
    public void sendNotificationAsynchronously(String to,String subject ,String text){

        emailService.sendSimpleMessage(to,subject,text);
        try {
            Thread.sleep(2000);
        }catch (Exception ex){
            log.info("thread exception "+ex.getMessage());
        }

    }
}
