package com.jobsPortal.jobs_finite.DTO;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class PrivateJobExcelDTO {

    @ExcelCellName("companyName")
    private String companyName;

    @ExcelCellName("postName")
    private String examName;

    @ExcelCellName("postDate")
    private String postDateString;

    @ExcelCellName("qualification")
    private String qualification;

    @ExcelCellName("postLastDate")
    private String postLastDateString;

    @ExcelCellName("url")
    private String url;

    //newly added fields


    @ExcelCellName("experience")
    private int experience;

    @ExcelCellName("jobDescription")
    private String jobDescription;

    @ExcelCellName("minPercentage")
    private String minPercentage;

    @ExcelCellName("CTC")
    private String ctc;

    @ExcelCellName("yearOfPassing")
    private String yearOfPassing;

    @ExcelCellName("fresher")
    private Boolean fresher;
}
