package com.phaser.project.repositories;

import com.phaser.project.entities.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {
}
