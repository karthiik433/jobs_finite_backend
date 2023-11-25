package com.jobsPortal.jobs_finite.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subscriber {

    @Id
    @Column(name = "SUBSCRIBER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subscriberId;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "STATE")
    private String state;

    @Column(name = "GOVT")
    private boolean govtPosts;

    @Column(name = "PRIVATE")
    private boolean privatePosts;

    @Column(name = "ALL_POSTS")
    private boolean allPosts;


}
