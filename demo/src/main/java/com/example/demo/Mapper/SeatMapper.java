package com.example.demo.Mapper;

import com.example.demo.DTO.SeatDTO.SeatDTO;
import com.example.demo.Model.Entity.SeatEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    // Ánh xạ từ SeatEntity sang SeatDTO
    @Mapping(source = "theater.theaterId", target = "theaterId")
    @Mapping(source = "auditorium.auditoriumId", target = "auditoriumId")
    SeatDTO toSeatDTO(SeatEntity seat);
    
    // Ánh xạ từ SeatDTO sang SeatEntity
    @Mapping(source = "theaterId", target = "theater.theaterId")
    @Mapping(source = "auditoriumId", target = "auditorium.auditoriumId")
    SeatEntity toSeatEntity(SeatDTO seat);

    // Lấy danh sách ghế ngồi
    List<SeatDTO> toDTOList(List<SeatEntity> seats);
}