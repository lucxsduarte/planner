package com.project.planner.repositories;

import com.project.planner.domains.link.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    List<Link> findByTripId(Integer tripId);
}
