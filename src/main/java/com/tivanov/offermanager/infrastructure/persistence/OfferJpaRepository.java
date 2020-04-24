package com.tivanov.offermanager.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tivanov.offermanager.domain.model.offer.Offer;


@EnableJpaRepositories
public interface OfferJpaRepository extends BaseRepository {
	
	List<Offer> findByCustomer(String customer);
	
	Offer findById(long id);
	
	@Query(value = "select o "  + 
			" from offer o " + 
			" where (to_days (current_date()) - to_days(o.createdAt)) <= o.validityDays")
	List<Offer> findAllValid();
	
}
