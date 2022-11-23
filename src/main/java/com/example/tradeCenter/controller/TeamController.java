package com.example.tradeCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tradeCenter.domain.Team;
import com.example.tradeCenter.domain.TeamRepository;

@Controller
public class TeamController {

	@Autowired
	private TeamRepository trepository;

	@GetMapping("/teamlist")
	public String teamList(Model model) {
		model.addAttribute("teams", trepository.findAll());
		return "teamlist";
	}

	@GetMapping("/addteam")
	public String addTeam(Model model) {
		model.addAttribute("team", new Team());
		return "addteam";
	}

	@PostMapping("/saveteam")
	public String saveteam(Team team) {
		trepository.save(team);
		return "redirect:teamlist";
	}

	@GetMapping("/deleteteam/{teamid}")
	public String deleteTeam(@PathVariable("id") Long teamId, Model model) {
		trepository.deleteById(teamId);
		return "redirect:../teamlist";
	}
}
