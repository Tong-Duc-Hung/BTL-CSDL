package com.example.demo.Mapper;

import com.example.demo.DTO.TicketDTO.TicketDTO;
import com.example.demo.DTO.TicketDTO.CancelTicketDTO;
import com.example.demo.Model.Entity.TicketEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    // Tạo vé mới
    @Mapping(source = "seatId", target = "seat.seatId")
    @Mapping(target = "ticketId", ignore = true)
    @Mapping(source = "showtimeId", target = "showtime.showtimeId")
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "ticketStatus", ignore = true)
    TicketEntity toTicketEntity(TicketDTO ticket);

    // Thông tin vé
    @Mapping(source = "seat.seatId", target = "seatId")
    @Mapping(source = "showtime.showtimeId", target = "showtimeId")
    @Mapping(source = "booking.bookingId", target = "bookingId")
    TicketDTO toTicketDTO(TicketEntity ticket);

    // Chuyển đổi dữ liệu hủy vé
    @Mapping(source = "booking.bookingId", target = "bookingId")
    @Mapping(source = "ticketId", target = "ticketId")
    CancelTicketDTO toCancelTicketDTO(TicketEntity ticket);

    // Lấy danh sách vé
    List<TicketDTO> toTicketDTOList(List<TicketEntity> tickets);
}