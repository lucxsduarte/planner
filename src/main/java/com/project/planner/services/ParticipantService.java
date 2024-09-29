package com.project.planner.services;

import com.project.planner.domains.participant.Participant;
import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.ParticipantCreateResponse;
import com.project.planner.dtos.ParticipantDTO;

import java.util.List;

public interface ParticipantService {

    Participant findById(Integer id);

    Participant save(Participant participant);

    void registerParticipantsToTrip(final List<String> participantsToInvite, final Trip trip);

    ParticipantCreateResponse registerParticipantToTrip(final String email, final Trip trip);

    void triggerConfirmationEmailToParticipants(final Integer tripId);

    void triggerConfirmationEmailToParticipant(final String email);

    List<ParticipantDTO> getAllParticipantsFromTrip(Integer tripID);
}
