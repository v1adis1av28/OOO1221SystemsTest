package com.test.TestTask.services;

import com.test.TestTask.DTO.DailyReportDTO;
import com.test.TestTask.DTO.IntakeDTO;
import com.test.TestTask.model.DishIntake;
import com.test.TestTask.model.Intake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final IntakeService intakeService;

    @Autowired
    public ReportService(IntakeService intakeService) {
        this.intakeService = intakeService;
    }

    public DailyReportDTO createDailyReport(int id) {
        DailyReportDTO dto = new DailyReportDTO();
        dto.setUserId(id);
        List<IntakeDTO> userIntakes = intakeService.getDailyIntakes(id);
        dto.setDishNameList(
                userIntakes.stream()
                .map(IntakeDTO::getDishesName)
                .collect(Collectors.toList()));
        dto.setIntakeCount(userIntakes.size());
        dto.setCalorieCount(intakeService.countCalories(userIntakes));
        return dto;
    }
}
