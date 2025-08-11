package com.hexaware.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.demo.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	

}
