package com.thalgrim.warhammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Possession.
 */
@Entity
@Table(name = "possession")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Possession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JsonIgnoreProperties("possessions")
    private Echelon echelon;

    @ManyToOne
    @JsonIgnoreProperties("possessions")
    private Classe classe;

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

    public Possession nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Echelon getEchelon() {
        return echelon;
    }

    public Possession echelon(Echelon echelon) {
        this.echelon = echelon;
        return this;
    }

    public void setEchelon(Echelon echelon) {
        this.echelon = echelon;
    }

    public Classe getClasse() {
        return classe;
    }

    public Possession classe(Classe classe) {
        this.classe = classe;
        return this;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Possession)) {
            return false;
        }
        return id != null && id.equals(((Possession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Possession{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
