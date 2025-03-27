package com.test.TestTask.model;


import java.util.List;

public class DailyReport {

    private int userId;
    private int calorieCount;
    private List<Intake> intakeList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    public List<Intake> getIntakeList() {
        return intakeList;
    }

    public void setIntakeList(List<Intake> intakeList) {
        this.intakeList = intakeList;
    }
}
