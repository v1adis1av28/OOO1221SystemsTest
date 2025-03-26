package com.test.TestTask.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Intakes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Intake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JoinColumn(name="userId", referencedColumnName = "id")
    @ManyToOne
    private User user;

    @Column(name="date")
    private LocalDate date;

    public List<DishIntake> getDishIntakes() {
        return dishIntakes;
    }

    public void setDishIntakes(List<DishIntake> dishIntakes) {
        this.dishIntakes = dishIntakes;
    }

    @OneToMany(mappedBy = "intake", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DishIntake> dishIntakes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
