package com.fightscene.backend.domain.video.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fightscene.backend.domain.fighter.Fighter;
import com.fightscene.backend.domain.fighter.repository.FighterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StorageLimitService {

    private final FighterRepository fighterRepository;

    public void addStorage(UUID fighterId, long size) {
        Fighter fighter = fighterRepository.findById(fighterId)
                .orElseThrow();
        fighter.setStorageUsedBytes(fighter.getStorageUsedBytes() + size);
    }

    public void deductStorage(UUID fighterId, long size) {
        Fighter fighter = fighterRepository.findById(fighterId)
                .orElseThrow();
        fighter.setStorageUsedBytes(
                Math.max(0, fighter.getStorageUsedBytes() - size)
        );
    }
}
