package com.project.planner.participant;

import com.project.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    public void registerParticipantsToTrip(final List<String> participantsToInvite, final Trip trip) {
        final var participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();

        this.repository.saveAll(participants);
    }

    public ParticipantCreateResponse registerParticipantToTrip(final String email, final Trip trip) {
        final var newParticipant = new Participant(email, trip);
        this.repository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }

    public void triggerConfirmationEmailToParticipants(final UUID tripId){

    }

    public void triggerConfirmationEmailToParticipant(final String email){

    }

    public List<ParticipantData> getAllParticipantsFromTrip(UUID tripID) {
        return this.repository.findParticipantByTripId(tripID).stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }
}
