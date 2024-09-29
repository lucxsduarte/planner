package com.project.planner.services;

import com.project.planner.domains.trip.Trip;

import java.util.List;
import java.util.UUID;

public interface TripService {

    Trip findById(UUID id);

    List<Trip> findAll();

    Trip save(Trip trip);
}
