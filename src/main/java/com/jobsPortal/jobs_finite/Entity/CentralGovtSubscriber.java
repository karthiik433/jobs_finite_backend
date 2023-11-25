package com.jobsPortal.jobs_finite.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CentralGovtSubscriber {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(targetEntity = CentralJobCategory.class,fetch = FetchType.EAGER)
//    @JsonIgnore
    private List<CentralJobCategory> centralJobCategoriesList;

}
