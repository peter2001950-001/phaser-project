package com.phaser.project.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "phase")
public class PhaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer phaseOrder;
    @OneToMany(mappedBy = "phase")
    private Set<ActionEntity> actions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhaseOrder() {
        return phaseOrder;
    }

    public void setPhaseOrder(Integer phaseOrder) {
        this.phaseOrder = phaseOrder;
    }

    public Set<ActionEntity> getActions() {
        return actions;
    }

    public void setActions(Set<ActionEntity> actions) {
        this.actions = actions;
    }
}
