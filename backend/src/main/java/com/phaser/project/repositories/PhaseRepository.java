package com.phaser.project.repositories;

import com.phaser.project.entities.PhaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseRepository extends JpaRepository<PhaseEntity,Long> {
}
