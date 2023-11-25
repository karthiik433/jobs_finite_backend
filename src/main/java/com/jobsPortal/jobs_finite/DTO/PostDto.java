package com.jobsPortal.jobs_finite.DTO;


import lombok.Data;

@Data
public class PostDto {

    private String jobType;         //UPSC,BANKING etc....

    private String examName;

    private String postDateString;

    private String qualification;

    private String postLastDateString;

    private String url;

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

    // state
    private String state;           //Andhra,Telangana etc....

    //    @JsonFormat(pattern="dd/MM/yyyy")
//    @Column(name = "POST_TYPE")
//    private String postType;

    private int examFee;

    private String eligibility;

    private int vacancyDetails;

    //private
    private String companyName;

    private int experience;

    private String minPercentage;

    private String ctc;

    private String yearOfPassing;

    private Boolean fresher;
}
