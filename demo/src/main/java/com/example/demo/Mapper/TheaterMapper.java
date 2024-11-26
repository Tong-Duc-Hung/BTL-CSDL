package com.example.demo.Mapper;

import com.example.demo.DTO.TheaterDTO.TheaterDTO;
import com.example.demo.Model.Entity.TheaterEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TheaterMapper {

    TheaterDTO toTheaterDTO(TheaterEntity theater);

    @Mapping(target = "auditoriums", ignore = true)
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    TheaterEntity toTheaterEntity(TheaterDTO theater);
    
    List<TheaterDTO> toDTOList(List<TheaterEntity> theaters);
}