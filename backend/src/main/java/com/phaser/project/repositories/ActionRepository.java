package com.phaser.project.repositories;

import com.phaser.project.entities.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {
    public List<ActionEntity> findAllByPhase_Id(long id);
}
