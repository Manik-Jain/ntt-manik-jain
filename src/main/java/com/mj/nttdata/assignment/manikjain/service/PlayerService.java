package com.mj.nttdata.assignment.manikjain.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mj.nttdata.assignment.manikjain.adapter.PlayerAdapter;
import com.mj.nttdata.assignment.manikjain.common.ErrorMessages;
import com.mj.nttdata.assignment.manikjain.entity.PlayerEntity;
import com.mj.nttdata.assignment.manikjain.enums.Country;
import com.mj.nttdata.assignment.manikjain.enums.MatchType;
import com.mj.nttdata.assignment.manikjain.exception.InvalidPlayerOperation;
import com.mj.nttdata.assignment.manikjain.model.CreatePlayer;
import com.mj.nttdata.assignment.manikjain.model.Player;
import com.mj.nttdata.assignment.manikjain.model.UpdatePlayer;
import com.mj.nttdata.assignment.manikjain.repository.PlayerRepository;
import com.mj.nttdata.assignment.manikjain.util.ValidationUtil;

@Service
public class PlayerService {

	private final Logger logger = LoggerFactory.getLogger(PlayerService.class);

	private final PlayerRepository playerRepository;

	PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Value("${players.response.limit:10}")
	private int responseLimit;

	public Set<Player> getPlayers(Optional<String> matchType) throws BadRequestException {

		if (matchType.isPresent()) {
			String type = matchType.get();
			if (!ValidationUtil.allowedMatchTypes().contains(type)) {
				logger.atError().log(ErrorMessages.INVALID_MATCH_TYPE.getMessage());
				throw new BadRequestException(ErrorMessages.INVALID_MATCH_TYPE.getMessage());
			}

			// get sorted records by match type

			// Limit limit = Limit.of(responseLimit);
			// TypedSort<PlayerEntity> typedSort = Sort.sort(PlayerEntity.class);

			Sort sortCriteria = Sort.by(Direction.ASC, "rank");
			// Sort sortCriteria = typedSort.by(PlayerEntity::getRank).ascending();
			Set<Player> players = this.playerRepository
					.findPlayersByMatchTypeOrderByRankAsc(MatchType.valueOf(type), sortCriteria).stream()
					.map(playerEntity -> PlayerAdapter.adapt.apply(playerEntity)).limit(responseLimit)
					.collect(Collectors.toSet());
			return players;
		}

		Set<Player> players = this.playerRepository.findAll().stream()
				.map(playerEntity -> PlayerAdapter.adapt.apply(playerEntity)).limit(responseLimit)
				.collect(Collectors.toSet());
		return players;
	}

	public Player addPlayer(CreatePlayer player) {

		if (Country.valueOf(player.getCountry()) == null) {
			String errorMessage = String.format(ErrorMessages.DUPLICATE_PLAYER.getMessage() + ": {}",
					player.getCountry());
			logger.atError().log(errorMessage);
			throw new InvalidPlayerOperation(errorMessage);
		}

		// find if there is an existing player record by - first name, last name,
		// country and match type
		PlayerEntity playerEntity = PlayerAdapter.adaptToPlayerEntity.apply(player);
		Example<PlayerEntity> example = Example.of(playerEntity);
		Optional<PlayerEntity> exisingPlayer = this.playerRepository.findOne(example);
		if (exisingPlayer.isPresent()) {
			logger.atError().log(ErrorMessages.DUPLICATE_PLAYER.getMessage());
			throw new InvalidPlayerOperation(ErrorMessages.DUPLICATE_PLAYER.getMessage());
		}

		PlayerEntity savedEntity = this.playerRepository.save(playerEntity);

		return PlayerAdapter.adapt.apply(savedEntity);
	}

	public Player updatePlayer(UpdatePlayer updatePlayer) {

		Optional<PlayerEntity> exisingPlayer = this.playerRepository.findById(updatePlayer.getId());
		if (exisingPlayer.isPresent()) {

			PlayerEntity playerEntity = exisingPlayer.get();

			if (!updatePlayer.getFirstName().isEmpty()) {
				playerEntity.setFirstName(updatePlayer.getFirstName().get());
			}

			if (!updatePlayer.getLastName().isEmpty()) {
				playerEntity.setLastName(updatePlayer.getLastName().get());
			}

			if (!updatePlayer.getCountry().isEmpty()) {
				playerEntity.setCountry(Country.valueOf(updatePlayer.getCountry().get()));
			}

			if (!updatePlayer.getRank().isEmpty()) {
				playerEntity.setRank(updatePlayer.getRank().get());
			}

			if (!updatePlayer.getRating().isEmpty()) {
				playerEntity.setRating(updatePlayer.getRating().get());
			}

			this.playerRepository.save(playerEntity);
			return PlayerAdapter.adapt.apply(playerEntity);

		}

		String errorMessage = String.format(ErrorMessages.INVALID_PLAYER_ID.getMessage() + ": {}",
				updatePlayer.getId());
		logger.atError().log(errorMessage);
		throw new InvalidPlayerOperation(errorMessage);
	}

	public Boolean deletePlayer(String id) {

		Optional<PlayerEntity> exisingPlayer = this.playerRepository.findById(id);
		if (exisingPlayer.isPresent()) {

			PlayerEntity playerEntity = exisingPlayer.get();

			this.playerRepository.delete(playerEntity);
			return true;
		}

		String errorMessage = String.format(ErrorMessages.INVALID_PLAYER_ID.getMessage() + ": {}",id);
		logger.atError().log(errorMessage);
		throw new InvalidPlayerOperation(errorMessage);
	}

}
