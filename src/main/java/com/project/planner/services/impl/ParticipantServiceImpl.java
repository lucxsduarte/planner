package com.project.planner.services.impl;

import com.project.planner.domains.participant.Participant;
import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.ParticipantCreateResponse;
import com.project.planner.dtos.ParticipantDTO;
import com.project.planner.repositories.ParticipantRepository;
import com.project.planner.services.ParticipantService;
import com.project.planner.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant findById(final Integer id) {
        final var participant = this.participantRepository.findById(id);
        return participant.orElseThrow(() -> new ObjectNotFound("Participante não encontrado"));
    }

    @Override
    public Participant save(final Participant participant) {
        return this.participantRepository.save(participant);
    }

    @Override
    public void registerParticipantsToTrip(final List<String> participantsToInvite, final Trip trip) {
        final var participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();

        this.participantRepository.saveAll(participants);
    }

    @Override
    public ParticipantCreateResponse registerParticipantToTrip(final String email, final Trip trip) {
        final var newParticipant = new Participant(email, trip);
        this.participantRepository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }

    @Override
    public void triggerConfirmationEmailToParticipants(final Integer tripId) {

    }

    @Override
    public void triggerConfirmationEmailToParticipant(final String email) {

    }

    @Override
    public List<ParticipantDTO> getAllParticipantsFromTrip(Integer tripID) {
        return this.participantRepository.findParticipantByTripId(tripID).stream().map(participant -> new ParticipantDTO(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }
}
