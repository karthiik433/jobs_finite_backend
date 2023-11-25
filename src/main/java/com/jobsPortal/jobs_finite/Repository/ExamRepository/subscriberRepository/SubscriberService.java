package com.jobsPortal.jobs_finite.Repository.ExamRepository.subscriberRepository;


import com.jobsPortal.jobs_finite.Entity.Subscriber;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public Subscriber saveSubscriber(Subscriber subscriber){

        return subscriberRepository.save(subscriber);
    }

}
