package com.hexaware.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDto {

    private int playerId;
	
	@NotNull(message = "Player name cannot be null")
	@NotEmpty(message = "Player name cannot be empty")
    @Size(min = 3, max = 50, message = "Player name must be between 3 and 50 characters")
	private String playerName;
	
    @NotNull(message = "Jersey number is required")
    @Min(value = 1, message = "Jersey number must be at least 1")
    @Max(value = 50, message = "Jersey number must be at most 50")
    private int jerseyNumber;
    
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "Batsman|Bowler|Keeper|All Rounder",
    message = "Role must be Batsman, Bowler, Keeper, or All Rounder")
    private String role;
    
    @NotNull
    @Min(0)
    private int totalMatches;
    
    @NotBlank(message = "Team name is required")
    private String teamName;
    
    
    @NotEmpty(message = "State name cannot be empty")
    private String state;
    
    @Size(max = 1200, message = "Description cannot exceed 1200 characters")
    private String description;
    
    
	
	

}
