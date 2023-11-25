package com.jobsPortal.jobs_finite.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PrivateJobSubscriber {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "EMAIL")
    private String email;

}
