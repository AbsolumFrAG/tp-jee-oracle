package com.jevendstout.api.entity;

import jakarta.persistence.*;

@Entity
public class LigneDePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Panier panier;

    @ManyToOne
    private Article article;

    private int quantite;
    private double prixUnitaire;

    // Getters

    public Long getId() { return id; }
    public Panier getPanier() { return panier; }
    public Article getArticle() { return article; }
    public int getQuantite() { return quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
