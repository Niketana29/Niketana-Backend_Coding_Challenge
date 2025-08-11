package com.hexaware.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.demo.entity.Player;
import com.hexaware.demo.service.IPlayerService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/players")
public class PlayerController {
	
    @Autowired
    IPlayerService playerService;
    
    @PostMapping("/insert")
    public Player addPlayer(@RequestBody Player player) {
        log.info("POST /insert - Adding player: {}", player);
        return playerService.addPlayer(player);
    }
    
    @PutMapping("/update")
    public Player updatePlayer(@RequestBody Player player) {
        log.info("PUT /update - Updating player: {}", player);
        return playerService.updatePlayer(player);
    }
    
    @GetMapping("/getbyid/{playerId}")
    public Player getPlayerById(@PathVariable int playerId) {
        log.info("GET /getbyid/{} - Fetching player by ID", playerId);
        return playerService.getPlayerById(playerId);
    }
    
    @DeleteMapping("/deletebyid/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {
        log.warn("DELETE /deletebyid/{} - Deleting player by ID", playerId);
        playerService.deletePlayer(playerId);
        return "Player with ID " + playerId + " deleted successfully.";
    }
    
    @GetMapping("/getall")
    public List<Player> getAllPlayers() {
        log.info("GET /getall - Fetching all players");
        return playerService.getAllPlayers();
    } 	

    
    

}
