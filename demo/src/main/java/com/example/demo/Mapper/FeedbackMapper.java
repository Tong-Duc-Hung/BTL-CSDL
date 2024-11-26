package com.example.demo.Mapper;

import com.example.demo.DTO.FeedbackDTO.*;
import com.example.demo.Model.Entity.FeedbackEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    // Chuyển đổi từ CreateCustomerFeedbackDTO sang FeedbackEntity
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(target = "feedbackId", ignore = true)
    @Mapping(target = "staffResponse", ignore = true)
    FeedbackEntity toFeedbackEntity(CreateCustomerFeedbackDTO createCustomerFeedbackDTO);

    // Chuyển đổi từ FeedbackEntity sang CustomerFeedbackDetailsDTO
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "feedbackText", target = "feedbackText")
    @Mapping(source = "feedbackId", target = "feedbackId")
    CustomerFeedbackDetailsDTO toCustomerFeedbackDetailsDTO(FeedbackEntity feedbackEntity);

    // Chuyển đổi từ CreateStaffResponseDTO sang FeedbackEntity (cập nhật phản hồi của nhân viên)
    @Mapping(source = "feedbackId", target = "feedbackId")
    @Mapping(source = "staffResponse", target = "staffResponse")
    @Mapping(target = "feedbackText", ignore = true)
    @Mapping(target = "feedbackStatus", ignore = true)
    @Mapping(target = "feedbackType", ignore = true)
    @Mapping(target = "user.userId", ignore = true)
    FeedbackEntity toFeedbackEntityFromStaffResponse(CreateStaffResponseDTO createStaffResponseDTO);

    // Chuyển đổi từ FeedbackEntity sang StaffResponseDetailsDTO
    @Mapping(source = "feedbackId", target = "feedbackId")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "feedbackText", target = "feedbackText")
    @Mapping(source = "staffResponse", target = "staffResponse")
    StaffResponseDetailsDTO toStaffResponseDetailsDTO(FeedbackEntity feedbackEntity);

    // Chuyển đổi danh sách FeedbackEntity sang danh sách CustomerFeedbackDetailsDTO
    List<CustomerFeedbackDetailsDTO> toCustomerFeedbackDetailsDTOList(List<FeedbackEntity> feedbackEntities);

    // Chuyển đổi danh sách FeedbackEntity sang danh sách StaffResponseDetailsDTO
    List<StaffResponseDetailsDTO> toStaffResponseDetailsDTOList(List<FeedbackEntity> feedbackEntities);
}