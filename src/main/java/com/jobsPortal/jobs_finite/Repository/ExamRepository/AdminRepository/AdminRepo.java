package com.jobsPortal.jobs_finite.Repository.ExamRepository.AdminRepository;

import com.jobsPortal.jobs_finite.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {


    Admin findAdminByEmail(String email);
}
