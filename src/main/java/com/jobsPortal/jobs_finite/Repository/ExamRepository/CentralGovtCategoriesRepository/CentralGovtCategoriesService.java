package com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtCategoriesRepository;


import com.jobsPortal.jobs_finite.Entity.CentralJobCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CentralGovtCategoriesService {


    @Autowired
    private CentralGovtCategoriesRepo centralGovtCategoriesRepo;


    public void saveALlCategories(){

        CentralJobCategory centralGovtCategories = new CentralJobCategory();
        centralGovtCategories.setCategoryCode(101);
        centralGovtCategories.setCategoryName("UPSC");
        centralGovtCategoriesRepo.save(centralGovtCategories);
        centralGovtCategories.setCategoryCode(102);
        centralGovtCategories.setCategoryName("Bank");
        centralGovtCategoriesRepo.save(centralGovtCategories);
        centralGovtCategories.setCategoryCode(103);
        centralGovtCategories.setCategoryName("Railways");
        centralGovtCategoriesRepo.save(centralGovtCategories);
        centralGovtCategories.setCategoryCode(104);
        centralGovtCategories.setCategoryName("SSC");
        centralGovtCategoriesRepo.save(centralGovtCategories);
        centralGovtCategories.setCategoryCode(109);
        centralGovtCategories.setCategoryName("All");
        centralGovtCategoriesRepo.save(centralGovtCategories);

        log.info("Saved all the central govt categories");
    }
}
