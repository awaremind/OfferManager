package com.tivanov.offermanager.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.tivanov.offermanager.domain.model.offer.Offer;

@NoRepositoryBean
public interface BaseRepository extends JpaRepository<Offer, Long> {
}
