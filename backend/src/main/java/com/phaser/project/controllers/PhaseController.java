package com.phaser.project.controllers;

import com.phaser.project.domain.dto.BulkUpdateOrderRequest;
import com.phaser.project.domain.dto.PhaseDto;
import com.phaser.project.domain.dto.PhaseRequest;
import com.phaser.project.domain.exceptions.RecordNotFoundException;
import com.phaser.project.domain.mapper.Mapper;
import com.phaser.project.entities.PhaseEntity;
import com.phaser.project.repositories.ActionRepository;
import com.phaser.project.repositories.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/phase")
@RequiredArgsConstructor
public class PhaseController {
    private final PhaseRepository phaseRepository;
    private final ActionRepository actionRepository;

    @GetMapping("{phaseId}")
    public ResponseEntity<?> GetPhase(@PathVariable Long phaseId) {
        var phase = phaseRepository.findById(phaseId).orElseThrow(() -> new RecordNotFoundException(""));

        return ResponseEntity.ok(Mapper.map(phase, PhaseDto.class));
    }

    @GetMapping()
    public ResponseEntity<?> GetPhases() {
        var phases = phaseRepository.findAllOrderByPhaseOrder();
        var mappedPhases = Mapper.mapList(phases, PhaseDto.class);

        return ResponseEntity.ok(mappedPhases);
    }

    @PostMapping()
    public ResponseEntity<?> CreatePhase(@RequestBody PhaseRequest request) {
        Integer max = phaseRepository.maxPhaseOrder();

        var phase = Mapper.map(request, PhaseEntity.class);
        phase.setPhaseOrder(max==null? 0: max+1);
        phase = phaseRepository.save(phase);
        var phaseDto = Mapper.map(phase, PhaseDto.class);
        return ResponseEntity.ok(phaseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdatePhase(@RequestBody PhaseRequest request, @PathVariable Long id) {
        var phase = phaseRepository.findById(id);
        if (!phase.isPresent()) throw new RecordNotFoundException("Phase id not found");
        var phaseValue = phase.get();
        var orderValue = phaseValue.getPhaseOrder();
        Mapper.map(request, phaseValue);
        phaseValue.setPhaseOrder(orderValue);
        phaseRepository.save(phaseValue);

        var phaseDto = Mapper.map(phaseValue, PhaseDto.class);
        return ResponseEntity.ok(phaseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeletePhase(@PathVariable Long id) {
        var phase = phaseRepository.findById(id);
        if (!phase.isPresent()) throw new RecordNotFoundException("Phase id not found");
        phaseRepository.delete(phase.get());
        return ResponseEntity.ok().build();
    }
    @PutMapping("ordering")
    public ResponseEntity<?> BulkUpdateOrder(@RequestBody BulkUpdateOrderRequest request){
        for (int i = 0; i < request.getOrderItems().size(); i++) {
            var phase = phaseRepository.findById(request.getOrderItems().get(i).getId());
            if (!phase.isPresent()) throw new RecordNotFoundException("Phase " + request.getOrderItems().get(i).getId() + " not found");
            var phaseValue = phase.get();
            phaseValue.setPhaseOrder(request.getOrderItems().get(i).getOrder());
            phaseRepository.save(phaseValue);
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("{phaseId}/{actionId}")
    public ResponseEntity<?> AddActionToPhase(@PathVariable Long phaseId, @PathVariable Long actionId){
        var phase = phaseRepository.findById(phaseId);
        if(!phase.isPresent()) throw new RecordNotFoundException("Phase id not found");
        var action = actionRepository.findById(actionId);
        if(!action.isPresent()) throw new RecordNotFoundException("Action id not found");
        var phaseEntity = phase.get();
        var actionEntity = action.get();

        phaseEntity.getActions().add(actionEntity);
        phaseRepository.save(phaseEntity);
        return ResponseEntity.ok().build();
    }
}
