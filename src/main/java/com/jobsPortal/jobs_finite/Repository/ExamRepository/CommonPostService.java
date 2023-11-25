package com.jobsPortal.jobs_finite.Repository.ExamRepository;


import com.jobsPortal.jobs_finite.DTO.PostDto;
import com.jobsPortal.jobs_finite.Entity.CentralGovtExamPost;
import com.jobsPortal.jobs_finite.Entity.PrivateExamPost;
import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtRepository.CentralGovtRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateExamrepository.PrivateExamPostRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository.StateGovtRepo;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import com.jobsPortal.jobs_finite.utilities.DateFormattingUtility;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonPostService {

    @Autowired
    private CentralGovtRepo centralGovtRepo;

    @Autowired
    private StateGovtRepo stateGovtRepo;

    @Autowired
    private PrivateExamPostRepo privateExamPostRepo;

    @Autowired
    private DateFormattingUtility dateFormattingUtility;


    public PostDto fetchParticularPost(String postType,int id) throws Exception{

        PostDto postDto = new PostDto();

        if(postType.equalsIgnoreCase("central")){

           CentralGovtExamPost centralGovtExamPost = centralGovtRepo.findById(id);
           if(centralGovtExamPost ==null){
               throw new GlobalException("Incorrect postId sent");
           }else{
               BeanUtils.copyProperties(centralGovtExamPost,postDto);
               String postDateString = dateFormattingUtility.formatDateToString(centralGovtExamPost.getPostDate());
               postDto.setPostDateString(postDateString);
               String lastDateString =  dateFormattingUtility.formatDateToString(centralGovtExamPost.getPostLastDate());
               postDto.setPostLastDateString(lastDateString);
               String lastDateforPaymentString = dateFormattingUtility.formatDateToString(centralGovtExamPost.getLastDayForPayment());
               postDto.setLastDayForPaymentString(lastDateforPaymentString);
               String lastDateforChallanString = dateFormattingUtility.formatDateToString(centralGovtExamPost.getLastDayForChallan());
               postDto.setLastDayForChallanString(lastDateforChallanString);
               String lastDateFOrCorrectionString = dateFormattingUtility.formatDateToString(centralGovtExamPost.getLastDayForCorrection());
               postDto.setLastDayForCorrectionString(lastDateFOrCorrectionString);
               return postDto;

           }
        }else if(postType.equalsIgnoreCase("state")){

            StateGovtExamPost stateGovtExamPost = stateGovtRepo.findById(id);
            if(stateGovtExamPost == null){
                throw new GlobalException("Incorrect postId sent");
            }else{
                BeanUtils.copyProperties(stateGovtExamPost,postDto);
                String postDateString = dateFormattingUtility.formatDateToString(stateGovtExamPost.getPostDate());
                postDto.setPostDateString(postDateString);
                String lastDateString =  dateFormattingUtility.formatDateToString(stateGovtExamPost.getPostLastDate());
                postDto.setPostLastDateString(lastDateString);
                String lastDateforPaymentString = dateFormattingUtility.formatDateToString(stateGovtExamPost.getLastDayForPayment());
                postDto.setLastDayForPaymentString(lastDateforPaymentString);
                String lastDateforChallanString = dateFormattingUtility.formatDateToString(stateGovtExamPost.getLastDayForChallan());
                postDto.setLastDayForChallanString(lastDateforChallanString);
                String lastDateFOrCorrectionString = dateFormattingUtility.formatDateToString(stateGovtExamPost.getLastDayForCorrection());
                postDto.setLastDayForCorrectionString(lastDateFOrCorrectionString);
                return postDto;
            }
        }else if(postType.equalsIgnoreCase("private")){

            PrivateExamPost privateExamPost = privateExamPostRepo.findById(id);
            if(privateExamPost == null){
                throw new GlobalException("Incorrect postId sent");
            }else{
                BeanUtils.copyProperties(privateExamPost,postDto);
                String postDateString = dateFormattingUtility.formatDateToString(privateExamPost.getPostDate());
                postDto.setPostDateString(postDateString);
                String lastDateString = dateFormattingUtility.formatDateToString(privateExamPost.getPostLastDate());
                postDto.setPostLastDateString(lastDateString);
                return postDto;
            }
        }else{
            throw new GlobalException("Incorrect postType sent");
        }
    }

}
