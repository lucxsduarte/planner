package com.project.planner.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.planner.BaseTests;
import com.project.planner.services.exception.ObjectNotFound;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Transactional
public class TripServiceTest extends BaseTests {

    @Autowired
    private TripService tripService;

    @Test
    @DisplayName("Teste para buscar viagem por ID")
    @Sql({"file:src/test/java/resources/sqls/trips.sql"})
    void findByIdTest() {
        var newTrip = tripService.findById(1);
        assertEquals("Tubarão SC", newTrip.getDestination());
    }

    @Test
    @DisplayName("Teste para buscar viagem por ID inexistente")
    @Sql({"file:src/test/java/resources/sqls/trips.sql"})
    void findByIdErrorTest() {
        var exception = assertThrows(ObjectNotFound.class, () -> tripService.findById(10));
        assertEquals("Viagem 10 não encontrada.", exception.getMessage());
    }
}
