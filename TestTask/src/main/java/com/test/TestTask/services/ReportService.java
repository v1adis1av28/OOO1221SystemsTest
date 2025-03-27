package com.test.TestTask.services;

import com.test.TestTask.DTO.CalorieCheckDTO;
import com.test.TestTask.DTO.DailyReportDTO;
import com.test.TestTask.DTO.IntakeDTO;
import com.test.TestTask.model.DishIntake;
import com.test.TestTask.model.Intake;
import com.test.TestTask.model.User;
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

    public CalorieCheckDTO getCalorieCheckReport(int id) {
        CalorieCheckDTO dto = intakeService.checkCalories(id);
        if(dto.getCurrentCalorieCount() > dto.getUserDailyCalorie())
            dto.setMessage("Calorie check failed! You exceed " + (dto.getCurrentCalorieCount()-dto.getUserDailyCalorie()) + " calories.");
        else
            dto.setMessage("Calorie check successful! You have " + (dto.getUserDailyCalorie()-dto.getCurrentCalorieCount()) + " calories left.");
        return dto;
    }
}
