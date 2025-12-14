package com.coco.jumboomovies.services;

import com.coco.jumboomovies.dtos.AwardDto;
import com.coco.jumboomovies.entities.Award;
import com.coco.jumboomovies.mappers.AwardMapper;
import com.coco.jumboomovies.mappers.RoleMapper;
import com.coco.jumboomovies.repositories.AwardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AwardService {

    private final AwardRepository awardRepository;
    private final AwardMapper awardMapper;
    private final RoleMapper roleMapper;

    public List<AwardDto> getAllAwards() {
        List<Award> awards = (List<Award>) awardRepository.findAll();
        return awards.stream().map(awardMapper::toDto).toList();
    }

    public Optional<AwardDto> getAwardById(int id) {
        return awardRepository.findById(id).map(awardMapper::toDto);
    }

    public AwardDto addAward(AwardDto awardDto) {
        Award award = awardMapper.toAward(awardDto);
        Award savedAward = awardRepository.save(award);
        return awardMapper.toDto(savedAward);
    }

    public List<AwardDto> getAllAwardsByType(String type) {
        return awardRepository.findAllByAwardTypeIgnoreCase(type)
                .stream()
                .map(awardMapper::toDto)
                .toList();
    }

}
