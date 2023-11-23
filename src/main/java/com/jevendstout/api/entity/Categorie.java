package com.jevendstout.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // Getters
    public Long getId() { return id; }
    public String getNom() { return nom; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
}
