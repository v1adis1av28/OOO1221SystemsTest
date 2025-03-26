package com.test.TestTask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    @NotEmpty(message = "Username should not be empty")
    private String username;

    @Email(message = "email must have correct form")
    @NotNull(message = "email should not be empty")
    @Column(name = "email")
    private String email;

    @Column(name = "age")
    @Min(value=18, message = "You can use service after you are 18")
    private int age;

    @Column(name="weight")
    @Min(value = 30)
    private double weight;

    @Min(value = 120)
    @Column(name="height")
    private double height;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal goal;

    @Column(name="daily_calories")
    private float dailyCalories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Intake> intakes = new ArrayList<>();

    public List<Intake> getIntakes() {
        return intakes;
    }

    public void setIntakes(List<Intake> intakes) {
        this.intakes = intakes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public float getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(float dailyCalories) {
        this.dailyCalories = dailyCalories;
    }
}
