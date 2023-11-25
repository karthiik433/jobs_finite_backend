package com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository;


import com.jobsPortal.jobs_finite.DTO.subscriber.PrivateJobSubscriberDTO;
import com.jobsPortal.jobs_finite.Entity.PrivateJobSubscriber;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrivateJobSubscriberServiceImpl {

    @Autowired
    private PrivateJobSubscriberRepo privateJobSubscriberRepo;

    @Autowired
    private EmailServiceImpl emailService;


    public String saveSubscriberData(PrivateJobSubscriberDTO privateJobSubscriberDTO) throws Exception{

        PrivateJobSubscriber privateJobSubscriber = new PrivateJobSubscriber();
        privateJobSubscriber.setEmail(privateJobSubscriberDTO.getEmailId());

        PrivateJobSubscriber privateJobSubscriber1 = privateJobSubscriberRepo.findByEmail(privateJobSubscriberDTO.getEmailId());
        log.info("searching for subscriber in database "+privateJobSubscriber1);
        if(privateJobSubscriber1 == null){
            log.info("Saving the subscriber ");
            privateJobSubscriberRepo.save(privateJobSubscriber);
            String successMessage = "Saved the subscriber successfully";
            emailService.sendSimpleMessage(privateJobSubscriberDTO.getEmailId(),"Subscription successful","Thanks for subscribing to Private posts and to our channel");
            return successMessage;
        }else {
            throw new GlobalException("You have already subscribed to this category");
        }
    }
}
