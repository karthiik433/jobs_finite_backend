package com.jobsPortal.jobs_finite.controller;

import com.jobsPortal.jobs_finite.DTO.*;
import com.jobsPortal.jobs_finite.DTO.subscriber.CentralGovtSubscriberDTO;
import com.jobsPortal.jobs_finite.DTO.subscriber.PrivateJobSubscriberDTO;
import com.jobsPortal.jobs_finite.DTO.subscriber.StateGovtSubscriberDTO;
import com.jobsPortal.jobs_finite.Entity.*;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.AdminRepository.AdminService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.AllPostsSubscriberService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.AllPostsUnSubscribeService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtCategoriesRepository.CentralGovtCategoriesRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtCategoriesRepository.CentralGovtCategoriesService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtRepository.CentralGovtRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtRepository.CentralGovtService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CommonPostService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.ExamService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.FeedBackRepository.FeedBackService;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository.CentralGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository.CentralGovtSubscriberServiceImpl;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateExamrepository.PrivateExamPostRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateExamrepository.PrivateExamServiceImpl;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository.PrivateJobSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository.PrivateJobSubscriberServiceImpl;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository.StateGovtExamServiceImpl;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository.StateGovtRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository.StateGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository.StateGovtSubscriberServiceImpl;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.subscriberRepository.SubscriberService;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import com.jobsPortal.jobs_finite.processor.GovtExamExcelProcessor;
import com.jobsPortal.jobs_finite.processor.PrivateExamExcelProcessor;
import com.jobsPortal.jobs_finite.utilities.DateFormattingUtility;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.net.MalformedURLException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class Controller {



    @Autowired
    private ExamService examService;


    @Autowired
    private CentralGovtService centralGovtService;

    @Autowired
    private StateGovtExamServiceImpl stateGovtExamService;

    @Autowired
    private PrivateExamServiceImpl privateExamService;

    @Autowired
    private ControllerService controllerService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private GovtExamExcelProcessor govtExamExcelProcessor;

    @Autowired
    private PrivateExamExcelProcessor privateExamExcelProcessor;

    @Autowired
    private CentralGovtSubscriberServiceImpl centralGovtSubscriberService;

    @Autowired
    private StateGovtSubscriberServiceImpl stateGovtSubscriberService;

    @Autowired
    private PrivateJobSubscriberServiceImpl privateJobSubscriberService;

    @Autowired
    private DateFormattingUtility dateFormattingUtility;

    @Autowired
    private CommonPostService commonPostService;

    @Autowired
    private AllPostsSubscriberService allPostsSubscriberService;

    @Autowired
    private AllPostsUnSubscribeService allPostsUnSubscribeService;

    @Autowired
    private CentralGovtSubscriberRepo centralGovtSubscriberRepo;

    @Autowired
    private StateGovtSubscriberRepo stateGovtSubscriberRepo;

    @Autowired
    private PrivateJobSubscriberRepo privateJobSubscriberRepo;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CentralGovtRepo centralGovtRepo;

    @Autowired
    private StateGovtRepo stateGovtRepo;

    @Autowired
    private PrivateExamPostRepo privateExamPostRepo;

    @Autowired
    private CentralGovtCategoriesService centralGovtCategoriesService;

    @Autowired
    private CentralGovtCategoriesRepo centralGovtCategoriesRepo;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private AdminValidationService adminValidationService;

//    @CrossOrigin(origins = {"http://localhost:3000"})
//    @PostMapping("/saveExam")
//    public ResponseEntity<StateGovtExamPost> sendExam(@RequestBody StateGovtExamPost stateGovtExamPost){
//
//        StateGovtExamPost response = examService.saveExam(stateGovtExamPost);
//        return new ResponseEntity<>(response,HttpStatus.CREATED);
//    }

    @GetMapping("/")
    public ResponseEntity<String> appStarted(){

        return new ResponseEntity<>("I have been deployed darling",HttpStatus.OK);
    }

    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/excelUpload")
    public ResponseEntity<String> processExcelFile(@RequestParam("file") MultipartFile file,@RequestParam("fileType") String fileType) throws Exception{
        log.info("Received file "+file.getOriginalFilename());


        File file1 = new File(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
        log.info("file location "+file1.getAbsolutePath());


        try {
            FileUtils.writeByteArrayToFile(file1, file.getBytes());
        }
        catch(IOException ex){
            log.error("Exception occurred while reading excel data "+ex.getMessage());
        }
        if(fileType.trim().equalsIgnoreCase("govt")){

            List<GovtExamExcelDTO> govtExamExcelDTOList = govtExamExcelProcessor.processExcelFile(file1);
            centralGovtService.saveExcelData(govtExamExcelDTOList);

        }else if(fileType.trim().equalsIgnoreCase("private")){

            List<PrivateJobExcelDTO> privateJobExcelDTOList = privateExamExcelProcessor.processExcelFile(file1);
            privateExamService.saveExcelData(privateJobExcelDTOList);
        }else{
            throw new GlobalException("In appropriate file type selected");
        }

        return new ResponseEntity<>("Successfully saved the data",HttpStatus.OK);
    }

//    @CrossOrigin(origins = {"http://localhost:3000"})
//    @PostMapping("/saveCentralGovtExam")
//    public ResponseEntity<CentralGovtExamPost> saveCentralGovtExam(@RequestBody CentralGovtExamPost centralGovtExamPost){
//
//        CentralGovtExamPost centralGovtExamPost1 = centralGovtService.saveCentralGovtExam(centralGovtExamPost);
//        return new ResponseEntity<>(centralGovtExamPost1,HttpStatus.CREATED);
//    }

//    @CrossOrigin(origins = {"http://localhost:3000"})
//    @GetMapping("/findGovtExamByName/{examName}")
//    public ResponseEntity<List<CentralGovtExamPost>> findCentralGovtExamByName(@PathVariable("examName") String examName){
//
//        List<CentralGovtExamPost> centralGovtExamPostList = centralGovtService.findExamByName(examName);
//        return new ResponseEntity<>(centralGovtExamPostList,HttpStatus.OK);
//    }
//
//    @GetMapping("/sendEmail")
//    public ResponseEntity<String> sendEmail(@RequestParam("to") String to,@RequestParam("subject") String subject,@RequestParam("text") String text){
//
//        try {
//            emailService.sendSimpleMessage(to, subject, text);
//            return new ResponseEntity<>("Send email successFully",HttpStatus.OK);
//        }catch(Exception ex){
//            return new ResponseEntity<>("Failed to send the mail "+ex.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    @PostMapping("/saveSubscriber")
//    public ResponseEntity<Subscriber> saveSubscriber(@RequestBody Subscriber subscriber){
//
//        Subscriber subscriber1 =  subscriberService.saveSubscriber(subscriber);
//        return new ResponseEntity<>(subscriber1,HttpStatus.CREATED);
//    }
//

    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/saveCentralGovtSubscriber")
    public ResponseEntity<String> saveCentralGovtSub(@RequestBody CentralGovtSubscriberDTO centralGovtSubscriberDTO) throws Exception{

        log.info("subscriber data received "+centralGovtSubscriberDTO);
        String successMessage = centralGovtSubscriberService.saveSubscriberData(centralGovtSubscriberDTO);

//        String successMessage = "Saved subscriber successfully";
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/saveStateGovtSubscriber")
    public ResponseEntity<String> saveStateGovtSub(@RequestBody StateGovtSubscriberDTO stateGovtSubscriberDTO) throws Exception{

        log.info("received subscriber data "+stateGovtSubscriberDTO);
        String successMessage = stateGovtSubscriberService.saveSubscriberData(stateGovtSubscriberDTO);

//        String successMessage = "Saved subscriber successfully";
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }



    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/savePrivateJobSubscriber")
    public ResponseEntity<String> savePrivateJobSub(@RequestBody PrivateJobSubscriberDTO privateJobSubscriberDTO) throws Exception{

        boolean response = adminValidationService.authenticateAdmin(privateJobSubscriberDTO.getEmailId());

        if(response){
            return new ResponseEntity<>("This is an admin mail ",HttpStatus.CREATED);
        }

        log.info("Sent subscriber data "+privateJobSubscriberDTO);
        String successMessage = privateJobSubscriberService.saveSubscriberData(privateJobSubscriberDTO);
//        String successMessage = "Saved subscriber successfully";
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @GetMapping("/getAllCentralGovtPost")
    public List<CentralGovtDetailsDTO> sendAllGovtPosts() throws Exception{

        try {
            List<CentralGovtExamPost> centralGovtExamPostList = centralGovtService.fetchAllcentralGovtPosts();
            return centralGovtExamPostList.stream().sorted(Comparator.comparing(CentralGovtExamPost::getPostDate).reversed()).map(item->{

                CentralGovtDetailsDTO centralGovtDetailsDTO = new CentralGovtDetailsDTO();
                BeanUtils.copyProperties(item,centralGovtDetailsDTO);
                try{
                    String postDateString = dateFormattingUtility.formatDateToString(item.getPostDate());
                    centralGovtDetailsDTO.setPostDateString(postDateString);
                    String lastDateString =  dateFormattingUtility.formatDateToString(item.getPostLastDate());
                    centralGovtDetailsDTO.setPostLastDateString(lastDateString);
                    String lastDateforPaymentString = dateFormattingUtility.formatDateToString(item.getLastDayForPayment());
                    centralGovtDetailsDTO.setLastDayForPaymentString(lastDateforPaymentString);
                    String lastDateforChallanString = dateFormattingUtility.formatDateToString(item.getLastDayForChallan());
                    centralGovtDetailsDTO.setLastDayForChallanString(lastDateforChallanString);
                    String lastDateFOrCorrectionString = dateFormattingUtility.formatDateToString(item.getLastDayForCorrection());
                    centralGovtDetailsDTO.setLastDayForCorrectionString(lastDateFOrCorrectionString);
                }
                catch(Exception ex){
                    log.error("Exception occurred while converting date to string "+ex.getMessage());
                }
                return centralGovtDetailsDTO;
            }).collect(Collectors.toList());

        }catch(Exception ex){
            log.info("Unexpected error occurred while fetching data "+ex.getMessage());
            throw new GlobalException(ex.getMessage());
        }
    }


    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @GetMapping("/getAllStateGovtPosts")
    public List<StateGovtExamDetailsDTO> sendAllStateGovtPosts() throws Exception{

        try{
            List<StateGovtExamPost> stateGovtExamPostList =  stateGovtExamService.fetchAllStateGovtExamPosts();
           return stateGovtExamPostList.stream().sorted(Comparator.comparing(StateGovtExamPost::getPostDate).reversed()).map(item->{

                StateGovtExamDetailsDTO stateGovtExamDetailsDTO = new StateGovtExamDetailsDTO();
                BeanUtils.copyProperties(item,stateGovtExamDetailsDTO);
                try{
//                    log.warn("date from db "+item.getPostDate());
//                    log.warn("date format required "+ new Date());
                    String postDateString = dateFormattingUtility.formatDateToString(item.getPostDate());
                    stateGovtExamDetailsDTO.setPostDateString(postDateString);
                    String lastDateString =  dateFormattingUtility.formatDateToString(item.getPostLastDate());
                    stateGovtExamDetailsDTO.setPostLastDateString(lastDateString);
                    String lastDateforPaymentString = dateFormattingUtility.formatDateToString(item.getLastDayForPayment());
                    stateGovtExamDetailsDTO.setLastDayForPaymentString(lastDateforPaymentString);
                    String lastDateforChallanString = dateFormattingUtility.formatDateToString(item.getLastDayForChallan());
                    stateGovtExamDetailsDTO.setLastDayForChallanString(lastDateforChallanString);
                    String lastDateFOrCorrectionString = dateFormattingUtility.formatDateToString(item.getLastDayForCorrection());
                    stateGovtExamDetailsDTO.setLastDayForCorrectionString(lastDateFOrCorrectionString);
                }catch(Exception ex){
                    log.error("Exception occurred while converting date to string "+ex.getMessage());
                }
                return stateGovtExamDetailsDTO;
            }).collect(Collectors.toList());

        }catch(Exception ex){

            log.info("Unexpected error occurred while fetching details "+ex.getMessage());
            throw  new GlobalException("Unexpected error occurred while fetching details "+ex.getMessage());
        }
    }


    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @GetMapping("/getAllPrivatePosts")
    public List<PrivateExamDetailsDTO> sendAllPrivateExamPosts() throws Exception{

        try{
            List<PrivateExamPost> privateExamPostList = privateExamService.fetchAllPrivateExamPosts();
            return privateExamPostList.stream().sorted(Comparator.comparing(PrivateExamPost::getPostDate).reversed()).map(item->{

                PrivateExamDetailsDTO privateExamDetailsDTO = new PrivateExamDetailsDTO();
                BeanUtils.copyProperties(item,privateExamDetailsDTO);
                try{
                    String postDateString = dateFormattingUtility.formatDateToString(item.getPostDate());
                    privateExamDetailsDTO.setPostDateString(postDateString);
                    String lastDateString = dateFormattingUtility.formatDateToString(item.getPostLastDate());
                    privateExamDetailsDTO.setPostLastDateString(lastDateString);
                }catch (Exception ex){
                    log.error("Exception occurred while converting date to string "+ex.getMessage());
                }
                return privateExamDetailsDTO;
            }).collect(Collectors.toList());

        }
        catch (Exception ex){
            log.info("Unexpected error occurred while fetching data "+ex.getMessage());
            throw new GlobalException("Unexpected error occurred while fetching data "+ex.getMessage());
        }
    }


    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @GetMapping("/getPostDetails/{jobType}/{id}")
    public ResponseEntity<PostDto> sendParticularPost(@PathVariable("jobType") String jobType,@PathVariable("id") int id) throws Exception{

        PostDto postDto = commonPostService.fetchParticularPost(jobType,id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/saveAllPostSubscriber")
    public ResponseEntity<String> saveSubscriber(@RequestBody AllPostSubscriberDto allPostSubscriberDto){

        allPostsSubscriberService.saveSubscriber(allPostSubscriberDto);
        return new ResponseEntity<>("Subscribed to all post successfully",HttpStatus.OK);
    }


    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @DeleteMapping("/unSubscribe/{email}")
    public ResponseEntity<String> unSubscriber(@PathVariable("email") String email){

        allPostsUnSubscribeService.unSubscribe(email);
        return new ResponseEntity<>("Un subscribed to all the posts successfully",HttpStatus.OK);
    }


    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/authenticatePassword")
    public ResponseEntity<String> authenticate(@RequestBody AdminAuthentication adminAuthentication) throws Exception{

        Admin admin = adminService.fetchAdmin(adminAuthentication.getEmail());
        if(admin != null){
            if(admin.getPassword().trim().equals(adminAuthentication.getPassword().trim())){
                log.info("Admin authentication successful");
                return new ResponseEntity<>("Admin Authentication successful",HttpStatus.OK);
            }else{
                throw new GlobalException("Admin authentication failed");
            }
        }else {
            throw new GlobalException("Admin email sent is incorrect");
        }
    }



    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
    @PostMapping("/sendFeedback")
    public ResponseEntity<String> saveFeedback( @RequestBody FeedBackDTO feedBackDTO){

        String response = feedBackService.saveFeedBack(feedBackDTO);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


//    @CrossOrigin(origins = {"https://jobsfiniteofficial.vercel.app","http://localhost:3000"})
//    @GetMapping("/getPrivatePostsWithEntity")
//    public ResponseEntity<Dummy> sendAllPrivateExamWithResponseEntityPosts() throws Exception{
//
//        try{
//            List<PrivateExamPost> privateExamPostList = privateExamService.fetchAllPrivateExamPosts();
//            List<PrivateExamDetailsDTO> privateExamDetailsDTOList = privateExamPostList.stream().map(item->{
//
//                PrivateExamDetailsDTO privateExamDetailsDTO = new PrivateExamDetailsDTO();
//                BeanUtils.copyProperties(item,privateExamDetailsDTO);
//                try{
//                    String postDateString = dateFormattingUtility.formatDateToString(item.getPostDate());
//                    privateExamDetailsDTO.setPostDateString(postDateString);
//                    String lastDateString = dateFormattingUtility.formatDateToString(item.getPostLastDate());
//                    privateExamDetailsDTO.setPostLastDateString(lastDateString);
//                }catch (Exception ex){
//                    log.error("Exception occurred while converting date to string "+ex.getMessage());
//                }
//                return privateExamDetailsDTO;
//            }).collect(Collectors.toList());
//
//            Dummy dummy =new Dummy();
//
//            dummy.setPrivateExamDetailsDTOList(privateExamDetailsDTOList);
//            log.info("responseEntity "+new ResponseEntity<>(dummy,HttpStatus.OK));
//            generateUrlParams();
//            return new ResponseEntity<>(dummy,HttpStatus.OK);
//        }
//        catch (Exception ex){
//            log.info("Unexpected error occurred while fetching data "+ex.getMessage());
//            throw new GlobalException("Unexpected error occurred while fetching data "+ex.getMessage());
//        }
//    }




//    @GetMapping("/deleteAllSubscribers")
//    public ResponseEntity<String> deleteAllSubscribers(){
//
//        centralGovtSubscriberRepo.deleteAll();
////        stateGovtSubscriberRepo.deleteAll();
////        privateJobSubscriberRepo.deleteAll();
//        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
//    }
//
//
//
    @GetMapping("/addCategories")
    public ResponseEntity<String> saveCategories(){

        centralGovtCategoriesService.saveALlCategories();
        return new ResponseEntity<>("Saved all the categories",HttpStatus.OK);
    }
//
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CentralJobCategory>> getAllCategories(){

        return new ResponseEntity<>(centralGovtCategoriesRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getAllCentralGovtSubs")
    public ResponseEntity<List<CentralGovtSubscriber>> getCentralSubs(){

        List<CentralGovtSubscriber> centralGovtSubscriberList = centralGovtSubscriberRepo.findAll();

        return new ResponseEntity<>(centralGovtSubscriberList,HttpStatus.OK);
    }
//
//    @DeleteMapping("/deleteAllCategories")
//    private ResponseEntity<String> deleteAllCategories(){
//        centralGovtCategoriesRepo.deleteAll();
//        return new ResponseEntity<>("Deleted all the categories ",HttpStatus.OK);
//    }
//
//    @GetMapping("/clearAllData")
//    public ResponseEntity<String> clearData(){
//        centralGovtRepo.deleteAll();
//        stateGovtRepo.deleteAll();
//        privateExamPostRepo.deleteAll();
//        return new ResponseEntity<>("CLeared data successfully",HttpStatus.OK);
//    }
//
//    private void generateUrlParams() throws MalformedURLException {
//
//        String url = "https://opraahdb-dev.aexp.com/GUIController?uploadId=11";
////        String url = "https://dashboard.heroku.com/apps/jobs-finite/resources";
//        try {
//            val newUrl = new java.net.URL(url);
//            val host = newUrl.getHost();
//            int port = newUrl.getDefaultPort();
//            log.info("new Url "+newUrl+ " ||"+" host "+host+" || "+"port "+port);
//        }catch(Exception ex){
//            log.error("something went wrong "+ex.getMessage());
//        }
//    }
//
//    @GetMapping("/findSub/{email}")
//    private CentralGovtSubscriber findCentralGovtSub(@PathVariable("email") String email){
//
//        return centralGovtSubscriberRepo.findByEmail(email);
//    }
//
//    @GetMapping("/deleteCentralSub")
//    private String deleteSub(){
//        centralGovtSubscriberRepo.deleteAll();
//        return "deleted all";
//    }
//
//
//    @PostMapping("/saveAdmin")
//    private ResponseEntity<String> saveAdmin(@RequestBody Admin admin) throws Exception{
//
//        log.info("Saving admin record");
//
//        String response = adminService.saveAdmin(admin);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//
//    @GetMapping("/saveAdmins")
//    private ResponseEntity<String> saveAdmins(){
//        log.info("saving all the admins");
//
//        String response = adminService.saveAdmins();
//
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    @GetMapping("/deleteAdmin")
//    private ResponseEntity<String> deleteAdmin(){
//        log.info("Deleting all the admins");
//         adminService.deleteAdmins();
//
//         return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
//    }
//
    @GetMapping("/getAdmin/{email}")
    private ResponseEntity<Admin> fetchAdmin(@PathVariable("email") String email) throws Exception{

        Admin admin =  adminService.fetchAdmin(email);

        if(admin == null){
            throw new GlobalException(" you are not an admin brother ");
        }
        else{
            return new ResponseEntity<>(admin,HttpStatus.OK);
        }
    }
}
