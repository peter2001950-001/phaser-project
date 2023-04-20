package com.phaser.project.controllers;

import com.phaser.project.domain.dto.ActionDto;
import com.phaser.project.domain.dto.ActionRequest;
import com.phaser.project.domain.exceptions.RecordNotFoundException;
import com.phaser.project.domain.mapper.Mapper;
import com.phaser.project.entities.ActionEntity;
import com.phaser.project.repositories.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/action")
@RequiredArgsConstructor
public class ActionController {
    private final ActionRepository actionRepository;

    @GetMapping("{actionId}")
    public ResponseEntity<?> GetAction(@PathVariable Long actionId) {
        var action = actionRepository.findById(actionId).orElseThrow(() -> new RecordNotFoundException(""));

        return ResponseEntity.ok(Mapper.map(action, ActionDto.class));
    }

    @GetMapping()
    public ResponseEntity<?> GetActions() {
        var actions = actionRepository.findAll();
        var mappedActions = Mapper.mapList(actions, ActionDto.class);

        return ResponseEntity.ok(mappedActions);
    }

    @PostMapping()
    public ResponseEntity<?> CreateAction(@RequestBody ActionRequest request) {
        var action = Mapper.map(request, ActionEntity.class);


        action = actionRepository.save(action);
        var actionDto = Mapper.map(action, ActionDto.class);
        return ResponseEntity.ok(actionDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateAction(@RequestBody ActionRequest request, @PathVariable Long id) {
        var action = actionRepository.findById(id);
        if (!action.isPresent()) throw new RecordNotFoundException("Action id not found");
        Mapper.map(request, action);
        actionRepository.save(action.get());

        var actionDto = Mapper.map(action, ActionDto.class);
        return ResponseEntity.ok(actionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteAction(@PathVariable Long id) {
        var action = actionRepository.findById(id);
        if (!action.isPresent()) throw new RecordNotFoundException("Action id not found");
        actionRepository.delete(action.get());
        return ResponseEntity.ok().build();
    }
}
