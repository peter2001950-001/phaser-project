package com.phaser.project.repositories;

import com.phaser.project.entities.PhaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhaseRepository extends JpaRepository<PhaseEntity,Long> {

    @Query(value = "SELECT max(phaseOrder) FROM PhaseEntity ")
    public Integer maxPhaseOrder();


    @Query(value = "SELECT x from PhaseEntity as x order by x.phaseOrder")
    public List<PhaseEntity> findAllOrderByPhaseOrder();

}
