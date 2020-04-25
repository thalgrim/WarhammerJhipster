package com.thalgrim.warhammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Talent.
 */
@Entity
@Table(name = "talent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Talent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "maxi")
    private Integer maxi;

    @Column(name = "test")
    private String test;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("talents")
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

    public Talent nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getMaxi() {
        return maxi;
    }

    public Talent maxi(Integer maxi) {
        this.maxi = maxi;
        return this;
    }

    public void setMaxi(Integer maxi) {
        this.maxi = maxi;
    }

    public String getTest() {
        return test;
    }

    public Talent test(String test) {
        this.test = test;
        return this;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getDescription() {
        return description;
    }

    public Talent description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Echelon getEchelon() {
        return echelon;
    }

    public Talent echelon(Echelon echelon) {
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
        if (!(o instanceof Talent)) {
            return false;
        }
        return id != null && id.equals(((Talent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Talent{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", maxi=" + getMaxi() +
            ", test='" + getTest() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
