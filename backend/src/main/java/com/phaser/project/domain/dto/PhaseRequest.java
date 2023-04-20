package com.phaser.project.domain.dto;

import lombok.Data;

@Data
public class PhaseRequest {
    private String name;
    private Integer phaseOrder;

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
}
