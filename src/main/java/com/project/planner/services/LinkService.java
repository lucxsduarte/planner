package com.project.planner.services;

import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.LinkDTO;
import com.project.planner.dtos.LinkRequestPayload;
import com.project.planner.dtos.LinkResponse;
import jdk.dynalink.linker.LinkRequest;

import java.util.List;
import java.util.UUID;

public interface LinkService {

    LinkResponse registerLink(final LinkRequestPayload payload, final Trip trip);

    List<LinkDTO> getAllLinksFromTrip(final UUID tripId);
}
