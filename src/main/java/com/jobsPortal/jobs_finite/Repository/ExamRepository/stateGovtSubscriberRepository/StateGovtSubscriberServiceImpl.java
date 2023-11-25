package com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository;

import com.jobsPortal.jobs_finite.DTO.subscriber.StateGovtSubscriberDTO;
import com.jobsPortal.jobs_finite.Entity.StateGovtSubscriber;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StateGovtSubscriberServiceImpl {


    @Autowired
    private StateGovtSubscriberRepo stateGovtSubscriberRepo;

    @Autowired
    private EmailServiceImpl emailService;


    public String saveSubscriberData(StateGovtSubscriberDTO stateGovtSubscriberDTO) throws Exception{

        StateGovtSubscriber stateGovtSubscriber = new StateGovtSubscriber();
        stateGovtSubscriber.setState(stateGovtSubscriberDTO.getState());
        stateGovtSubscriber.setEmail(stateGovtSubscriberDTO.getEmailId());

        StateGovtSubscriber stateGovtSubscriber1 = stateGovtSubscriberRepo.findByEmail(stateGovtSubscriberDTO.getEmailId());

        log.info("searching for subscriber "+stateGovtSubscriber1);
        if(stateGovtSubscriber1 == null){
            log.info("Saving State Govt Exam subscriber");
            stateGovtSubscriberRepo.save(stateGovtSubscriber);
            String successMessage  = "Saved subscriber successfully";
            emailService.sendSimpleMessage(stateGovtSubscriberDTO.getEmailId(),"Subscription successful","Thanks for subscribing to state government posts and to our channel");
            return successMessage;
        }
        else{
            log.info("subscriber already exists ");
            throw new GlobalException("You have already subscribed to the category");
        }
    }
}
