package com.phaser.project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "action")
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float duration;
    @ManyToOne
    private PhaseEntity phase;

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

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public PhaseEntity getPhase() {
        return phase;
    }

    public void setPhase(PhaseEntity phase) {
        this.phase = phase;
    }
}
