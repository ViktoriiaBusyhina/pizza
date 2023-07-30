package com.example.pizza.entity;

import jakarta.persistence.*;
import lombok.Data;
/**
 * Entity class representing a user authority in the system.
 * User authorities are stored in the "authorities" table in the database.
 */

@Data
@Entity
@Table(name = "authorities")
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
