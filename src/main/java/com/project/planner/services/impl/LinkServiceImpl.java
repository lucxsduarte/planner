package com.project.planner.services.impl;

import com.project.planner.domains.link.Link;
import com.project.planner.domains.trip.Trip;
import com.project.planner.dtos.LinkDTO;
import com.project.planner.dtos.LinkRequestPayload;
import com.project.planner.dtos.LinkResponse;
import com.project.planner.repositories.LinkRepository;
import com.project.planner.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public LinkResponse registerLink(final LinkRequestPayload payload, final Trip trip) {
        final var newLink = new Link(payload.title(), payload.url(), trip);
        this.linkRepository.save(newLink);
        return new LinkResponse(newLink.getId());
    }

    @Override
    public List<LinkDTO> getAllLinksFromTrip(final Integer tripId) {
        return this.linkRepository.findByTripId(tripId).stream().map(link -> new LinkDTO(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
