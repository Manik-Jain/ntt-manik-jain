package com.mj.nttdata.assignment.manikjain.repository;

import java.util.Set;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mj.nttdata.assignment.manikjain.entity.PlayerEntity;
import com.mj.nttdata.assignment.manikjain.enums.MatchType;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {
	
	Set<PlayerEntity> findPlayersByMatchTypeOrderByRankAsc(MatchType matchType, Sort sort);
	
	PlayerEntity findByFirstNameAndLastNameAndCountry(String fistName, String lastName, String country);

}
