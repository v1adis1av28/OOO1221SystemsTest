package com.test.TestTask.DTO;

import java.util.List;

public class IntakeDTO {

    private int userId;

    private List<String> dishesName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getDishesName() {
        return dishesName;
    }

    public void setDishesName(List<String> dishesName) {
        this.dishesName = dishesName;
    }
}
