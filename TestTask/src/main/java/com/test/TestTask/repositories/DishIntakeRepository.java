package com.test.TestTask.repositories;

import com.test.TestTask.model.DishIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishIntakeRepository extends JpaRepository<DishIntake, Integer> {
}
