package com.jobsPortal.jobs_finite.DTO;


import lombok.Data;

import java.util.List;

@Data
public class AllPostSubscriberDto {

    private String emailId;

    private List<String> categoryList;

    private String state;
}
