package com.fightscene.backend.domain.fighter.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fightscene.backend.domain.fighter.Fighter;

public interface FighterRepository extends JpaRepository<Fighter, UUID> {

	Optional<Fighter> findByUser_UserId(UUID userId);

	List<Fighter> findByGym_GymId(UUID gymId);

	@Query("""
			    SELECT f
			    FROM Fighter f
			    WHERE LOWER(f.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
			       OR LOWER(f.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
			       OR LOWER(f.nickname) LIKE LOWER(CONCAT('%', :name, '%'))
			""")
	List<Fighter> searchByName(String name);

}
