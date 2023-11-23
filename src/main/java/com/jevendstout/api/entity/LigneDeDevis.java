package com.jevendstout.api.entity;

import jakarta.persistence.*;

@Entity
public class LigneDeDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Devis devis;

    @ManyToOne
    private Article article;

    private int quantite;
    private double prixUnitaire;

    // Getters
    public Long getId() { return id; }
    public Devis getDevis() { return devis; }
    public Article getArticle() { return article; }
    public int getQuantite() { return quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDevis(Devis devis) { this.devis = devis; }
    public void setArticle(Article article) { this.article = article; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
}
