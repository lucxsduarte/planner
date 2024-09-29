package com.project.planner.controllers;

import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.*;
import com.project.planner.services.ActivityService;
import com.project.planner.services.LinkService;
import com.project.planner.services.ParticipantService;
import com.project.planner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody final TripRequestPayload payload) {
        final var newTrip = new Trip(payload);
        this.tripService.save(newTrip);
        this.participantService.registerParticipantsToTrip(payload.emails_to_invite(), newTrip);

        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    @PostMapping("/invite/{id}")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable final Integer id, @RequestBody final ParticipantRequestPayload payload) {
        final var trip = this.tripService.findById(id); //TODO

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/activities/{id}")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable final Integer id, @RequestBody final ActivityRequestPayload payload) {
        final var trip = this.tripService.findById(id);
        final var activityResponse = this.activityService.registerActivity(payload, trip);

        return ResponseEntity.ok(activityResponse);
    }

    @PostMapping("/links/{id}")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable final Integer id, @RequestBody final LinkRequestPayload payload) {
        final var trip = this.tripService.findById(id);
        final var linkResponse = this.linkService.registerLink(payload, trip);

        return ResponseEntity.ok(linkResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable final Integer id, @RequestBody final TripRequestPayload payload) {
        final var trip = this.tripService.findById(id);
        trip.setEndsAt(LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME));
        trip.setStartsAt(LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
        trip.setDestination(payload.destination());
        this.tripService.save(trip);

        return ResponseEntity.ok(trip);
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getTrips() {
        final var trips = this.tripService.findAll();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable final Integer id) {
        final var trip = this.tripService.findById(id);

        return ResponseEntity.ok(trip);
    }

    @GetMapping("/confirm/{id}")
    public ResponseEntity<Trip> confirmTrip(@PathVariable final Integer id) {
        final var trip = this.tripService.findById(id);
        trip.setIsConfirmed(true);
        this.tripService.save(trip);
        this.participantService.triggerConfirmationEmailToParticipants(id);

        return ResponseEntity.ok(trip);
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<List<ParticipantDTO>> listAllParticipants(@PathVariable final Integer id) {
        final var participantsList = this.participantService.getAllParticipantsFromTrip(id);

        return ResponseEntity.ok(participantsList);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<List<ActivityDTO>> listAllActivities(@PathVariable final Integer id) {
        final var activitiesDataList = this.activityService.getAllActivitiesFromTrip(id);

        return ResponseEntity.ok(activitiesDataList);
    }

    @GetMapping("/links/{id}")
    public ResponseEntity<List<LinkDTO>> listAllLinks(@PathVariable final Integer id) {
        final var linksDataList = this.linkService.getAllLinksFromTrip(id);

        return ResponseEntity.ok(linksDataList);
    }
}
