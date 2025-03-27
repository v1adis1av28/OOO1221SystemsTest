package com.test.TestTask.services;

import com.test.TestTask.DTO.*;
import com.test.TestTask.model.DishIntake;
import com.test.TestTask.model.Intake;
import com.test.TestTask.model.User;
import com.test.TestTask.util.LocalDateSortingComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

    public HistoryDTO getHistoryReport(int id) {
        HistoryDTO dto = new HistoryDTO();
        dto.setUserId(id);
        // 1) получаем все интэйки пользователя
        // 2) сортируем по дате
        // 3) создаем отдельные дтошки
        List<Intake> userAllIntakes = intakeService.getUserIntakes(id);
        Collections.sort(userAllIntakes, new LocalDateSortingComparator());

        HashSet<LocalDate> dates = getDates(userAllIntakes);//Сет дат когда пользователь добавлял еду
        for (LocalDate date : dates) {
            DaysIntakeDTO daysIntakeDTO = new DaysIntakeDTO();
            daysIntakeDTO.setDate(date);

            List<IntakeDTO> intakesByDate = intakeService.convertToDTO(intakeService.getIntakesByDate(date,id));
            List<String> dishNames = intakesByDate.stream()
                    .flatMap(intakeDTO -> intakeDTO.getDishesName().stream())
                    .collect(Collectors.toList());

            daysIntakeDTO.setDishesName(dishNames);
            dto.getDaysIntakeList().add(daysIntakeDTO);
        }
        return dto;
    }

    private HashSet<LocalDate> getDates(List<Intake> userAllIntakes) {
        HashSet<LocalDate> dates = new HashSet<>();
        for(Intake intake : userAllIntakes) {
            if(!dates.contains(intake.getDate()))
                dates.add(intake.getDate());
            else
                continue;
        }
        return dates;
    }
}
