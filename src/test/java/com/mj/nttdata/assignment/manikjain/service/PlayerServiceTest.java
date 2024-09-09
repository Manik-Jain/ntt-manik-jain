package com.mj.nttdata.assignment.manikjain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mj.nttdata.assignment.manikjain.entity.PlayerEntity;
import com.mj.nttdata.assignment.manikjain.enums.Country;
import com.mj.nttdata.assignment.manikjain.enums.MatchType;
import com.mj.nttdata.assignment.manikjain.model.CreatePlayer;
import com.mj.nttdata.assignment.manikjain.model.Player;
import com.mj.nttdata.assignment.manikjain.model.UpdatePlayer;
import com.mj.nttdata.assignment.manikjain.repository.PlayerRepository;

@SpringBootTest
public class PlayerServiceTest {
	
	@MockBean
	PlayerRepository playerRepository;
	
	@Autowired
	PlayerService playerService; 
	
	Set<PlayerEntity> set = new HashSet<>();
	
	@Test
	public void getPlayersTest() throws BadRequestException {
		Set<PlayerEntity> set = this.getPlayers();
		Optional<String> matchType = Optional.of(MatchType.T20.name());
		Mockito.when(playerRepository.findPlayersByMatchTypeOrderByRankAsc(Mockito.any(), Mockito.any())).thenReturn(set);
		Set<Player> response = playerService.getPlayers(matchType);
		assertNotNull(response);
	}
	
	@Test
	public void addPlayerTest() throws BadRequestException {
		
		CreatePlayer player = new CreatePlayer();
		player.setCountry(Country.AUS.name());
		player.setFirstName("A");
		player.setLastName("B");
		player.setMatchType("T20");
		
		PlayerEntity playerEntity = new PlayerEntity();
		playerEntity.setFirstName("A");
		playerEntity.setLastName("B");
		playerEntity.setMatchType(MatchType.T20);
		playerEntity.setCountry(Country.AUS);
		
		PlayerEntity playerEntitySaved = new PlayerEntity();
		playerEntitySaved.setId("8b920eff");
		playerEntitySaved.setFirstName("A");
		playerEntitySaved.setLastName("B");
		playerEntitySaved.setMatchType(MatchType.T20);
		playerEntitySaved.setCountry(Country.AUS);
		playerEntitySaved.setRank(1);
		playerEntitySaved.setRating(2);
		
		Mockito.when(playerRepository.findOne(Mockito.any())).thenReturn(Optional.empty());
		
		Mockito.when(playerRepository.save(playerEntity)).thenReturn(playerEntitySaved);
		Player response = playerService.addPlayer(player);
		assertNotNull(response);
	}
	
	@Test
	public void updatePlayerTest() throws BadRequestException {
		
		UpdatePlayer player = new UpdatePlayer();
		player.setId("8b920eff");
		player.setCountry(Optional.of(Country.AUS.name()));
		player.setFirstName(Optional.of("A"));
		player.setLastName(Optional.of("B"));
		player.setMatchType(Optional.of("T20"));
		
		PlayerEntity playerEntity = new PlayerEntity();
		playerEntity.setFirstName("A");
		playerEntity.setLastName("B");
		playerEntity.setMatchType(MatchType.T20);
		playerEntity.setCountry(Country.AUS);
		
		PlayerEntity playerEntitySaved = new PlayerEntity();
		playerEntitySaved.setId("8b920eff");
		playerEntitySaved.setFirstName("A");
		playerEntitySaved.setLastName("B");
		playerEntitySaved.setMatchType(MatchType.T20);
		playerEntitySaved.setCountry(Country.AUS);
		playerEntitySaved.setRank(1);
		playerEntitySaved.setRating(2);
		
		Mockito.when(playerRepository.findById(Mockito.any())).thenReturn(Optional.of(playerEntity));
		
		Mockito.when(playerRepository.save(playerEntity)).thenReturn(playerEntitySaved);
		Player response = playerService.updatePlayer(player);
		assertNotNull(response);
	}
	
	@Test
	public void deletePlayerTest() throws BadRequestException {
		
		PlayerEntity playerEntity = new PlayerEntity();
		playerEntity.setId("8b920eff");
		playerEntity.setFirstName("A");
		playerEntity.setLastName("B");
		playerEntity.setMatchType(MatchType.T20);
		playerEntity.setCountry(Country.AUS);
		
		PlayerEntity playerEntitySaved = new PlayerEntity();
		playerEntitySaved.setId("8b920eff");
		playerEntitySaved.setFirstName("A");
		playerEntitySaved.setLastName("B");
		playerEntitySaved.setMatchType(MatchType.T20);
		playerEntitySaved.setCountry(Country.AUS);
		playerEntitySaved.setRank(1);
		playerEntitySaved.setRating(2);
		
		Mockito.when(playerRepository.findById(Mockito.any())).thenReturn(Optional.of(playerEntity));
		Boolean response = playerService.deletePlayer("8b920eff");
		assertNotNull(response);
	}


	
	private Set<PlayerEntity> getPlayers() {
		PlayerEntity playerEntity;
		for (int i = 0; i < 5; i++) {
			playerEntity = new PlayerEntity();
			playerEntity.setFirstName("A" + i);
			playerEntity.setLastName("B" + i);
			playerEntity.setMatchType(MatchType.T20);
			playerEntity.setCountry(Country.AUS);
			playerEntity.setRank(i);
			playerEntity.setRating(i);
			
			set.add(playerEntity);
		}

		return set;
	}
}
