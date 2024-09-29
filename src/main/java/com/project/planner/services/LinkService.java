package com.project.planner.services;

import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.LinkDTO;
import com.project.planner.dtos.LinkRequestPayload;
import com.project.planner.dtos.LinkResponse;

import java.util.List;

public interface LinkService {

    LinkResponse registerLink(final LinkRequestPayload payload, final Trip trip);

    List<LinkDTO> getAllLinksFromTrip(final Integer tripId);
}
