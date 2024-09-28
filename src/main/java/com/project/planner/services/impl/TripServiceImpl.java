package com.project.planner.services.impl;

import com.project.planner.repositories.TripRepository;
import com.project.planner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
}
