package com.example.demo.Mapper;

import com.example.demo.DTO.PaymentDTO.PaymentDTO;
import com.example.demo.Model.Entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    // Chuyển đổi từ PaymentEntity sang PaymentDTO
    @Mapping(source = "booking.bookingId", target = "bookingId")
    @Mapping(source = "bank.bankId", target = "bankId")
    PaymentDTO toPaymentDTO(PaymentEntity paymentEntity);

    // Chuyển đổi từ PaymentDTO sang PaymentEntity
    @Mapping(source = "bookingId", target = "booking.bookingId")
    @Mapping(source = "bankId", target = "bank.bankId")
    @Mapping(target = "paymentId", ignore = true)
    PaymentEntity toPaymentEntity(PaymentDTO paymentDTO);

    // Chuyển đổi danh sách PaymentEntity sang danh sách PaymentDTO
    List<PaymentDTO> toDTOList(List<PaymentEntity> paymentEntities);

    // Chuyển đổi danh sách PaymentDTO sang danh sách PaymentEntity
    List<PaymentEntity> toEntityList(List<PaymentDTO> paymentDTOs);
}