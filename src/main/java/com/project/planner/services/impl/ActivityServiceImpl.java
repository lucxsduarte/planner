package com.project.planner.services.impl;

import com.project.planner.dtos.ActivityRequestPayload;
import com.project.planner.dtos.ActivityResponse;
import com.project.planner.domains.activity.Activity;
import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.ActivityDTO;
import com.project.planner.repositories.ActivityRepository;
import com.project.planner.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public ActivityResponse registerActivity(final ActivityRequestPayload payload, final Trip trip) {
        final var newActivity = new Activity(payload.title(), payload.occurs_at(), trip);
        this.activityRepository.save(newActivity);
        return new ActivityResponse(newActivity.getId());
    }

    @Override
    public List<ActivityDTO> getAllActivitiesFromTrip(final UUID tripId) {
        return this.activityRepository.findByTripId(tripId).stream().map(activity -> new ActivityDTO(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
    }
}
