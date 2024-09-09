package com.mj.nttdata.assignment.manikjain.adapter;

import java.util.function.Function;
import java.util.function.Supplier;

import com.mj.nttdata.assignment.manikjain.entity.PlayerEntity;
import com.mj.nttdata.assignment.manikjain.enums.Country;
import com.mj.nttdata.assignment.manikjain.enums.MatchType;
import com.mj.nttdata.assignment.manikjain.model.CreatePlayer;
import com.mj.nttdata.assignment.manikjain.model.Player;

public class PlayerAdapter {
	
	public static Function<PlayerEntity, Player> adapt = (playerEntity) -> {
	
		Player.PlayerBuilder playerBuilder = PlayerAdapter.playerBuilderSupplier.get();
		return playerBuilder
					.id(playerEntity.getId())
					.rank(playerEntity.getRank())
					.country(playerEntity.getCountry().getCountry())
					.rating(playerEntity.getRank())
					.firstName(playerEntity.getFirstName())
					.lastName(playerEntity.getLastName())
					.matchType(playerEntity.getMatchType().name())
					.build();
	};
	
	public static Function<CreatePlayer, PlayerEntity> adaptToPlayerEntity = (player) -> {
		
		PlayerEntity playerEntity = PlayerAdapter.playerEntity.get();
		playerEntity.setFirstName(player.getFirstName());
		playerEntity.setLastName(player.getLastName());
		playerEntity.setCountry(Country.valueOf(player.getCountry()));
		playerEntity.setMatchType(MatchType.valueOf(player.getMatchType()));
				
		return playerEntity;
	};
	
	public static Supplier<Player.PlayerBuilder> playerBuilderSupplier = () -> {
		return Player.builder();
	};
	
	public static Supplier<PlayerEntity> playerEntity = () -> {
		return new PlayerEntity();
	};
	
	

}
