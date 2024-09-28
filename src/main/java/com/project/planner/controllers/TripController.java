package com.project.planner.controllers;

import com.project.planner.dtos.*;
import com.project.planner.dtos.ActivityRequestPayload;
import com.project.planner.services.impl.ActivityServiceImpl;
import com.project.planner.dtos.LinkRequestPayload;
import com.project.planner.services.impl.LinkServiceImpl;
import com.project.planner.domains.trip.Trip;
import com.project.planner.services.impl.ParticipantServiceImpl;
import com.project.planner.repositories.TripRepository;
import com.project.planner.dtos.TripRequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ParticipantServiceImpl participantServiceImpl;

    @Autowired
    private ActivityServiceImpl activityServiceImpl;

    @Autowired
    private LinkServiceImpl linkServiceImpl;

    @Autowired
    private TripRepository repository;

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody final TripRequestPayload payload) {
        final var newTrip = new Trip(payload);

        this.repository.save(newTrip);
        this.participantServiceImpl.registerParticipantsToTrip(payload.emails_to_invite(), newTrip);

        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable final UUID id) {
        final var trip = this.repository.findById(id);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable final UUID id, @RequestBody final TripRequestPayload payload) {
        final var trip = this.repository.findById(id);

        if (trip.isPresent()) {
            final var rawTrip = trip.get();
            rawTrip.setEndsAt(LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setStartsAt(LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setDestination(payload.destination());

            this.repository.save(rawTrip);

            return ResponseEntity.ok(rawTrip);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/confirm/{id}")
    public ResponseEntity<Trip> confirmTrip(@PathVariable final UUID id) {
        final var trip = this.repository.findById(id);

        if (trip.isPresent()) {
            final var rawTrip = trip.get();
            rawTrip.setIsConfirmed(true);

            this.repository.save(rawTrip);
            this.participantServiceImpl.triggerConfirmationEmailToParticipants(id);

            return ResponseEntity.ok(rawTrip);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/invite/{id}")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable final UUID id, @RequestBody final ParticipantRequestPayload payload) {
        final var trip = this.repository.findById(id);

        if (trip.isPresent()) {
            final var rawTrip = trip.get();


        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<List<ParticipantDTO>> listAllParticipants(@PathVariable final UUID id) {
        final var participantsList = this.participantServiceImpl.getAllParticipantsFromTrip(id);

        return ResponseEntity.ok(participantsList);
    }

    @PostMapping("/activities/{id}")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable final UUID id, @RequestBody final ActivityRequestPayload payload) {
        final var trip = this.repository.findById(id);

        if (trip.isPresent()) {
            final var rawTrip = trip.get();
            final var activityResponse = this.activityServiceImpl.registerActivity(payload, rawTrip);
            return ResponseEntity.ok(activityResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<List<ActivityDTO>> listAllActivities(@PathVariable final UUID id) {
        final var activitiesDataList = this.activityServiceImpl.getAllActivitiesFromTrip(id);

        return ResponseEntity.ok(activitiesDataList);
    }

    @PostMapping("/links/{id}")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable final UUID id, @RequestBody final LinkRequestPayload payload) {
        final var trip = this.repository.findById(id);

        if (trip.isPresent()) {
            final var rawTrip = trip.get();
            final var linkResponse = this.linkServiceImpl.registerLink(payload, rawTrip);
            return ResponseEntity.ok(linkResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/links/{id}")
    public ResponseEntity<List<LinkDTO>> listAllLinks(@PathVariable final UUID id) {
        final var linksDataList = this.linkServiceImpl.getAllLinksFromTrip(id);

        return ResponseEntity.ok(linksDataList);
    }
}
