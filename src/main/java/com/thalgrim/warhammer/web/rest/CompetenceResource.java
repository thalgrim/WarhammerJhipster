package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Competence;
import com.thalgrim.warhammer.repository.CompetenceRepository;
import com.thalgrim.warhammer.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Competence}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CompetenceResource {

    private final Logger log = LoggerFactory.getLogger(CompetenceResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterCompetence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompetenceRepository competenceRepository;

    public CompetenceResource(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    /**
     * {@code POST  /competences} : Create a new competence.
     *
     * @param competence the competence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competence, or with status {@code 400 (Bad Request)} if the competence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/competences")
    public ResponseEntity<Competence> createCompetence(@RequestBody Competence competence) throws URISyntaxException {
        log.debug("REST request to save Competence : {}", competence);
        if (competence.getId() != null) {
            throw new BadRequestAlertException("A new competence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Competence result = competenceRepository.save(competence);
        return ResponseEntity.created(new URI("/api/competences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /competences} : Updates an existing competence.
     *
     * @param competence the competence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competence,
     * or with status {@code 400 (Bad Request)} if the competence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the competence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/competences")
    public ResponseEntity<Competence> updateCompetence(@RequestBody Competence competence) throws URISyntaxException {
        log.debug("REST request to update Competence : {}", competence);
        if (competence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Competence result = competenceRepository.save(competence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, competence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /competences} : get all the competences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of competences in body.
     */
    @GetMapping("/competences")
    public List<Competence> getAllCompetences() {
        log.debug("REST request to get all Competences");
        return competenceRepository.findAll();
    }

    /**
     * {@code GET  /competences/:id} : get the "id" competence.
     *
     * @param id the id of the competence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the competence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/competences/{id}")
    public ResponseEntity<Competence> getCompetence(@PathVariable Long id) {
        log.debug("REST request to get Competence : {}", id);
        Optional<Competence> competence = competenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(competence);
    }

    /**
     * {@code DELETE  /competences/:id} : delete the "id" competence.
     *
     * @param id the id of the competence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/competences/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        log.debug("REST request to delete Competence : {}", id);
        competenceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
