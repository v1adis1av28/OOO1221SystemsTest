package com.test.TestTask.repositories;

import com.test.TestTask.model.Intake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntakeRepository extends JpaRepository<Intake, Integer> {
    Intake save(Intake intake);
}
