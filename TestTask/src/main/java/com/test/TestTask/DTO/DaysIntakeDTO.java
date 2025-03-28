package com.test.TestTask.DTO;

import java.time.LocalDate;
import java.util.List;

public class DaysIntakeDTO {

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getDishesName() {
        return dishesName;
    }

    public void setDishesName(List<String> dishesName) {
        this.dishesName = dishesName;
    }

    private LocalDate date;
    private List<String> dishesName;
}
