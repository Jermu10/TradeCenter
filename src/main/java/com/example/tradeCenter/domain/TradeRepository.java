package com.example.tradeCenter.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, Long> {
	List<Trade> findByTradeId(Long tradeId);
}
