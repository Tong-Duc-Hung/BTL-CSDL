package com.example.demo.Mapper;

import com.example.demo.DTO.ReviewDTO.*;
import com.example.demo.Model.Entity.ReviewEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "movieId", target = "movie.movieId")
    ReviewEntity toReviewEntity(ReviewDTO review);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "movie.movieId", target = "movieId")
    ReviewDTO toReviewDTO(ReviewEntity review);
    
    // Chuyển đổi danh sách ReviewEntity sang danh sách ReviewDTO
    List<ReviewDTO> toDTOList(List<ReviewEntity> reviews);
}