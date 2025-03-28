package com.test.TestTask.DTO;

public class CalorieCheckDTO {

    private float userDailyCalorie;
    private float currentCalorieCount;
    private String message;

    public float getUserDailyCalorie() {
        return userDailyCalorie;
    }

    public void setUserDailyCalorie(float userDailyCalorie) {
        this.userDailyCalorie = userDailyCalorie;
    }

    public float getCurrentCalorieCount() {
        return currentCalorieCount;
    }

    public void setCurrentCalorieCount(float currentCalorieCount) {
        this.currentCalorieCount = currentCalorieCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
