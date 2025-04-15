package org.example.api;

import org.example.model.SimulationRequest;
import org.example.model.SimulationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DeplacementController {

    private final DeplacementService deplacementService;

    @Autowired
    public DeplacementController(DeplacementService deplacementService) {
        this.deplacementService = deplacementService;
    }

    @PostMapping("/simuler")
    public ResponseEntity<SimulationResponse> simulerDeplacement(@RequestBody SimulationRequest request) throws IOException {
        SimulationResponse response = deplacementService.simulerDeplacement(
                request.getCartePath(),
                request.getInitialX(),
                request.getInitialY(),
                request.getDeplacements()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}