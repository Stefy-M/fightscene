package com.fightscene.backend.domain.gym.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fightscene.backend.domain.gym.Gym;
import com.fightscene.backend.domain.gym.repository.GymRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GymService {

    private final GymRepository gymRepository;

    public Gym createGym(String name, String location) {
        gymRepository.findByName(name).ifPresent(existing -> {
            throw new IllegalStateException("Gym name already exists");
        });

        Gym gym = Gym.builder()
                .name(name)
                .location(location)
                .build();

        return gymRepository.save(gym);
    }

    @Transactional(readOnly = true)
    public Gym getGym(UUID gymId) {
        return gymRepository.findById(gymId)
                .orElseThrow(() -> new IllegalStateException("Gym not found"));
    }

    public Gym updateGym(UUID gymId, String name, String location) {
        Gym gym = getGym(gymId);

        if (name != null && !name.equalsIgnoreCase(gym.getName())) {
            gymRepository.findByName(name).ifPresent(existing -> {
                if (!existing.getGymId().equals(gymId)) {
                    throw new IllegalStateException("Gym name already exists");
                }
            });
            gym.setName(name);
        }

        if (location != null) {
            gym.setLocation(location);
        }

        return gym;
    }

    @Transactional(readOnly = true)
    public List<Gym> searchByLocation(String query) {
        return gymRepository.findByLocationContainingIgnoreCase(query);
    }
}
