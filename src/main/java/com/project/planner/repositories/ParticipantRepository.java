package com.project.planner.repositories;

import com.project.planner.domains.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    List<Participant> findParticipantByTripId(final UUID tripId);
}
