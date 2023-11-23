package com.jevendstout.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneDePanier> lignesDePanier;
    private double montantTotalHT;
    private boolean estValide;

    // Getters
    public Long getId() { return id; }
    public Long getClientId() { return clientId; }
    public List<LigneDePanier> getLignesDePanier() { return lignesDePanier; }
    public double getMontantTotalHT() { return montantTotalHT; }
    public boolean getEstValide() { return estValide; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public void setLignesDePanier(List<LigneDePanier> lignesDePanier) { this.lignesDePanier = lignesDePanier; }
    public void setMontantTotalHT(double montantTotalHT) { this.montantTotalHT = montantTotalHT; }
    public void setEstValide(boolean estValide) { this.estValide = estValide; }
}
