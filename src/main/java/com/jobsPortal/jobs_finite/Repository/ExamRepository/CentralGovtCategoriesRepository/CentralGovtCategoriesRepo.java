package com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtCategoriesRepository;


import com.jobsPortal.jobs_finite.Entity.CentralJobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentralGovtCategoriesRepo extends JpaRepository<CentralJobCategory,Integer> {

    public CentralJobCategory findByCategoryName(String categoryName);
}
