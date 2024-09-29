package com.project.planner.controllers;

import com.project.planner.domains.participant.Participant;
import com.project.planner.dtos.ParticipantRequestPayload;
import com.project.planner.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping("/confirm/{id}")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable final Integer id, @RequestBody final ParticipantRequestPayload payload) {
        final var participant = participantService.findById(id);
        participant.setIsConfirmed(true);
        participant.setName(payload.name());
        this.participantService.save(participant);
        return ResponseEntity.ok(participant);
    }
}
