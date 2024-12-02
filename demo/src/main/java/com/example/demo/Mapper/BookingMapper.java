package com.example.demo.Mapper;

import com.example.demo.Model.Entity.BookingEntity;
import com.example.demo.DTO.BookingDTO.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "showtimeId", target = "showtime.showtimeId")
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "bookingId", ignore = true)
    BookingEntity toBookingEntity(BookingDTO booking);

    // tạo booking
    @Mapping(source = "showtimeId", target = "showtime.showtimeId")
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "bookingDate", target = "bookingDate")
    @Mapping(source = "bookingStatus", target = "bookingStatus")
    @Mapping(source = "seatsBooked", target = "seatsBooked")
    @Mapping(target = "bookingId", ignore = true)
    @Mapping(target = "payment", ignore = true)
    BookingEntity toCreateBookingEntity(CreateBookingDTO createBookingDTO);

    // hủy booking
    @Mapping(target = "showtime.showtimeId", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    @Mapping(target = "bookingStatus", ignore = true)
    @Mapping(target = "seatsBooked", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "originalSeatsBooked", ignore = true)
    BookingEntity toBookingEntity(CancelBookingDTO booking);

    // thông tin của booking
    @Mapping(source = "showtime.showtimeId", target = "showtimeId")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "bookingDate", target = "bookingDate")
    @Mapping(source = "bookingStatus", target = "bookingStatus")
    @Mapping(source = "seatsBooked", target = "seatsBooked")
    BookingDTO toBookingDTO(BookingEntity booking);
    List<BookingDTO> toBookingDTOList(List<BookingEntity> bookings);
}