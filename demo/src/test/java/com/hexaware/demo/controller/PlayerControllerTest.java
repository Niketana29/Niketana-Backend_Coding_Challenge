package com.hexaware.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.hexaware.demo.entity.Player;


@SpringBootTest
class PlayerControllerTest {
	
	@Autowired
	RestTemplate restTemplate;
	
	String baseUrl = "http://localhost:8090/api/players";
	
    @Test
    public void testGetPlayerById() {
        int id = 1; 
        ResponseEntity<Player> response = restTemplate.getForEntity(baseUrl + "/getbyid/" + id, Player.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getPlayerId());
    }
    
    @Test
    public void testDeletePlayer() {
        int id = 3;
        restTemplate.delete(baseUrl + "/deletebyid/" + id);
        ResponseEntity<Player> response = restTemplate.getForEntity(baseUrl + "/getbyid/" + id, Player.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    




}
