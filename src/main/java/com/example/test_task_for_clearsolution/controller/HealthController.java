package com.example.test_task_for_clearsolution.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> healthcheck(){
        return ResponseEntity.ok().body(Map.of("Status", "Up"));
    }
}
