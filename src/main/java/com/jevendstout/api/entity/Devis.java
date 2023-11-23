package com.jevendstout.api.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreation;
    private Long commercialId;
    private Long clientId;
    @OneToMany(mappedBy = "devis")
    private List<LigneDeDevis> ligneDeDevis;
    private double montantTotalHT;
    private boolean estValide;

    // Getters
    public Long getId() { return id; }
    public Date getDate() { return dateCreation; }
    public Long getCommercialId() { return commercialId; }
    public Long getClientId() { return clientId; }
    public List<LigneDeDevis> getLigneDeDevis() { return ligneDeDevis; }
    public double getMontantTotalHT() { return montantTotalHT; }
    public boolean getEstValide() { return estValide; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDate(Date date) { this.dateCreation = date; }
    public void setCommercialId(Long commercialId) { this.commercialId = commercialId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public void setLigneDeDevis(List<LigneDeDevis> ligneDeDevis) { this.ligneDeDevis = ligneDeDevis; }
    public void setMontantTotalHT(double montantTotalHT) { this.montantTotalHT = montantTotalHT; }
    public void setEstValide(boolean estValide) { this.estValide = estValide; }
}
