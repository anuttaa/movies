package com.example.movies.mappers;

import com.example.movies.dtos.AwardDto;
import com.example.movies.entities.Award;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AwardMapper {

    AwardDto toDto(Award award);

    Award toAward(AwardDto awardDto);
}
