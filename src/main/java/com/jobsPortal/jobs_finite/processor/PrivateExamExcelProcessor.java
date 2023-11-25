package com.jobsPortal.jobs_finite.processor;

import com.jobsPortal.jobs_finite.DTO.GovtExamExcelDTO;
import com.jobsPortal.jobs_finite.DTO.PrivateJobExcelDTO;
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
public class PrivateExamExcelProcessor {


    @Autowired
    private DateFormattingUtility dateFormattingUtility;


    private List<PrivateJobExcelDTO> privateJobExcelDTOList = new ArrayList<>();

    private boolean error = false;

    private String errorMessage;

    private int rowNumber = 1;

    public List<PrivateJobExcelDTO> processExcelFile(File file) throws Exception{
        privateJobExcelDTOList.clear();
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
        Poiji.fromExcel(file, PrivateJobExcelDTO.class,options,this::validateData);
        if(error){
            throw new ExcelFileException(errorMessage);
        }
        return privateJobExcelDTOList;

    }

    private void validateData(PrivateJobExcelDTO privateJobExcelDTO){

        rowNumber+=1;

        if(privateJobExcelDTO.getExamName() == null){
            this.error = true;
            this.errorMessage = "Exam Name cannot be empty at Row "+rowNumber;
        }
        if(privateJobExcelDTO.getPostDateString() == null){
            this.error= true;
            this.errorMessage = "Exam Date cannot be empty at Row "+rowNumber;
        }else {
            try {
                dateFormattingUtility.formatDate(privateJobExcelDTO.getPostDateString());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }
        if(privateJobExcelDTO.getCompanyName() == null){
            this.error = true;
            this.errorMessage = "Company name cannot be empty at Row "+rowNumber;
        }
        if(privateJobExcelDTO.getQualification() == null){
            this.error = true;
            this.errorMessage = "Qualification  cannot be empty at Row "+rowNumber;
        }
//        if(govtExamExcelDTO.getQualification() == null){
//            this.error =  true;
//            this.errorMessage = "Qualification cannot be empty at Row "+rowNumber;
//        }
//        if(govtExamExcelDTO.getAdvtNumber() == null){
//            this.error = true;
//            this.errorMessage = "Advt Number cannot be empty at Row "+rowNumber;
//        }
        if(privateJobExcelDTO.getPostLastDateString() == null){
            this.error = true;
            this.errorMessage = "Last date cannot be empty "+rowNumber;
        }else {
            try {
                dateFormattingUtility.formatDate(privateJobExcelDTO.getPostDateString());
            }catch (Exception ex){
                this.error = false;
                this.errorMessage = "Incorrect Date format";
            }
        }
        if(privateJobExcelDTO.getUrl() == null){
            this.error =  true;
            this.errorMessage = " url cannot be empty at Row "+rowNumber;
        }

        if(privateJobExcelDTO.getFresher() == null){
                this.error = true;
                this.errorMessage = "Fresher value cannot be null at Row "+rowNumber;
        }
        if(privateJobExcelDTO.getMinPercentage() == null){
            this.error = true;
            this.errorMessage = "Minimum percentage cannot be null or empty at Row "+rowNumber;
        }

//        if(govtExamExcelDTO.getJobType() == null){
//            this.error = true;
//            this.errorMessage = "Job Type cannot be null or empty "+rowNumber;
//        }

        this.privateJobExcelDTOList.add(privateJobExcelDTO);
    }
}
