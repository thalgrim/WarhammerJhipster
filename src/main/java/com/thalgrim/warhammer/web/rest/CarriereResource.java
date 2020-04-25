package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Carriere;
import com.thalgrim.warhammer.repository.CarriereRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Carriere}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CarriereResource {

    private final Logger log = LoggerFactory.getLogger(CarriereResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterCarriere";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarriereRepository carriereRepository;

    public CarriereResource(CarriereRepository carriereRepository) {
        this.carriereRepository = carriereRepository;
    }

    /**
     * {@code POST  /carrieres} : Create a new carriere.
     *
     * @param carriere the carriere to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carriere, or with status {@code 400 (Bad Request)} if the carriere has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carrieres")
    public ResponseEntity<Carriere> createCarriere(@RequestBody Carriere carriere) throws URISyntaxException {
        log.debug("REST request to save Carriere : {}", carriere);
        if (carriere.getId() != null) {
            throw new BadRequestAlertException("A new carriere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Carriere result = carriereRepository.save(carriere);
        return ResponseEntity.created(new URI("/api/carrieres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carrieres} : Updates an existing carriere.
     *
     * @param carriere the carriere to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carriere,
     * or with status {@code 400 (Bad Request)} if the carriere is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carriere couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carrieres")
    public ResponseEntity<Carriere> updateCarriere(@RequestBody Carriere carriere) throws URISyntaxException {
        log.debug("REST request to update Carriere : {}", carriere);
        if (carriere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Carriere result = carriereRepository.save(carriere);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, carriere.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /carrieres} : get all the carrieres.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carrieres in body.
     */
    @GetMapping("/carrieres")
    public List<Carriere> getAllCarrieres(@RequestParam(required = false) String filter) {
        if ("personnage-is-null".equals(filter)) {
            log.debug("REST request to get all Carrieres where personnage is null");
            return StreamSupport
                .stream(carriereRepository.findAll().spliterator(), false)
                .filter(carriere -> carriere.getPersonnage() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Carrieres");
        return carriereRepository.findAll();
    }

    /**
     * {@code GET  /carrieres/:id} : get the "id" carriere.
     *
     * @param id the id of the carriere to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carriere, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carrieres/{id}")
    public ResponseEntity<Carriere> getCarriere(@PathVariable Long id) {
        log.debug("REST request to get Carriere : {}", id);
        Optional<Carriere> carriere = carriereRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(carriere);
    }

    /**
     * {@code DELETE  /carrieres/:id} : delete the "id" carriere.
     *
     * @param id the id of the carriere to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carrieres/{id}")
    public ResponseEntity<Void> deleteCarriere(@PathVariable Long id) {
        log.debug("REST request to delete Carriere : {}", id);
        carriereRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
