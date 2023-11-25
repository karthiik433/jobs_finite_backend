package com.jobsPortal.jobs_finite.Repository.ExamRepository.FeedBackRepository;


import com.jobsPortal.jobs_finite.DTO.FeedBackDTO;
import com.jobsPortal.jobs_finite.Entity.Feedback;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeedBackService {

    @Autowired
    private FeedBackRepo feedBackRepo;

    @Autowired
    private EmailServiceImpl emailService;


    public String saveFeedBack(FeedBackDTO feedBackDTO){

        String email = "kommurukalyan@gmail.com";

        Feedback feedback =  new Feedback();
        feedback.setFeedBack(feedBackDTO.getFeedback().trim());

        feedBackRepo.save(feedback);
        log.info("Feed back saved to the database");

        emailService.sendSimpleMessage(email,"Feedback from the User",feedBackDTO.getFeedback());
        log.info("Feed back sent through mail");
        return "Feed back saved to the database";
    }
}
