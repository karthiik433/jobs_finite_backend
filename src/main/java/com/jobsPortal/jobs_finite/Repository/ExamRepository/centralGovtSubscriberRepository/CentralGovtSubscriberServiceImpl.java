package com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository;


import com.jobsPortal.jobs_finite.DTO.subscriber.CentralGovtSubscriberDTO;
import com.jobsPortal.jobs_finite.Entity.CentralGovtSubscriber;
import com.jobsPortal.jobs_finite.Entity.CentralJobCategory;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtCategoriesRepository.CentralGovtCategoriesRepo;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CentralGovtSubscriberServiceImpl {


    @Autowired
    private CentralGovtSubscriberRepo centralGovtSubscriberRepo;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private CentralGovtCategoriesRepo centralGovtCategoriesRepo;


    public String saveSubscriberData(CentralGovtSubscriberDTO centralGovtSubscriberDTO) throws Exception{

        CentralGovtSubscriber centralGovtSubscriber = new CentralGovtSubscriber();
        centralGovtSubscriber.setEmail(centralGovtSubscriberDTO.getEmailId());
        List<String> categories = centralGovtSubscriberDTO.getCategoryList();

        CentralGovtSubscriber centralGovtSubscriber1 = centralGovtSubscriberRepo.findByEmail(centralGovtSubscriberDTO.getEmailId());
        log.info(" searching for subscriber "+centralGovtSubscriber1);
        if(centralGovtSubscriber1 == null) {
           List<CentralJobCategory> centralJobCategoriesList = categories.stream().map(item->{
               CentralJobCategory centralJobCategories = centralGovtCategoriesRepo.findByCategoryName(item);
                return centralJobCategories;
            }).collect(Collectors.toList());
           centralGovtSubscriber.setCentralJobCategoriesList(centralJobCategoriesList);
            centralGovtSubscriberRepo.save(centralGovtSubscriber);
            String successMessage = "Saved Subscriber successfully";
            emailService.sendSimpleMessage(centralGovtSubscriberDTO.getEmailId(),"Subscription successful","Thanks for subscribing to central government posts and to our channel");
            return successMessage;
        }else{
            throw new GlobalException("You have already subscribed to this category ");
        }
    }
}
