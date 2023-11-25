package com.jobsPortal.jobs_finite.DTO;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import java.util.Date;

@Data
public class GovtExamExcelDTO {

    @ExcelCellName("examName")
    private String examName;

    @ExcelCellName("examDate")
    private String examDate;

    @ExcelCellName("recruitmentBoard")
    private String recruitmentBoard;

    @ExcelCellName("postName")
    private String postName;

    @ExcelCellName("qualification")
    private String qualification;

//    @ExcelCellName("advtNumber")
//    private String advtNumber;

    @ExcelCellName("lastDate")
    private String lastDate;

    @ExcelCellName("applyUrl")
    private String url;

    @ExcelCellName("jobType")
    private String jobType;

    @ExcelCellName("category")
    private String category;

    @ExcelCellName("state")
    private String state;

    //newly added fields

    @ExcelCellName("generalFee")
    private int generalFee;

    @ExcelCellName("scFee")
    private int scFee;

    @ExcelCellName("paymentMode")
    private String paymentMode;

    @ExcelCellName("lastDayForPayment")
    private String lastDayForPaymentString;

    @ExcelCellName("lastDayForChallan")
    private String lastDayForChallanString;

    @ExcelCellName("lastDayforCorrection")
    private String lastDayForCorrectionString;

    @ExcelCellName("minimumAge")
    private int minimumAge;

    @ExcelCellName("maximumAge")
    private int maximumAge;

    @ExcelCellName("generalVacancy")
    private int generalVacancy;

    @ExcelCellName("scVacancy")
    private int scVacancy;

    @ExcelCellName("stVacancy")
    private int stVacancy;

    @ExcelCellName("officialUrl")
    private String officialUrl;

    @ExcelCellName("jobDescription")
    private String jobDescription;
}
