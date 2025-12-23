package com.fightscene.backend.domain.fighter.service;

import java.util.UUID;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fightscene.backend.domain.fighter.Fighter;
import com.fightscene.backend.domain.fighter.repository.FighterRepository;
import com.fightscene.backend.domain.gym.Gym;
import com.fightscene.backend.domain.gym.repository.GymRepository;
import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.user.repository.UserRepository;
import com.fightscene.backend.dto.fighter.CreateFighterDto;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FighterService {
	
	private final FighterRepository fighterRepository;
	private final GymRepository gymRepository;
	private final UserRepository userRepository;
	
	public Fighter createFighterProfile(UUID userId, CreateFighterDto dto) {
		
		fighterRepository.findByUser_UserId(userId)
			.ifPresent(f -> {
				throw new IllegalStateException("Fighter profile already exist");
			});
		
		Gym gym = null;
		if (dto.gymId() != null) {
			gym = gymRepository.findById(dto.gymId())
					.orElseThrow(() -> new IllegalStateException("Gym not found"));
		}
		
		  Fighter fighter = Fighter.builder()
	                .user(User.builder().userId(userId).build())
	                .firstName(dto.firstName())
	                .lastName(dto.lastName())
	                .nickname(dto.nickname())
	                .gender(dto.gender())
	                .weightclass(dto.weightClass())
	                .gym(gym)
	                .storageUsedBytes(0L)
	                .build();
		
		
		return fighterRepository.save(fighter);
	}
	
	@Transactional(readOnly = true)
	public Fighter getFighterByUserId(UUID userId) {
		return fighterRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new IllegalStateException("Fighter not found"));
	}
	
	@Transactional(readOnly = true)
	public Fighter getFighterById(UUID fighterId) {
		
		return fighterRepository.findById(fighterId)
				.orElseThrow(() -> new IllegalStateException("Fighter not found"));
				
	}
	
	@Transactional(readOnly = true)
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalStateException("User not found"));
	}
	
}
