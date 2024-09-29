package com.project.planner.services;

import com.project.planner.domains.trip.Trip;

import java.util.List;

public interface TripService {

    Trip findById(Integer id);

    List<Trip> findAll();

    Trip save(Trip trip);
}
