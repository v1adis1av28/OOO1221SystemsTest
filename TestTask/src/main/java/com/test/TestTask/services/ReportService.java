package com.test.TestTask.services;

import com.test.TestTask.DTO.DailyReportDTO;
import com.test.TestTask.DTO.IntakeDTO;
import com.test.TestTask.model.Intake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final IntakeService intakeService;
    private final DishIntakeService dishIntakeService;
    private final DishService dishService;

    @Autowired
    public ReportService(IntakeService intakeService, DishIntakeService dishIntakeService, DishService dishService) {
        this.intakeService = intakeService;
        this.dishIntakeService = dishIntakeService;
        this.dishService = dishService;
    }

    public DailyReportDTO createDailyReport(int id) {
        List<IntakeDTO> userIntakes = intakeService.getDailyIntakes(id);
    }
}
