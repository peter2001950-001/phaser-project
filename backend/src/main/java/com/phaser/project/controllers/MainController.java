package com.phaser.project.controllers;

import com.phaser.project.domain.dto.PhaseDto;
import com.phaser.project.domain.dto.PhaseRequest;
import com.phaser.project.domain.mapper.Mapper;
import com.phaser.project.entities.PhaseEntity;
import com.phaser.project.process.PhaseExecutor;
import com.phaser.project.repositories.ActionRepository;
import com.phaser.project.repositories.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final PhaseRepository phaseRepository;
    private final ActionRepository actionRepository;
    @PostMapping("run")
    public ResponseEntity<?> RunProcess() {

        Phaser phaser = new Phaser(1);
        var phases = phaseRepository.findAllOrderByPhaseOrder();
        var phaseExecutors = new ArrayList<PhaseExecutor>();
        var executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < phases.size(); i++) {
            var actions = actionRepository.findAllByPhase_Id(phases.get(i).getId());
            phaseExecutors.add(new PhaseExecutor(actions, phaser, executorService));
        }
        for (int i = 0; i < phaseExecutors.size(); i++) {
            phaseExecutors.get(i).start();
        }

        return ResponseEntity.ok().build();
    }
}
