package com.thalgrim.warhammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Race.
 */
@Entity
@Table(name = "race")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Race implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cc")
    private Integer cc;

    @Column(name = "ct")
    private Integer ct;

    @Column(name = "fo")
    private Integer fo;

    @Column(name = "en")
    private Integer en;

    @Column(name = "ini")
    private Integer ini;

    @Column(name = "ag")
    private Integer ag;

    @Column(name = "dex")
    private Integer dex;

    @Column(name = "inte")
    private Integer inte;

    @Column(name = "fm")
    private Integer fm;

    @Column(name = "soc")
    private Integer soc;

    @Column(name = "destin")
    private Integer destin;

    @Column(name = "resilience")
    private Integer resilience;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "mouvement")
    private Integer mouvement;

    @Column(name = "points_suplementaires")
    private Integer pointsSuplementaires;

    @Column(name = "blessures")
    private Integer blessures;

    @Column(name = "nom")
    private String nom;

    @OneToOne(mappedBy = "race")
    @JsonIgnore
    private Personnage personnage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCc() {
        return cc;
    }

    public Race cc(Integer cc) {
        this.cc = cc;
        return this;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public Integer getCt() {
        return ct;
    }

    public Race ct(Integer ct) {
        this.ct = ct;
        return this;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public Integer getFo() {
        return fo;
    }

    public Race fo(Integer fo) {
        this.fo = fo;
        return this;
    }

    public void setFo(Integer fo) {
        this.fo = fo;
    }

    public Integer getEn() {
        return en;
    }

    public Race en(Integer en) {
        this.en = en;
        return this;
    }

    public void setEn(Integer en) {
        this.en = en;
    }

    public Integer getIni() {
        return ini;
    }

    public Race ini(Integer ini) {
        this.ini = ini;
        return this;
    }

    public void setIni(Integer ini) {
        this.ini = ini;
    }

    public Integer getAg() {
        return ag;
    }

    public Race ag(Integer ag) {
        this.ag = ag;
        return this;
    }

    public void setAg(Integer ag) {
        this.ag = ag;
    }

    public Integer getDex() {
        return dex;
    }

    public Race dex(Integer dex) {
        this.dex = dex;
        return this;
    }

    public void setDex(Integer dex) {
        this.dex = dex;
    }

    public Integer getInte() {
        return inte;
    }

    public Race inte(Integer inte) {
        this.inte = inte;
        return this;
    }

    public void setInte(Integer inte) {
        this.inte = inte;
    }

    public Integer getFm() {
        return fm;
    }

    public Race fm(Integer fm) {
        this.fm = fm;
        return this;
    }

    public void setFm(Integer fm) {
        this.fm = fm;
    }

    public Integer getSoc() {
        return soc;
    }

    public Race soc(Integer soc) {
        this.soc = soc;
        return this;
    }

    public void setSoc(Integer soc) {
        this.soc = soc;
    }

    public Integer getDestin() {
        return destin;
    }

    public Race destin(Integer destin) {
        this.destin = destin;
        return this;
    }

    public void setDestin(Integer destin) {
        this.destin = destin;
    }

    public Integer getResilience() {
        return resilience;
    }

    public Race resilience(Integer resilience) {
        this.resilience = resilience;
        return this;
    }

    public void setResilience(Integer resilience) {
        this.resilience = resilience;
    }

    public Integer getExperience() {
        return experience;
    }

    public Race experience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getMouvement() {
        return mouvement;
    }

    public Race mouvement(Integer mouvement) {
        this.mouvement = mouvement;
        return this;
    }

    public void setMouvement(Integer mouvement) {
        this.mouvement = mouvement;
    }

    public Integer getPointsSuplementaires() {
        return pointsSuplementaires;
    }

    public Race pointsSuplementaires(Integer pointsSuplementaires) {
        this.pointsSuplementaires = pointsSuplementaires;
        return this;
    }

    public void setPointsSuplementaires(Integer pointsSuplementaires) {
        this.pointsSuplementaires = pointsSuplementaires;
    }

    public Integer getBlessures() {
        return blessures;
    }

    public Race blessures(Integer blessures) {
        this.blessures = blessures;
        return this;
    }

    public void setBlessures(Integer blessures) {
        this.blessures = blessures;
    }

    public String getNom() {
        return nom;
    }

    public Race nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Race personnage(Personnage personnage) {
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
        if (!(o instanceof Race)) {
            return false;
        }
        return id != null && id.equals(((Race) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Race{" +
            "id=" + getId() +
            ", cc=" + getCc() +
            ", ct=" + getCt() +
            ", fo=" + getFo() +
            ", en=" + getEn() +
            ", ini=" + getIni() +
            ", ag=" + getAg() +
            ", dex=" + getDex() +
            ", inte=" + getInte() +
            ", fm=" + getFm() +
            ", soc=" + getSoc() +
            ", destin=" + getDestin() +
            ", resilience=" + getResilience() +
            ", experience=" + getExperience() +
            ", mouvement=" + getMouvement() +
            ", pointsSuplementaires=" + getPointsSuplementaires() +
            ", blessures=" + getBlessures() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
