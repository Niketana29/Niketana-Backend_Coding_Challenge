package com.hexaware.demo.service;

import java.util.List;

import com.hexaware.demo.entity.Player;

public interface IPlayerService {
	
	public Player addPlayer(Player player);
	public Player updatePlayer(Player player);
	
	public Player getPlayerById(int playerId);
	public String deletePlayer(int playerId);
	
	public List<Player> getAllPlayers();

}
