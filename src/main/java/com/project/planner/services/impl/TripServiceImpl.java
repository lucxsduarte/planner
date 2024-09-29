package com.project.planner.services.impl;

import com.project.planner.domains.trip.Trip;
import com.project.planner.repositories.TripRepository;
import com.project.planner.services.TripService;
import com.project.planner.services.exception.BusinessException;
import com.project.planner.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip findById(final UUID id) {
        final var participant = this.tripRepository.findById(id);
        return participant.orElseThrow(() -> new ObjectNotFound("Viagem não encontrada."));
    }

    @Override
    public List<Trip> findAll() {
        final var trips = this.tripRepository.findAll();
        if (trips.isEmpty()) {
            throw new BusinessException("Nenhuma viagem encontrada.");
        }

        return trips;
    }

    @Override
    public Trip save(Trip trip) {
        return this.tripRepository.save(trip);
    }
}
