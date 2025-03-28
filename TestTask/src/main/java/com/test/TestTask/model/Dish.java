package com.test.TestTask.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DISHES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message = "dish name shouldn`t be empty")
    @Column(name="name")
    private String name;

    @Column(name="calorie")
    @Min(value = 10, message = "Number of calories should be greater than 10")
    private float calorie;

    @Min(value = 0, message = "protein should be greater than 0")
    @Column(name="protein")
    private float protein;

    @Min(value = 0, message = "fats should be greater than 0")
    @Column(name="fat")
    private float fat;

    @Min(value = 0, message = "carbs should be greater than 0")
    @Column(name="carbs")
    private float carbs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCalorie() {
        return calorie;
    }

    public void setCalorie(float calorie) {
        this.calorie = calorie;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }
}
