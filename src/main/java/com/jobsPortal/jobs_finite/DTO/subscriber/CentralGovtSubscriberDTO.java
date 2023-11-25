package com.jobsPortal.jobs_finite.DTO.subscriber;

import lombok.Data;

import java.util.List;

@Data
public class CentralGovtSubscriberDTO {

    private String emailId;

    private List<String> categoryList;

}
