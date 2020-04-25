package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Personnage;
import com.thalgrim.warhammer.repository.PersonnageRepository;
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
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Personnage}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonnageResource {

    private final Logger log = LoggerFactory.getLogger(PersonnageResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterPersonnage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonnageRepository personnageRepository;

    public PersonnageResource(PersonnageRepository personnageRepository) {
        this.personnageRepository = personnageRepository;
    }

    /**
     * {@code POST  /personnages} : Create a new personnage.
     *
     * @param personnage the personnage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personnage, or with status {@code 400 (Bad Request)} if the personnage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personnages")
    public ResponseEntity<Personnage> createPersonnage(@RequestBody Personnage personnage) throws URISyntaxException {
        log.debug("REST request to save Personnage : {}", personnage);
        if (personnage.getId() != null) {
            throw new BadRequestAlertException("A new personnage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Personnage result = personnageRepository.save(personnage);
        return ResponseEntity.created(new URI("/api/personnages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personnages} : Updates an existing personnage.
     *
     * @param personnage the personnage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personnage,
     * or with status {@code 400 (Bad Request)} if the personnage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personnage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personnages")
    public ResponseEntity<Personnage> updatePersonnage(@RequestBody Personnage personnage) throws URISyntaxException {
        log.debug("REST request to update Personnage : {}", personnage);
        if (personnage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Personnage result = personnageRepository.save(personnage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personnage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personnages} : get all the personnages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnages in body.
     */
    @GetMapping("/personnages")
    public List<Personnage> getAllPersonnages() {
        log.debug("REST request to get all Personnages");
        return personnageRepository.findAll();
    }

    /**
     * {@code GET  /personnages/:id} : get the "id" personnage.
     *
     * @param id the id of the personnage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personnage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personnages/{id}")
    public ResponseEntity<Personnage> getPersonnage(@PathVariable Long id) {
        log.debug("REST request to get Personnage : {}", id);
        Optional<Personnage> personnage = personnageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personnage);
    }

    /**
     * {@code DELETE  /personnages/:id} : delete the "id" personnage.
     *
     * @param id the id of the personnage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personnages/{id}")
    public ResponseEntity<Void> deletePersonnage(@PathVariable Long id) {
        log.debug("REST request to delete Personnage : {}", id);
        personnageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
