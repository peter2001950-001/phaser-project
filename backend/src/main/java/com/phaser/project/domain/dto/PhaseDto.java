package com.phaser.project.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PhaseDto {
    private Long id;

    private String name;
    private Integer phaseOrder;

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
}
