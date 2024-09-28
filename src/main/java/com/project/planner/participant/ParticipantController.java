package com.project.planner.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository repository;

    @PostMapping("/confirm/{id}")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable final UUID id, @RequestBody final ParticipantRequestPayload payload) {
        final var participant = this.repository.findById(id);

        if (participant.isPresent()) {
            final var rawParticipant = participant.get();
            rawParticipant.setIsConfirmed(true);
            rawParticipant.setName(payload.name());

            this.repository.save(rawParticipant);

            return ResponseEntity.ok(rawParticipant);
        }

        return ResponseEntity.notFound().build();
    }
}
