package com.example.tradeCenter.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tradeId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tradeDay;

	@JsonIgnoreProperties("trade")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trade")
	private List<Player> players;

	@ManyToOne
	@JsonIgnoreProperties("trade")
	@JoinColumn(name = "teamid")
	private Team team;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Date getTradeDay() {
		return tradeDay;
	}

	public void setTradeDay(Date tradeDay) {
		this.tradeDay = tradeDay;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
