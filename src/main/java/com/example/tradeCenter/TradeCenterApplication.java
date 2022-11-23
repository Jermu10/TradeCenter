package com.example.tradeCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tradeCenter.domain.Player;
import com.example.tradeCenter.domain.PlayerRepository;
import com.example.tradeCenter.domain.Team;
import com.example.tradeCenter.domain.TeamRepository;
import com.example.tradeCenter.domain.User;
import com.example.tradeCenter.domain.UserRepository;

@SpringBootApplication
public class TradeCenterApplication {

	private static final Logger log = LoggerFactory.getLogger(TradeCenterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TradeCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PlayerRepository prepository, TeamRepository trepository,
			UserRepository urepository) {
		return (args) -> {

			// Adding teams
			trepository.save(new Team("Ahkiomaan Palloahmat"));
			trepository.save(new Team("Saimaan Sinitakit"));

			// Adding players
			prepository.save(new Player("Joni Jääkiekko", "Canada", "Left", 8.9,
					trepository.findByTeamName("Saimaan Sinitakit").get(0)));

			prepository.save(new Player("Luigi Lemiux", "Italy", "Right", 6.9,
					trepository.findByTeamName("Ahkiomaan Palloahmat").get(0)));

			// Adding users
			urepository.save(new User("user", "$2a$10$DEnkf9y4Vec3qxHinw841OYnVEzrBPr7eePph.7wwOtcqlcq/vrhG",
					"user@user.com", "USER"));
			urepository.save(new User("admin", "$2a$10$TLfHPGgw8GYcWu7gJYNhtefYozWMQvKsnSKmIJcch5dErJEUoQ51O",
					"admin@admin.com", "ADMIN"));

			// For eclipse console
			log.info("fetch all categories");
			for (Team team : trepository.findAll()) {

				log.info(team.toString());
			}

			log.info("fetch all books");
			for (Player player : prepository.findAll()) {

				log.info(player.toString());
			}

		};
	}

}
