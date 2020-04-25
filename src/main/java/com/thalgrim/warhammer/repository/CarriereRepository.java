package com.thalgrim.warhammer.repository;

import com.thalgrim.warhammer.domain.Carriere;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Carriere entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarriereRepository extends JpaRepository<Carriere, Long> {
}
