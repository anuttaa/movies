package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.AwardDto;
import com.coco.jumboomovies.entities.Award;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AwardMapper {

    AwardDto toDto(Award award);

    Award toAward(AwardDto awardDto);
}
