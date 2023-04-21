package com.phaser.project.controllers;

import com.phaser.project.domain.dto.ActionDto;
import com.phaser.project.domain.dto.ActionRequest;
import com.phaser.project.domain.dto.BulkUpdateOrderRequest;
import com.phaser.project.domain.exceptions.RecordNotFoundException;
import com.phaser.project.domain.mapper.Mapper;
import com.phaser.project.entities.ActionEntity;
import com.phaser.project.repositories.ActionRepository;
import com.phaser.project.repositories.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/phase")
@RequiredArgsConstructor
public class ActionController {
    private final ActionRepository actionRepository;
    private final PhaseRepository phaseRepository;

    @GetMapping("{phaseId}/actions/{actionId}")
    public ResponseEntity<?> GetAction(@PathVariable Long actionId, @PathVariable Long phaseId) {
        var action = actionRepository.findById(actionId).orElseThrow(() -> new RecordNotFoundException("Action not found"));

        if(action.getPhase().getId() != phaseId){
            throw new RecordNotFoundException("Action not found");
        }
        return ResponseEntity.ok(Mapper.map(action, ActionDto.class));
    }

    @GetMapping("{phaseId}/actions")
    public ResponseEntity<?> GetActions(@PathVariable Long phaseId) {
        var actions = actionRepository.findAll();
        var mappedActions = Mapper.mapList(actions, ActionDto.class);

        return ResponseEntity.ok(mappedActions);
    }

    @PostMapping("{phaseId}/actions")
    public ResponseEntity<?> CreateAction(@PathVariable Long phaseId, @RequestBody ActionRequest request) {
        var action = Mapper.map(request, ActionEntity.class);
        var phase = phaseRepository.findById(phaseId);
        if(!phase.isPresent()) throw  new RecordNotFoundException("Phase Id does not exist!");

        action.setPhase(phase.get());
        action = actionRepository.save(action);
        var actionDto = Mapper.map(action, ActionDto.class);
        return ResponseEntity.ok(actionDto);
    }

    @PutMapping("{phaseId}/actions/{id}")
    public ResponseEntity<?> UpdateAction(@RequestBody ActionRequest request, @PathVariable Long id, @PathVariable Long phaseId) {
        var action = actionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Action not found"));
        if(action.getPhase().getId() != phaseId){
            throw new RecordNotFoundException("Action not found");
        }

        Mapper.map(request, action);
        actionRepository.save(action);

        var actionDto = Mapper.map(action, ActionDto.class);
        return ResponseEntity.ok(actionDto);
    }

    @DeleteMapping("{phaseId}/actions/{id}")
    public ResponseEntity<?> DeleteAction(@PathVariable Long id, @PathVariable Long phaseId) {
        var action = actionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Action not found"));
        if(action.getPhase().getId() != phaseId){
            throw new RecordNotFoundException("Action not found");
        }
        actionRepository.delete(action);
        return ResponseEntity.ok().build();
    }
}
