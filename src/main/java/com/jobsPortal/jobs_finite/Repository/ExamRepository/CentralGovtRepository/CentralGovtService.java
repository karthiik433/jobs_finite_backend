package com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtRepository;


import com.jobsPortal.jobs_finite.DTO.GovtExamExcelDTO;
import com.jobsPortal.jobs_finite.Entity.CentralGovtExamPost;
import com.jobsPortal.jobs_finite.Entity.CentralGovtSubscriber;
import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import com.jobsPortal.jobs_finite.Entity.StateGovtSubscriber;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository.CentralGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository.StateGovtRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository.StateGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import com.jobsPortal.jobs_finite.notifySubscribers.NotifySubscribers;
import com.jobsPortal.jobs_finite.utilities.DateFormattingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CentralGovtService {

    @Autowired
    private CentralGovtRepo centralGovtRepo;

    @Autowired
    private StateGovtRepo stateGovtRepo;

    @Autowired
    private CentralGovtSubscriberRepo centralGovtSubscriberRepo;

    @Autowired
    private StateGovtSubscriberRepo stateGovtSubscriberRepo;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private NotifySubscribers notifySubscribers;

    private List<CentralGovtExamPost> centralGovtExamPostList = new ArrayList<>();

    private List<StateGovtExamPost> stateGovtExamPostList = new ArrayList<>();

    @Autowired
    private DateFormattingUtility dateFormattingUtility;

    public CentralGovtExamPost saveCentralGovtExam(CentralGovtExamPost centralGovtExamPost){

        CentralGovtExamPost centralGovtExamPost1 = centralGovtRepo.save(centralGovtExamPost);

        return centralGovtExamPost1;
    }

    public List<CentralGovtExamPost> findExamByName(String examName){

        examName = examName.toUpperCase();
        List<CentralGovtExamPost> centralGovtExamPostList = centralGovtRepo.findAllByExamName(examName);
        return centralGovtExamPostList;
    }

    public String saveExcelData(List<GovtExamExcelDTO> govtExamExcelDTOList)throws Exception{

        centralGovtExamPostList.clear();
        stateGovtExamPostList.clear();

         govtExamExcelDTOList.stream().forEach(item->{
            if(item.getJobType().trim().equalsIgnoreCase("central")){
                CentralGovtExamPost centralGovtExamPost =  new CentralGovtExamPost();
                BeanUtils.copyProperties(item, centralGovtExamPost);
                try {
                    Date postDate = dateFormattingUtility.formatDate(item.getExamDate());
                    Date lastDate = dateFormattingUtility.formatDate(item.getLastDate());
                    Date lastDateforPayment = dateFormattingUtility.formatDate(item.getLastDayForPaymentString());
                    Date lastDateForChallan = dateFormattingUtility.formatDate(item.getLastDayForChallanString());
                    Date lastDateForCorrection = dateFormattingUtility.formatDate(item.getLastDayForCorrectionString());

                    centralGovtExamPost.setLastDayForPayment(lastDateforPayment);
                    centralGovtExamPost.setLastDayForChallan(lastDateForChallan);
                    centralGovtExamPost.setLastDayForCorrection(lastDateForCorrection);
                    centralGovtExamPost.setPostDate(postDate);
                    centralGovtExamPost.setPostLastDate(lastDate);
                } catch (Exception e) {
                    log.error("Error occurred while parsing date "+e.getMessage());
                }
//                System.out.println("central " +centralGovtExamPost);
               CentralGovtExamPost centralGovtExamPostSaved =  centralGovtRepo.save(centralGovtExamPost);
                centralGovtExamPostList.add(centralGovtExamPostSaved);
            }else{
                StateGovtExamPost stateGovtExamPost = new StateGovtExamPost();
                BeanUtils.copyProperties(item,stateGovtExamPost);
                try {
                    Date postDate = dateFormattingUtility.formatDate(item.getExamDate());
                    Date lastDate = dateFormattingUtility.formatDate(item.getLastDate());
                    Date lastDateforPayment = dateFormattingUtility.formatDate(item.getLastDayForPaymentString());
                    Date lastDateForChallan = dateFormattingUtility.formatDate(item.getLastDayForChallanString());
                    Date lastDateForCorrection = dateFormattingUtility.formatDate(item.getLastDayForCorrectionString());

                    stateGovtExamPost.setLastDayForPayment(lastDateforPayment);
                    stateGovtExamPost.setLastDayForChallan(lastDateForChallan);
                    stateGovtExamPost.setLastDayForCorrection(lastDateForCorrection);
                    stateGovtExamPost.setPostDate(postDate);
                    stateGovtExamPost.setPostLastDate(lastDate);
                } catch (Exception e) {
                    log.error("Error occurred while parsing date "+e.getMessage());
                }
//                System.out.println("central " +stateGovtExamPost);
               StateGovtExamPost stateGovtExamPostSaved = stateGovtRepo.save(stateGovtExamPost);
                stateGovtExamPostList.add(stateGovtExamPostSaved);
            }
        });

        notifySubscribers.notifyCentralGovtExamSubscribers(centralGovtExamPostList);
        notifySubscribers.notifyStateGovtExamSubscriber(stateGovtExamPostList);
         String successMessage = "Saved Excel data successfully";
        log.info("Saved Excel data successfully");
        return successMessage;
    }



    public List<CentralGovtExamPost> fetchAllcentralGovtPosts(){
        return centralGovtRepo.findAll();
    }
}
