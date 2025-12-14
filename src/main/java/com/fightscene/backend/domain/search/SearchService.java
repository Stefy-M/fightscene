package com.fightscene.backend.domain.search;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fightscene.backend.domain.fighter.Fighter;
import com.fightscene.backend.domain.fighter.repository.FighterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final FighterRepository fighterRepository;

    public List<Fighter> searchFighters(String name) {
        return fighterRepository.searchByName(name);
    }

    public List<Fighter> filterByGym(UUID gymId) {
        return fighterRepository.findByGym_GymId(gymId);
    }
}