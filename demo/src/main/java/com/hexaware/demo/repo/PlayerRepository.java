package com.hexaware.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.demo.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
    Optional<Player> findByJerseyNumber(int jerseyNumber);

}
