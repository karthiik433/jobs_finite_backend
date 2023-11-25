package com.jobsPortal.jobs_finite.controller;


import com.jobsPortal.jobs_finite.Entity.Admin;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.AdminRepository.AdminRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.AdminRepository.AdminService;
import com.jobsPortal.jobs_finite.email.EmailServiceImpl;
import com.jobsPortal.jobs_finite.processor.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminValidationService {


    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordGenerator passwordGenerator;

    public boolean authenticateAdmin(String email){

        Admin admin = adminService.fetchAdmin(email);

        if(admin == null){
            log.info("Not an Admin");
            return false;
        }else{
            emailService.sendSimpleMessage(admin.getEmail(),"Password","Hello team \n This is your password: "+admin.getPassword());
            log.info("Sent mail to the Admin");
            return true;
        }
    }
}
