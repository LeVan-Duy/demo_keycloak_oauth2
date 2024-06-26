package org.example.demo_keycloak.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo_keycloak.entity.Menu;
import org.example.demo_keycloak.repository.MenuRepository;
import org.example.demo_keycloak.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<Menu> getMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public Menu update(Menu menu) {
        return null;
    }

    @Override
    public Menu delete(Menu menu) {
        return null;
    }

    @Override
    public Menu findById(Long id) {
        return null;
    }
}
