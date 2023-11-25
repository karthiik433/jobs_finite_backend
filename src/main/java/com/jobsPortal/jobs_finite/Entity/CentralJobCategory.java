package com.jobsPortal.jobs_finite.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class CentralJobCategory {

    @Id
    @Column(name = "CATEGORY_CODE")
    private int categoryCode;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToMany(targetEntity = CentralGovtSubscriber.class,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CentralGovtSubscriber> centralGovtSubscriberList;
}
