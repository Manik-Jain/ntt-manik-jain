package com.mj.nttdata.assignment.manikjain.controller;

import java.util.Optional;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mj.nttdata.assignment.manikjain.model.CreatePlayer;
import com.mj.nttdata.assignment.manikjain.model.Player;
import com.mj.nttdata.assignment.manikjain.model.UpdatePlayer;
import com.mj.nttdata.assignment.manikjain.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {
	
	private final PlayerService playerService;
	
	PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PostMapping
	public Player createPlayer(@Valid @RequestBody CreatePlayer player) {
		return this.playerService.addPlayer(player);
	}
	
	@GetMapping
	public Set<Player> getPlayers(@RequestParam(value = "matchType") @Nullable String matchType) throws BadRequestException {
		return this.playerService.getPlayers(Optional.ofNullable(matchType));
	}
	
	//@PreAuthorize()
	@PutMapping
	public Player updatePlayer(@Valid @RequestBody UpdatePlayer player) {
		return this.playerService.updatePlayer(player);
	}

	//@PreAuthorize()
	@DeleteMapping("/{id}")
	public Boolean deletePlayer(@PathVariable(value="id") String playerId) {
		return this.playerService.deletePlayer(playerId);
	}

}
