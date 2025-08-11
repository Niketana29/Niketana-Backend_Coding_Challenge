package com.hexaware.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hexaware.demo.entity.Player;
import com.hexaware.demo.exception.ResourceNotFoundException;
import com.hexaware.demo.repo.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlayerServiceImpl implements IPlayerService {

	
	@Autowired
	PlayerRepository playerRepo;

	@Override
	public Player addPlayer(Player player) {
		// TODO Auto-generated method stub
		log.info("Service - addPlayer() called");
		return playerRepo.save(player);
	}

	@Override
	public Player updatePlayer(Player player) {
		// TODO Auto-generated method stub
		log.info("Service - updatePlayer() called");
        if (!playerRepo.existsById(player.getPlayerId())) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Cannot update — Player not found with ID: " + player.getPlayerId());
        }
		return playerRepo.save(player);
	}

	@Override
	public Player getPlayerById(int playerId) {
		// TODO Auto-generated method stub
		log.debug("Service - getPlayerById() called");
		return playerRepo.findById(playerId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Player not found with ID: " + playerId));
	}

	@Override
	public String deletePlayer(int playerId) {
		// TODO Auto-generated method stub
		log.warn("Service - deletePlayer() called");
        if (!playerRepo.existsById(playerId)) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Cannot delete — Player not found with ID: " + playerId);
        }
		playerRepo.deleteById(playerId);
		return "Record Deleted Successfully";
	}

	@Override
	public List<Player> getAllPlayers() {
		// TODO Auto-generated method stub
        log.debug("Service - getAllPlayers() called");
        return playerRepo.findAll();
	}

}
