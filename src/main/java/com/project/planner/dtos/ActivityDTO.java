package com.project.planner.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityDTO(UUID id, String title, LocalDateTime occurs_at) {
}
