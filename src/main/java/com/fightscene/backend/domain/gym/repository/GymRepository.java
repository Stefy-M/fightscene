package com.fightscene.backend.domain.gym.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fightscene.backend.domain.gym.Gym;

public interface GymRepository extends JpaRepository<Gym, UUID>{
	
	Optional<Gym> findByName(String name);
	
	List<Gym> findByLocationContainingIgnoreCase(String location);

}
