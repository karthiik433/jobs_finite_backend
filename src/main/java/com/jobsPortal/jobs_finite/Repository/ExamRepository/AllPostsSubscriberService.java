package com.jobsPortal.jobs_finite.Repository.ExamRepository;


import com.jobsPortal.jobs_finite.DTO.AllPostSubscriberDto;
import com.jobsPortal.jobs_finite.Entity.CentralGovtSubscriber;
import com.jobsPortal.jobs_finite.Entity.CentralJobCategory;
import com.jobsPortal.jobs_finite.Entity.PrivateJobSubscriber;
import com.jobsPortal.jobs_finite.Entity.StateGovtSubscriber;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtCategoriesRepository.CentralGovtCategoriesRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository.CentralGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository.PrivateJobSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository.StateGovtSubscriberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AllPostsSubscriberService {

    @Autowired
    private CentralGovtSubscriberRepo centralGovtSubscriberRepo;

    @Autowired
    private StateGovtSubscriberRepo stateGovtSubscriberRepo;

    @Autowired
    private PrivateJobSubscriberRepo privateJobSubscriberRepo;

    @Autowired
    private CentralGovtCategoriesRepo centralGovtCategoriesRepo;



    public void saveSubscriber(AllPostSubscriberDto allPostSubscriberDto){

        //Central Govt
        CentralGovtSubscriber centralGovtSubscriber = new CentralGovtSubscriber();
        centralGovtSubscriber.setEmail(allPostSubscriberDto.getEmailId());
        List<String> categories = allPostSubscriberDto.getCategoryList();

        List<CentralJobCategory> centralJobCategoriesList = categories.stream().map(item->{
            CentralJobCategory centralJobCategories = centralGovtCategoriesRepo.findByCategoryName(item);
            return centralJobCategories;
        }).collect(Collectors.toList());
        centralGovtSubscriber.setCentralJobCategoriesList(centralJobCategoriesList);
        CentralGovtSubscriber centralGovtSubscriber1 = centralGovtSubscriberRepo.findByEmail(allPostSubscriberDto.getEmailId());
        if(centralGovtSubscriber1 == null) {
            centralGovtSubscriberRepo.save(centralGovtSubscriber);
        }else{
            centralGovtSubscriberRepo.delete(centralGovtSubscriber1);
            centralGovtSubscriberRepo.save(centralGovtSubscriber);
        }

        //State Govt
        StateGovtSubscriber stateGovtSubscriber = new StateGovtSubscriber();
        stateGovtSubscriber.setEmail(allPostSubscriberDto.getEmailId());
        stateGovtSubscriber.setState(allPostSubscriberDto.getState());
        StateGovtSubscriber stateGovtSubscriber1 = stateGovtSubscriberRepo.findByEmail(allPostSubscriberDto.getEmailId());
        if(stateGovtSubscriber1 == null){
            stateGovtSubscriberRepo.save(stateGovtSubscriber);
        }else{
            stateGovtSubscriberRepo.delete(stateGovtSubscriber1);
            stateGovtSubscriberRepo.save(stateGovtSubscriber);
        }

        //Private Govt
        PrivateJobSubscriber privateJobSubscriber = new PrivateJobSubscriber();
        privateJobSubscriber.setEmail(allPostSubscriberDto.getEmailId());
        privateJobSubscriberRepo.save(privateJobSubscriber);
        PrivateJobSubscriber privateJobSubscriber1 = privateJobSubscriberRepo.findByEmail(allPostSubscriberDto.getEmailId());
        if(privateJobSubscriber1 == null){
            privateJobSubscriberRepo.save(privateJobSubscriber);
        }else{
            privateJobSubscriberRepo.delete(privateJobSubscriber1);
            privateJobSubscriberRepo.save(privateJobSubscriber);
        }

        log.info("Saved subscriber to all types of posts");
    }
}
