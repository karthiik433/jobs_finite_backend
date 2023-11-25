package com.jobsPortal.jobs_finite.Repository.ExamRepository.AdminRepository;


import com.jobsPortal.jobs_finite.Entity.Admin;
import com.jobsPortal.jobs_finite.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;


    public Admin fetchAdmin(String email){

        Admin admin = adminRepo.findAdminByEmail(email);
        return admin;
    }

    public String saveAdmin(Admin admin) throws Exception{

        Admin admin1 =  adminRepo.findAdminByEmail(admin.getEmail());
        if(admin1 == null){
            adminRepo.save(admin);
            return "Saved admin successfully";
        }else{
            log.info("Admin already exists");
            throw new GlobalException("Admin already exists");
        }

    }

    public String saveAdmins(){

        List<Admin> adminList =  new ArrayList<>();

       final Admin admin = new Admin();
       final Admin admin1 = new Admin();
       final Admin admin2 = new Admin();

        admin.setEmail("karthik12345kumar@gmail.com");
        admin.setPassword("kartheek");
        adminList.add(admin);


        admin1.setEmail("kommurukalyan@gmail.com");
        admin1.setPassword("karthik");
        adminList.add(admin1);



        admin2.setEmail("arunkumarchintha136@gmail.com");
        admin2.setPassword("karthika");
        adminList.add(admin2);


        adminList.stream().forEach(item->{
           Admin admin3 =  adminRepo.findAdminByEmail(item.getEmail());
           if(admin3 == null){
               adminRepo.save(item);
           }else{
               log.error("Admin already present "+admin3.getEmail());
           }
        });

        return "Saved all the admins successfully";

    }

    public void deleteAdmins(){

        adminRepo.deleteAll();
        log.info("deleted");
    }
}
