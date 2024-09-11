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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {
	
	private final PlayerService playerService;
	
	PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@Operation(
			summary = "Create a new Player", 
			description = "Add a new player to ICC database with default rank and rating (which can be updated later based on player performance)",
			operationId = "add new player"
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Record created succesffuly"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
	        @ApiResponse(responseCode = "500", description = "Internal Server Error"),
	})
	@PostMapping
	public Player createPlayer(@Valid @RequestBody CreatePlayer player) {
		return this.playerService.addPlayer(player);
	}
	
	@Operation(
			summary = "Get players information", 
			description = "Return top ranked players sorted by rank ASC or an empty list if none exists. "
					+ "We have a configurable property mentioned in application properties file to limit the records (by default fetches top 100 results)",
			operationId = "get players details"
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful request"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
	        @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping
	public Set<Player> getPlayers(@RequestParam(value = "matchType") @Nullable String matchType) throws BadRequestException {
		return this.playerService.getPlayers(Optional.ofNullable(matchType));
	}
	
	@Operation(
			summary = "Update player information", 
			description = "update player records based on how they perform and the rating accumulated. "
					+ "Due to time crunch, this version allows public updates which shall be rectified in v2",
			operationId = "update players details"
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful request"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
	        @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	//@PreAuthorize()
	@PutMapping
	public Player updatePlayer(@Valid @RequestBody UpdatePlayer player) {
		return this.playerService.updatePlayer(player);
	}

	@Operation(
			summary = "Delete player information", 
			description = "Delete player records if the record exists, else throw InvalidPlayerOperation exception",
			operationId = "Delete players details"
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful request"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
	        @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	//@PreAuthorize()
	@DeleteMapping("/{id}")
	public Boolean deletePlayer(@PathVariable(value="id") String playerId) {
		return this.playerService.deletePlayer(playerId);
	}

}
