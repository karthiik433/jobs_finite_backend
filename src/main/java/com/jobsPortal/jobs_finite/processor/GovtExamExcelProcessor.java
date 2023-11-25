package com.jobsPortal.jobs_finite.processor;


import com.jobsPortal.jobs_finite.DTO.GovtExamExcelDTO;
import com.jobsPortal.jobs_finite.exception.ExcelFileException;
import com.jobsPortal.jobs_finite.utilities.DateFormattingUtility;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GovtExamExcelProcessor {

    @Autowired
    private DateFormattingUtility dateFormattingUtility;


    private List<GovtExamExcelDTO> govtExamExcelDTOList = new ArrayList<>();

    private boolean error = false;

    private String errorMessage;

    private int rowNumber = 1;

    public List<GovtExamExcelDTO> processExcelFile(File file) throws Exception{
        govtExamExcelDTOList.clear();
        error = false;
        errorMessage = "";
        rowNumber = 1;
        log.info("Reading excel file");
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .preferNullOverDefault(true)
                .caseInsensitive(true)
                .trimCellValue(true)
                .namedHeaderMandatory(true)
                .build();

        log.info(file.exists()+" file exists or not");
        Poiji.fromExcel(file, GovtExamExcelDTO.class,options,this::validateData);
        if(error){
            throw new ExcelFileException(errorMessage);
        }
        return govtExamExcelDTOList;

    }

    private void validateData(GovtExamExcelDTO govtExamExcelDTO){

        rowNumber+=1;

        if(govtExamExcelDTO.getExamName() == null){
            this.error = true;
            this.errorMessage = "Exam Date cannot be empty at Row "+rowNumber;
        }
        if(govtExamExcelDTO.getExamDate() == null){
            this.error= true;
            this.errorMessage = "Exam Date cannot be empty at Row "+rowNumber;
        }else {
            try {
                dateFormattingUtility.formatDate(govtExamExcelDTO.getExamDate());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }
        if(govtExamExcelDTO.getRecruitmentBoard() == null){
            this.error = true;
            this.errorMessage = "Recruitment board cannot be empty at Row "+rowNumber;
        }
        if(govtExamExcelDTO.getPostName() == null){
            this.error = true;
            this.errorMessage = "Post name cannot be empty at Row "+rowNumber;
        }
        if(govtExamExcelDTO.getQualification() == null){
            this.error =  true;
            this.errorMessage = "Qualification cannot be empty at Row "+rowNumber;
        }
//        if(govtExamExcelDTO.getAdvtNumber() == null){
//            this.error = true;
//            this.errorMessage = "Advt Number cannot be empty at Row "+rowNumber;
//        }
        if(govtExamExcelDTO.getLastDate() == null){
            this.error = true;
            this.errorMessage = "Last date cannot be empty "+rowNumber;
        }else {
            try {
                dateFormattingUtility.formatDate(govtExamExcelDTO.getLastDate());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }
        if(govtExamExcelDTO.getUrl() == null){
            this.error =  true;
            this.errorMessage = " url cannot be empty at Row "+rowNumber;
        }

        if(govtExamExcelDTO.getJobType() == null){
            this.error = true;
            this.errorMessage = "Job Type cannot be null or empty at Row "+rowNumber;
        }

        if(govtExamExcelDTO.getPaymentMode() == null){
            this.error = true;
            this.errorMessage = "Payment mode cannot be null or empty at Row"+rowNumber;
        }
        if(govtExamExcelDTO.getLastDayForPaymentString() == null){
         this.error = true;
         this.errorMessage = "Last day for payment cannot be null or empty at Row"+rowNumber;
        }else{
            try {
                dateFormattingUtility.formatDate(govtExamExcelDTO.getLastDayForPaymentString());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }

        if(govtExamExcelDTO.getLastDayForChallanString() == null){
            this.error = true;
            this.errorMessage = "Last day for challan cannot be null or empty at Row"+rowNumber;
        }else{
            try {
                dateFormattingUtility.formatDate(govtExamExcelDTO.getLastDayForChallanString());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }

        if(govtExamExcelDTO.getLastDayForCorrectionString() == null){
            this.error = true;
            this.errorMessage = "Last day for correction cannot be null or empty at Row"+rowNumber;
        }else{
            try {
                dateFormattingUtility.formatDate(govtExamExcelDTO.getLastDayForCorrectionString());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }

        if(govtExamExcelDTO.getMinimumAge() == 0){
            this.error =true;
            this.errorMessage = "Minimum age cannot be null or empty at Row "+rowNumber;
        }
        if(govtExamExcelDTO.getMaximumAge() == 0){
            this.error = true;
            this.errorMessage = "Maximum age cannot be null or empty at Row "+rowNumber;
        }

        if(govtExamExcelDTO.getOfficialUrl() == null){
            this.error =true;
            this.errorMessage = "Official url cannot be empty or null at Row "+rowNumber;
        }
        if(govtExamExcelDTO.getJobDescription() == null){
            this.error = true;
            this.errorMessage = "Job Description cannot be null or empty "+rowNumber;
        }

        this.govtExamExcelDTOList.add(govtExamExcelDTO);
    }
}
