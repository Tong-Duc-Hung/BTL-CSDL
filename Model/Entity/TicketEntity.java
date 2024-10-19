package com.example.movieticketbooking.Model.Entity;

import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng ticketprices.
*/
@Entity
@Table(name = "ticketprices")
public class TicketEntity {

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="price_id", nullable=false, unique=true)
    private int price_id;

    /**
     * Liên kết với bảng showtimes thông qua showtime_id.
    */
    @ManyToOne
    @JoinColumn(name="showtime_id", nullable=false)
    private ShowtimeEntity showtime;

    /**
     * Giá vé.
    */
    @Column(name="ticket_price", nullable=false, precision = 10, scale = 2)
    private double ticket_price;

    /**
     * Constructor mặc định.
    */
    public TicketEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param showtime Đối tượng showtime liên kết.
     * @param ticket_price Giá vé.
    */
    public TicketEntity(ShowtimeEntity showtime, double ticket_price) {
        this.showtime = showtime;
        this.ticket_price = ticket_price;
    }

    // Các hàm getter và setter.
    
    public int getPrice_id() {
        return price_id;
    }
    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }

    public ShowtimeEntity getShowtime() {
        return showtime;
    }
    public void setShowtime_id(ShowtimeEntity showtime) {
        this.showtime = showtime;
    }

    public double getTicket_price() {
        return ticket_price;
    }
    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }
}