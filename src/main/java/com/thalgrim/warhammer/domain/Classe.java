package com.thalgrim.warhammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Classe.
 */
@Entity
@Table(name = "classe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "classe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Possession> possessions = new HashSet<>();

    @OneToOne(mappedBy = "classe")
    @JsonIgnore
    private Carriere carriere;

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

    public Classe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Possession> getPossessions() {
        return possessions;
    }

    public Classe possessions(Set<Possession> possessions) {
        this.possessions = possessions;
        return this;
    }

    public Classe addPossession(Possession possession) {
        this.possessions.add(possession);
        possession.setClasse(this);
        return this;
    }

    public Classe removePossession(Possession possession) {
        this.possessions.remove(possession);
        possession.setClasse(null);
        return this;
    }

    public void setPossessions(Set<Possession> possessions) {
        this.possessions = possessions;
    }

    public Carriere getCarriere() {
        return carriere;
    }

    public Classe carriere(Carriere carriere) {
        this.carriere = carriere;
        return this;
    }

    public void setCarriere(Carriere carriere) {
        this.carriere = carriere;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Classe)) {
            return false;
        }
        return id != null && id.equals(((Classe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Classe{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
