package org.example.demo_keycloak.service;

import org.example.demo_keycloak.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenu();

    Menu save(Menu menu);

    Menu update(Menu menu);

    Menu delete(Menu menu);

    Menu findById(Long id);
}
