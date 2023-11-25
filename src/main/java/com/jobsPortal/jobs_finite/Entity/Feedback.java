package com.jobsPortal.jobs_finite.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Feedback {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "FEED_BACK")
    private String feedBack;
}
