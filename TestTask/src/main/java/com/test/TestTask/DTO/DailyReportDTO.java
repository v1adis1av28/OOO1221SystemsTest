package com.test.TestTask.DTO;


import java.util.List;


public class DailyReportDTO {

    private int userId;
    private int calorieCount;
    private int intakeCount;
    private List<List<String>> dishNameList;

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

    public int getIntakeCount() {
        return intakeCount;
    }

    public void setIntakeCount(int intakeCount) {
        this.intakeCount = intakeCount;
    }

    public List<List<String>> getDishNameList() {
        return dishNameList;
    }

    public void setDishNameList(List<List<String>> dishNameList) {
        this.dishNameList = dishNameList;
    }
}
