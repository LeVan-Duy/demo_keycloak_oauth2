package org.example.demo_keycloak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 250, nullable = false,name = "name")
    private String name;


}
