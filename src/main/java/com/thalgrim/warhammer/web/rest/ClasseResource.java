package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Classe;
import com.thalgrim.warhammer.repository.ClasseRepository;
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
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Classe}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClasseResource {

    private final Logger log = LoggerFactory.getLogger(ClasseResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterClasse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClasseRepository classeRepository;

    public ClasseResource(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    /**
     * {@code POST  /classes} : Create a new classe.
     *
     * @param classe the classe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classe, or with status {@code 400 (Bad Request)} if the classe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classes")
    public ResponseEntity<Classe> createClasse(@RequestBody Classe classe) throws URISyntaxException {
        log.debug("REST request to save Classe : {}", classe);
        if (classe.getId() != null) {
            throw new BadRequestAlertException("A new classe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Classe result = classeRepository.save(classe);
        return ResponseEntity.created(new URI("/api/classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /classes} : Updates an existing classe.
     *
     * @param classe the classe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classe,
     * or with status {@code 400 (Bad Request)} if the classe is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classes")
    public ResponseEntity<Classe> updateClasse(@RequestBody Classe classe) throws URISyntaxException {
        log.debug("REST request to update Classe : {}", classe);
        if (classe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Classe result = classeRepository.save(classe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, classe.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /classes} : get all the classes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classes in body.
     */
    @GetMapping("/classes")
    public List<Classe> getAllClasses(@RequestParam(required = false) String filter) {
        if ("carriere-is-null".equals(filter)) {
            log.debug("REST request to get all Classes where carriere is null");
            return StreamSupport
                .stream(classeRepository.findAll().spliterator(), false)
                .filter(classe -> classe.getCarriere() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Classes");
        return classeRepository.findAll();
    }

    /**
     * {@code GET  /classes/:id} : get the "id" classe.
     *
     * @param id the id of the classe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classes/{id}")
    public ResponseEntity<Classe> getClasse(@PathVariable Long id) {
        log.debug("REST request to get Classe : {}", id);
        Optional<Classe> classe = classeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(classe);
    }

    /**
     * {@code DELETE  /classes/:id} : delete the "id" classe.
     *
     * @param id the id of the classe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        log.debug("REST request to delete Classe : {}", id);
        classeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
