package com.thalgrim.warhammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Competence.
 */
@Entity
@Table(name = "competence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "caracteristique")
    private String caracteristique;

    @Column(name = "de_base")
    private Boolean deBase;

    @Column(name = "groupee")
    private Boolean groupee;

    @ManyToOne
    @JsonIgnoreProperties("competences")
    private Echelon echelon;

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

    public Competence nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCaracteristique() {
        return caracteristique;
    }

    public Competence caracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
        return this;
    }

    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public Boolean isDeBase() {
        return deBase;
    }

    public Competence deBase(Boolean deBase) {
        this.deBase = deBase;
        return this;
    }

    public void setDeBase(Boolean deBase) {
        this.deBase = deBase;
    }

    public Boolean isGroupee() {
        return groupee;
    }

    public Competence groupee(Boolean groupee) {
        this.groupee = groupee;
        return this;
    }

    public void setGroupee(Boolean groupee) {
        this.groupee = groupee;
    }

    public Echelon getEchelon() {
        return echelon;
    }

    public Competence echelon(Echelon echelon) {
        this.echelon = echelon;
        return this;
    }

    public void setEchelon(Echelon echelon) {
        this.echelon = echelon;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Competence)) {
            return false;
        }
        return id != null && id.equals(((Competence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Competence{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", caracteristique='" + getCaracteristique() + "'" +
            ", deBase='" + isDeBase() + "'" +
            ", groupee='" + isGroupee() + "'" +
            "}";
    }
}
