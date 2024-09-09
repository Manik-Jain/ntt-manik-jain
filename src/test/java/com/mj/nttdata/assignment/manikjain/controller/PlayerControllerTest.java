package com.mj.nttdata.assignment.manikjain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.mj.nttdata.assignment.manikjain.adapter.PlayerAdapter;
import com.mj.nttdata.assignment.manikjain.enums.Country;
import com.mj.nttdata.assignment.manikjain.enums.MatchType;
import com.mj.nttdata.assignment.manikjain.model.CreatePlayer;
import com.mj.nttdata.assignment.manikjain.model.Player;
import com.mj.nttdata.assignment.manikjain.model.UpdatePlayer;
import com.mj.nttdata.assignment.manikjain.service.PlayerService;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	PlayerService playerService;
	
	Set<Player> set = new HashSet<>();

	@Test
	void shouldFindAllPlayers() throws Exception {
		Set<Player> set = this.getPlayers();
		Mockito.when(playerService.getPlayers(Optional.ofNullable(MatchType.T20.name()))).thenReturn(set);
		MvcResult result = mockMvc.perform(get("/v1/players?matchType=T20")).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	void shouldAddNewPlayer() throws Exception {
		CreatePlayer player = new CreatePlayer();
		player.setCountry(Country.AUS.getCountry());
		Mockito.when(this.playerService.addPlayer(player)).thenReturn(PlayerAdapter.playerBuilderSupplier.get().build());
		MvcResult result = mockMvc.perform(post("/v1/players")).andReturn();
		assertNotNull(result);
	}
	
	@Test
	void shouldUpdatePlayer() throws Exception {
		UpdatePlayer player = new UpdatePlayer();
		
		Mockito.when(this.playerService.updatePlayer(player)).thenReturn(PlayerAdapter.playerBuilderSupplier.get().build());
		MvcResult result = mockMvc.perform(put("/v1/players")).andReturn();
		assertNotNull(result);
	}
	
	@Test
	void shouldDeletePlayer() throws Exception {
		Mockito.when(this.playerService.deletePlayer("8b920eff-68ae-4f16-a4c9-498edc80c4ba")).thenReturn(true);
		MvcResult result = mockMvc.perform(delete("/v1/players")).andReturn();
		assertNotNull(result);
	}

	private Set<Player> getPlayers() {
		Player.PlayerBuilder builder = PlayerAdapter.playerBuilderSupplier.get();
		for (int i = 0; i < 5; i++) {
			set.add(builder.firstName("A" + i).lastName("B" + i).country(Country.AUS.getCountry()).rank(i)
					.matchType(MatchType.T20.name()).build());
		}

		return set;
	}

}
