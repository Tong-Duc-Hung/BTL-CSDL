package com.example.demo.Mapper;

import com.example.demo.DTO.ShowtimeDTO.ShowtimeDTO;
import com.example.demo.Model.Entity.ShowtimeEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    @Mapping(source = "auditorium.auditoriumId", target = "auditoriumId")
    @Mapping(source = "theater.theaterId", target = "theaterId")
    @Mapping(source = "movie.movieId", target = "movieId")
    ShowtimeDTO toShowtimeDTO(ShowtimeEntity showtime);

    @Mapping(target = "showtimeId", ignore = true)
    @Mapping(source = "movieId", target = "movie.movieId")
    @Mapping(source = "theaterId", target = "theater.theaterId")
    @Mapping(source = "auditoriumId", target = "auditorium.auditoriumId")
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "ticketPrices", ignore = true)
    ShowtimeEntity toShowtimeEntity(ShowtimeDTO showtime);

    List<ShowtimeDTO> toDTOList(List<ShowtimeEntity> showtimes);
}
