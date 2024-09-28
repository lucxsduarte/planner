package com.project.planner.link;

import com.project.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public LinkResponse registerLink(final LinkRequestPayload payload, final Trip trip) {
        final var newLink = new Link(payload.title(), payload.url(), trip);
        this.repository.save(newLink);
        return new LinkResponse(newLink.getId());
    }

    public List<LinkData> getAllLinksFromTrip(final UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
