package com.jobsPortal.jobs_finite.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class StateGovtExamDetailsDTO {

    private int id;

    private String state;           //Andhra,Telangana etc....

    //    @JsonFormat(pattern="dd/MM/yyyy")
    private String postDateString;

//    @Column(name = "POST_TYPE")
//    private String postType;

    private String examName;

    private int examFee;

    private String eligibility;

    private String qualification;

    private String postLastDateString;

    private int vacancyDetails;

    private String url;

    //newly added fields
    private int generalFee;

    private int scFee;

    private String paymentMode;

    private String lastDayForPaymentString;

    private String lastDayForChallanString;

    private String lastDayForCorrectionString;

    private int minimumAge;

    private int maximumAge;

    private int generalVacancy;

    private int scVacancy;

    private int stVacancy;

    private String officialUrl;

    private String jobDescription;
}
