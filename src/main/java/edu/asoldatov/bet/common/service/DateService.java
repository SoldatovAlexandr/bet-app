package edu.asoldatov.bet.common.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DateService {

    public LocalDate today() {
        return LocalDate.of(2024, 6, 16);
    }

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
