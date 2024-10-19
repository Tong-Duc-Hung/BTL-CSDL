package com.example.movieticketbooking.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Lớp Entity đại diện cho bảng reviews.
*/
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false, unique = true)
    private int review_id;

    /**
     * Liên kết với bảng movies thông qua movie_id.
    */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    /**
     * Liên kết với bảng users thông qua user_id.
    */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Xếp hạng bằng sao (từ 1 đến 5).
    */
    @Min(1)
    @Max(5)
    @Column(name = "star_rating", nullable = false)
    private int star_rating;

    /**
     * Nội dung đánh giá.
    */
    @Column(name = "review_text")
    private String review_text;

    /**
     * Constructor mặc định.
    */
    public ReviewEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param movie Đối tượng movie liên kết.
     * @param user Đối tượng user liên kết.
     * @param star_rating Xếp hạng bằng sao.
     * @param review_text Nội dung đánh giá.
    */
    public ReviewEntity(MovieEntity movie, UserEntity user, int star_rating, String review_text) {
        this.movie = movie;
        this.user = user;
        this.star_rating = star_rating;
        this.review_text = review_text;
    }

    // Các hàm getter và setter.

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getStar_rating() {
        return star_rating;
    }

    public void setStar_rating(int star_rating) {
        this.star_rating = star_rating;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }
}