package com.project.planner.services;

import com.project.planner.domains.participant.Participant;
import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.ParticipantCreateResponse;
import com.project.planner.dtos.ParticipantDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    Participant findById(UUID id);

    Participant save(Participant participant);

    void registerParticipantsToTrip(final List<String> participantsToInvite, final Trip trip);

    ParticipantCreateResponse registerParticipantToTrip(final String email, final Trip trip);

    void triggerConfirmationEmailToParticipants(final UUID tripId);

    void triggerConfirmationEmailToParticipant(final String email);

    List<ParticipantDTO> getAllParticipantsFromTrip(UUID tripID);
}
