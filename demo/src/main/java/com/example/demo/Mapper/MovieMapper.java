package com.example.demo.Mapper;

import com.example.demo.DTO.MovieDTO.MovieDTO;
import com.example.demo.Model.Entity.MovieEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    // Ánh xạ từ MovieEntity sang MovieInfoDTO
    MovieDTO toMovieInfoDTO(MovieEntity movie);

    // Ánh xạ từ CreateMovieDTO sang MovieEntity để tạo phim mới
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    MovieEntity toMovieEntity(MovieDTO movie);

    // Lấy danh sách phim
    List<MovieDTO> toDTOList(List<MovieEntity> movies);
}