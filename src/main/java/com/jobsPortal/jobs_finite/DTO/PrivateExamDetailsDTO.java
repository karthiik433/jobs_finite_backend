package com.jobsPortal.jobs_finite.DTO;


import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class PrivateExamDetailsDTO {

    private int id;

    private String companyName;

    private String examName;

    private String postDateString;

    private String qualification;

    private String postLastDateString;

    private String url;

    private int experience;

    private String jobDescription;

    private String minPercentage;

    private String ctc;

    private String yearOfPassing;

    private Boolean fresher;

}
