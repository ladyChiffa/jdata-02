package com.example.jdata_02.controller;

import com.example.jdata_02.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository personRepository;

    @GetMapping("products/fetch-product")
    public ResponseEntity<String> getProductById(@RequestParam("name") String name) {
        return ResponseEntity.ok(personRepository.getProductName(name));
    }
}

