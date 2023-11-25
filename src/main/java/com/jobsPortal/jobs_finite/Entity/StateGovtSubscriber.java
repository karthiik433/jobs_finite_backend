package com.jobsPortal.jobs_finite.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StateGovtSubscriber {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "STATE")
    private String state;

}
