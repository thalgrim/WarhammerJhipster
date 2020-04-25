package com.thalgrim.warhammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Carriere.
 */
@Entity
@Table(name = "carriere")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Carriere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToOne
    @JoinColumn(unique = true)
    private Echelon echelon;

    @OneToOne
    @JoinColumn(unique = true)
    private Classe classe;

    @OneToOne(mappedBy = "carriere")
    @JsonIgnore
    private Personnage personnage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Carriere nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Echelon getEchelon() {
        return echelon;
    }

    public Carriere echelon(Echelon echelon) {
        this.echelon = echelon;
        return this;
    }

    public void setEchelon(Echelon echelon) {
        this.echelon = echelon;
    }

    public Classe getClasse() {
        return classe;
    }

    public Carriere classe(Classe classe) {
        this.classe = classe;
        return this;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Carriere personnage(Personnage personnage) {
        this.personnage = personnage;
        return this;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carriere)) {
            return false;
        }
        return id != null && id.equals(((Carriere) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Carriere{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
