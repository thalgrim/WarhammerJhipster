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
 * A Echelon.
 */
@Entity
@Table(name = "echelon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Echelon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "niveau")
    private Integer niveau;

    @Column(name = "cc")
    private Boolean cc;

    @Column(name = "ct")
    private Boolean ct;

    @Column(name = "fo")
    private Boolean fo;

    @Column(name = "en")
    private Boolean en;

    @Column(name = "ini")
    private Boolean ini;

    @Column(name = "ag")
    private Boolean ag;

    @Column(name = "dex")
    private Boolean dex;

    @Column(name = "inte")
    private Boolean inte;

    @Column(name = "fm")
    private Boolean fm;

    @Column(name = "soc")
    private Boolean soc;

    @Column(name = "statut")
    private String statut;

    @OneToMany(mappedBy = "echelon")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Competence> competences = new HashSet<>();

    @OneToMany(mappedBy = "echelon")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Talent> talents = new HashSet<>();

    @OneToMany(mappedBy = "echelon")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Possession> possessions = new HashSet<>();

    @OneToOne(mappedBy = "echelon")
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

    public Echelon nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public Echelon niveau(Integer niveau) {
        this.niveau = niveau;
        return this;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Boolean isCc() {
        return cc;
    }

    public Echelon cc(Boolean cc) {
        this.cc = cc;
        return this;
    }

    public void setCc(Boolean cc) {
        this.cc = cc;
    }

    public Boolean isCt() {
        return ct;
    }

    public Echelon ct(Boolean ct) {
        this.ct = ct;
        return this;
    }

    public void setCt(Boolean ct) {
        this.ct = ct;
    }

    public Boolean isFo() {
        return fo;
    }

    public Echelon fo(Boolean fo) {
        this.fo = fo;
        return this;
    }

    public void setFo(Boolean fo) {
        this.fo = fo;
    }

    public Boolean isEn() {
        return en;
    }

    public Echelon en(Boolean en) {
        this.en = en;
        return this;
    }

    public void setEn(Boolean en) {
        this.en = en;
    }

    public Boolean isIni() {
        return ini;
    }

    public Echelon ini(Boolean ini) {
        this.ini = ini;
        return this;
    }

    public void setIni(Boolean ini) {
        this.ini = ini;
    }

    public Boolean isAg() {
        return ag;
    }

    public Echelon ag(Boolean ag) {
        this.ag = ag;
        return this;
    }

    public void setAg(Boolean ag) {
        this.ag = ag;
    }

    public Boolean isDex() {
        return dex;
    }

    public Echelon dex(Boolean dex) {
        this.dex = dex;
        return this;
    }

    public void setDex(Boolean dex) {
        this.dex = dex;
    }

    public Boolean isInte() {
        return inte;
    }

    public Echelon inte(Boolean inte) {
        this.inte = inte;
        return this;
    }

    public void setInte(Boolean inte) {
        this.inte = inte;
    }

    public Boolean isFm() {
        return fm;
    }

    public Echelon fm(Boolean fm) {
        this.fm = fm;
        return this;
    }

    public void setFm(Boolean fm) {
        this.fm = fm;
    }

    public Boolean isSoc() {
        return soc;
    }

    public Echelon soc(Boolean soc) {
        this.soc = soc;
        return this;
    }

    public void setSoc(Boolean soc) {
        this.soc = soc;
    }

    public String getStatut() {
        return statut;
    }

    public Echelon statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public Echelon competences(Set<Competence> competences) {
        this.competences = competences;
        return this;
    }

    public Echelon addCompetence(Competence competence) {
        this.competences.add(competence);
        competence.setEchelon(this);
        return this;
    }

    public Echelon removeCompetence(Competence competence) {
        this.competences.remove(competence);
        competence.setEchelon(null);
        return this;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }

    public Set<Talent> getTalents() {
        return talents;
    }

    public Echelon talents(Set<Talent> talents) {
        this.talents = talents;
        return this;
    }

    public Echelon addTalent(Talent talent) {
        this.talents.add(talent);
        talent.setEchelon(this);
        return this;
    }

    public Echelon removeTalent(Talent talent) {
        this.talents.remove(talent);
        talent.setEchelon(null);
        return this;
    }

    public void setTalents(Set<Talent> talents) {
        this.talents = talents;
    }

    public Set<Possession> getPossessions() {
        return possessions;
    }

    public Echelon possessions(Set<Possession> possessions) {
        this.possessions = possessions;
        return this;
    }

    public Echelon addPossession(Possession possession) {
        this.possessions.add(possession);
        possession.setEchelon(this);
        return this;
    }

    public Echelon removePossession(Possession possession) {
        this.possessions.remove(possession);
        possession.setEchelon(null);
        return this;
    }

    public void setPossessions(Set<Possession> possessions) {
        this.possessions = possessions;
    }

    public Carriere getCarriere() {
        return carriere;
    }

    public Echelon carriere(Carriere carriere) {
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
        if (!(o instanceof Echelon)) {
            return false;
        }
        return id != null && id.equals(((Echelon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Echelon{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", niveau=" + getNiveau() +
            ", cc='" + isCc() + "'" +
            ", ct='" + isCt() + "'" +
            ", fo='" + isFo() + "'" +
            ", en='" + isEn() + "'" +
            ", ini='" + isIni() + "'" +
            ", ag='" + isAg() + "'" +
            ", dex='" + isDex() + "'" +
            ", inte='" + isInte() + "'" +
            ", fm='" + isFm() + "'" +
            ", soc='" + isSoc() + "'" +
            ", statut='" + getStatut() + "'" +
            "}";
    }
}
