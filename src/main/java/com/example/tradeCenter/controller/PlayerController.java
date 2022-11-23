package com.example.tradeCenter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tradeCenter.domain.Player;
import com.example.tradeCenter.domain.PlayerRepository;
import com.example.tradeCenter.domain.TeamRepository;

@Controller
public class PlayerController {

	@Autowired
	private PlayerRepository prepository;
	@Autowired
	private TeamRepository trepository;

	@GetMapping("/playerlist")
	public String playerList(Model model) {
		model.addAttribute("players", prepository.findAll());
		return "playerlist";
	}

	@GetMapping("/addplayer")
	public String addPlayer(Model model) {
		model.addAttribute("player", new Player());
		model.addAttribute("teams", trepository.findAll());
		return "addplayer";
	}

	@GetMapping("/playersByTeam/{team}")
	public String playersByTeam(@PathVariable("teamid") Long teamid, Model model) {
		model.addAttribute("players", prepository.findById(teamid));
		return "/playerlist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deletePlayer(@PathVariable("id") Long playerId, Model model) {
		prepository.deleteById(playerId);
		return "redirect:../playerlist";
	}

	@GetMapping("/edit/{id}")
	public String editPlayer(@PathVariable("id") Long playerId, Long teamid, Model model) {
		model.addAttribute("player", prepository.findById(playerId));
		model.addAttribute("teams", trepository.findAll());
		return "editplayer";
	}

	@PostMapping("/saveplayer")
	public String save(Player player) {
		prepository.save(player);
		return "redirect:playerlist";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	// RESTful services

	@GetMapping("/players")
	public @ResponseBody List<Player> bookListRest() {
		return (List<Player>) prepository.findAll();
	}

	@GetMapping(value = "/player/{id}")
	public @ResponseBody Optional<Player> findBookRest(@PathVariable("id") Long playerId) {
		return prepository.findById(playerId);
	}

}
