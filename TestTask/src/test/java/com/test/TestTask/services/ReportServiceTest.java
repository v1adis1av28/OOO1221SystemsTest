package com.test.TestTask.services;

import com.test.TestTask.DTO.CalorieCheckDTO;
import com.test.TestTask.DTO.DailyReportDTO;
import com.test.TestTask.DTO.IntakeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReportServiceTest {

    @MockitoBean
    private IntakeService intakeService;

    @Autowired
    private ReportService reportService;

    @Test
    public void testCreateDailyReport_Success() {
        // Arrange
        IntakeDTO intakeDTO = new IntakeDTO();
        intakeDTO.setDishesName(List.of("Pizza", "Salad"));

        when(intakeService.getDailyIntakes(1)).thenReturn(List.of(intakeDTO));
        when(intakeService.countCalories(anyList())).thenReturn(400);

        DailyReportDTO result = reportService.createDailyReport(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getIntakeCount());
        assertEquals(400, result.getCalorieCount());
        assertTrue(result.getDishNameList().get(0).contains("Pizza"));
        assertTrue(result.getDishNameList().get(0).contains("Salad"));
    }

    @Test
    public void testCreateDailyReport_NoIntakes() {
        when(intakeService.getDailyIntakes(1)).thenReturn(new ArrayList<>());
        when(intakeService.countCalories(anyList())).thenReturn(0);


        DailyReportDTO result = reportService.createDailyReport(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals(0, result.getIntakeCount());
        assertEquals(0, result.getCalorieCount());
        assertTrue(result.getDishNameList().isEmpty());
    }

    @Test
    public void testGetCalorieCheckReport_WithinLimit() {
        CalorieCheckDTO dto = new CalorieCheckDTO();
        dto.setCurrentCalorieCount(400);
        dto.setUserDailyCalorie(500);

        when(intakeService.checkCalories(1)).thenReturn(dto);

        CalorieCheckDTO result = reportService.getCalorieCheckReport(1);

        assertNotNull(result);
        assertEquals(400, result.getCurrentCalorieCount());
        assertEquals(500, result.getUserDailyCalorie());
    }

    @Test
    public void testGetCalorieCheckReport_ExceedsLimit() {
        CalorieCheckDTO dto = new CalorieCheckDTO();
        dto.setCurrentCalorieCount(600);
        dto.setUserDailyCalorie(500);

        when(intakeService.checkCalories(1)).thenReturn(dto);
        CalorieCheckDTO result = reportService.getCalorieCheckReport(1);

        assertNotNull(result);
        assertEquals(600, result.getCurrentCalorieCount());
        assertEquals(500, result.getUserDailyCalorie());
    }
}