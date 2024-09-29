package com.project.planner.repositories;

import com.project.planner.domains.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByTripId(Integer tripId);
}
