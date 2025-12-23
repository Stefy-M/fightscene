package com.fightscene.backend.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fightscene.backend.domain.fighter.Fighter;
import com.fightscene.backend.domain.fighter.service.FighterService;
import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.dto.fighter.CreateFighterDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("fighters")
@RequiredArgsConstructor
public class FighterController {
	
	private final FighterService fighterService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createFighter")
	public ResponseEntity<Fighter> createFighter(@Valid @RequestBody CreateFighterDto dto, @AuthenticationPrincipal UserDetails userDetails){
	
		
		String email = userDetails.getUsername();
		User user = fighterService.getUserByEmail(email);
		
		Fighter fighter = fighterService.createFighterProfile(user.getUserId(), dto);
		
		return ResponseEntity.ok(fighter);
		
	}
}
