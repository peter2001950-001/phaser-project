package com.phaser.project.domain.dto;

import lombok.Data;

@Data
public class ActionRequest {
    private String name;
    private Float duration;

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
}
