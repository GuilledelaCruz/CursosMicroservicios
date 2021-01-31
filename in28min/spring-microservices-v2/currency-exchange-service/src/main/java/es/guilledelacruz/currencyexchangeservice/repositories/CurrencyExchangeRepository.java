package es.guilledelacruz.currencyexchangeservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.guilledelacruz.currencyexchangeservice.beans.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
