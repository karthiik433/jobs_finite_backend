package com.jobsPortal.jobs_finite.controller;


import com.jobsPortal.jobs_finite.DTO.GovtExamExcelDTO;
import com.jobsPortal.jobs_finite.exception.ExcelFileException;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ControllerService {

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
        }
        if(govtExamExcelDTO.getUrl() == null){
            this.error =  true;
            this.errorMessage = " url cannot be empty at Row "+rowNumber;
        }

        this.govtExamExcelDTOList.add(govtExamExcelDTO);
    }
}
