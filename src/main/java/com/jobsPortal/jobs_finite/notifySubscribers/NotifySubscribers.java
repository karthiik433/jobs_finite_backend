package com.jobsPortal.jobs_finite.notifySubscribers;


import com.jobsPortal.jobs_finite.Entity.*;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository.CentralGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository.PrivateJobSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository.StateGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class NotifySubscribers {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PrivateJobSubscriberRepo privateJobSubscriberRepo;

    @Autowired
    private CentralGovtSubscriberRepo centralGovtSubscriberRepo;

    @Autowired
    private StateGovtSubscriberRepo stateGovtSubscriberRepo;

    @Autowired
    private AsyncNotify asyncNotify;


    @Async
    public void notifyPrivateJobSubscriber(List<PrivateExamPost> privateExamPostList){

        try{
            privateExamPostList.stream().forEach(post->{

                List<PrivateJobSubscriber> privateJobSubscriberList = privateJobSubscriberRepo.findAll();
                privateJobSubscriberList.stream().forEach(sub->{
//                    try {
                        asyncNotify.sendNotificationAsynchronously(sub.getEmail(), post.getExamName() + "-Private", "A new " + post.getExamName() + " notification is out , Please check into our channel to know more information");
//                    }catch(Exception ex){
//                        log.info("Unexpected system exception "+ex);
//                    }
//                    emailService.sendSimpleMessage(sub.getEmail(),post.getExamName(),"A new "+post.getExamName()+" notification is out , Please check into our channel to know more information");
                });
            });
            log.info("Notified all the private Job subscribers successfully");
        }catch (Exception ex){
            log.info("Unexpected error occurred while sending notification mails to subscribers");
        }
    }

    @Async
    public void notifyCentralGovtExamSubscribers(List<CentralGovtExamPost> centralGovtExamPostList){

        try {
            log.info("sending notifications to central govt subs");
            List<CentralGovtSubscriber> centralGovtSubscriberList = centralGovtSubscriberRepo.findAll();
//            log.info("subscribers present in central govt subscriber list "+centralGovtSubscriberList);

            centralGovtSubscriberList.stream().forEach(item -> {

                if(item.getCentralJobCategoriesList().get(0).getCategoryName() == "All"){

                    centralGovtExamPostList.stream().forEach(post -> {

//                        try {
                            asyncNotify.sendNotificationAsynchronously(item.getEmail(), post.getExamName() + "-Central", "A new " + post.getExamName() + " notification is out , Please check into our channel to know more information");
//                        }catch(Exception ex){
//                            log.info("Unexpected system exception "+ex);
//                        }
//                        emailService.sendSimpleMessage(item.getEmail(), post.getExamName(), "A new " + post.getExamName() + " notification is out, Please get into the our channel to know more information ");
                    });
                }else {
                    HashSet<String> set = new HashSet<>();
                    item.getCentralJobCategoriesList().stream().forEach(category->{
                        set.add(category.getCategoryName());
                    });
                    centralGovtExamPostList.stream().forEach(post -> {
//                        log.info("job type "+post.getJobType());
                        if(set.contains(post.getCategory()))
//                            try {
                                asyncNotify.sendNotificationAsynchronously(item.getEmail(), post.getExamName() + "-Central", "A new " + post.getExamName() + " notification is out , Please check into our channel to know more information");
//                            }catch(Exception ex){
//                                log.info("Unexpected system exception "+ex);
//                            }
                    });
                }

            });
            log.info("Notified all the Central government jobs subscribers");
        }catch (Exception ex){
            log.error("Unexpected error occurred while sending notification mails "+ex.getMessage());
        }


    }

    @Async
    public void notifyStateGovtExamSubscriber(List<StateGovtExamPost> stateGovtExamPostList){

        try {
            stateGovtExamPostList.stream().forEach(item -> {

                List<StateGovtSubscriber> stateGovtSubscriberList = stateGovtSubscriberRepo.findAllByState(item.getState());

                stateGovtSubscriberList.stream().forEach(sub -> {
//                    try {
                        asyncNotify.sendNotificationAsynchronously(sub.getEmail(), item.getExamName() + "-state", "A new " + item.getExamName() + " notification is out , Please check into our channel to know more information");
//                    }catch(Exception ex){
//                        log.info("Unexpected system exception "+ex);
//                    }
//                    emailService.sendSimpleMessage(sub.getEmail(), item.getExamName(), "A new " + item.getExamName() + " notification is out, Please get into our channel to know more information");
                });
            });
            log.info("Notified all the state government subscribers successfully");
        }catch (Exception ex){
            log.error("Unexpected error occurred while sending notification mails "+ex.getMessage());
        }

    }
}
