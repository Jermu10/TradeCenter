package com.example.tradeCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tradeCenter.domain.Trade;
import com.example.tradeCenter.domain.TradeRepository;

@Controller
public class TradeController {

	@Autowired
	private TradeRepository trdrepository;

	@GetMapping("/tradelist")
	public String tradeList(Model model) {
		model.addAttribute("trades", trdrepository.findAll());
		return "tradelist";
	}

	@GetMapping("/addtrade")
	public String addTrade(Model model) {
		model.addAttribute("trade", new Trade());
		return "addtrade";
	}

	@PostMapping("/savetrade")
	public String saveteam(Trade trade) {
		trdrepository.save(trade);
		return "redirect:tradelist";
	}
}
