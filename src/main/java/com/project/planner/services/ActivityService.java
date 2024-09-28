package com.project.planner.services;

import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.ActivityDTO;
import com.project.planner.dtos.ActivityRequestPayload;
import com.project.planner.dtos.ActivityResponse;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    ActivityResponse registerActivity(final ActivityRequestPayload payload, final Trip trip);

    List<ActivityDTO> getAllActivitiesFromTrip(final UUID tripId);
}
