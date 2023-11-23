package com.jevendstout.api.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Commercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    @ManyToMany
    private Set<Categorie> categories;

    // Getters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public Set<Categorie> getCategories() { return categories; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }
    public void setCategories(Set<Categorie> categories) { this.categories = categories; }
}
