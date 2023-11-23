package com.jevendstout.api.entity;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    // Getters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public double getPrix() { return prix; }
    public Categorie getCategorie() { return categorie; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
}
