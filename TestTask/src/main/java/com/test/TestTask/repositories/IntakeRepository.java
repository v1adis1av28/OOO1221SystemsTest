package com.test.TestTask.repositories;

import com.test.TestTask.model.Intake;
import com.test.TestTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntakeRepository extends JpaRepository<Intake, Integer> {
    Intake save(Intake intake);
    List<Intake> getIntakesByUser(User user);
}
