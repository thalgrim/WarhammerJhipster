package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Possession;
import com.thalgrim.warhammer.repository.PossessionRepository;
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
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Possession}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PossessionResource {

    private final Logger log = LoggerFactory.getLogger(PossessionResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterPossession";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PossessionRepository possessionRepository;

    public PossessionResource(PossessionRepository possessionRepository) {
        this.possessionRepository = possessionRepository;
    }

    /**
     * {@code POST  /possessions} : Create a new possession.
     *
     * @param possession the possession to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new possession, or with status {@code 400 (Bad Request)} if the possession has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/possessions")
    public ResponseEntity<Possession> createPossession(@RequestBody Possession possession) throws URISyntaxException {
        log.debug("REST request to save Possession : {}", possession);
        if (possession.getId() != null) {
            throw new BadRequestAlertException("A new possession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Possession result = possessionRepository.save(possession);
        return ResponseEntity.created(new URI("/api/possessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /possessions} : Updates an existing possession.
     *
     * @param possession the possession to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated possession,
     * or with status {@code 400 (Bad Request)} if the possession is not valid,
     * or with status {@code 500 (Internal Server Error)} if the possession couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/possessions")
    public ResponseEntity<Possession> updatePossession(@RequestBody Possession possession) throws URISyntaxException {
        log.debug("REST request to update Possession : {}", possession);
        if (possession.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Possession result = possessionRepository.save(possession);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, possession.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /possessions} : get all the possessions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of possessions in body.
     */
    @GetMapping("/possessions")
    public List<Possession> getAllPossessions() {
        log.debug("REST request to get all Possessions");
        return possessionRepository.findAll();
    }

    /**
     * {@code GET  /possessions/:id} : get the "id" possession.
     *
     * @param id the id of the possession to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the possession, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/possessions/{id}")
    public ResponseEntity<Possession> getPossession(@PathVariable Long id) {
        log.debug("REST request to get Possession : {}", id);
        Optional<Possession> possession = possessionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(possession);
    }

    /**
     * {@code DELETE  /possessions/:id} : delete the "id" possession.
     *
     * @param id the id of the possession to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/possessions/{id}")
    public ResponseEntity<Void> deletePossession(@PathVariable Long id) {
        log.debug("REST request to delete Possession : {}", id);
        possessionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
