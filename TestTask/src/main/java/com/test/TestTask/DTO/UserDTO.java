package com.test.TestTask.DTO;

import com.test.TestTask.model.Gender;
import com.test.TestTask.model.Goal;

public class UserDTO {
    private String username;
    private String email;
    private int age;
    private float weight;
    private float height;
    private float dailyCalories;
    private Goal goal;
    private Gender gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(float dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}