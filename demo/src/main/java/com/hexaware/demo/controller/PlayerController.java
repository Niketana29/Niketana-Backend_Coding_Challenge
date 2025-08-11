package com.hexaware.demo.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.demo.dto.PlayerDto;
import com.hexaware.demo.entity.Player;
import com.hexaware.demo.service.IPlayerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @PostMapping("/insert")
    public Player addPlayer(@Valid @RequestBody PlayerDto playerDto) {
        log.info("POST /insert - Adding player: {}", playerDto);
        Player player = mapDtoToEntity(playerDto);
        return playerService.addPlayer(player);
    }

    @PutMapping("/update")
    public Player updatePlayer(@Valid @RequestBody PlayerDto playerDto) {
        log.info("PUT /update - Updating player: {}", playerDto);
        Player player = mapDtoToEntity(playerDto);
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
    
    @GetMapping("/getbyjersey/{jerseyNumber}")
    public Player getPlayerByJerseyNumber(@PathVariable int jerseyNumber) {
        log.info("GET /getbyjersey/{} - Fetching player by jersey number", jerseyNumber);
        return playerService.getPlayerByJerseyNumber(jerseyNumber);
    }

    private Player mapDtoToEntity(PlayerDto dto) {
        Player player = new Player();
        player.setPlayerId(dto.getPlayerId());
        player.setPlayerName(dto.getPlayerName());
        player.setJerseyNumber(dto.getJerseyNumber());
        player.setRole(dto.getRole());
        player.setTotalMatches(dto.getTotalMatches());
        player.setTeamName(dto.getTeamName());
        player.setState(dto.getState());
        player.setDescription(dto.getDescription());
        return player;
    }
}
