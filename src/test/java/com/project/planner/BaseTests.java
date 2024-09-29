package com.project.planner;

import com.project.planner.services.ActivityService;
import com.project.planner.services.LinkService;
import com.project.planner.services.ParticipantService;
import com.project.planner.services.TripService;
import com.project.planner.services.impl.ActivityServiceImpl;
import com.project.planner.services.impl.LinkServiceImpl;
import com.project.planner.services.impl.ParticipantServiceImpl;
import com.project.planner.services.impl.TripServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@SpringBootTest
@ActiveProfiles ("test")
public class BaseTests {

    @Bean
    public TripService tripService() {
        return new TripServiceImpl();
    }

    @Bean
    public ParticipantService participantService() {
        return new ParticipantServiceImpl();
    }

    @Bean
    public LinkService linkService() {
        return new LinkServiceImpl();
    }

    @Bean
    public ActivityService activityService() {
        return new ActivityServiceImpl();
    }
}
