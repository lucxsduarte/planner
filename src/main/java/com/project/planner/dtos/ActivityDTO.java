package com.project.planner.dtos;

import java.time.LocalDateTime;

public record ActivityDTO(Integer id, String title, LocalDateTime occurs_at) {
}
