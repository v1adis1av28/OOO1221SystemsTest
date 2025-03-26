package com.test.TestTask.controllers;


import com.test.TestTask.DTO.IntakeDTO;
import com.test.TestTask.exceptions.DishNotFoundException;
import com.test.TestTask.exceptions.UserNotCreatedException;
import com.test.TestTask.model.Intake;
import com.test.TestTask.services.IntakeService;
import com.test.TestTask.util.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intake")
public class IntakeController {

    private final IntakeService intakeService;

    @Autowired
    public IntakeController(IntakeService intakeService)
    {
        this.intakeService = intakeService;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> addIntake(@RequestBody IntakeDTO intakeDTO)
    {
        intakeService.save(intakeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<IntakeDTO> getAllIntakes()
    {
        return intakeService.getAll();
    }

    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(DishNotFoundException e) {
        ResponseError response = new ResponseError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
