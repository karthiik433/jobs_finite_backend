package com.jobsPortal.jobs_finite.Entity;


import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class CentralGovtExamPost {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "JOB_TYPE")
    private String jobType;         //UPSC,BANKING etc....

    @Column(name = "EXAM_NAME")
    private String examName;

    @Column(name = "POST_DATE")
    private Date postDate;

//    @Column(name = "RECRUITMENT_BOARD")
//    private String recruitmentBoard;
//
//    @Column(name = "POST_NAME")
//    private String postName;
//
    @Column(name = "QUALIFICATION")
    private String qualification;
//
//    @Column(name = "ADVT_NO")
//    private String advtNumber;

    @Column(name = "LAST_DATE")
    private Date postLastDate;

    @Column(name = "URL")
    private String url;


    // newly added fields

    @Column(name = "GENERAL_FEE")
    private int generalFee;

    @Column(name = "SC_FEE")
    private int scFee;

    @Column(name = "PAYMENT_MODE")
    private String paymentMode;

    @Column(name = "LAST_DAY_FOR_PAYMENT")
    private Date lastDayForPayment;

    @Column(name = "LAST_DAY_FOR_CHALLAN")
    private Date lastDayForChallan;

    @Column(name = "LAST_DAY_FOR_CORRECTION")
    private Date lastDayForCorrection;

    @Column(name = "MINIMUM_AGE")
    private int minimumAge;

    @Column(name = "MAXIMUM_AGE")
    private int maximumAge;

    @Column(name = "GENERAL_VACANCY")
    private int generalVacancy;

    @Column(name = "SC_VACANCY")
    private int scVacancy;

    @Column(name = "ST_VACANCY")
    private int stVacancy;

    @Column(name = "OFFICIAL_URL")
    private String officialUrl;

    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;

    @Column(name = "CATEGORY")
    private String category;
}
