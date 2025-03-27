package com.test.TestTask.controllers;

import com.test.TestTask.DTO.DailyReportDTO;
import com.test.TestTask.model.DailyReport;
import com.test.TestTask.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/daily")
    public DailyReportDTO getDailyReport(@RequestParam("userId") int id) {
        return reportService.createDailyReport(id);
    }

//- отчет за день с суммой всех калорий и приемов пищи;
//- проверка, уложился ли пользователь в свою дневную норму калорий;
//- история питания по дням.

}
