package com.project.planner.repositories;

import com.project.planner.domains.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    List<Participant> findParticipantByTripId(final Integer tripId);
}
