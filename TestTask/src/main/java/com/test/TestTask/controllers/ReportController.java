package com.test.TestTask.controllers;

import com.test.TestTask.DTO.DailyReportDTO;
import com.test.TestTask.exceptions.DishNotFoundException;
import com.test.TestTask.exceptions.UserNotFoundException;
import com.test.TestTask.model.DailyReport;
import com.test.TestTask.services.ReportService;
import com.test.TestTask.util.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(UserNotFoundException e) {
        ResponseError response = new ResponseError("User with that id does not exist");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(DishNotFoundException e) {
        ResponseError response = new ResponseError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//- отчет за день с суммой всех калорий и приемов пищи;
//- проверка, уложился ли пользователь в свою дневную норму калорий;
//- история питания по дням.

}
