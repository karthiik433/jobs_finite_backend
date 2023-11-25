package com.jobsPortal.jobs_finite.DTO;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class CentralGovtDetailsDTO {

    private int id;

    private String jobType;         //UPSC,BANKING etc....

    private String examName;

    private String postDateString;

    //    @Column(name = "RECRUITMENT_BOARD")
//    private String recruitmentBoard;
//
//    @Column(name = "POST_NAME")
//    private String postName;
//
    private String qualification;
//
//    @Column(name = "ADVT_NO")
//    private String advtNumber;

    private String postLastDateString;

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
