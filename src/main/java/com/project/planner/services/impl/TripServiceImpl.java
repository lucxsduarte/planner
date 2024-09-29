package com.project.planner.services.impl;

import com.project.planner.domains.trip.Trip;
import com.project.planner.repositories.TripRepository;
import com.project.planner.services.TripService;
import com.project.planner.services.exception.BusinessException;
import com.project.planner.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip findById(final Integer id) {
        final var participant = this.tripRepository.findById(id);
        return participant.orElseThrow(() -> new ObjectNotFound("Viagem %s não encontrada.".formatted(id)));
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
    public Trip save(final Trip trip) {
        if (trip.getStartsAt().isAfter(trip.getEndsAt()) || trip.getStartsAt().isEqual(trip.getEndsAt())) {
            throw new BusinessException("Data inicial deve ser anterior a data final.");
        }

        return this.tripRepository.save(trip);
    }
}
