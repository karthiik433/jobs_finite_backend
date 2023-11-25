package com.jobsPortal.jobs_finite.utilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Service
@Slf4j
public class DateFormattingUtility {


    public Date formatDate(String inputDate) throws Exception{

//        System.out.println("input string "+inputDate);
        String[] stringArray1 =  inputDate.split("/");
        String[] stringArray2 = inputDate.split("-");
//        System.out.println("String 1 "+ Arrays.toString(stringArray1));
//        System.out.println("String 2 "+ Arrays.toString(stringArray2));
        if(stringArray1.length>0){

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(inputDate);
//            System.out.println(date);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println(dateFormat.format(date)+" converted back");
            return date;

        }else if(stringArray2.length>0){

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = format.parse(inputDate);
//            System.out.println(date);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println(dateFormat.format(date)+" converted back");
            return date;

        }else{
            throw new Exception("Date format incorrect");
        }
    }

    public String formatDateToString(Date date){

//        log.info("Received date "+date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormat.format(date);
    }
}
