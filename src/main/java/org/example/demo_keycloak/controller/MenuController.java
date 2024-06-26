package org.example.demo_keycloak.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo_keycloak.entity.Menu;
import org.example.demo_keycloak.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<Object> getMenu() {
        return ResponseEntity.ok(menuService.getMenu());
    }
}
