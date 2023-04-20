package com.phaser.project.domain.dto;

import lombok.Data;

@Data
public class ActionDto {
    private Long id;

    private String name;
    private Float duration;
    private PhaseDto phase;

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

    public PhaseDto getPhase() {
        return phase;
    }

    public void setPhase(PhaseDto phase) {
        this.phase = phase;
    }

}
