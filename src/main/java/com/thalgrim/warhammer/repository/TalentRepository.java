package com.thalgrim.warhammer.repository;

import com.thalgrim.warhammer.domain.Talent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Talent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {
}
