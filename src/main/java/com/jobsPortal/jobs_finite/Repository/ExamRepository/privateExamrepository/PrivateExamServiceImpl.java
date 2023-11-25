package com.jobsPortal.jobs_finite.Repository.ExamRepository.privateExamrepository;


import com.jobsPortal.jobs_finite.DTO.PrivateJobExcelDTO;
import com.jobsPortal.jobs_finite.Entity.PrivateExamPost;
import com.jobsPortal.jobs_finite.Entity.PrivateJobSubscriber;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository.PrivateJobSubscriberRepo;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.notifySubscribers.NotifySubscribers;
import com.jobsPortal.jobs_finite.utilities.DateFormattingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrivateExamServiceImpl {

    @Autowired
    private PrivateExamPostRepo privateExamPostRepo;

    @Autowired
    private DateFormattingUtility dateFormattingUtility;

    @Autowired
    private PrivateJobSubscriberRepo privateJobSubscriberRepo;

    @Autowired
    private NotifySubscribers notifyPrivateJobSubscriber;

    @Autowired
    private EmailServiceImpl emailService;

    public List<PrivateExamPost> fetchAllPrivateExamPosts(){

        return privateExamPostRepo.findAll();
    }

    public String saveExcelData(List<PrivateJobExcelDTO> privateJobExcelDTOList){

       List<PrivateExamPost> privateExamPostList = privateJobExcelDTOList.stream().map(item->{

            PrivateExamPost privateExamPost = new PrivateExamPost();
            BeanUtils.copyProperties(item,privateExamPost);
            try{
                Date postDate =  dateFormattingUtility.formatDate(item.getPostDateString());
                privateExamPost.setPostDate(postDate);
                Date postLastDate = dateFormattingUtility.formatDate(item.getPostLastDateString());
                privateExamPost.setPostLastDate(postLastDate);
            }
            catch (Exception ex){
                log.error("Incorrect  date format sent "+ex.getMessage());
            }
            privateExamPostRepo.save(privateExamPost);
            return privateExamPost;
        }).collect(Collectors.toList());

        notifyPrivateJobSubscriber.notifyPrivateJobSubscriber(privateExamPostList);
       String successMessage = "Saved private Exam posts ";
       log.info("Saved private Exam posts ");
        return successMessage;
    }



}
