package com.example.demo.Mapper;

import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.Model.Entity.AuditoriumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {

    // Ánh xạ từ AuditoriumEntity sang AuditoriumDTO
    @Mapping(source = "theater.theaterId", target = "theaterId")  // Ánh xạ theaterId từ TheaterEntity
    AuditoriumDTO toAuditoriumDTO(AuditoriumEntity auditorium);

    // Ánh xạ từ AuditoriumDTO sang AuditoriumEntity
    @Mapping(source = "theaterId", target = "theater.theaterId")  // Ánh xạ theaterId từ DTO sang TheaterEntity
    @Mapping(target = "showtimes", ignore = true)  // Không ánh xạ showtimes
    @Mapping(target = "seats", ignore = true)  // Không ánh xạ seats
    AuditoriumEntity toAuditoriumEntity(AuditoriumDTO auditorium);

    // Ánh xạ danh sách từ AuditoriumEntity sang AuditoriumDTO
    List<AuditoriumDTO> toDTOList(List<AuditoriumEntity> auditoriums);
}