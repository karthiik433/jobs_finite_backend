package com.jobsPortal.jobs_finite.Entity;


import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PrivateExamPost {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "COMPANY")
    private String companyName;

    @Column(name = "EXAM_NAME")
    private String examName;

    @Column(name = "POST_DATE")
    private Date postDate;

    @Column(name = "QUALIFICATION")
    private String qualification;

    @Column(name = "LAST_DATE")
    private Date postLastDate;

    @Column(name = "URL")
    private String url;

    //new fields added

    @Column(name = "EXPERIENCE")
    private int experience;

    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;

    @Column(name = "MIN_PERCENTAGE")
    private String minPercentage;

    @Column(name = "CTC")
    private String ctc;

    @Column(name = "YEAR_OF_PASSING")
    private String yearOfPassing;

    @Column(name = "FRESHER")
    private Boolean fresher;
}
