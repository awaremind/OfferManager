package com.tivanov.offermanager.domain.model.enumerated;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

class OfferStatusTest {

	@Test
	void testOfferStatus_shouldContainTheFollowingValues() {
		assertThat(OfferStatus.EXPIRED, is(notNullValue()));
		assertThat(OfferStatus.VALID, is(notNullValue()));
	}

}
